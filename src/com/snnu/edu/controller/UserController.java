package com.snnu.edu.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snnu.edu.entity.Users;
import com.snnu.edu.serviceInterface.UserService;
import com.snnu.edu.serviceInterface.impl.UserServiceImpl;
import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("user")
@SuppressWarnings("unchecked")
public class UserController {
	UserService us = new UserServiceImpl();
	//用户登录
	@RequestMapping("login")
	public String login(String name,String password){					
		boolean flag = us.findIsExitUser(name, password);
		HashMap hashmap = new HashMap();
		if(flag){			
			hashmap.put("statusCode", 200);
			hashmap.put("msg", "OK");		
		}else{
			hashmap.put("statusCode", 400);
			hashmap.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(hashmap);
	}
   //用户登陆状态的查询
	@RequestMapping("login_status")
	public String findStatus(int id){
	Users user = us.getUserById(id);
	HashMap hashmap = new HashMap();
	if(user!=null){		
		hashmap.put("status", user.getStatus());
		hashmap.put("statusCode", 200);
		hashmap.put("msg", "OK");		
	}else{
		hashmap.put("statusCode", 400);
		hashmap.put("msg", "Resource is not found not exist");
	}
	return Tools.getJson(hashmap);
	}
	//用户注册
	@RequestMapping("add")
	public String add(Users user){
		boolean flag = us.saveOrUpdateUsers(user);
		HashMap hashmap = new HashMap();
		if(flag){			
			hashmap.put("statusCode", 200);
			hashmap.put("msg", "OK");		
		}else{
			hashmap.put("statusCode", 500);
			hashmap.put("msg", "Server error");
		}
		return Tools.getJson(hashmap);
	}
	//查询当前用户的所有信息
		@RequestMapping("information")
		public String findInformation(int id){
		Users user = us.getUserById(id);
		HashMap hashmap = new HashMap();
		if(user!=null){		
			hashmap.put("status", user);
			hashmap.put("statusCode", 200);
			hashmap.put("msg", "OK");		
		}else{
			hashmap.put("statusCode", 400);
			hashmap.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(hashmap);
		}
		//查询所有用户信息
				@RequestMapping("list_all")
				public String findAll(){
				List<Users> users = us.findWithPage();
				HashMap hashmap = new HashMap();
				if(users.size()!=0){
					hashmap.put("status", users);
					hashmap.put("statusCode", 200);
					hashmap.put("msg", "OK");		
				}else{
					hashmap.put("statusCode", 400);
					hashmap.put("msg", "Resource is not found not exist");
				}
				return Tools.getJson(hashmap);
				}
				//查询各类型用户的信息
				@RequestMapping("list_by_type")
				public String findSomeByType(int type){
				List<Users> users = us.getUserByType(type);
				HashMap hashmap = new HashMap();
				if(users.size()!=0){
					hashmap.put("status", users);
					hashmap.put("statusCode", 200);
					hashmap.put("msg", "OK");		
				}else{
					hashmap.put("statusCode", 400);
					hashmap.put("msg", "Resource is not found not exist");
				}
				return Tools.getJson(hashmap);
				}
				//删除用户
				@RequestMapping("delete")
				public String delete(int id){
					HashMap hashmap = new HashMap();
					Users user = us.getUserById(id);
					if(user!=null){
						boolean flag = us.delUserInfo(user);						
						if(flag){			
							hashmap.put("statusCode", 200);
							hashmap.put("msg", "OK");		
						}else{
							hashmap.put("statusCode", 500);
							hashmap.put("msg", "Server error");
						}
					}else{
						hashmap.put("statusCode", 400);
						hashmap.put("msg", "Resource is not found not exist");
					}
					
					return Tools.getJson(hashmap);
				}
				//更新用户信息
				@RequestMapping("update")
				public String update(Users user){
					HashMap hashmap = new HashMap();
					boolean flag = us.saveOrUpdateUsers(user);
					if(flag){
							hashmap.put("statusCode", 200);
							hashmap.put("msg", "OK");		
					}else{
							hashmap.put("statusCode", 500);
							hashmap.put("msg", "Server error");
						}					
					return Tools.getJson(hashmap);
				}
}
