package com.snnu.edu.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snnu.edu.entity.Users;
import com.snnu.edu.serviceInterface.UserService;
import com.snnu.edu.serviceInterface.impl.UserServiceImpl;
import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("user")
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class UserController {
	UserService us = new UserServiceImpl();

	// 用户登录
	@RequestMapping("login")
	@ResponseBody
	public String login(String name, String password,String validateCode, HttpSession session)
			throws Exception {
		boolean flag = us.findIsExitUser(name, password);
		HashMap hashmap = new HashMap();
		if (flag) {
			Users current_user = us.getUserBynameAndpassword(name, password);
			if (current_user != null) {
				session.setAttribute("current_user", current_user);
				hashmap.put("content", current_user);
				hashmap.put("code", 200);
				hashmap.put("msg", "OK");
			}
		} else {
			hashmap.put("content", "");
			hashmap.put("code", 400);
			hashmap.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(hashmap);
	}

	// 用户注销
	@RequestMapping("logout")
	@ResponseBody
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("current_user");
		HashMap hashmap = new HashMap();
		if (session.getAttribute("current_user") == null) {
			hashmap.put("content", "");
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("content", "");
			hashmap.put("code", 200);
			hashmap.put("msg", "fail to logout");
		}
		return Tools.getJson(hashmap);
	}

	// 用户登陆状态的查询
	@RequestMapping("login_status")
	@ResponseBody
	public String findStatus(HttpSession session) {
		Users user = (Users) session.getAttribute("current_user");
		HashMap hashmap = new HashMap();
		if (user != null) {
			hashmap.put("loginStatus", true);
			hashmap.put("user", user);
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("loginStatus", false);
			hashmap.put("code", 200);
			hashmap.put("msg", "not login");
		}
		return Tools.getJson(hashmap);
	}

	// 用户注册
	@RequestMapping("add")
	@ResponseBody
	public String add(Users user) {
		boolean flag = us.saveOrUpdateUsers(user);
		HashMap hashmap = new HashMap();
		if (flag) {
			hashmap.put("content", "");
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("content", "");
			hashmap.put("code", 200);
			hashmap.put("msg", "fail to add");
		}
		return Tools.getJson(hashmap);
	}

	// 查询当前用户的所有信息
	@RequestMapping("information")
	@ResponseBody
	public String findInformation(Integer id,HttpSession session) {
		Users user = (Users) session.getAttribute("current_user");
		HashMap hashmap = new HashMap();
		if (user != null) {
			hashmap.put("content", user);
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("code", 400);
			hashmap.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(hashmap);
	}

	// 查询所有用户信息
	@RequestMapping("list_all")
	@ResponseBody
	public String findAll() {
		List<Users> users = us.findWithPage();
		HashMap hashmap = new HashMap();
		if (users.size() != 0) {
			hashmap.put("content", users);
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("content", "");
			hashmap.put("code", 400);
			hashmap.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(hashmap);
	}

	// 查询各类型用户的信息
	@RequestMapping("list_by_type")
	@ResponseBody
	public String findSomeByType(Integer type) {
		List<Users> users = us.getUserByType(type);
		HashMap hashmap = new HashMap();
		if (users.size() != 0) {
			hashmap.put("status", users);
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("code", 400);
			hashmap.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(hashmap);
	}

	// 删除用户
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Integer id) {
		HashMap hashmap = new HashMap();
		Users user = us.getUserById(id);
		if (user != null) {
			boolean flag = us.delUserInfo(user);
			if (flag) {
				hashmap.put("code", 200);
				hashmap.put("msg", "OK");
			} else {
				hashmap.put("code", 500);
				hashmap.put("msg", "Server error");
			}
		} else {
			hashmap.put("code", 400);
			hashmap.put("msg", "Resource is not found not exist");
		}

		return Tools.getJson(hashmap);
	}

	// 更新用户信息
	@RequestMapping("update")
	@ResponseBody
	public String update(Users user) {
		HashMap hashmap = new HashMap();
		boolean flag = us.saveOrUpdateUsers(user);
		if (flag) {
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("code", 500);
			hashmap.put("msg", "Server error");
		}
		return Tools.getJson(hashmap);
	}
}
