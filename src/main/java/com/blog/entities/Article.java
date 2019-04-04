package com.blog.entities;

import java.util.Date;
import java.util.List;

public class Article {
	private Integer articleId;

	private String title;

	private String summary;

	private Date postTime;

	private Integer visit;

	private Integer type;

	private String content;

	// 文章类型
	private ArticleType articleType;

	// 属于此文章的评论
	private List<Comment> comments;

	// 该文章所属的标签
	private List<Lable> lables;

	public Article() {
		super();
	}

	public Article(String title, String summary, Date postTime, Integer type,
			String content) {
		super();
		this.title = title;
		this.summary = summary;
		this.postTime = postTime;
		this.type = type;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title
				+ ", summary=" + summary + ", postTime=" + postTime
				+ ", visit=" + visit + ", type=" + type + ", content="
				+ content + ", articleType=" + articleType + ", comments=" + comments + ", lables=" + lables
				+ "]";
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
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

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Lable> getLables() {
		return lables;
	}

	public void setLables(List<Lable> lables) {
		this.lables = lables;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}