package com.blog.entities;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Comment {
    private Integer commentId;

    @NotEmpty(message="请输入评论")
    private String content;
    
    @Email
    @NotEmpty(message="请输入邮箱")
    private String email;

    private Integer articleId;

    private Date postTime;
    

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content
				+ ", email=" + email + ", articleId=" + articleId
				+ ", postTime=" + postTime + "]";
	}
}