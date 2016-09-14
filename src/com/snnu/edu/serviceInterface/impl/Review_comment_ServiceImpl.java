package com.snnu.edu.serviceInterface.impl;

import java.util.List;

import com.snnu.edu.dao.BaseDao;
import com.snnu.edu.entity.Review_Comment;
import com.snnu.edu.entity.Users;
import com.snnu.edu.serviceInterface.Review_comment_Service;

@SuppressWarnings("unchecked")
public class Review_comment_ServiceImpl implements Review_comment_Service {
	
	@Override
	public boolean saveOrUpdateComment(Review_Comment review_comment) {
		try {
			BaseDao.saveOrUpdateObj(review_comment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();			
		}		
		return false;
		
	}

	@Override
	public boolean delComment(Review_Comment review_comment) {
		try {
			BaseDao.deleteObj(review_comment);
			return true;
		} catch (Exception e) {
			e.printStackTrace();		
		}		
		return false;		
	}

	@Override
	public Review_Comment getCommentById(int id) {
		return (Review_Comment)BaseDao.getObject("from Review_Comment where id='"+id+"'");
	} 

	public  List<Review_Comment>findWithPage(){
		return (List<Review_Comment>)BaseDao.findWithPage("from Review_Comment ");
	}


}
