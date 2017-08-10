package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.ArticleHome;
import com.chinaredstar.cms.api.service.ArticleHomeService;
import com.chinaredstar.cms.api.vo.article.ArticleHomeQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHomeVo;
import com.chinaredstar.cms.mapper.ArticleHomeMapper;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 家装文章服务实现类
 */
@Service("articleHomeService")
@Timed
public class ArticleHomeServiceImpl implements ArticleHomeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleHomeServiceImpl.class);

    @Autowired
    private ArticleHomeMapper articleHomeMapper;

    @Override
    public ServiceResult<List<ArticleHome>> getArticleHomeList(ArticleHomeQueryVo queryVo, Page page) {
        ServiceResult<List<ArticleHome>> serviceResult = new ServiceResult<>(true);
        try {
            List<ArticleHome> articleHomeList = articleHomeMapper.getArticleHomeList(queryVo, page);
            serviceResult.setData(articleHomeList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("get article home failed. e:{}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<List<ArticleHomeVo>> getArticleHomeListByIds(List<Integer> idList) {
        ServiceResult<List<ArticleHomeVo>> serviceResult = new ServiceResult<>(true);
        try {
            List<ArticleHomeVo> articleHomeList = articleHomeMapper.getArticleHomeListByIds(idList);
            serviceResult.setData(articleHomeList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取家装文章失败. Cause by : {}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<ArticleHomeVo> getDetailById(Integer id) {
        ServiceResult<ArticleHomeVo> serviceResult = new ServiceResult<>(true);

        try {
            ArticleHomeVo articleHome = articleHomeMapper.getDetailById(id);
            serviceResult.setData(articleHome);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过id获取文章失败，{}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

}
