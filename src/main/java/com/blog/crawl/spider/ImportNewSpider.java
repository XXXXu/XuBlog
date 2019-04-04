package com.blog.crawl.spider;

import com.blog.crawl.pipeline.ImportNewPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Author: xubin
 * @Date: 2019/2/18
 */
public class ImportNewSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
                            .setDomain("http://www.importnew.com").setCharset("utf-8");

    public void process(Page page) {

        int pageNum = Integer.valueOf(page.getUrl().regex("page/(\\d+)").toString()) + 1;
        if(pageNum < 3) {
            page.addTargetRequest("http://www.importnew.com/all-posts/page/"+pageNum);
        }
        page.putField("title", page.getHtml().xpath("//div[@class='post-meta']/p[1]/a[1]/text()").all());
        page.putField("createTime", page.getHtml().xpath("//div[@class='post-meta']/p[1]/text()").all());
        page.putField("summary", page.getHtml().xpath("//div[@class='post-meta']/span/p/text()").all());
    }

    public static void main(String[] args) {
        Spider.create(new ImportNewSpider())
              .addPipeline(new ImportNewPipeline())
              .addUrl("http://www.importnew.com/all-posts/page/1")
              .thread(2)
              .run();
    }
    public Site getSite() {
        return site;
    }
}
