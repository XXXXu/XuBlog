package com.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.blog.entities.Article;
import com.blog.entities.ArticleExample;
import com.blog.entities.ArticleGroupByTime;

public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    Integer selectArticleCountByType(Integer type);
    
    List<Article> selectArticleByType(Integer type);
    
    List<Article> selectByExample(ArticleExample example);

    Article selectByPrimaryKey(Integer articleId);
    
    List<Article> searchArticle(String searchParam);
    
    List<ArticleGroupByTime> selectArticleGroup();
    
    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

	List<Article> selectArticleByTime(String format);

}