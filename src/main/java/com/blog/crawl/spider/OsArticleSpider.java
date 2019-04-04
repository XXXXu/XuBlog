package com.blog.crawl.spider;

import com.blog.crawl.pipeline.OsArticlePipe;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.XpathSelector;

import java.util.List;

public class OsArticleSpider implements PageProcessor
{
    private Site site = Site.me().setRetryTimes(2).setSleepTime(100);

    public void process(Page page) {
        if(page.getUrl().toString().contains("/blog/"))
        {
            page.putField("articleUrl", page.getUrl().toString());
            String content = new XpathSelector("//div[@id='articleContent']").select(page.getRawText());
            page.putField("content", content);
        }
        else
        {
            System.out.println("开始爬取");
            List<String> articleUrls = page.getHtml().xpath("//a[@class='sc overh blog-title-link']/@href").all();
            page.putField("articleUrl", articleUrls);
            page.putField("author",page.getHtml().xpath("//div[@class='box item']//div[@class='box-fl']/a/img/@alt").all());
            page.putField("title",page.getHtml().xpath("//a[@class='sc overh blog-title-link']/h2/text()").all());
            page.putField("postTime",page.getHtml().xpath("//div[@class='box-aw']/footer/span[3]/text()").all());
            page.putField("summary",page.getHtml().xpath("//div[@class='box-aw']/section/text()").all());

            page.addTargetRequests(articleUrls);
        }

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new OsArticleSpider())
              .addUrl("https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0&p=1")
              .addPipeline(new OsArticlePipe())
              .thread(2)
              .run();
    }

}
