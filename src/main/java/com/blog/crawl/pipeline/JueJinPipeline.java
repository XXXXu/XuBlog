package com.blog.crawl.pipeline;

import com.blog.entities.CrawlArticle;
import com.blog.service.ArticleService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: xubin
 * @Date: 2019/3/25
 */
public class JueJinPipeline implements Pipeline {

    private ApplicationContext context;

    public JueJinPipeline() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    public void process(ResultItems resultItems, Task task) {
        ArticleService articleService = (ArticleService) context.getBean("articleService");
        //ArticleService articleService = ApplicationContextUtil.getBean(ArticleService.class);
        if(Objects.isNull(resultItems.get("content"))) {
            List<String> articleUrls = resultItems.get("articleUrl");
            List<String> authors = resultItems.get("author");
            List<String> titles = resultItems.get("title");
            List<String> keywords = resultItems.get("keyword");
            List<String> postTimes = resultItems.get("postTime");
            List<String> summarys = resultItems.get("summary");
            List<CrawlArticle> crawlArticles = new ArrayList<>();
            for (int i = 0; i < articleUrls.size(); i++) {
                CrawlArticle crawlArticle = new CrawlArticle();
                crawlArticle.setArticleUrl(articleUrls.get(i));
                crawlArticle.setAuthor(authors.get(i));
                crawlArticle.setTitle(titles.get(i));
                crawlArticle.setKeyword(keywords.get(i));
                try {
                    Date date = DateUtils.parseDate(postTimes.get(i), "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    crawlArticle.setPostTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                crawlArticle.setSummary(summarys.get(i));
                crawlArticle.setCrawlDay(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                crawlArticles.add(crawlArticle);
            }
            articleService.batchInsertCrawlArticle(crawlArticles);
        }else {
            String articleUrl = resultItems.get("articleUrl");
            System.out.println(articleUrl);
            String content = resultItems.get("content");
            articleService.updateCrawlArticle(articleUrl, content);
        }
    }
}
