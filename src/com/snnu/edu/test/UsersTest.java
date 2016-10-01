package com.snnu.edu.test;

import java.util.List;

import com.snnu.edu.entity.Papers;
import com.snnu.edu.serviceInterface.PaperService;
import com.snnu.edu.serviceInterface.impl.PaperServiceImpl;

public class UsersTest {
public static void main(String[] args) {
	PaperService ps = new PaperServiceImpl();
	List<Papers> papers = ps.getPaperByUserId(1);
	System.out.println(papers);
}
}
