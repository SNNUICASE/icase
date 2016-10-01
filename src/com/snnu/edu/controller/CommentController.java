package com.snnu.edu.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snnu.edu.entity.Review_Comment;
import com.snnu.edu.serviceInterface.Review_comment_Service;
import com.snnu.edu.serviceInterface.impl.Review_comment_ServiceImpl;
import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("comment")
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class CommentController {
	Review_comment_Service cs = new Review_comment_ServiceImpl();
	//添加评价
	@RequestMapping("add")
	@ResponseBody
	public String add(Review_Comment rc){					
		boolean flag = cs.saveOrUpdateComment(rc);
		HashMap hashmap = new HashMap();
		if(flag){			
			hashmap.put("code", 200);
			hashmap.put("msg", "create successful");		
		}else{
			hashmap.put("code", 500);
			hashmap.put("msg", "Server error");
		}
		return Tools.getJson(hashmap);
	}
	//填写评价
		@RequestMapping("write")
		@ResponseBody
		public String update(String content,Integer id){
			Review_Comment rc = cs.getCommentById(id);
			rc.setContent(content);
			boolean flag = cs.saveOrUpdateComment(rc);
			HashMap hashmap = new HashMap();
			if(flag){			
				hashmap.put("code", 300);
				hashmap.put("msg", "complete");		
			}else{
				hashmap.put("code", 500);
				hashmap.put("msg", "Server error");
			}
			return Tools.getJson(hashmap);
		}
		
   //查询所有评价
	@RequestMapping("list_all")
	@ResponseBody
	public String findAllComment(){
	List<Review_Comment> rc = cs.findWithPage();
	HashMap hashmap = new HashMap();
	if(rc.size()!=0){		
		hashmap.put("status", rc);
		hashmap.put("code", 200);
		hashmap.put("msg", "OK");		
	}else{
		hashmap.put("code", 500);
		hashmap.put("msg", "Server error");
	}
	return Tools.getJson(hashmap);
	}
	
}
