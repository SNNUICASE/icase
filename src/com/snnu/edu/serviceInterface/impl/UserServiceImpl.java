package com.snnu.edu.serviceInterface.impl;

import java.util.List;

import com.snnu.edu.dao.BaseDao;
import com.snnu.edu.entity.Users;
import com.snnu.edu.serviceInterface.UserService;

@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

	public boolean saveOrUpdateUsers(Users user) {
		try {
			BaseDao.saveOrUpdateObj(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean delUserInfo(Users user) {
		try {
			BaseDao.deleteObj(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Users> getUserByType(int type) {
		return (List<Users>) BaseDao.getObject("from Users where id='"+type+"'");
	} 

	public Users getUserById(int id) {
		return (Users) BaseDao.getObject("from Users where id='" + id + "'");
	}

	public List<Users> findWithPage() {
		return (List<Users>) BaseDao.findWithPage("from Users ");
	}
	@Override
	public boolean findIsExitUser(String name, String password) {
		Users user =  (Users) BaseDao.getObject("from Users where name="+name+" and password ="+password+"");
		if(user!=null){
			return true;
		}
		return false;
	}

}
