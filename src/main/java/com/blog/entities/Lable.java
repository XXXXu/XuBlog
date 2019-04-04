package com.blog.entities;

import java.util.List;

public class Lable {
	private Integer lableId;

	private String name;

	private List<Article> articles;

	public Lable() {
		super();
	}

	public Lable(Integer lableId, String name, List<Article> articles) {
		super();
		this.lableId = lableId;
		this.name = name;
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Lable [lableId=" + lableId + ", name=" + name + ", articles="
				+ articles + "]";
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Integer getLableId() {
		return lableId;
	}

	public void setLableId(Integer lableId) {
		this.lableId = lableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}