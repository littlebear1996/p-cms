package com.chinaredstar.cms.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * BaseMapper
 * 所有的Mapper都要继承BaseMapper
 */
public interface BaseMapper<T> {

    int insert(T model);

    int deleteByPrimaryKey(Integer id);

    int update(T model);

    int updateByPrimaryKey(T model);

    T selectByPrimaryKey(Integer id);

    List<T> findList(Map<String, Object> params);

    Integer updateViewCountById(@Param("id") Integer id, @Param("viewCount") Integer viewCount);

    List<Integer> getAllIds();

    List<Integer> getAllIdsByType(Integer type);
}
