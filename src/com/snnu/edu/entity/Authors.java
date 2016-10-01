package com.snnu.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Authors {
	private Integer id;
	private String name;
	private String address;
	private String country;
	private String email;
	private String tel;
	private Integer type = 0;
	private Integer authors_order = 0;
	private Papers paper;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="_order")
	public Integer getAuthors_order() {
		return authors_order;
	}
	public void setAuthors_order(Integer authors_order) {
		this.authors_order = authors_order;
	}
	@ManyToOne
	public Papers getPaper() {
		return paper;
	}
	public void setPaper(Papers paper) {
		this.paper = paper;
	}
	public Authors() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Authors(String name, String address, String country, String email,
			String tel, Integer type, Integer authors_order) {
		super();
		this.name = name;
		this.address = address;
		this.country = country;
		this.email = email;
		this.tel = tel;
		this.type = type;
		this.authors_order = authors_order;
	}
	
}
