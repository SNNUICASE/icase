package com.snnu.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class AuthorController {
	AuthorService authorservice = new AuthorServiceImpl();
	PaperService paperservice = new PaperServiceImpl();
	@RequestMapping("add")
	@ResponseBody
	public String paperAddAuthor(Integer paper_id, Authors author) {
		boolean flag = authorservice.saveOrUpdateAuthor(author);
		Map map = new HashMap();
		if(flag){
			Papers paper = paperservice.getPaperById(paper_id);
			if(paper!=null) {
				author.setPaper(paper);
				map.put("code", 200);
				map.put("msg", "OK");
			}else {
				map.put("code", 400);
				map.put("msg", "Resource is not found not exist");
			}			 			
		}	
		return Tools.getJson(map);
	}
	
	@RequestMapping("list_paper")
	@ResponseBody
	//通过文章查询作者
	public String getPaperAuthor(Integer paper_id) {		
		List<Authors> list = authorservice.getAuthorByPaperId(paper_id);
		Map map = new HashMap();
		if(list!=null) {
			map.put("authors", list);
			map.put("code", 200);
			map.put("msg", "OK");
		}else {
			map.put("code", 400);
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
	
	@RequestMapping("list_all")
	@ResponseBody
	public String getAllAuthor() {
		AuthorService authorservice = new AuthorServiceImpl();
		List<Authors> list = authorservice.getAllAuthor();
		Map map = new HashMap();
		if(list!=null) {
			map.put("authors", list);
			map.put("code", 200);
			map.put("msg", "OK");
		}else {
			map.put("code", 400);
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
}
