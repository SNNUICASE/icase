package com.snnu.edu.serviceInterface.impl;

import java.util.List;

import com.snnu.edu.dao.BaseDao;
import com.snnu.edu.entity.Authors;
import com.snnu.edu.entity.Papers;
import com.snnu.edu.serviceInterface.AuthorService;
@SuppressWarnings("unchecked")
public class AuthorServiceImpl implements AuthorService {

	public boolean saveOrUpdateAuthor(Authors author) {
		try {
			BaseDao.saveOrUpdateObj(author);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delAuthorInfo(Authors author) {
		try {
			BaseDao.deleteObj(author);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Authors getAuthorById(int id) {
		return (Authors) BaseDao.getObject("from Authors where id = '"+ id +"'");
	}

	public List<Authors> getAuthorByPaperId(int paper_id) {
		return (List<Authors>) BaseDao.getObject("from Authors where paper_id = '"+ paper_id +"'");
	}
	public List<Authors> getAllAuthor() {
		return (List<Authors>) BaseDao.getObject("from Authors");
	}
	

}
