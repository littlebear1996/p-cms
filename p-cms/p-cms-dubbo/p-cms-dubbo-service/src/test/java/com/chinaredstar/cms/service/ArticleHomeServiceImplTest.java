package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.ArticleHome;
import com.chinaredstar.cms.api.service.ArticleHomeService;
import com.chinaredstar.cms.api.vo.article.ArticleHomeQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHomeVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/8/23.
 */
public class ArticleHomeServiceImplTest extends BaseTest {

    @Autowired
    private ArticleHomeService articleHomeService;

    @Test
    public void getArticeHomeList() throws Exception {
        ArticleHomeQueryVo articleHomeQueryVo = new ArticleHomeQueryVo();
        articleHomeQueryVo.setTop(true);
        ServiceResult<List<ArticleHome>> result = articleHomeService.getArticleHomeList(articleHomeQueryVo, new Page(1));
        Assert.assertTrue(result.isSuccess());

        result = articleHomeService.getArticleHomeList(null, null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getArticleHomeListByIds() throws Exception {
        List<Integer> idList = new ArrayList<>();
        for(int i=1;i<=100;i++){
            idList.add(i);
        }
        ServiceResult<List<ArticleHomeVo>> result = articleHomeService.getArticleHomeListByIds(idList);
        Assert.assertTrue(result.isSuccess());

        result = articleHomeService.getArticleHomeListByIds(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getDetailById() {
        Integer id = 1;
        ServiceResult<ArticleHomeVo> result = articleHomeService.getDetailById(id);
        Assert.assertTrue(result.isSuccess());


    }

}