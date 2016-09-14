package com.snnu.edu.serviceInterface;

import java.util.List;

import com.snnu.edu.entity.Papers;

public interface PaperService {
	//添加论文或更新论文信息
	public  boolean saveOrUpdatePaper(Papers paper);
	//通过id删除该论文信息
	public  boolean delPaperInfo(Papers paper);
	//通过id查询该论文
	public  Papers getPaperById(int id);
	//通过用户id查询论文
	public List<Papers> getPaperByUserId(int user_id);
	//查询所有论文信息
	public List<Papers> getAllPaper();
}
