package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexCollectionService;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionBrandVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionTargetVo;
import com.chinaredstar.cms.api.vo.index.IndexCollectionDataVo;
import com.chinaredstar.cms.api.vo.index.IndexCollectionVo;
import com.chinaredstar.cms.mapper.CmsIndexCollectionDataMapper;
import com.chinaredstar.cms.mapper.CmsIndexCollectionMapper;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.redstar.digital.open.bean.OmsBrandInfo;
import com.redstar.digital.open.service.OmsBrandInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by pengfei.wang on 2017/3/29.
 */
@Service("cmsIndexCollectionService")
public class CmsIndexCollectionServiceImpl implements CmsIndexCollectionService {

    private final Logger logger = LoggerFactory.getLogger(CmsIndexCollectionServiceImpl.class);

    @Autowired
    private CmsIndexCollectionMapper cmsIndexCollectionMapper;
    @Autowired
    private CmsIndexCollectionDataMapper cmsIndexCollectionDataMapper;
    @Autowired
    private OmsBrandInfoService omsBrandInfoService;

    @Override
    public ServiceResult<List<IndexCollectionVo>> findCollectionByType(CmsCollectionQueryVo queryVo) {
        ServiceResult<List<IndexCollectionVo>> serviceResult = new ServiceResult<>(false);
        if (queryVo == null) {
            serviceResult.setMsg("查询参数不能为空");
            return serviceResult;
        }
        try {
            List<IndexCollectionVo> indexCollectionVos = cmsIndexCollectionMapper.findCollectionByType(queryVo);
            if (CollectionUtils.isNotEmpty(indexCollectionVos)) {
                getCollectionData(indexCollectionVos);
                processBrandCollectionData(indexCollectionVos);
            }
            serviceResult.setData(indexCollectionVos);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            logger.error("查询合辑列表异常, params:{}", JsonUtil.toJson(queryVo, false), e);
            serviceResult.setSuccess(Boolean.FALSE);
            serviceResult.setMsg("查询合辑列表异常");
        }
        return serviceResult;
    }

    /**
     * 查询品牌合辑下的数据
     *
     * @param indexCollectionVos
     */
    private void getCollectionData(List<IndexCollectionVo> indexCollectionVos) {
        List<Integer> ids = new ArrayList<>();
        for (IndexCollectionVo vo : indexCollectionVos) {
            ids.add(vo.getId());
        }
        List<IndexCollectionDataVo> indexCollectionDataVos = cmsIndexCollectionDataMapper.findByCollectionIds(ids);
        if (CollectionUtils.isNotEmpty(indexCollectionDataVos)) {
            for (IndexCollectionVo collectionVo : indexCollectionVos) {
                List<IndexCollectionDataVo> dataVos = new ArrayList<>();
                for (IndexCollectionDataVo dataVo : indexCollectionDataVos) {
                    if (dataVo.getCollectionId().equals(Long.valueOf(collectionVo.getId()))) {
                        dataVos.add(dataVo);
                    }
                }
                Collections.sort(dataVos, new Comparator<IndexCollectionDataVo>() {
                    @Override
                    public int compare(IndexCollectionDataVo o1, IndexCollectionDataVo o2) {
                        return o1.getSortNo() > o2.getSortNo() ? 1 : o1.getSortNo() == o2.getSortNo() ? 0 : -1;
                    }
                });
                collectionVo.setCollectionDataList(dataVos);
            }
        }
    }

    private List<IndexCollectionVo> processBrandCollectionData(List<IndexCollectionVo> indexCollectionVos) {
        for (IndexCollectionVo indexCollectionVo : indexCollectionVos) {
            List<Integer> brandIdList = new ArrayList<>();
            List<IndexCollectionDataVo> collectionDataList = indexCollectionVo.getCollectionDataList();
            if (collectionDataList == null || collectionDataList.isEmpty()) {
                continue;
            }
            for (IndexCollectionDataVo indexCollectionDataVo : collectionDataList) {
                if (indexCollectionDataVo == null || indexCollectionDataVo.getTargetId() == null) {
                    continue;
                }
                brandIdList.add(Integer.valueOf(indexCollectionDataVo.getTargetId()));
            }

            com.redstar.digital.open.vo.ServiceResult<List<OmsBrandInfo>> brandServiceResult =
                    omsBrandInfoService.getOmsBrandInfoById(brandIdList);

            if (!brandServiceResult.isSuccess()) {
                logger.error("获取品牌信息失败. Parameter : {},  serviceResult : {}", brandIdList, JsonUtil.toJson(brandIdList, false));
                continue;
            }

            List<OmsBrandInfo> brandInfoList = brandServiceResult.getData();
            List<CmsCollectionTargetVo> brandVoList = new ArrayList<>();
            Map<Integer, CmsCollectionBrandVo> cmsCollectionBrandVoMap = new HashMap<>();
            for (OmsBrandInfo omsBrandInfo : brandInfoList) {
                CmsCollectionBrandVo brandVo = new CmsCollectionBrandVo();
                BeanUtils.copyProperties(omsBrandInfo, brandVo);
                cmsCollectionBrandVoMap.put(brandVo.getId(), brandVo);
            }

            // 品牌排序
            for (IndexCollectionDataVo indexCollectionDataVo : collectionDataList) {
                if (indexCollectionDataVo == null || indexCollectionDataVo.getTargetId() == null) {
                    continue;
                }
                Integer targetId = Integer.valueOf(indexCollectionDataVo.getTargetId());
                if (null != targetId && null != cmsCollectionBrandVoMap.get(targetId)) {
                    brandVoList.add(cmsCollectionBrandVoMap.get(targetId));
                }
            }
            indexCollectionVo.setCmsCollectionTargetVoList(brandVoList);
        }
        return indexCollectionVos;
    }
}
