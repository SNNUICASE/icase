package com.snnu.edu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import org.junit.Test;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;  
import org.springframework.mail.javamail.JavaMailSenderImpl;  

import com.google.gson.Gson;

@SuppressWarnings("unused")
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
@Test
public static String getPaper_number() {
	SimpleDateFormat foo = new SimpleDateFormat("yyyyMMddhhmmss");
	System.out.println(foo.format(new Date()));
	return foo.format(new Date());
	
}
@Test
	public static Date getTime() {
		Calendar cal = Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		return cal.getTime();
	}

public static boolean sendEmail(String from,String to,String content){
	 JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();  
     // 设定mail server  
     senderImpl.setHost("smtp.163.com");  
     // 建立邮件消息  
     SimpleMailMessage mailMessage = new SimpleMailMessage();  
     // 设置收件人，寄件人 用数组发送多个邮件  
     // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};  
     // mailMessage.setTo(array);  
     mailMessage.setTo(to);  
     mailMessage.setFrom(from);  
     mailMessage.setSubject(" ICASE期刊通知！ ");  
     mailMessage.setText(content);  

     senderImpl.setUsername("aym_fuhong@163.com"); // 根据自己的情况,设置username  
     senderImpl.setPassword("fh180236"); // 根据自己的情况, 设置password  

     Properties prop = new Properties();  
     prop.put(" mail.smtp.auth ", " true "); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
     prop.put(" mail.smtp.timeout ", " 25000 ");  
     senderImpl.setJavaMailProperties(prop);  
     // 发送邮件  
     try {
		senderImpl.send(mailMessage);  
		 System.out.println(" 邮件发送成功.. ");
		 return true;
	} catch (MailException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	return false;
}
public static void main(String[] args) {
	sendEmail("aym_fuhong@163.com","aymfuhong@gmail.com"," 测试我的简单邮件发送机制！！ ");
}
}