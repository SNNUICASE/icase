package com.snnu.edu.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static SessionFactory factory=null;
	static{
		try {
			Configuration cfg=new Configuration().configure();
			factory=cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public static Session  getSession(){
		return factory.openSession();	
	}
	
	public static void closeSession(Session session){
		if(session!=null){
			session.close();
		}
	}


}
