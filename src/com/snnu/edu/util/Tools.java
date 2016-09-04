package com.snnu.edu.util;

import com.google.gson.Gson;

public class Tools {

public static String getJson(Object obj){
	try {
		Gson gson = new Gson() ;
		return gson.toJson(obj);
	
	} catch (Exception e) {
		e.printStackTrace();
	}	
	return null;
}
}