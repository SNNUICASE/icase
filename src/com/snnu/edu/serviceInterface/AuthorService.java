package com.snnu.edu.serviceInterface;

import java.util.List;

import com.snnu.edu.entity.Authors;

public interface AuthorService {
	//添加作者或更新作者信息
	public  boolean saveOrUpdateAuthor(Authors author);
	//通过id删除该作者信息
	public  boolean delAuthorInfo(Authors author);
	//通过id查询该作者
	public  Authors getAuthorById(int id);
	//通过论文id查询作者
	public List<Authors> getAuthorByPaperId(int paper_id);
	//查询所有作者信息
	public List<Authors> getAllAuthor();
}
