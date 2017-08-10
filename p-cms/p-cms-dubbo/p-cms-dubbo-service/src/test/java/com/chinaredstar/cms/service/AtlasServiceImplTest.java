package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.AtlasService;
import com.chinaredstar.cms.api.vo.atlas.AtlasDetailVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasQueryVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sunny on 16-8-23.
 */
public class AtlasServiceImplTest extends BaseTest {

    @Autowired
    private AtlasService atlasService;

    @Test
    public void listByType() throws Exception {

    }

    @Test
    public void testGetListByType() {
        AtlasQueryVo queryVo = new AtlasQueryVo();
        queryVo.setSubType(101);
        queryVo.setRecommand(true);
        queryVo.setCurrentTime(new Date());
        queryVo.setOrderBy("atlas.set_recommand_time DESC , atlas.create_time DESC");
        ServiceResult<List<AtlasVo>> result = atlasService.listByType(queryVo);
        System.out.print(result);

        result = atlasService.listByType(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testGetAtlasByIds() throws Exception {
        List<Integer> idList = new ArrayList<>();
        for(int i=1;i<=100;i++){
            idList.add(i);
        }
        ServiceResult<List<AtlasVo>> result = atlasService.getAtlasByIds(idList);
        System.out.println(result);

        result = atlasService.getAtlasByIds(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testGetAtlasById() throws Exception {
        ServiceResult<AtlasVo> result = atlasService.getAtlasById(1);
        System.out.println(result.isSuccess());

        result = atlasService.getAtlasById(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testInsert() {
        AtlasVo atlasVo = new AtlasVo();
        atlasVo.setCaseId("1");
        atlasVo.setCategoryTags("test");
        atlasVo.setType(1);
        atlasVo.setSubType(2);
        atlasVo.setTitle("test");
        atlasVo.setTags("hello");
        atlasVo.setDescription("测试");
        atlasVo.setDesignerId("1");
        atlasVo.setOpenId("1");
        atlasVo.setCoverImgUrl("http://10.11.25.215/group01/M00/00/8A/CgsZ2FfKucmAVQQ2AARNfXFij64998.jpg");

        List<AtlasDetailVo> atlasDetails = new ArrayList<>();
        AtlasDetailVo atlasDetailVo1 = new AtlasDetailVo();
        atlasDetailVo1.setImgUrl("http://10.11.25.215/group01/M00/00/8A/CgsZ2FfKucmAVQQ2AARNfXFij64998.jpg");
        atlasDetails.add(atlasDetailVo1);

        AtlasDetailVo atlasDetailVo2 = new AtlasDetailVo();
        atlasDetailVo2.setImgUrl("http://10.11.25.215/group01/M00/00/8A/CgsZ2FfKucmAVQQ2AARNfXFij64998.jpg");
        atlasDetails.add(atlasDetailVo2);

        atlasVo.setAtlasDetails(atlasDetails);
        ServiceResult result = atlasService.insertAtlas(atlasVo);
        System.out.println(JsonUtil.toJson(result, false));

        result = atlasService.insertAtlas(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void delete() {
        ServiceResult result = atlasService.deleteAtlas("1", "1", "1");
        System.out.println("删除返回结果" + JsonUtil.toJson(result, false));

        result = atlasService.deleteAtlas(null, null, null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void getAtlasByDesignerCaseId() {
        ServiceResult<AtlasVo> result = atlasService.getAtlasByDesignerCaseId("57", "20");
        System.out.println("通过designerId和caseId获取图集结果：" + JsonUtil.toJson(result, false));

        result = atlasService.getAtlasByDesignerCaseId(null, null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testDeleteAtlasByVo() {
        AtlasVo vo = new AtlasVo();
        vo.setId(3);
        ServiceResult result = atlasService.deleteAtlasByVo(vo);
        Assert.assertTrue(result.isSuccess());
        result = atlasService.deleteAtlasByVo(null);
    }

}