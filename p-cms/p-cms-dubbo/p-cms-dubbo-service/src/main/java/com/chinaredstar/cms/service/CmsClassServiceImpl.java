package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsClassService;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassQueryVo;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassTagsVo;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassVo;
import com.chinaredstar.cms.mapper.CmsClassMapper;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2016/8/29.
 */
@Service("cmsClassService")
@Timed
public class CmsClassServiceImpl implements CmsClassService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsClassServiceImpl.class);

    @Autowired
    private CmsClassMapper cmsClassMapper;
    @Override
    public ServiceResult<List<CmsClassVo>> getCmsClassListByQuery(CmsClassQueryVo queryVo) {
        ServiceResult<List<CmsClassVo>> serviceResult = new ServiceResult<>(true);
        try {
            List<CmsClassVo> cmsClassVoList = cmsClassMapper.getCmsClassListByQuery(queryVo);
            serviceResult.setData(cmsClassVoList);
            return serviceResult;
        }catch (Exception e){
            LOGGER.error("get cmsClassVo is failed. cause by : {}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<List<CmsClassTagsVo>> getCmsClassTagsByIdList(List<Integer> idList) {
        ServiceResult<List<CmsClassTagsVo>> serviceResult = new ServiceResult<>(true);
        try {
            List<CmsClassTagsVo> cmsClassTagsVoList = cmsClassMapper.getCmsClassTagsByIdList(idList);
            serviceResult.setData(cmsClassTagsVoList);
            return serviceResult;
        }catch (Exception e){
            LOGGER.error("获取分类标签失败. Cause by : {}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }
}
