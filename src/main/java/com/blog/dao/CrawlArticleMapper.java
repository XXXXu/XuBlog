package com.blog.dao;

import com.blog.entities.CrawlArticle;
import com.blog.entities.CrawlArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrawlArticleMapper {
    long countByExample(CrawlArticleExample example);

    int deleteByExample(CrawlArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CrawlArticle record);

    int insertSelective(CrawlArticle record);

    List<CrawlArticle> selectByExampleWithBLOBs(CrawlArticleExample example);

    List<CrawlArticle> selectByExample(CrawlArticleExample example);

    CrawlArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CrawlArticle record, @Param("example") CrawlArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") CrawlArticle record, @Param("example") CrawlArticleExample example);

    int updateByExample(@Param("record") CrawlArticle record, @Param("example") CrawlArticleExample example);

    int updateByPrimaryKeySelective(CrawlArticle record);

    int updateByPrimaryKeyWithBLOBs(CrawlArticle record);

    int updateByPrimaryKey(CrawlArticle record);

    void batchInsert(List<CrawlArticle> crawlArticles);

    void updateByObjectId(@Param("articleUrl")String articleUrl, @Param("content")String content);

    List<CrawlArticle> selectByCrawlDay(String crawlDay);
}