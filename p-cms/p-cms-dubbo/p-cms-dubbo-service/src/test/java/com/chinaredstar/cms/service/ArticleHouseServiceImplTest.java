package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.ArticleHouse;
import com.chinaredstar.cms.api.service.ArticleHouseService;
import com.chinaredstar.cms.api.vo.article.ArticleHouseQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by sunny on 16-8-31.
 */
public class ArticleHouseServiceImplTest extends BaseTest {

    @Autowired
    private ArticleHouseService articleHouseService;

    @Test
    public void getArticleHouseWithPage() throws Exception {
        ServiceResult<List<ArticleHouseVo>> result = articleHouseService.getArticleHouseWithPage(new Page(1, 10));
        Assert.assertTrue(result.isSuccess());

        result = articleHouseService.getArticleHouseWithPage(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void listByCategoryTag() {
        ArticleHouseQueryVo queryVo = new ArticleHouseQueryVo();
        queryVo.setCurrentTime(new Date());
        queryVo.setCategoryTag("");
        ServiceResult<List<ArticleHouseVo>> result = articleHouseService.listByCategoryTag(queryVo);
        Assert.assertTrue(result.isSuccess());

        result = articleHouseService.listByCategoryTag(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getDetailById() {
        ServiceResult<ArticleHouseVo> result = articleHouseService.getDetailById(1);
        Assert.assertTrue(result.isSuccess());

        result = articleHouseService.getDetailById(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getArticleHouseListByIds() {
        List<Integer> idList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            idList.add(i);
        }
        ServiceResult<List<ArticleHouseVo>> result = articleHouseService.getArticleHouseListByIds(idList);
        Assert.assertTrue(result.isSuccess());

        result = articleHouseService.getArticleHouseListByIds(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getAllTags() {
        ArticleHouseQueryVo queryVo = new ArticleHouseQueryVo();
        queryVo.setCurrentTime(new Date());
        queryVo.setCategoryTag("本科");
        ServiceResult<Set<String>> result = articleHouseService.getAllTags(queryVo);
        Assert.assertTrue(result.isSuccess());

        result = articleHouseService.getAllTags(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void deleteById() {
        ServiceResult serviceResult = articleHouseService.deleteById(38,"417616c9c1a18850b1030ab7c88ab17a",1483679383327L);
        System.out.println(JsonUtil.toJson(serviceResult,true));
    }

    @Test
    public void save() {
        ArticleHouse articleHouse = new ArticleHouse();
        articleHouse.setTitle("test_sunyixin");
        articleHouse.setSubTitle("test");
        articleHouse.setCategoryTags("test");
        articleHouse.setCoverImgUrl("http://img2.imgtn.bdimg.com/it/u=716456033,2739143703&fm=23&gp=0.jpg");
        articleHouse.setCreateTime(new Date());
        articleHouse.setCreatorId(111);
        articleHouse.setCreatorName("sunyixin");
        articleHouse.setStartTime(new Date());
        articleHouse.setEndTime(new Date());
        articleHouse.setContent("test");
        articleHouse.setTags("test");
        articleHouse.setSource(1);
        articleHouse.setCreatorOpenId("udsr-2ewr-342e-6dfa");
        articleHouse.setCheckStatus(0);
        ServiceResult serviceResult = articleHouseService.save(articleHouse);
        Assert.assertTrue(serviceResult.isSuccess());
    }

    @Test
    public void updateById() {
        ArticleHouse articleHouse = new ArticleHouse();
        articleHouse.setId(105);
        articleHouse.setTitle("test_sunyixin_test");
        articleHouse.setSubTitle("test_sunyixin");
        articleHouse.setCategoryTags("test");
        articleHouse.setCoverImgUrl("http://img2.imgtn.bdimg.com/it/u=716456033,2739143703&fm=23&gp=0.jpg");
        articleHouse.setCreateTime(new Date());
        articleHouse.setCreatorId(112);
        articleHouse.setCreatorName("sunyixin_test");
        articleHouse.setStartTime(new Date());
        articleHouse.setEndTime(new Date());
        articleHouse.setContent("test_sunyixin");
        articleHouse.setTags("test_sunyixin");
        articleHouse.setUpdateTime(new Date());
        articleHouse.setUpdaterId(112);
        articleHouse.setUpdaterName("test");
        articleHouse.setCheckStatus(0);
        ServiceResult serviceResult = articleHouseService.updateById(articleHouse);

        articleHouse.setId(null);
        serviceResult = articleHouseService.updateById(articleHouse);
    }
}
