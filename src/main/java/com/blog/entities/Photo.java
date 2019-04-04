package com.blog.entities;

public class Photo {
	private Integer photoId;

	private String url;

	private Integer albumId;

	private String photoDesc;

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Photo(String url, String photoDesc) {
		super();
		this.url = url;
		this.photoDesc = photoDesc;
	}

	@Override
	public String toString() {
		return "Photo [photoId=" + photoId + ", url=" + url + ", albumId="
				+ albumId + ", photoDesc=" + photoDesc + "]";
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getPhotoDesc() {
		return photoDesc;
	}

	public void setPhotoDesc(String photoDesc) {
		this.photoDesc = photoDesc == null ? null : photoDesc.trim();
	}
}