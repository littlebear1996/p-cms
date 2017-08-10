package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.ArticleMarketService;
import com.chinaredstar.cms.api.vo.article.ArticleMarketQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunny on 16-8-31.
 */
public class ArticleMarketServiceImplTest extends BaseTest {

    @Autowired
    private ArticleMarketService articleMarketService;

    @Test
    public void listByCategoryTag() {
        ArticleMarketQueryVo articleMarketQueryVo = new ArticleMarketQueryVo();
        articleMarketQueryVo.setCategoryTag("独特体验");
        articleMarketQueryVo.setCurrentTime(new Date());
        articleMarketQueryVo.setOnline(true);
        articleMarketQueryVo.setRecommand(true);
        ServiceResult<List<ArticleMarketVo>> serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
        System.out.println(JsonUtil.toJson(serviceResultMarket, false));
    }

    @Test
    public void listByCategoryTagAndCity() {
        ArticleMarketQueryVo articleMarketQueryVo = new ArticleMarketQueryVo();
        articleMarketQueryVo.setCityId("310100");
        articleMarketQueryVo.setCategoryTag("独特体验");
        articleMarketQueryVo.setCurrentTime(new Date());
        articleMarketQueryVo.setOnline(true);
        articleMarketQueryVo.setRecommand(true);
        articleMarketQueryVo.setPageNo(1);
        articleMarketQueryVo.setPageSize(10);
        ServiceResult<List<ArticleMarketVo>> serviceResultMarket = articleMarketService.listByCategoryTagAndCity(articleMarketQueryVo);
        System.out.println(JsonUtil.toJson(serviceResultMarket, false));
    }

    @Test
    public void getArticleMarketListByIds() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        ServiceResult<List<ArticleMarketVo>> result = articleMarketService.getArticleMarketListByIds(idList);
        System.out.println(result);

        result = articleMarketService.getArticleMarketListByIds(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getDetailById() {
        Integer id = 1;
        ServiceResult<ArticleMarketVo> result = articleMarketService.getDetailById(id);
        System.out.println(result);

        result = articleMarketService.getDetailById(null);
        Assert.assertTrue(!result.isSuccess());
    }

}
