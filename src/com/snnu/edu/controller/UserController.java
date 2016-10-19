package com.snnu.edu.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
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
	public String login(String name, String password, String validateCode,
			HttpSession session) throws Exception {
		// 这里是验证用户名密码格式
		Users current_user = us.getUserBynameAndpassword(name, password);
		HashMap hashmap = new HashMap();
		if (current_user != null) {
			// if ( session.getAttribute("Constants.KAPTCHA_SESSION_KEY") ==
			// validateCode) {
			session.setAttribute("current_user", current_user);
			current_user.setPassword("");
			hashmap.put("content", current_user);
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
			// }
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
	public String findStatus(HttpSession session) throws Exception {
		Users user = (Users) session.getAttribute("current_user");
		HashMap hashmap = new HashMap();
		if (user != null) {
			user.setPassword("");
			hashmap.put("loginStatus", true);
			hashmap.put("content", user);
			hashmap.put("code", 200);
			hashmap.put("msg", "OK");
		} else {
			hashmap.put("loginStatus", false);
			hashmap.put("content", "");
			hashmap.put("code", 200);
			hashmap.put("msg", "not login");
		}
		return Tools.getJson(hashmap);
	}

	// 用户注册
	@RequestMapping("add")
	@ResponseBody
	public String add(Users user) throws Exception {
		// 验证用户名密码格式
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
	public String findInformation(Integer id, HttpSession session)
			throws Exception {
		Users user = null;
		if (id != null) {
			Users tem_user = us.getUserById(id);
			if (tem_user != null) {
				user = tem_user;
			}
		} else {
			user = (Users) session.getAttribute("current_user");
		}
		HashMap hashmap = new HashMap();
		if (user != null) {
			user.setPassword("");
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
	public String findAll() throws Exception {
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
	public String findSomeByType(Integer type) throws Exception {
		List<Users> users = us.getUserByType(type);
		HashMap hashmap = new HashMap();
		if (users.size() != 0) {
			hashmap.put("content", users);
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
	public String delete(Integer id) throws Exception {
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
	public String update(Users user) throws Exception {
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
