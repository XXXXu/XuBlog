package com.blog.dao;

import com.blog.entities.ArticleLable;
import com.blog.entities.ArticleLableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleLableMapper {
    long countByExample(ArticleLableExample example);

    int deleteByExample(ArticleLableExample example);

    int deleteByPrimaryKey(Integer articleLableId);

    int insert(ArticleLable record);

    int insertSelective(ArticleLable record);

    List<ArticleLable> selectByExample(ArticleLableExample example);

    ArticleLable selectByPrimaryKey(Integer articleLableId);

    int updateByExampleSelective(@Param("record") ArticleLable record, @Param("example") ArticleLableExample example);

    int updateByExample(@Param("record") ArticleLable record, @Param("example") ArticleLableExample example);

    int updateByPrimaryKeySelective(ArticleLable record);

    int updateByPrimaryKey(ArticleLable record);
}