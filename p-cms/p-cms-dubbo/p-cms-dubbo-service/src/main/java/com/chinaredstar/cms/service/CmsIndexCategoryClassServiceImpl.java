package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexCategoryClassService;
import com.chinaredstar.cms.api.vo.index.IndexCategoryClassVo;
import com.chinaredstar.cms.mapper.CmsIndexCategoryClassMapper;
import com.google.common.collect.Lists;
import com.redstar.digital.open.service.ShowCategoryService;
import com.redstar.digital.open.vo.ShowCategoryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yixin.sun on 2017/3/27.
 */
@Service("cmsIndexCategoryClassService")
public class CmsIndexCategoryClassServiceImpl implements CmsIndexCategoryClassService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsIndexCategoryClassServiceImpl.class);

    @Autowired
    private CmsIndexCategoryClassMapper indexCategoryClassMapper;

    @Autowired
    private ShowCategoryService showCategoryService;

    @Override
    public ServiceResult<List<IndexCategoryClassVo>> findListByCategoryId(Integer categoryId) {
        ServiceResult<List<IndexCategoryClassVo>> serviceResult = new ServiceResult<>(false);
        if (categoryId == null) {
            serviceResult.setMsg("categoryId不能为空");
            return serviceResult;
        }
        try {
            List<IndexCategoryClassVo> indexCategoryClassVos = indexCategoryClassMapper.findPageListByCategoryId(categoryId);
            processIndexCategoryClassVos(indexCategoryClassVos);
            serviceResult.setSuccess(true);
            serviceResult.setData(indexCategoryClassVos);
        } catch (Exception e) {
            LOGGER.error("通过categoryId查询分类出错:", e);
            serviceResult.setMsg("通过categoryId查询分类出错");
        }
        return serviceResult;
    }

    private void processIndexCategoryClassVos(List<IndexCategoryClassVo> indexCategoryClassVos) {
        Map<Integer, List<IndexCategoryClassVo>> categoryClassMap = new HashMap<>();
        for (IndexCategoryClassVo categoryClassVo : indexCategoryClassVos) {
            if (categoryClassVo != null && categoryClassVo.getSubClassList() != null && categoryClassVo.getSubClassList().size() != 0) {
                for (IndexCategoryClassVo classVo : categoryClassVo.getSubClassList()) {
                    if (categoryClassMap.get(Integer.valueOf(classVo.getClassId())) == null) {
                        categoryClassMap.put(Integer.valueOf(classVo.getClassId()), new ArrayList<IndexCategoryClassVo>());
                    }
                    categoryClassMap.get(Integer.valueOf(classVo.getClassId())).add(classVo);
                }
            }
        }
        com.redstar.digital.open.vo.ServiceResult<List<ShowCategoryVo>> categoryServiceResult = showCategoryService.getShowCategoryListByCateIds(Lists.newArrayList(categoryClassMap.keySet()));
        if (categoryServiceResult != null && categoryServiceResult.isSuccess()) {
            List<ShowCategoryVo> showCategoryVos = categoryServiceResult.getData();
            for (ShowCategoryVo showCategoryVo : showCategoryVos) {
                List<IndexCategoryClassVo> indexCategoryClassVoList = categoryClassMap.get(showCategoryVo.getCategoryId());
                for (IndexCategoryClassVo indexCategoryClassVo : indexCategoryClassVoList) {
                    indexCategoryClassVo.setClassName(showCategoryVo.getCategoryName());
                    indexCategoryClassVo.setClassImgUrl(showCategoryVo.getImgUrl());
                }

            }
        }
    }
}
