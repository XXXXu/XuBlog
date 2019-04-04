package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entities.ArticleType;
import com.blog.service.TypeService;
import com.blog.util.Message;

@Controller
@RequestMapping("type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@ResponseBody
	@RequestMapping("addType")
	public Message addType(ArticleType type) {
		typeService.addType(type);
		System.out.println(type);
		return Message.getSuccessMessage().addObject("type", type);
	}
	
	@ResponseBody
	@RequestMapping("getTypes")
	public Message getTypes() {
		List<ArticleType> types = typeService.getTypes();
		return Message.getSuccessMessage().addObject("types", types);
	}
}
