package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.ArticleCustomService;
import com.chinaredstar.cms.api.vo.article.ArticleCustomVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 16-8-31.
 */
public class ArticleCustomServiceImplTest extends BaseTest {

    @Autowired
    private ArticleCustomService articleCustomService;

    @Test
    public void getArticleCustomListByIds() {
        List<Integer> idList = new ArrayList<>();
        for(int i=1;i<=100;i++){
            idList.add(i);
        }
        ServiceResult<List<ArticleCustomVo>> result = articleCustomService.getArticleCustomListByIds(idList);
        Assert.assertTrue(result.isSuccess());

        result = articleCustomService.getArticleCustomListByIds(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getDetailById() {
        Integer id = 1;
        ServiceResult<ArticleCustomVo> result = articleCustomService.getDetailById(id);
        Assert.assertTrue(result.isSuccess());


    }
}
