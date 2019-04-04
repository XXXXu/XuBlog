package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.entities.Lable;
import com.blog.service.LableService;
import com.blog.util.Message;

@Controller
@RequestMapping("lable")
public class LableController {
	
	@Autowired
	private LableService lableService;
	
	@ResponseBody
	@RequestMapping("addLable")
	public Message addLable(Lable lable) {
		lableService.addLable(lable);
		System.out.println(lable);
		return Message.getSuccessMessage().addObject("lable", lable);
	}
	
	@ResponseBody
	@RequestMapping("getLables")
	public Message addLable() {
		List<Lable> lables = lableService.getLables();
		return Message.getSuccessMessage().addObject("lables", lables);
	}
}
