package com.snnu.edu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snnu.edu.entity.Authors;
import com.snnu.edu.entity.Papers;
import com.snnu.edu.entity.Users;
import com.snnu.edu.serviceInterface.PaperService;
import com.snnu.edu.serviceInterface.UserService;
import com.snnu.edu.serviceInterface.impl.PaperServiceImpl;
import com.snnu.edu.serviceInterface.impl.UserServiceImpl;
import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("paper")
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class PaperController {
	PaperService paperservice = new PaperServiceImpl();
	UserService us = new UserServiceImpl();

	@RequestMapping("add")
	@ResponseBody
	public String add(Papers paper) {
		String paper_number = Tools.getPaper_number();
		Date submit_time = Tools.getTime();
		paper.setpaper_number(paper_number);
		paper.setSubmit_time(submit_time);
		boolean flag = paperservice.saveOrUpdatePaper(paper);
		Map map = new HashMap();
		if (flag) {
			Papers current_paper = paperservice.getPaperByNumber(paper
					.getpaper_number());
			if (current_paper != null) {
				map.put("content", current_paper);
				map.put("code", 200);
				map.put("msg", "OK");
			} else {
				map.put("content", "");
				map.put("code", 500);
				map.put("msg", "server error");
			}
		} else {
			map.put("content", "");
			map.put("code", 200);
			map.put("msg", "add fail");
		}
		return Tools.getJson(map);
	}

	@RequestMapping("list_review")
	@ResponseBody
	// 根据用户不同类型查询相应状态的文章，返回list<papers>
	public String reviewPaper(HttpSession session) {
		Users user = (Users) session.getAttribute("current_user");
		// 有待改进
		List<Papers> papers = paperservice.getPaperByStatus(user.getStatus());
		Map map = new HashMap();
		if (papers.size() >= 0) {
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

	@RequestMapping("list_user")
	@ResponseBody
	public String getCurrentUserPaper(Integer userId, HttpSession session) {
		List<Papers> papers = new ArrayList();
		if (userId != null) {
			papers = paperservice.getPaperByUserId(userId);
		} else {
			Users user = (Users) session.getAttribute("current_user");
		//	System.out.println("session中用户：" + user.getId());
		//	papers = paperservice.getPaperByUserId(user.getId());
		}
		System.out.println("list论文集合：" + papers);
		Map map = new HashMap();
		if (papers.size() >= 0 ) {
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

	@RequestMapping("list_all")
	@ResponseBody
	public String getAllPaper() {
		List<Papers> papers = paperservice.getAllPaper();
		Map map = new HashMap();
		if (papers.size() >= 0) {
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
//更改文章的状态
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