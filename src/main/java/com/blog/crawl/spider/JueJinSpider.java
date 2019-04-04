package com.blog.crawl.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.crawl.other.HttpClientDownloader;
import com.blog.crawl.pipeline.JueJinPipeline;
import com.blog.service.ArticleService;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.XpathSelector;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xubin
 * @Date: 2019/3/25
 */
@Repository
public class JueJinSpider implements PageProcessor {

    private static final String URL_POST = "https://juejin\\.im/post/\\w+";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
                            .addHeader("Origin","https://juejin.im")
                            .addHeader("Referer", "https://juejin.im/welcome/backend")
                            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

    public void process(Page page) {
        if(page.getUrl().regex(URL_POST).match()) {
            /*String[] split = page.getUrl().toString().split("post/");
            page.putField("articleUrl", split[1]);*/
            //对文章处理，替换掉一些掘金特有的符号
            page.putField("articleUrl", page.getUrl().toString());
            String content = new XpathSelector("//div[@class='article-content']").select(page.getRawText());
            content = content.replace("h1", "h3").replace("data-src", "src").replace("data-width", "width").replace("data-height", "height").replace("'", "\"").replace("'", "\"");
            page.putField("content", content);
        }else {
            //过滤出属于掘金的文章链接，放入待爬取url列表
            String juejinData = filterJueJinUrl(page.getRawText());
            List<String> articleUrls = new JsonPathSelector("$.d.entrylist[*].originalUrl").selectList(juejinData);
            page.addTargetRequests(articleUrls);

            page.putField("articleUrl", articleUrls);//文章唯一标识
            page.putField("author", new JsonPathSelector("$.d.entrylist[*].user.username").selectList(juejinData));
            page.putField("title", new JsonPathSelector("$.d.entrylist[*].title").selectList(juejinData));
            page.putField("keyword", new JsonPathSelector("$.d.entrylist[*].tags[0].title").selectList(juejinData));
            page.putField("postTime", new JsonPathSelector("$.d.entrylist[*].createdAt").selectList(juejinData));
            page.putField("summary", new JsonPathSelector("$.d.entrylist[*].summaryInfo").selectList(juejinData));
        }
    }

    public static void main(String[] args) {
        Spider.create(new JueJinSpider())
              .setDownloader(new HttpClientDownloader())
              .addPipeline(new JueJinPipeline())
              .addUrl("https://timeline-merger-ms.juejin.im/v1/get_entry_by_rank?src=web&limit=20&category=5562b419e4b00c57d9b94ae2")
              .thread(2)
              .run();
    }

    public static String filterJueJinUrl(String rawtext) {
        JSONObject dataJson = JSON.parseObject(rawtext);
        JSONArray jsonArray = dataJson.getJSONObject("d").getJSONArray("entrylist");
        for(int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String originalUrl = jsonObject.get("originalUrl").toString();
            if(!originalUrl.startsWith("https://juejin.im/post")) {
                jsonArray.remove(jsonObject);
            }
        }
        dataJson.put("entrylist", jsonArray);
        return dataJson.toString();
    }

    public Site getSite() {
        return site;
    }
}
