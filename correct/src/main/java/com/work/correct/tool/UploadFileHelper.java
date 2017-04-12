package com.work.correct.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class UploadFileHelper {
	private String rootPath = getClass().getResource("/").getFile().toString()
			.replace("WorkCorrection/WEB-INF/classes/", "StudentFiles/");
	private String realPath=null;
	public  void setPath(int classid){
		realPath=rootPath+classid;
	}
	private static UploadFileHelper helper = new UploadFileHelper();

	private UploadFileHelper() {
	}

	public static UploadFileHelper getUploadFileHelper() {
		return helper;
	}

	// input名称不相同多文件上传
	public List<String> saveFiles(HttpServletRequest request)
			throws IllegalStateException, IOException {
		List<String> list = new ArrayList<String>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = mRequest.getFileNames();
			String fileName = null;
			String path = null;
			File saveFile = null;
			MultipartFile file = null;
			while (iter.hasNext()) {
				file = mRequest.getFile(iter.next());
				if (file != null) {
					fileName = file.getOriginalFilename();
					if (fileName.trim() != "") {
						checkDir(realPath);
						path = filePath(realPath, file.getOriginalFilename());
						saveFile = new File(path);
						file.transferTo(saveFile);
						list.add(path);
						file = null;
					}
				}
			}
		}
		return list;
	}

	// 多文件上传
	public List<String> saveFiles(CommonsMultipartFile[] files)
			throws IOException {
		List<String> list = new ArrayList<String>();
		if (files.length > 0) {
			File saveFile = null;
			String path = null;
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isEmpty()) {
					checkDir(realPath);
					path = filePath(realPath, files[i].getOriginalFilename());
					saveFile = new File(path);
					files[i].transferTo(saveFile);
					list.add(path);
					saveFile = null;
				}
			}
		}
		return list;
	}

	// 单文件上传
	public String saveFile(CommonsMultipartFile file) throws IOException {
		checkDir(realPath);
		String path = filePath(realPath, file.getOriginalFilename());
		File saveFile = new File(path);
		file.transferTo(saveFile);
		return path;
	}

	// 拼接字符串
	private String filePath(String parentPath, String fileName) {
		StringBuffer stringBuffer = new StringBuffer(parentPath);
		stringBuffer.append(File.separator);
		stringBuffer.append(fileName);
		return stringBuffer.toString();
	}

	// 检查文件夹是否存在不存在就创建
	private void checkDir(String path) {
		File dirFile = new File(path);
		if (!dirFile.exists() && !dirFile.isDirectory()) {
			dirFile.mkdirs();
		}
	}
}
