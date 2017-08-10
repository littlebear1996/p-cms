package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.AtlasService;
import com.chinaredstar.cms.api.vo.atlas.AtlasDetailVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasQueryVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import com.chinaredstar.cms.mapper.AtlasDetailMapper;
import com.chinaredstar.cms.mapper.AtlasMapper;
import com.codahale.metrics.annotation.Timed;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by sunny on 16-8-17.
 */
@Service("atlasService")
@Timed
public class AtlasServiceImpl implements AtlasService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtlasServiceImpl.class);

    @Autowired
    private AtlasMapper atlasMapper;

    @Autowired
    private AtlasDetailMapper atlasDetailMapper;

    @Override
    public ServiceResult<List<AtlasVo>> listByType(AtlasQueryVo queryVo) {
        long start = System.currentTimeMillis();
        LOGGER.info("listByType请求参数：" + queryVo);
        ServiceResult<List<AtlasVo>> serviceResult = new ServiceResult<List<AtlasVo>>();
        if (queryVo == null) {
            LOGGER.error("queryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }
        try {
            List<AtlasVo> atlases = atlasMapper.listByType(queryVo);
            serviceResult.setData(atlases);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("通过类型获取图集, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("listByType返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<AtlasVo> getAtlasById(Integer id) {
        long start = System.currentTimeMillis();
        LOGGER.info("getAtlasById请求参数：" + id);
        ServiceResult<AtlasVo> serviceResult = new ServiceResult<AtlasVo>();
        if (id == null) {
            LOGGER.error("id must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id must not be null");
            return serviceResult;
        }
        try {
            AtlasVo atlasVo = atlasMapper.getDetailById(id);
            if (atlasVo != null && atlasVo.getAtlasDetails() != null) {
                atlasVo.setCount(atlasVo.getAtlasDetails().size());
            } else {
                if (atlasVo != null) {
                    atlasVo.setCount(0);
                }
            }

            serviceResult.setData(atlasVo);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("通过id列表获取图集, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("getAtlasById返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<List<AtlasVo>> getAtlasByIds(List<Integer> idList) {
        ServiceResult<List<AtlasVo>> serviceResult = new ServiceResult<>(true);
        if (idList == null || idList.isEmpty()) {
            LOGGER.error("idList must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id must not be null");
            return serviceResult;
        }
        try {
            List<AtlasVo> atlasVoList = atlasMapper.getAtlasByIds(idList);
            serviceResult.setData(atlasVoList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过id列表获取图集, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
            return serviceResult;
        }
    }


    @Transactional(value = "transactionManager", rollbackFor = Exception.class, readOnly = false)
    @Override
    public ServiceResult deleteAtlasByVo(AtlasVo vo) {
        ServiceResult serviceResult = new ServiceResult(true);
        if (null == vo) {
            LOGGER.error("params must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("params must not be null");
            return serviceResult;
        }
        if (vo.getObjType() == null && StringUtils.isBlank(vo.getObjUuid()) && StringUtils.isBlank(vo.getObjValue())) {
            LOGGER.error("params must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("params must not be null");
            return serviceResult;
        }
        atlasMapper.deleteAtlasByVo(vo);
        atlasDetailMapper.deleteByAtlasId(vo);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, readOnly = false)
    public ServiceResult insertAtlas(AtlasVo atlasVo) {
        ServiceResult serviceResult = new ServiceResult(true);
        if (atlasVo == null) {
            LOGGER.error("atlasVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("atlasVo must not be null");
            return serviceResult;
        }
        atlasVo.setCreateTime(new Date());
        deleteAtlas(atlasVo.getDesignerId(), atlasVo.getCaseId(), atlasVo.getOpenId());
        atlasMapper.insert(atlasVo);
        Integer id = atlasVo.getId();
        for (AtlasDetailVo atlasDetailVo : atlasVo.getAtlasDetails()) {
            atlasDetailVo.setAtlasId(Long.valueOf(id));
            atlasDetailMapper.insert(atlasDetailVo);
        }
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class, readOnly = false)
    public ServiceResult deleteAtlas(String designerId, String caseId, String openId) {
        ServiceResult serviceResult = new ServiceResult(true);
        if (StringUtils.isBlank(designerId) || StringUtils.isBlank(caseId) || StringUtils.isBlank(openId)) {
            LOGGER.error("params must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("params must not be null");
            return serviceResult;
        }
        atlasDetailMapper.delete(designerId, caseId, openId);
        atlasMapper.delete(designerId, caseId, openId);
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<AtlasVo> getAtlasByDesignerCaseId(String designerId, String caseId) {
        ServiceResult<AtlasVo> serviceResult = new ServiceResult<>(true);
        if (StringUtils.isBlank(designerId) || StringUtils.isBlank(caseId)) {
            LOGGER.error("params must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("params must not be null");
            return serviceResult;
        }
        try {
            AtlasVo atlasVo = atlasMapper.getAtlasByDesignerCaseId(designerId, caseId);
            serviceResult.setSuccess(true);
            serviceResult.setData(atlasVo);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过设计师id和caseId获取图集失败：", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("通过设计师id和caseId获取图集失败");
            return serviceResult;
        }
    }

}
