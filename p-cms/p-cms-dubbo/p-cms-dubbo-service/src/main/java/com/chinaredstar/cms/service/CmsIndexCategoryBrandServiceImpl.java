package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexCategoryBrandService;
import com.chinaredstar.cms.api.vo.index.IndexCategoryBrandVo;
import com.chinaredstar.cms.mapper.CmsIndexCategoryBrandMapper;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.redstar.digital.open.bean.OmsBrandInfo;
import com.redstar.digital.open.service.OmsBrandInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/27.
 */
@Service("cmsIndexCategoryBrandService")
public class CmsIndexCategoryBrandServiceImpl implements CmsIndexCategoryBrandService {

    private final Logger logger = LoggerFactory.getLogger(CmsIndexCategoryBrandServiceImpl.class);

    @Autowired
    private CmsIndexCategoryBrandMapper cmsIndexCategoryBrandMapper;

    @Autowired
    private OmsBrandInfoService omsBrandInfoService;

    @Override
    public ServiceResult<List<IndexCategoryBrandVo>> findByCategoryId(Integer categoryId) {
        ServiceResult<List<IndexCategoryBrandVo>> result = new ServiceResult<>(false);
        if (categoryId == null) {
            result.setMsg("分类id不能为空");
            return result;
        }
        try {
            List<IndexCategoryBrandVo> categoryBrandVos = cmsIndexCategoryBrandMapper.findByCategoryId(categoryId);
            List<Integer> brandIds = genBrandIds(categoryBrandVos);
            if (CollectionUtils.isNotEmpty(brandIds)) {
                com.redstar.digital.open.vo.ServiceResult<List<OmsBrandInfo>> brandServiceResult =
                        omsBrandInfoService.getOmsBrandInfoById(brandIds);
                if (brandServiceResult.isSuccess()) {
                    List<OmsBrandInfo> omsBrandInfos = brandServiceResult.getData();
                    if (CollectionUtils.isNotEmpty(omsBrandInfos)) {
                        for (IndexCategoryBrandVo brandVo : categoryBrandVos) {
                            for (OmsBrandInfo info : omsBrandInfos) {
                                if (brandVo.getBrandId().equals(String.valueOf(info.getId()))) {
                                    brandVo.setBrandName(info.getBrandName());
                                    brandVo.setBrandLogo(info.getBrandLogo());
                                    break;
                                }
                            }
                        }
                    } else {
                        logger.error("通过dubbo调用查询品牌列表接口(omsBrandInfoService.getOmsBrandInfoById(...))返回数据为空, params:{}",
                                JsonUtil.toJson(brandIds, false));
                    }
                } else {
                    logger.error("通过dubbo调用查询品牌列表接口(omsBrandInfoService.getOmsBrandInfoById(...))返回数据异常, params:{}, serviceResult:{}",
                            JsonUtil.toJson(brandIds, false), JsonUtil.toJson(brandServiceResult, false));
                }
            }
            result.setSuccess(true);
            result.setData(categoryBrandVos);
        } catch (Exception e) {
            logger.error("根据分类id查询品牌列表异常, params:{}", categoryId, e);
            result.setMsg("根据分类id查询品牌列表异常");
        }
        return result;
    }

    private List<Integer> genBrandIds(List<IndexCategoryBrandVo> categoryBrandVos) {
        List<Integer> brandIds = new ArrayList<>();
        if (CollectionUtils.isEmpty(categoryBrandVos)) return brandIds;
        for (IndexCategoryBrandVo brandVo : categoryBrandVos) {
            String brandId = brandVo.getBrandId();
            if (StringUtils.isBlank(brandId) || !NumberUtils.isNumber(brandId)) {
                continue;
            } else {
                brandIds.add(Integer.valueOf(brandVo.getBrandId()));
            }
        }
        return brandIds;
    }
}
