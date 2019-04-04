package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.UserMapper;
import com.blog.entities.User;
import com.blog.entities.UserExample;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(User user) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUserNameEqualTo(user.getUserName())
									.andPasswordEqualTo(user.getPassword());
		List<User> list = userMapper.selectByExample(userExample);
		return list.size() > 0 ? list.get(0) : null;
	}
}
