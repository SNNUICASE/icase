package com.snnu.edu.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

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
	public String upload(@RequestParam("file") CommonsMultipartFile file)throws Exception {
		System.out.println("filename------->"+file.getOriginalFilename());
		if(!file.isEmpty()){
			try {
				String filename = new Date().getTime()+file.getOriginalFilename();
				FileOutputStream os = new FileOutputStream("/WebRoot/files/" + filename);
				InputStream in = file.getInputStream();
				int b = 0;
				while((b=in.read()) != -1){
					os.write(b);
				}
				HashMap hashmap = new HashMap();
				hashmap.put("content",filename);
				os.flush();
				os.close();		
				in.close();
				return Tools.getJson(hashmap);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	@RequestMapping("download")
	@ResponseBody
	public String download()throws Exception {
		
		return null;
	}
}
