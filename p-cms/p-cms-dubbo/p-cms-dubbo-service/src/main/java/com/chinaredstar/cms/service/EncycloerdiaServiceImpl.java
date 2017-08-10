package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.EncyclopediaService;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaQueryVo;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaVo;
import com.chinaredstar.cms.mapper.EncyclopediaMapper;
import com.codahale.metrics.annotation.Timed;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunny on 16-8-23.
 */
@Service("encyclopediaService")
@Timed
public class EncycloerdiaServiceImpl implements EncyclopediaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncycloerdiaServiceImpl.class);

    @Autowired
    private EncyclopediaMapper encyclopediaMapper;

    @Override
    public ServiceResult<List<EncyclopediaVo>> listByType(EncyclopediaQueryVo queryVo) {
        long start = System.currentTimeMillis();
        LOGGER.info("listByType通过类型获取百科请求参数：" + queryVo);
        ServiceResult<List<EncyclopediaVo>> serviceResult = new ServiceResult<>();

        if (queryVo == null) {
            LOGGER.error("queryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }

        try {
            List<EncyclopediaVo> encyclopediaVos = encyclopediaMapper.listByType(queryVo);
            serviceResult.setSuccess(true);
            serviceResult.setData(encyclopediaVos);
        } catch (Exception e) {
            LOGGER.error("通过类型获取百科, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("listByType通过类型获取百科返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<EncyclopediaVo> getDetailById(Integer id) {
        ServiceResult<EncyclopediaVo> serviceResult = new ServiceResult<>();
        if (id == null) {
            LOGGER.error("id must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id must not be null");
            return serviceResult;
        }

        try {
            EncyclopediaVo encyclopediaVo = encyclopediaMapper.getDetailById(id);
            serviceResult.setSuccess(true);
            serviceResult.setData(encyclopediaVo);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过id获取百科详情失败，{}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<List<EncyclopediaVo>> getEncyclopediaListByIds(List<Integer> idList) {
        ServiceResult<List<EncyclopediaVo>> serviceResult = new ServiceResult<>(true);
        if (idList == null || idList.isEmpty()) {
            LOGGER.error("idList must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("idList must not be null");
            return serviceResult;
        }

        try {
            List<EncyclopediaVo> encyclopediaVos = encyclopediaMapper.getEncyclopediaListByIds(idList);
            serviceResult.setData(encyclopediaVos);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过id列表获取百科列表失败，{}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<List<EncyclopediaVo>> getEncyclopediaListByTags(String tags) {
        ServiceResult<List<EncyclopediaVo>> serviceResult = new ServiceResult<>();
        if (StringUtils.isBlank(tags)) {
            LOGGER.error("tags must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("tags must not be null");
            return serviceResult;
        }

        try {
            List<EncyclopediaVo> encyclopediaVos = encyclopediaMapper.getEncyclopediaListByTags(tags);
            serviceResult.setSuccess(true);
            serviceResult.setData(encyclopediaVos);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过tags获取百科列表失败,{}",e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("通过tags获取百科列表失败");
            return serviceResult;
        }
    }
}
