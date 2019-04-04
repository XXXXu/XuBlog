package com.blog.dao;

import com.blog.entities.Lable;
import com.blog.entities.LableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LableMapper {
    long countByExample(LableExample example);

    int deleteByExample(LableExample example);

    int deleteByPrimaryKey(Integer lableId);

    int insert(Lable record);

    int insertSelective(Lable record);

    List<Lable> selectByArticleId(Integer articleId);
    
    List<Lable> selectByExample(LableExample example);

    Lable selectByPrimaryKey(Integer lableId);

    int updateByExampleSelective(@Param("record") Lable record, @Param("example") LableExample example);

    int updateByExample(@Param("record") Lable record, @Param("example") LableExample example);

    int updateByPrimaryKeySelective(Lable record);

    int updateByPrimaryKey(Lable record);
}