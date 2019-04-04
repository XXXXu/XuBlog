package com.blog.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class CrawlArticle {
    private Long id;

    /**
     * 文章链接
     */
    private String articleUrl;


    /**
     * 作者
     */
    private String author;


    /**
     * 标题
     */
    private String title;


    /**
     * 关键字
     */
    private String keyword;


    /**
     * 摘要
     */
    private String summary;


    /**
     * 发表时间
     */
    private Date postTime;


    /**
     * 文章内容
     */
    private String content;

    /**
     * 爬取日期
     */
    private String crawlDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCrawlDay() {
        return crawlDay;
    }

    public void setCrawlDay(String crawlDay) {
        this.crawlDay = crawlDay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}