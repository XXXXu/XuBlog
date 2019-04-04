package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.CommentMapper;
import com.blog.entities.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	
	public void addComment(Comment comment) {
		commentMapper.insertSelective(comment);
	}

	public void deleteComment(Integer commentId) {
		commentMapper.deleteByPrimaryKey(commentId);
	}
}
