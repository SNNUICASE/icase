package com.snnu.edu.serviceInterface;

import java.util.List;

import com.snnu.edu.entity.Review_Comment;


public interface Review_comment_Service {

	public  boolean saveOrUpdateComment(Review_Comment review_comment);	
	
	public  boolean delComment(Review_Comment review_comment);
	
	public  Review_Comment getCommentById(Integer id);
	
	public  List<Review_Comment>findWithPage();
}
