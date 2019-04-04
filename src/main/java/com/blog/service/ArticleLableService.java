package com.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.blog.dao.ArticleLableMapper;
import com.blog.entities.ArticleLable;

@Controller
public class ArticleLableService {

	@Autowired
	private ArticleLableMapper articleLableMapper;
	
	public void addArticleLable(ArticleLable articleLable) {
		articleLableMapper.insertSelective(articleLable);
	}

}
