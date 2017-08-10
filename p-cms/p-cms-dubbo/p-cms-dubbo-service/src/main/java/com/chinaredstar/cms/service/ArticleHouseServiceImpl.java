package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.ArticleHouse;
import com.chinaredstar.cms.api.service.ArticleHouseService;
import com.chinaredstar.cms.api.vo.article.ArticleHouseQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import com.chinaredstar.cms.mapper.ArticleHouseMapper;
import com.chinaredstar.cms.utils.MD5Utils;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sunny on 16-8-24.
 */
@Service("articleHouseService")
@Timed
public class ArticleHouseServiceImpl implements ArticleHouseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleHouseServiceImpl.class);

    @Value("${fc.key}")
    private String fcKey;

    @Value("${bigdata.url}")
    private String bigDataUrl;

    @Value("${fc.id}")
    private String fcId;
    @Autowired
    private ArticleHouseMapper articleHouseMapper;
    private List<ArticleHouseVo> articleHouseVos;

    @Override
    public ServiceResult<List<ArticleHouseVo>> getArticleHouseWithPage(Page page) {
        ServiceResult<List<ArticleHouseVo>> serviceResult = new ServiceResult<>(true);
        try {
            List<ArticleHouseVo> articleHouseVoList = articleHouseMapper.getArticleHouseListWithPage(page);
            serviceResult.setData(articleHouseVoList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取房产文章失败 , {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<List<ArticleHouseVo>> listByCategoryTag(ArticleHouseQueryVo queryVo) {
        long start = System.currentTimeMillis();
        LOGGER.info("listByTag请求参数：" + JsonUtil.toJson(queryVo, false));
        ServiceResult<List<ArticleHouseVo>> serviceResult = new ServiceResult<List<ArticleHouseVo>>();
        if (queryVo == null) {
            LOGGER.error("articleHouseQueryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("articleHouseQueryVo must not be null");
            return serviceResult;
        }
        try {
            List<ArticleHouseVo> articleHouseVos = articleHouseMapper.listByCategoryTag(queryVo);
            serviceResult.setData(articleHouseVos);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("通过分类标签获取房产文章, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("listByTag返回数据：" + JsonUtil.toJson(serviceResult,false) + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<ArticleHouseVo> getDetailById(Integer id) {
        ServiceResult<ArticleHouseVo> serviceResult = new ServiceResult<>(true);
        if (id == null) {
            LOGGER.error("id must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id must not be null");
            return serviceResult;
        }

        try {
            ArticleHouseVo articleHouseVo = articleHouseMapper.getDetailById(id);
            serviceResult.setData(articleHouseVo);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过id获取房产文章失败，{}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<List<ArticleHouseVo>> getArticleHouseListByIds(List<Integer> idList) {
        ServiceResult<List<ArticleHouseVo>> serviceResult = new ServiceResult<>(true);
        if (idList == null || idList.isEmpty()) {
            serviceResult.setSuccess(false);
            serviceResult.setMsg("idList must be not null");
            return serviceResult;
        }

        try {
            List<ArticleHouseVo> articleHouseList = articleHouseMapper.getArticleHouseListByIds(idList);
            serviceResult.setData(articleHouseList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过id获取房产文章失败，{}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<Set<String>> getAllTags(ArticleHouseQueryVo queryVo) {
        ServiceResult<Set<String>> serviceResult = new ServiceResult<>(true);

        if (queryVo == null) {
            LOGGER.error("queryVo must be not null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("请求参数错误");
            return serviceResult;
        }
        queryVo.setPageNo(1);
        queryVo.setPageSize(5);
        try {
            List<ArticleHouseVo> articleHouseVos = articleHouseMapper.listByCategoryTag(queryVo);
            Set<String> tagSet = new HashSet<>();
            for (ArticleHouseVo articleHouseVo : articleHouseVos) {
                if (articleHouseVo == null || StringUtils.isBlank(articleHouseVo.getTags())) {
                    continue;
                }

                String tags = articleHouseVo.getTags();
                tagSet.add(tags.split(",")[0]);
//                for(String tag : tags.split(",")){
//                    if(StringUtils.isBlank(tag)) continue;
//                    tagSet.add(tag);
//                }
            }
            serviceResult.setData(tagSet);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取房产标签失败，{}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<ArticleHouse> save(ArticleHouse articleHouse) {
        ServiceResult<ArticleHouse> serviceResult = new ServiceResult<>();
        if (articleHouse == null) {
            serviceResult.setSuccess(false);
            serviceResult.setMsg("参数不能为空");
            return serviceResult;
        }
        try {
            articleHouse.setCreateTime(new Date());
            articleHouse.setCheckStatus(0);
            articleHouseMapper.save(articleHouse);
            serviceResult.setSuccess(true);
            serviceResult.setData(articleHouse);
        } catch (Exception e) {
            LOGGER.error("文章保存失败:", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("服务内部错误");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult deleteById(Integer id, String token, long timeStamp) {
        ServiceResult serviceResult = new ServiceResult();
        if (id == null || id == 0) {
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id不能为空");
            return serviceResult;
        }
        String authToken = fcKey + fcId + timeStamp;
        String tokenMD5 = MD5Utils.MD5(authToken);
        if (!tokenMD5.equals(token)) {
            serviceResult.setSuccess(false);
            serviceResult.setMsg("身份验证未通过");
            return serviceResult;
        }
        try {
            ArticleHouseVo ArticleHouseVo = articleHouseMapper.getDetailById(id);
            if (ArticleHouseVo == null) {
                serviceResult.setSuccess(false);
                serviceResult.setMsg("数据不存在");
                return serviceResult;
            }
            Integer result = articleHouseMapper.deleteById(id);
            if (result == 1) {
                serviceResult.setSuccess(true);
            } else {
                serviceResult.setSuccess(false);
                serviceResult.setMsg("删除失败");
            }
        } catch (Exception e) {
            LOGGER.error("房产文章删除失败：", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("服务内部错误");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateById(ArticleHouse articleHouse) {
        ServiceResult serviceResult = new ServiceResult();
        if (articleHouse == null) {
            serviceResult.setSuccess(false);
            serviceResult.setMsg("参数不能为空");
            return serviceResult;
        }
        if (articleHouse.getId() == null || articleHouse.getId() == 0) {
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id不能为空");
            return serviceResult;
        }
        try {
            articleHouse.setUpdateTime(new Date());
            Integer result = articleHouseMapper.updateById(articleHouse);
            if (result == 1) {
                serviceResult.setSuccess(true);
            } else {
                serviceResult.setSuccess(false);
                serviceResult.setMsg("更新文章失败");
            }
        } catch (Exception e) {
            LOGGER.error("更新文章失败:", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("服务内部错误");
        }
        return serviceResult;
    }

}
