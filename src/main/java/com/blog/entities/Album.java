package com.blog.entities;

import java.util.Date;
import java.util.List;

public class Album {
	private Integer albumId;

	private String name;

	private Date createTime;

	private List<Photo> photos;

	public Album() {
		super();
	}

	public Album(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", name=" + name + ", createTime="
				+ createTime + ", photos=" + photos + "]";
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
}