package com.snnu.edu.dao;

import java.util.List;
import org.hibernate.Session;

import com.snnu.edu.util.HibernateUtils;

public class BaseDao {

	
	public static void saveOrUpdateObj(Object obj){
		Session session=HibernateUtils.getSession();
		try {
			session.getTransaction().begin();
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
	}
	

	
	public static void deleteObj(Object obj){
		Session session=HibernateUtils.getSession();
		
		try {
			session.getTransaction().begin();
			session.delete(obj);  //delete 
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
	}
	
	

	public static  List<?>findWithPage(String HQL){
		Session session=HibernateUtils.getSession();
		List<?> list=null;
		try {
			list= session.createQuery(HQL).list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		
		
		return list;
		
	}
	
	public static Object getObject(String HQL){
		Session session=HibernateUtils.getSession();
		Object obj=session.createQuery(HQL).uniqueResult();
		HibernateUtils.closeSession(session);
		return obj;
	
	}
	
	

}
