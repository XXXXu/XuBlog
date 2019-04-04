package com.blog.entities;

import java.util.List;

public class ArticleType {
	private Integer articleTypeId;

	private String name;

	private Integer articleCount;

	private List<Article> articles;

	public ArticleType() {
		super();
	}

	public ArticleType(String name) {
		super();
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Integer getArticleTypeId() {
		return articleTypeId;
	}

	public void setArticleTypeId(Integer articleTypeId) {
		this.articleTypeId = articleTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	@Override
	public String toString() {
		return "ArticleType [articleTypeId=" + articleTypeId + ", name=" + name
				+ ", articleCount=" + articleCount + ", articles=" + articles
				+ "]";
	}

}