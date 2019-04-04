package com.blog.entities;

public class ArticleLable {
	private Integer articleLableId;

	private Integer articleId;

	private Integer lableId;

	public ArticleLable() {
		super();
	}

	public ArticleLable(Integer articleId, Integer lableId) {
		super();
		this.articleId = articleId;
		this.lableId = lableId;
	}

	public Integer getArticleLableId() {
		return articleLableId;
	}

	public void setArticleLableId(Integer articleLableId) {
		this.articleLableId = articleLableId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getLableId() {
		return lableId;
	}

	public void setLableId(Integer lableId) {
		this.lableId = lableId;
	}

	@Override
	public String toString() {
		return "ArticleLable [articleLableId=" + articleLableId
				+ ", articleId=" + articleId + ", lableId=" + lableId + "]";
	}

}