package com.blog.util;

import java.util.HashMap;
import java.util.Map;

public class Message {

	// 返回的状态码，100表示失败，200表示成功
	private Integer code;
	// 返回的消息
	private String msg;
	// 携带的数据
	private Map<String, Object> map = new HashMap<String, Object>();

	public static Message getSuccessMessage() {
		Message message = new Message();
		message.setCode(200);
		message.setMsg("success");
		return message;
	}

	public static Message getFailMessage() {
		Message message = new Message();
		message.setCode(100);
		message.setMsg("fail");
		return message;
	}
	
	public Message addObject(String key, Object value) {
		this.map.put(key, value);
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
