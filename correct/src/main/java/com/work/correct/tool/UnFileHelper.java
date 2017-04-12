package com.work.correct.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

public class UnFileHelper {
	private static UnFileHelper helper = new UnFileHelper();

	private UnFileHelper() {
	}

	public static UnFileHelper getUnFileHelper() {
		return helper;
	}

	public Map<String, List<String>> unFiles(List<String> zipPaths) throws Exception {
		int length;
		Map<String, List<String>> map = null;
		if ((length = zipPaths.size()) > 0) {
			String zipPath = null;
			List<String> fileList = null;
			map = new HashMap<String, List<String>>();
			for (int i = 0; i < length; i++) {
				zipPath = zipPaths.get(i);
				fileList = unFile(zipPath);
				map.put(zipPath.substring(zipPath.lastIndexOf(File.separator) + 1), fileList);
			}
		}
		return map;
	}

	public List<String> unFile(String zipPath) throws Exception {
		List<String> fileList = new ArrayList<String>();
		if (zipPath != null) {
			if (zipPath.toLowerCase().endsWith(".zip")) {
				fileList = unZipFile(zipPath);
			} else if (zipPath.toLowerCase().endsWith(".rar")) {
				fileList = unRarFile(zipPath);
			} else {
				fileList.add("请上传rar文件或zip文件！");
			}
		} else {
			fileList.add("无文件上传！");
		}
		return fileList;
	}

	// 解压rar文件
	private List<String> unRarFile(String rarPath) throws Exception {
		String parentPath = parentFolder(rarPath);
		List<String> fileList = new ArrayList<String>();
		String fileName = null;
		File rarFile = new File(rarPath);
		Archive archive = null;
		FileOutputStream outputStream = null;
		File unFile = null;
		File folder = null;
		try {
			archive = new Archive(rarFile);
			if (archive != null) {
				List<FileHeader> fileHeaders = archive.getFileHeaders();
				for (int i = 0; i < fileHeaders.size(); i++) {
					if (fileHeaders.get(i) != null) {
						fileName = fileHeaders.get(i).getFileNameString().trim();
						if (fileHeaders.get(i).isDirectory()) {
							folder = new File(filePath(parentPath, fileName));
							folder.mkdirs();
							folder = null;
						} else {
							unFile = new File(filePath(parentPath, fileName));
							if (!unFile.exists()) {
								if (!unFile.getParentFile().exists()) {
									unFile.getParentFile().mkdirs();
								}
							}
							try {
								outputStream = new FileOutputStream(unFile);
								archive.extractFile(fileHeaders.get(i), outputStream);
							} finally {
								if (outputStream != null) {
									outputStream.close();
								}
								unFile = null;
							}
						}
						fileList.add(fileName);
					}
				}
			}
		} finally {
			archive.close();
		}
		return fileList;
	}

	// 解压zip文件
	private List<String> unZipFile(String zipPath) throws Exception {
		String parentPath = parentFolder(zipPath);
		List<String> fileList = new ArrayList<String>();
		ZipFile zipFile = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		ZipEntry entry = null;
		String zipEntryName = null;
		File folder = null;
		File unFile = null;
		String filePath = null;
		byte[] byteBuffer;
		int length;
		try {
			zipFile = new ZipFile(zipPath);
			for (Enumeration<?> entries = zipFile.getEntries(); entries.hasMoreElements();) {
				entry = (ZipEntry) entries.nextElement();
				zipEntryName = entry.getName();
				if (entry.isDirectory()) {
					folder = new File(filePath(parentPath, zipEntryName));
					folder.mkdirs();
					folder = null;
				} else {
					filePath = filePath(parentPath, zipEntryName);
					unFile = new File(filePath);
					if (!unFile.exists()) {
						if (!unFile.getParentFile().exists()) {
							unFile.getParentFile().mkdirs();
						}
					}
					unFile = null;
					try {
						inputStream = zipFile.getInputStream(entry);
						outputStream = new FileOutputStream(filePath);
						byteBuffer = new byte[1024];
						while ((length = inputStream.read(byteBuffer)) > 0) {
							outputStream.write(byteBuffer, 0, length);
						}
					} finally {
						if (inputStream != null) {
							inputStream.close();
						}
						if (outputStream != null) {
							outputStream.close();
						}
					}
				}
				fileList.add(zipEntryName);
			}
		} finally {
			zipFile.close();
		}
		return fileList;
	}

	// 拼接文件路径
	private String filePath(String parentPath, String fileName) {
		StringBuffer stringBuffer = new StringBuffer(parentPath);
		stringBuffer.append(File.separator);
		stringBuffer.append(fileName);
		return stringBuffer.toString();
	}

	// 创建父目录
	private String parentFolder(String path) {
		String parentPath = path.substring(0, path.lastIndexOf("."));
		File parentFolder = new File(parentPath);
		if (!parentFolder.exists() && !parentFolder.isDirectory()) {
			parentFolder.mkdirs();
		}
		return parentPath;
	}
}
