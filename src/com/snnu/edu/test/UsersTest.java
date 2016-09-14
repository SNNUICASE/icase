package com.snnu.edu.test;

import com.snnu.edu.entity.Users;
import com.snnu.edu.serviceInterface.UserService;
import com.snnu.edu.serviceInterface.impl.UserServiceImpl;

public class UsersTest {
public static void main(String[] args) {
	UserService userservice = new UserServiceImpl();
	Users user = new Users("dfff","1111");
	userservice.saveOrUpdateUsers(user);
}
}
