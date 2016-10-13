package com.snnu.edu.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review_Comment {
	private Integer id;
	private String comment_number;
	private String content;
	private Date create_time;
	private Date submit_time;
	private Users user;
	private Papers paper;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getComment_number() {
		return comment_number;
	}
	public void setComment_number(String comment_number) {
		this.comment_number = comment_number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}
	@ManyToOne
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	@ManyToOne
	public Papers getPaper() {
		return paper;
	}
	public void setPaper(Papers paper) {
		this.paper = paper;
	}
	public Review_Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review_Comment(String content, Date create_time, Date submit_time) {
		super();
		this.content = content;
		this.create_time = create_time;
		this.submit_time = submit_time;
	}
	
	
}
