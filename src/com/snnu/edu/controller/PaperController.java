package com.snnu.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.snnu.edu.entity.Papers;
import com.snnu.edu.serviceInterface.PaperService;
import com.snnu.edu.serviceInterface.impl.PaperServiceImpl;
import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("paper")
@SuppressWarnings("all")
public class PaperController {
	@RequestMapping
	public String add(Papers paper) {
		PaperService paperservice = new PaperServiceImpl();
		boolean flag = paperservice.saveOrUpdatePaper(paper);
		Map map = new HashMap();
		if(flag) {
			map.put("statusCode", "200");
			map.put("msg", "OK");
		}else {
			map.put("statusCode", "500");
			map.put("msg", "Server error");
		}
		return Tools.getJson(map);
	}
	
	@RequestMapping("list_user") 
	public String getCurrentUserPaper(int user_id) {
		PaperService paperservice = new PaperServiceImpl();
		List<Papers> list = paperservice.getPaperByUserId(user_id);
		Map map = new HashMap();
		if(list!=null) {
			map.put("papers", list);
			map.put("statusCode", "200");
			map.put("msg", "OK");
		}else {
			map.put("statusCode", "400");
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
	
	@RequestMapping("list_all")
	public String getAllPaper(){
		PaperService paperservice = new PaperServiceImpl();
		List<Papers> list = paperservice.getAllPaper();
		Map map = new HashMap();
		if(list!=null) {
			map.put("papers", list);
			map.put("statusCode", "200");
			map.put("msg", "OK");
		}else {
			map.put("statusCode", "400");
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
	
	@RequestMapping("update")
	public String updatePaper(Papers paper) {
		PaperService paperservice = new PaperServiceImpl();
		boolean flag = paperservice.saveOrUpdatePaper(paper);
		Map map = new HashMap();
		if(flag) {
			map.put("statusCode", "200");
			map.put("msg", "OK");
		}else {
			map.put("statusCode", "500");
			map.put("msg", "Server error");
		}
		return Tools.getJson(map);
	}
	
	@RequestMapping("submit_paper")
	public String submitPaper(int paper_id) {
		PaperService paperservice = new PaperServiceImpl();
		Papers paper = paperservice.getPaperById(paper_id);
		paper.setStatus(200);
		Map map = new HashMap();
		if(paper!=null) {
			map.put("statusCode", "200");
			map.put("msg", "OK");
		}else {
			map.put("statusCode", "400");
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}
}
