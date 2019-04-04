package com.blog.crawl.pipeline;

import com.blog.entities.CrawlArticle;
import com.blog.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OsArticlePipe implements Pipeline {

    private ApplicationContext context;

    public OsArticlePipe() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    public void process(ResultItems resultItems, Task task) {
        ArticleService articleService = (ArticleService) context.getBean("articleService");
        if (Objects.nonNull(resultItems.get("content"))) {
            String content = resultItems.get("content");
            String articleUrl = resultItems.get("articleUrl");
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(content);
            content = handlerString(m.replaceAll("")).replace("'", "\"").replace("'", "\"");
            articleService.updateCrawlArticle(articleUrl, content);
        } else {
            List<String> articleUrls = resultItems.get("articleUrl");
            List<String> authors = resultItems.get("author");
            List<String> titles = resultItems.get("title");
            List<Date> postTimes = processTime(resultItems.get("postTime"));
            List<String> summarys = resultItems.get("summary");
            List<CrawlArticle> crawlArticles = new ArrayList<>();
            for (int i = 0; i < articleUrls.size(); i++) {
                CrawlArticle crawlArticle = new CrawlArticle();
                crawlArticle.setArticleUrl(articleUrls.get(i));
                crawlArticle.setAuthor(authors.get(i));
                crawlArticle.setTitle(titles.get(i));
                crawlArticle.setKeyword("无");
                crawlArticle.setPostTime(postTimes.get(i));
                crawlArticle.setSummary(summarys.get(i));
                crawlArticle.setCrawlDay(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
                crawlArticles.add(crawlArticle);
            }

            articleService.batchInsertCrawlArticle(crawlArticles);
        }
    }

    //将"6小时前"这种格式的数据转换为日期时间
    private static List<Date> processTime(List<String> postTimes) {
        List<Date> postTimeDates = new ArrayList<>();
        for(String postTime : postTimes) {
            Calendar calendar = Calendar.getInstance();
            if(postTime.contains("天")) {
                String[] time = postTime.split("天");
                calendar.add(Calendar.DAY_OF_YEAR, -Integer.parseInt(time[0]));
            }else if(postTime.contains("小时")) {
                String[] time = postTime.split("小时");
                calendar.add(Calendar.HOUR, -Integer.parseInt(time[0]));
            }
            postTimeDates.add(calendar.getTime());
        }
        return postTimeDates;
    }

    //给img标签带上/结尾
    /*private static String handlerString(String str) {
        if(StringUtils.isBlank(str)) {
            return str;
        }
        int index = -1;
        StringBuilder stringBuilder = new StringBuilder(str.length()+10);
        while((index = str.indexOf("<img")) != -1) {
            stringBuilder.append(str.substring(0, index+4));
            str = StringUtils.replaceOnce(str.substring(index+4), ">", "/>");
        }
        return stringBuilder.append(str).toString();
    }*/

    private static String handlerString(String str) {
        if(StringUtils.isBlank(str)) {
            return str;
        }
        int start = -1;
        StringBuilder stringBuilder = new StringBuilder(str);
        while((start = stringBuilder.indexOf("<!--")) != -1) {
            int end = stringBuilder.indexOf("-->");
            stringBuilder.delete(start, end+3);
        }
        while((start = stringBuilder.indexOf("<script")) != -1) {
            int end = stringBuilder.indexOf("</script");
            stringBuilder.delete(start, end+9);
        }
        return stringBuilder.toString();
    }


}
