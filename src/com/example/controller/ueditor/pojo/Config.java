package com.example.controller.ueditor.pojo;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private String imageUrl;
	private String imagePath;
	private String imageUrlPrefix = "";
	private String imageActionName = "uploadimage";
	private String imageFieldName = "upfile";
	private Integer imageMaxSize = 2048;

	@SuppressWarnings("serial")
	private List<String> imageAllowFiles = new ArrayList<String>() {
		{
			this.add(".png");
			this.add(".jpg");
			this.add(".jpeg");
			this.add(".gif");
			this.add(".bmp");
		}
	};

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageUrlPrefix() {
		return imageUrlPrefix;
	}

	public void setImageUrlPrefix(String imageUrlPrefix) {
		this.imageUrlPrefix = imageUrlPrefix;
	}

	public String getImageActionName() {
		return imageActionName;
	}

	public void setImageActionName(String imageActionName) {
		this.imageActionName = imageActionName;
	}

	public String getImageFieldName() {
		return imageFieldName;
	}

	public void setImageFieldName(String imageFieldName) {
		this.imageFieldName = imageFieldName;
	}

	public Integer getImageMaxSize() {
		return imageMaxSize;
	}

	public void setImageMaxSize(Integer imageMaxSize) {
		this.imageMaxSize = imageMaxSize;
	}

	public List<String> getImageAllowFiles() {
		return imageAllowFiles;
	}

	public void setImageAllowFiles(List<String> imageAllowFiles) {
		this.imageAllowFiles = imageAllowFiles;
	}

}
