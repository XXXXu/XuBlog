package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.LableMapper;
import com.blog.entities.Lable;

@Service
public class LableService {
	
	@Autowired
	private LableMapper lableMapper;
	
	public void addLable(Lable lable) {
		lableMapper.insertSelective(lable);
	}

	public List<Lable> getLables() {
		return lableMapper.selectByExample(null);
	}
}
