package com.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entities.User;
import com.blog.service.UserService;
import com.blog.util.Message;

@Controller
@RequestMapping("login")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("loginPage")
	public String loginPage() {
		return "loginPage";
	}
	
	@ResponseBody
	@RequestMapping("validatedLogin")
	public Message validatedLogin(User user, HttpSession session) {
		User user2 = userService.getUser(user);
		if(user2 != null) {
			session.setAttribute("user", user2);
			return Message.getSuccessMessage();
		}else {
			return Message.getFailMessage();
		}
	}
	
	@RequestMapping("exitLogin")
	public String exitLogin(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/article/articleList";
	}
	
}
