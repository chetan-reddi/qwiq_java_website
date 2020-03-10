package com.transport.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Blog {

	private long blogId;
	@NotNull
	@NotEmpty
	private String blogTitle;
	@NotNull
	@NotEmpty
	private String blogDesc;
	@NotNull
	@NotEmpty
	private String blogImage;
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogDesc() {
		return blogDesc;
	}
	public void setBlogDesc(String blogDesc) {
		this.blogDesc = blogDesc;
	}
	public String getBlogImage() {
		return blogImage;
	}
	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}
	
}
