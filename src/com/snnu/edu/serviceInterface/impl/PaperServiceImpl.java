package com.snnu.edu.serviceInterface.impl;

import java.util.List;

import com.snnu.edu.dao.BaseDao;
import com.snnu.edu.entity.Papers;
import com.snnu.edu.serviceInterface.PaperService;
@SuppressWarnings("unchecked")
public class PaperServiceImpl implements PaperService {

	public boolean saveOrUpdatePaper(Papers paper) {
		try {
			BaseDao.saveOrUpdateObj(paper);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean delPaperInfo(Papers paper) {
		try {
			BaseDao.deleteObj(paper);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Papers getPaperById(Integer id) {
		return (Papers) BaseDao.getObject("from Papers where id = '" + id +"'");
	}

	public List<Papers> getPaperByUserId(Integer user_id) {
		return (List<Papers>) BaseDao.findWithPage("from Papers where user_id = '"+ user_id +"'");
	}
	
	public List<Papers> getAllPaper() {
		return (List<Papers>) BaseDao.findWithPage("from Papers");
	}

	public Papers getPaperByNumber(String paper_number) {
		return (Papers) BaseDao.getObject("from Papers where paper_number = " + paper_number +"");
	}

	@Override
	public List<Papers> getPaperByStatus(Integer status) {
		return (List<Papers>) BaseDao.findWithPage("from Papers where status = '"+ status +"'");
	}
}
