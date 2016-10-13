package com.snnu.edu.serviceInterface;

import java.util.List;

import com.snnu.edu.entity.Papers;

public interface PaperService {
	//添加论文或更新论文信息
	public  boolean saveOrUpdatePaper(Papers paper);
	//通过id删除该论文信息
	public  boolean delPaperInfo(Papers paper);
	//通过id查询该论文
	public  Papers getPaperById(Integer id);
	//通过用户id查询论文
	public List<Papers> getPaperByUserId(Integer user_id);
	//通过状态查询论文
	public List<Papers> getPaperByStatus(Integer status);
	//通过用户文章编号查询论文
	public Papers getPaperByNumber(String paper_number);
	//查询所有论文信息
	public List<Papers> getAllPaper();
}
