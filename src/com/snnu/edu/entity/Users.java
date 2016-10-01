package com.snnu.edu.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Users {
	private Integer id;
	private String name;
	private String password;
	private String tel;
	private String email;
	private String country;
	private String address;
	private Integer type = 0;
	private Date birthday;
	private Integer status = 0;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Users() {
		
	}
	public Users(String name, String password, String tel, String email,
			String country, String address, Integer type, Date birthday, Integer status) {	
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.country = country;
		this.address = address;
		this.type = type;
		this.birthday = birthday;
		this.status = status;
	}
	public Users(String name, String password) {		
		this.name = name;
		this.password = password;
	}
	public Users(String name, String password, String tel, String address) {
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.address = address;
	}
	public Users(String name, String password, String tel, String address,
			Integer status) {	
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.address = address;
		this.status = status;
	}
	
	
}
