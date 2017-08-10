package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.ArticleCustomService;
import com.chinaredstar.cms.api.vo.article.ArticleCustomVo;
import com.chinaredstar.cms.mapper.ArticleCustomMapper;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品文章
 */
@Service("articleCustomService")
@Timed
public class ArticleCustomServiceImpl implements ArticleCustomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleCustomServiceImpl.class);

    @Autowired
    private ArticleCustomMapper articleCustomMapper;

    @Override
    public ServiceResult<List<ArticleCustomVo>> getArticleCustomListByIds(List<Integer> customIdList) {
        ServiceResult<List<ArticleCustomVo>> serviceResult = new ServiceResult<>(true);
        try {
            List<ArticleCustomVo> articleCustomList = articleCustomMapper.getArticleCustomListByIds(customIdList);
            serviceResult.setData(articleCustomList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取商品文章信息失败. Cause by : {}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg("获取商品文章信息失败.");
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<ArticleCustomVo> getDetailById(Integer id) {
        ServiceResult<ArticleCustomVo> serviceResult = new ServiceResult<>(true);
        try {
            ArticleCustomVo articleCustomVo = articleCustomMapper.getDetailById(id);
            serviceResult.setData(articleCustomVo);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过id获取商品文章详情失败：{}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("通过id获取商品文章详情失败");
            return serviceResult;
        }
    }
}
