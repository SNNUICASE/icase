package com.snnu.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snnu.edu.dao.BaseDao;
import com.snnu.edu.entity.Authors;
import com.snnu.edu.entity.Papers;
import com.snnu.edu.serviceInterface.AuthorService;
import com.snnu.edu.serviceInterface.PaperService;
import com.snnu.edu.serviceInterface.impl.AuthorServiceImpl;
import com.snnu.edu.serviceInterface.impl.PaperServiceImpl;
import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("author")
@SuppressWarnings("all")
public class AuthorController {
	@RequestMapping("add")
	public String paperAddAuthor(int paper_id, Authors author) {
		PaperService paperservice = new PaperServiceImpl();
		Papers paper = paperservice.getPaperById(paper_id);
		author.setPaper(paper);
		Map map = new HashMap();
		if(paper!=null) {
			map.put("statusCode", 120);
			map.put("msg", "Add author successfully");
		}else {
			map.put("statusCode", 400);
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
	
	@RequestMapping("list_paper")
	public String getPaperAuthor(int paper_id) {
		AuthorService authorservice = new AuthorServiceImpl();
		List<Authors> list = authorservice.getAuthorByPaperId(paper_id);
		Map map = new HashMap();
		if(list!=null) {
			map.put("authors", list);
			map.put("statusCode", 200);
			map.put("msg", "OK");
		}else {
			map.put("statusCode", "400");
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
	
	@RequestMapping("list_all")
	public String getAllAuthor() {
		AuthorService authorservice = new AuthorServiceImpl();
		List<Authors> list = authorservice.getAllAuthor();
		Map map = new HashMap();
		if(list!=null) {
			map.put("authors", list);
			map.put("statusCode", 200);
			map.put("msg", "OK");
		}else {
			map.put("statusCode", "400");
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
}
