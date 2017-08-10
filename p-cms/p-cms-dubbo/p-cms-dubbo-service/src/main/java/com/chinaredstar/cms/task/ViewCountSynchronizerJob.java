package com.chinaredstar.cms.task;

import com.chinaredstar.cms.mapper.*;
import com.chinaredstar.cms.result.BigDataHistory;
import com.chinaredstar.cms.result.BigDataInfo;
import com.chinaredstar.cms.result.BigDataResult;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.chinaredstar.perseus.utils.PropertiesUtil;
import com.mmall.job.core.handler.IJobHandler;
import com.mmall.job.core.handler.annotation.JobHander;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yixin.sun on 2016/12/27.
 */
@JobHander(value = "viewCountSynchronizerJob")
@Service
public class ViewCountSynchronizerJob extends IJobHandler {
    private static final Integer THREAD_SIZE = 8;
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewCountSynchronizerJob.class);

    private String bigdataUrl = PropertiesUtil.getProperty("system.properties").get("bigdata.url");

    private static final int PER_COUNT = 100;

    @Autowired
    private ArticleHomeMapper articleHomeMapper;

    @Autowired
    private ArticleCustomMapper articleCustomMapper;

    @Autowired
    private ArticleHouseMapper articleHouseMapper;

    @Autowired
    private ArticleMarketMapper articleMarketMapper;

    @Autowired
    private AtlasMapper atlasMapper;

    @Autowired
    private CmsIndexLifeMapper indexLifeMapper;

    @Autowired
    private CmsIndexBrandMapper indexBrandMapper;

    @Override
    @Transactional
    public JobHandleStatus execute(String... strings) throws Exception {
        LOGGER.info("job start");
        long begin = System.currentTimeMillis();

        List<Callable<JobHandleStatus>> jobs = new ArrayList<>();

        SyncCallable articleHomeCallable = new SyncCallable(articleHomeMapper, "text_deco_lifestyle", bigdataUrl);

        SyncCallable articleCustomCallable = new SyncCallable(articleCustomMapper, "text_product", bigdataUrl);

        SyncCallable articleHouseCallable = new SyncCallable(articleHouseMapper, "text_house", bigdataUrl);

        SyncCallable articleMarketCallable = new SyncCallable(articleMarketMapper, "text_mall", bigdataUrl);

        SyncCallable indexBrandCallable = new SyncCallable(indexBrandMapper, "big_brand_here", bigdataUrl);

        SyncCallable indexLifeCallable = new SyncCallable(indexLifeMapper, "life_home", bigdataUrl);

        AtlasSyncCallable atlasHomeCallable = new AtlasSyncCallable(atlasMapper, "image_deco", bigdataUrl, 1);

        AtlasSyncCallable atlasCustomCallable = new AtlasSyncCallable(atlasMapper, "image_product", bigdataUrl, 2);
        jobs.add(articleHomeCallable);
        jobs.add(articleCustomCallable);
        jobs.add(articleHouseCallable);
        jobs.add(articleMarketCallable);
        jobs.add(indexBrandCallable);
        jobs.add(indexLifeCallable);
        jobs.add(atlasHomeCallable);
        jobs.add(atlasCustomCallable);


        List<Future<JobHandleStatus>> futures = executorService.invokeAll(jobs);
        for (Future<JobHandleStatus> future : futures) {
            if (future.get().equals(JobHandleStatus.FAIL)) {
                return JobHandleStatus.FAIL;
            }
        }
        long last = System.currentTimeMillis() - begin;
        LOGGER.info("同步访问量耗时：" + (last / 1000) + "秒" + (last % 1000) + "毫秒");
        LOGGER.info("job end");
        return JobHandleStatus.SUCCESS;
    }




    static class SyncCallable implements Callable<JobHandleStatus> {

        private BaseMapper mapper;
        private String operation;

        private String url;

        public SyncCallable(BaseMapper mapper, String operation, String url) {
            this.mapper = mapper;
            this.operation = operation;
            this.url = url;
        }

        @Override
        public JobHandleStatus call() throws Exception {
            try {
                List<Integer> ids = mapper.getAllIds();
                if (ids == null || ids.isEmpty()) {
                    return JobHandleStatus.SUCCESS;
                }
                RestTemplate restTemplate = new RestTemplate();
                List<String> requestIds = ViewCountSynchronizerJob.getRequestIds(ids);
                for(String requestId : requestIds) {
                    Map<Integer, Integer> failDataMap = new HashMap<>();
                    Map<String, String> paramMap = new HashMap<>();
                    paramMap.put("operation", operation);
                    paramMap.put("ids", requestId);
                    LOGGER.info(JsonUtil.toJson(paramMap, false));
                    BigDataResult bigDataResult = restTemplate.getForObject(url, BigDataResult.class, paramMap);
                    LOGGER.info(operation + ",大数据返回结果：" + JsonUtil.toJson(bigDataResult, false));
                    if (bigDataResult != null && bigDataResult.getCode() == 200) {
                        List<BigDataInfo> infos = bigDataResult.getInfo();
                        if (infos != null && infos.size() != 0) {
                            for (BigDataInfo info : infos) {
                                Integer id = Integer.valueOf(info.getId());
                                BigDataHistory history = info.getHistory();
                                if (StringUtils.isBlank(history.getDays_pv())) {
                                    continue;
                                }
                                Integer viewCount = Integer.valueOf(history.getDays_pv());
                                Integer result = mapper.updateViewCountById(id, viewCount);
                                if (result != 1) {
                                    failDataMap.put(id, viewCount);
                                }
                            }
                        }
                    }
                    if (failDataMap.size() != 0) {
                        for (Map.Entry<Integer, Integer> entry : failDataMap.entrySet()) {
                            Integer updateResult = mapper.updateViewCountById(entry.getKey(), entry.getValue());
                            if (updateResult != 1) {
                                LOGGER.error(operation + "类型文章id=" + entry.getKey() + "更新用户访问数viewCount=" + entry.getValue() + "失败");
                            }
                        }
                    }
                }
                return JobHandleStatus.SUCCESS;
            } catch (Exception e) {
                LOGGER.error("同步任务发生错误：{},", operation, e);
                throw e;
            }
        }
    }

    static class AtlasSyncCallable implements Callable<JobHandleStatus> {

        private BaseMapper mapper;

        private String operation;

        private String url;

        private Integer type;

        public AtlasSyncCallable(BaseMapper mapper, String operation, String url, Integer type) {
            this.mapper = mapper;
            this.operation = operation;
            this.url = url;
            this.type = type;
        }

        @Override
        public JobHandleStatus call() throws Exception {
            try {
                List<Integer> ids = mapper.getAllIdsByType(type);
                if (ids == null || ids.isEmpty()) {
                    return JobHandleStatus.SUCCESS;
                }

                RestTemplate restTemplate = new RestTemplate();
                List<String> requestIds = ViewCountSynchronizerJob.getRequestIds(ids);
                for(String requestId : requestIds) {
                    Map<Integer, Integer> failDataMap = new HashMap<>();
                    Map<String, String> paramMap = new HashMap<>();
                    paramMap.put("operation", operation);
                    paramMap.put("ids", requestId);
                    LOGGER.info(JsonUtil.toJson(paramMap, false));
                    BigDataResult bigDataResult = restTemplate.getForObject(url, BigDataResult.class, paramMap);
                    LOGGER.info(operation + ",大数据返回结果：" + JsonUtil.toJson(bigDataResult, false));
                    if (bigDataResult != null && bigDataResult.getCode() == 200) {
                        List<BigDataInfo> infos = bigDataResult.getInfo();
                        if (infos != null && infos.size() != 0) {
                            for (BigDataInfo info : infos) {
                                Integer id = Integer.valueOf(info.getId());
                                BigDataHistory history = info.getHistory();
                                if (StringUtils.isBlank(history.getDays_pv())) {
                                    continue;
                                }
                                Integer viewCount = Integer.valueOf(history.getDays_pv());
                                Integer result = mapper.updateViewCountById(id, viewCount);
                                if (result != 1) {
                                    failDataMap.put(id, viewCount);
                                }
                            }
                        }
                    }
                    if (failDataMap.size() != 0) {
                        for (Map.Entry<Integer, Integer> entry : failDataMap.entrySet()) {
                            Integer updateResult = mapper.updateViewCountById(entry.getKey(), entry.getValue());
                            if (updateResult != 1) {
                                LOGGER.error(operation + "类型图集id=" + entry.getKey() + "更新用户访问数viewCount=" + entry.getValue() + "失败");
                            }
                        }
                    }
                }
                return JobHandleStatus.SUCCESS;
            } catch (Exception e) {
                LOGGER.error("图集同步任务发布错误，{},", operation, e);
                throw e;
            }
        }
    }

    /**
     * 拆分请求id, 按间隔数量生成id字符串
     * @param ids
     * @return
     */
    public static List<String> getRequestIds(List<Integer> ids) {
        List<String> result = new ArrayList<>(PER_COUNT);
        if (ids.size() <= PER_COUNT) {
            result.add(StringUtils.join(ids, ","));
        } else {
            StringBuilder sb = new StringBuilder(400);
            for (int i = 1; i <= ids.size(); i++) {
                sb.append(ids.get(i - 1)).append(",");
                if (i % PER_COUNT == 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    result.add(sb.toString());
                    sb.setLength(0);
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        Map<String, String> property = PropertiesUtil.getProperty("system.properties");
//        System.out.println(property.get("bigdata.url"));
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<=6666;i++){
            list.add(i);
        }
        List<String> requestIds = getRequestIds(list);
        for(String s : requestIds){
            System.out.println(s);
        }
        System.out.println("time: "+(System.currentTimeMillis() - start));
    }
}
