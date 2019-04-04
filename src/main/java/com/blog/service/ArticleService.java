package com.blog.service;

import java.util.Date;
import java.util.List;

import com.blog.crawl.other.HttpClientDownloader;
import com.blog.crawl.pipeline.JueJinPipeline;
import com.blog.crawl.pipeline.OsArticlePipe;
import com.blog.crawl.spider.JueJinSpider;
import com.blog.crawl.spider.OsArticleSpider;
import com.blog.dao.CrawlArticleMapper;
import com.blog.entities.CrawlArticle;
import com.blog.entities.CrawlArticleExample;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.ArticleMapper;
import com.blog.entities.Article;
import com.blog.entities.ArticleExample;
import com.blog.entities.ArticleGroupByTime;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

@Service
public class ArticleService {

    private static final String JUEJIN_BACKEND_ARTICLE_LIST_URL = "https://timeline-merger-ms.juejin.im/v1/get_entry_by_rank?src=web&limit=20&category=5562b419e4b00c57d9b94ae2";

    @Resource
	private ArticleMapper articleMapper;

	@Resource
	private CrawlArticleMapper crawlArticleMapper;
	
	public void addArticle(Article article) {
		articleMapper.insertSelective(article);
	}

	public Article getArticleById(Integer articleId) {
		return articleMapper.selectByPrimaryKey(articleId);
	}

	public List<Article> getArticles() {
		ArticleExample articleExample = new ArticleExample();
		articleExample.setOrderByClause("post_time");
		return articleMapper.selectByExample(articleExample);
	}

	public List<CrawlArticle> getCrawlArticle() {
		CrawlArticleExample example = new CrawlArticleExample();
		example.setOrderByClause("post_time");
		return crawlArticleMapper.selectByExample(example);
	}

    public Boolean cralwGetTodayArticle() {
        String crawlDay = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        List<CrawlArticle> crawlArticles = crawlArticleMapper.selectByCrawlDay(crawlDay);
        //若今日已经爬取过
        if(CollectionUtils.isNotEmpty(crawlArticles)) {
            return false;
        }
        //爬取掘金
        Spider.create(new JueJinSpider())
              .setDownloader(new HttpClientDownloader())
              .addPipeline(new JueJinPipeline())
              .addUrl(JUEJIN_BACKEND_ARTICLE_LIST_URL)
              .thread(2)
              .run();
        //爬取开源中国
		Spider.create(new OsArticleSpider())
			  .addUrl("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0&p=1")
			  .addPipeline(new OsArticlePipe())
			  .thread(2)
			  .run();
        return true;
    }

	public void batchInsertCrawlArticle(List<CrawlArticle> crawlArticles) {
        crawlArticleMapper.batchInsert(crawlArticles);
    }

    public void updateCrawlArticle(String articleUrl, String content) {
	    crawlArticleMapper.updateByObjectId(articleUrl, content);
    }

	public CrawlArticle getCrawlArticleById(Long articleId) {
		return crawlArticleMapper.selectByPrimaryKey(articleId);
	}
	
	public List<ArticleGroupByTime> selectArticleGroup() {
		return articleMapper.selectArticleGroup();
	}

	public List<Article> selectArticleByType(Integer articleTypeId) {
		return articleMapper.selectArticleByType(articleTypeId);
	}

	public List<Article> selectArticleByTime(String format) {
		return articleMapper.selectArticleByTime(format);
	}

	public void updateArticle(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
	}

	public void deleteArticle(Integer articleId) {
		articleMapper.deleteByPrimaryKey(articleId);
	}

	public List<Article> searchArticle(String searchParam) {
		return articleMapper.searchArticle(searchParam);
	}

}
