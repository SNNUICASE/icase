package com.snnu.edu.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.snnu.edu.util.Tools;

@Controller
@RequestMapping("tools")
@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
public class ToolsController {

	@RequestMapping("upload")
	@ResponseBody
	public void upload(@RequestParam("file") CommonsMultipartFile file,HttpSession session)throws Exception {
		System.out.println("filename------->"+file.getOriginalFilename());
		if(!file.isEmpty()){
			try {
				String filename = new Date().getTime()+file.getOriginalFilename();
				String realPath = session.getServletContext().getRealPath("/WEB-INF/upload/");  			   
				FileOutputStream os = new FileOutputStream(realPath + filename);
				InputStream in = file.getInputStream();
				int b = 0;
				while((b=in.read()) != -1){
					os.write(b);
				}				
				os.flush();
				os.close();		
				in.close();				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("download")
	@ResponseBody
	public void download(@RequestParam("file") CommonsMultipartFile file)throws Exception {
		System.out.println("filename------->"+file.getOriginalFilename());		
		if(!file.isEmpty()){
			FileOutputStream fos = new FileOutputStream(file.getOriginalFilename());  
	        byte[] buffer = new byte[1024 * 1024];  
	        int bytesum = 0;  
	        int byteread = 0;  
	        InputStream in = file.getInputStream();
	        while ((byteread = in.read(buffer)) != -1) {  
	            bytesum += byteread;  
	            fos.write(buffer, 0, byteread);  
	            fos.flush();  
	        }  
	        fos.close();  
	        in.close();        
		}
	}
	@RequestMapping("sendEmail")
	@ResponseBody
	public String sendEmail(String from,String to,String content)throws Exception {
		boolean flag = Tools.sendEmail(from, to, content);
		HashMap map = new HashMap();
		if(flag){
			map.put("content", "");
			map.put("code",200);
			map.put("msg", "OK");
		}else{
			map.put("content", "");
			map.put("code",500);
			map.put("msg", "server error");
		}
		
		return Tools.getJson(map);
	}

}
