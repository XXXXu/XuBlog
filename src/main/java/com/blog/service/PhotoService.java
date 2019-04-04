package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.PhotoMapper;
import com.blog.entities.Photo;

@Service
public class PhotoService {
	
	@Autowired
	private PhotoMapper photoMapper;
	
	public void addPhoto(Photo photo) {
		photoMapper.insertSelective(photo);
	}

	public List<Photo> getAllPhoto() {
		return photoMapper.selectByExample(null);	
	}

	public void deletePhoto(Integer photoId) {
		photoMapper.deleteByPrimaryKey(photoId);
	}
}
