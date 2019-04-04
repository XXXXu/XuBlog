package com.blog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entities.Comment;
import com.blog.service.CommentService;
import com.blog.util.Message;

@Controller
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping("addComment")
	public Message addComment(Comment comment) {
		comment.setPostTime(new Date());
		commentService.addComment(comment);
		return Message.getSuccessMessage().addObject("comment", comment);
	}
		
	@ResponseBody
	@RequestMapping("validComment")
	public Message validComment(@Validated Comment comment, 
			BindingResult result) {
		Message validMessage = new Message();
		validMessage.setCode(200);
		List<FieldError> errors = result.getFieldErrors();
		for (FieldError fieldError : errors) {
			validMessage.addObject(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		if(errors.size() > 0) {
			validMessage.setMsg("validFail");
		}else {
			validMessage.setMsg("validSuccess");
		}
		return validMessage;
	}
	
	@ResponseBody
	@RequestMapping("deleteComment")
	public Message deleteComment(Integer commentId) {
		commentService.deleteComment(commentId);
		return Message.getSuccessMessage();
	}
}
