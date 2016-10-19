package com.snnu.edu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

	@RequestMapping("upload-single")
	@ResponseBody
	public void upload(@RequestParam("file") CommonsMultipartFile file,
			HttpSession session) throws Exception {
		System.out.println("filename------->" + file.getOriginalFilename());
		if (!file.isEmpty()) {
			try {
				String filename = new Date().getTime()
						+ file.getOriginalFilename();
				String realPath = session.getServletContext().getRealPath(
						"/WEB-INF/upload/");
				FileOutputStream os = new FileOutputStream(realPath + filename);
				InputStream in = file.getInputStream();
				int b = 0;
				while ((b = in.read()) != -1) {
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

	@RequestMapping("upload")
	public String threeFileUpload(
			@RequestParam("file") CommonsMultipartFile files[],
			HttpSession session) {

		List<String> list = new ArrayList<String>();
		// 获得项目的路径
		String path = session.getServletContext().getRealPath(
				"/WEB-INF/upload/");
		// 上传位置
		File f = new File(path);
		if (!f.exists())
			f.mkdirs();
		for (int i = 0; i < files.length; i++) {
			// 获得原始文件名
			String fileName = files[i].getOriginalFilename();
			System.out.println("原始文件名:" + fileName);
			// 新文件名
			String newFileName = new Date().getTime()
					+ files[i].getOriginalFilename();
			if (!files[i].isEmpty()) {
				try {
					FileOutputStream fos = new FileOutputStream(path
							+ newFileName);
					InputStream in = files[i].getInputStream();
					int b = 0;
					while ((b = in.read()) != -1) {
						fos.write(b);
					}
					fos.close();
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("上传文件到:" + path + newFileName);
			list.add(path + newFileName);

		}
		HashMap map = new HashMap();
		map.put("content", list);
		map.put("code", 200);
		map.put("msg", "OK");
		return Tools.getJson(map);
	}

	@RequestMapping("download")
	public String downFile(String fileName, HttpSession session,
			HttpServletResponse response) {
		HashMap map = new HashMap();
		System.out.println("1");
		try {
			fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
			System.out.println("3");
			// 获取上传文件的目录
			String fileSaveRootPath = session.getServletContext().getRealPath(
					"/WEB-INF/upload");
			System.out.println("4");
			System.out.println(fileSaveRootPath + "\\" + fileName);
			// 得到要下载的文件
			File file = new File(fileSaveRootPath + "\\" + fileName);

			// 如果文件不存在
			if (!file.exists()) {
				map.put("content", "");
				map.put("code", 400);
				map.put("msg", "您要下载的资源已被删除！！");
				System.out.println("您要下载的资源已被删除！！");
			}
			// 处理文件名
			String realname = fileName.substring(fileName.indexOf("_") + 1);
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode(realname, "UTF-8"));
			// 读取要下载的文件，保存到文件输入流
			FileInputStream in = new FileInputStream(fileSaveRootPath + "\\"
					+ fileName);
			// 创建输出流
			OutputStream out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			// 关闭文件输入流
			in.close();
			// 关闭输出流
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("content", "");
		map.put("code", 200);
		map.put("msg", "OK");
		return Tools.getJson(map);
	}

	@RequestMapping("sendEmail")
	@ResponseBody
	public String sendEmail(String from, String to, String content)
			throws Exception {
		boolean flag = Tools.sendEmail(from, to, content);
		HashMap map = new HashMap();
		if (flag) {
			map.put("content", "");
			map.put("code", 200);
			map.put("msg", "OK");
		} else {
			map.put("content", "");
			map.put("code", 500);
			map.put("msg", "server error");
		}

		return Tools.getJson(map);
	}

}
