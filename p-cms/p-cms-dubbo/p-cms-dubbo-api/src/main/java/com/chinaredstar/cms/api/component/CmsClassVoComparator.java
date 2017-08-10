package com.chinaredstar.cms.api.component;

import com.chinaredstar.cms.api.vo.cmsClass.CmsClassVo;

import java.util.Comparator;

/**
 * 推荐排序
 */
public class CmsClassVoComparator implements Comparator<CmsClassVo> {


    @Override
    public int compare(CmsClassVo o1, CmsClassVo o2) {
        if (o1.getSequence() < o2.getSequence()) {
            return -1;
        } else if (o1.getSequence().equals(o1.getSequence())) {
            return 0;
        } else {
            return 1;
        }
    }
}
