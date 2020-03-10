package com.transport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="blog")
public class AddBlog {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@NotNull
	@NotEmpty
	@Column(name="blog_title")
	private String blogTitle;
	@NotNull
	@NotEmpty
	@Column(name="blog_desc")
	private String blogDesc;
	@NotNull
	@NotEmpty
	@Column(name="blog_image")
	private String blogImage;
	@Column(name="created_date")
	private String createdDate;
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
