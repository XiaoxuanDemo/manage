package com.result.manage.tool;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.result.manage.com.warehouse.Paths;

public class UploadFileHelper {
	private static UploadFileHelper helper = new UploadFileHelper();

	private UploadFileHelper() {
	}

	public static UploadFileHelper getUploadFileHelper() {
		return helper;
	}

	// input名称不同多文件上传
	public void saveFiles(HttpServletRequest request) throws IllegalStateException, IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = mRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = mRequest.getFile(iter.next());
				if (file != null) {
					String fileName = file.getOriginalFilename();
					if (fileName.trim() != "") {
						checkDir(Paths.BASE_PATH);
						String path = Paths.BASE_PATH + "\\" + fileName;
						System.out.print(path);
						File saveFile = new File(path);
						file.transferTo(saveFile);
					}
				}
			}
		}
	}

	// 多文件上传
	public void saveFiles(CommonsMultipartFile[] files) throws IOException {
		if (files.length>0) {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isEmpty()) {
					checkDir(Paths.BASE_PATH);
					String path = Paths.BASE_PATH +"\\" + files[i].getOriginalFilename();
					System.out.println(path);
					File saveFile = new File(path);
					files[i].transferTo(saveFile);
				}
			}
		}
	}

	// 单文件上传
	public String saveFile(CommonsMultipartFile file) throws IOException {
		checkDir(Paths.BASE_PATH);
		String path = Paths.BASE_PATH + "\\" + file.getOriginalFilename();
		File saveFile = new File(path);
		file.transferTo(saveFile);
		return path;
	}

	// 检查文件夹是否存在不存在则创建
	private void checkDir(String path) {
		File dirFile = new File(path);
		if (!dirFile.exists() && !dirFile.isDirectory()) {
			dirFile.mkdir();
		}
	}
}
