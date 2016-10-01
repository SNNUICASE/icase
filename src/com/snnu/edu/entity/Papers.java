package com.snnu.edu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Papers {
	private Integer id;
	private String paper_number;
	private Integer type = 0;
	private String title;
	private String paper_abstract;
	private Date submit_time;
	private Integer classification = 0;
	private Integer status = 100;
	private Users user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getpaper_number() {
		return paper_number;
	}
	public void setpaper_number(String paper_number) {
		this.paper_number = paper_number;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="abstract")
	public String getPaper_abstract() {
		return paper_abstract;
	}
	public void setPaper_abstract(String paper_abstract) {
		this.paper_abstract = paper_abstract;
	}
	public Date getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}
	public Integer getClassification() {
		return classification;
	}
	public void setClassification(Integer classification) {
		this.classification = classification;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@ManyToOne
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Papers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Papers(String paper_number, Integer type, String title,
			String paper_abstract, Date submit_time, Integer classification,
			Integer status) {
		super();
		this.paper_number = paper_number;
		this.type = type;
		this.title = title;
		this.paper_abstract = paper_abstract;
		this.submit_time = submit_time;
		this.classification = classification;
		this.status = status;
	}
	
	
}
