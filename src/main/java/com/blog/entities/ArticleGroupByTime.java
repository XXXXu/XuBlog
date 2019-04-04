package com.blog.entities;

import java.util.Date;

/**
 * 将文章按时间查询后，返回的包装类
 * 
 * @author mi
 * 
 */
public class ArticleGroupByTime {
	// 按照年和月分，数据库中分别有多少条时间记录
	private Date postTime;
	// 该时间段下公有多少篇文章
	private Integer articleCount;

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	@Override
	public String toString() {
		return "ArticleGroupByTime [postTime=" + postTime + ", articleCount="
				+ articleCount + "]";
	}

}
