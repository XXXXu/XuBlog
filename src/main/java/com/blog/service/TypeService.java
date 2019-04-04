package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.ArticleTypeMapper;
import com.blog.entities.ArticleType;

@Service
public class TypeService {

	@Autowired
	private ArticleTypeMapper articleTypeMapper;
	
	public List<ArticleType> getTypes() {
		return articleTypeMapper.selectByExample(null);
	}

	public void addType(ArticleType type) {
		articleTypeMapper.insertSelective(type);	
	}
}
