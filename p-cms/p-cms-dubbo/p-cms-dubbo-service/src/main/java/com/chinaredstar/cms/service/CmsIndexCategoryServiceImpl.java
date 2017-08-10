package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexCategoryBrandService;
import com.chinaredstar.cms.api.service.CmsIndexCategoryClassService;
import com.chinaredstar.cms.api.service.CmsIndexCategoryService;
import com.chinaredstar.cms.api.vo.index.IndexCategoryBrandVo;
import com.chinaredstar.cms.api.vo.index.IndexCategoryClassVo;
import com.chinaredstar.cms.api.vo.index.IndexCategoryVo;
import com.chinaredstar.cms.mapper.CmsIndexCategoryMapper;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/27.
 */
@Service("cmsIndexCategoryService")
public class CmsIndexCategoryServiceImpl implements CmsIndexCategoryService {

    private final Logger logger = LoggerFactory.getLogger(CmsIndexCategoryServiceImpl.class);

    @Autowired
    private CmsIndexCategoryMapper cmsIndexCategoryMapper;
    @Autowired
    private CmsIndexCategoryBrandService cmsIndexCategoryBrandService;
    @Autowired
    private CmsIndexCategoryClassService cmsIndexCategoryClassService;

    @Override
    public ServiceResult<List<IndexCategoryVo>> getAll() {
        ServiceResult<List<IndexCategoryVo>> result = new ServiceResult<>(true);
        try {
            List<IndexCategoryVo> categoryVos = cmsIndexCategoryMapper.getAll();
            result.setData(categoryVos);
        } catch (Exception e) {
            logger.error("查询主app分类列表异常", e);
            result.setSuccess(false);
            result.setMsg("查询主app分类列表异常");
        }
        return result;
    }

    @Override
    public ServiceResult<IndexCategoryVo> findById(Integer id) {
        ServiceResult<IndexCategoryVo> result = new ServiceResult<>(false);
        if (id == null) {
            result.setMsg("分类id不能为空");
            return result;
        }
        try {
            IndexCategoryVo indexCategoryVo = cmsIndexCategoryMapper.findById(id);
            if (indexCategoryVo != null) {
                ServiceResult<List<IndexCategoryBrandVo>> brandServiceResult = cmsIndexCategoryBrandService.findByCategoryId(id);
                if (brandServiceResult.isSuccess()) {
                    indexCategoryVo.setBrands(brandServiceResult.getData());
                } else {
                    logger.error("调用查询主app分类下品牌接口(cmsIndexCategoryBrandService.findByCategoryId(...))异常, params:{}, serviceResult:{}",
                            id, JsonUtil.toJson(brandServiceResult, false));
                }
                ServiceResult<List<IndexCategoryClassVo>> classServiceResult = cmsIndexCategoryClassService.findListByCategoryId(id);
                if (classServiceResult.isSuccess()) {
                    indexCategoryVo.setClasses(classServiceResult.getData());
                } else {
                    logger.error("调用查询主app分类下分类接口(cmsIndexCategoryClassService.findListByCategoryId(...))异常, params:{}, serviceResult:{}",
                            id, JsonUtil.toJson(brandServiceResult, false));
                }
            }
            result.setSuccess(true);
            result.setData(indexCategoryVo);
        } catch (Exception e) {
            logger.error("查询主app分类详情及其所有分类/品牌异常, params:{}", id, e);
            result.setMsg("查询主app分类详情异常");
        }
        return result;
    }
}
