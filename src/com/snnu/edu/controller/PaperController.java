package com.snnu.edu.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.snnu.edu.entity.Papers;
import com.snnu.edu.entity.Users;
import com.snnu.edu.serviceInterface.PaperService;
import com.snnu.edu.serviceInterface.impl.PaperServiceImpl;
import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("paper")
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class PaperController {
	PaperService paperservice = new PaperServiceImpl();

	@RequestMapping("add")
	@ResponseBody
	public String add(Papers paper) {
		boolean flag = paperservice.saveOrUpdatePaper(paper);
		Map map = new HashMap();
		if (flag) {
			map.put("content", paper);//有问题，要修改
			map.put("code", 200);
			map.put("msg", "OK");
		} else {
			map.put("content", "");
			map.put("code", 200);
			map.put("msg", "add fail");
		}
		return Tools.getJson(map);
	}

	
	@RequestMapping("list_review")
	@ResponseBody
	public String reviewPaper() {
		//根据用户不同status值查询相应状态的文章，返回list<papers>
		return null;
	}

	@RequestMapping("list_user")
	@ResponseBody
	public String getCurrentUserPaper(Integer id,HttpSession session) {
		Users user = (Users) session.getAttribute("current_user");
		System.out.println("++++++++++++" + user.getId());
		List<Papers> papers = paperservice.getPaperByUserId(user.getId());
		Map map = new HashMap();
		//if (papers != null) {
			map.put("content", papers);
			map.put("code", 200);
			map.put("msg", "OK");
		//} else {
		//	map.put("code", 400);
		//	map.put("msg", "Resource is not found not exist");
		//}
		return Tools.getJson(map);
	}

	@RequestMapping("list_all")
	@ResponseBody
	public String getAllPaper() {
		List<Papers> papers = paperservice.getAllPaper();
		Map map = new HashMap();
		if (papers != null) {
			map.put("content", papers);
			map.put("code", 200);
			map.put("msg", "OK");
		} else {
			map.put("content", "");
			map.put("code", 500);
			map.put("msg", "Server error");
		}
		return Tools.getJson(map);
	}

	@RequestMapping("update_paper_status")
	@ResponseBody
	public String updatePaper(Integer paperId, Integer status) {
		Papers paper = paperservice.getPaperById(paperId);
		Map map = new HashMap();
		if (paper != null) {
			paper.setStatus(status);
			map.put("content", paper);
			map.put("code", 200);
			map.put("msg", "OK");
		} else {
			map.put("content", "");
			map.put("code", 400);
			map.put("msg", "Resource is not found not exist");
		}
		return Tools.getJson(map);
	}

}