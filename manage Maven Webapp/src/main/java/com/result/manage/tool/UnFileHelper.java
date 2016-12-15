package com.result.manage.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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

	public List<String> unFile(String zipPath) {
		List<String> fileList = new ArrayList<String>();
		if (zipPath.toLowerCase().endsWith(".zip")) {
			fileList = unZipFile(zipPath);
		} else if (zipPath.toLowerCase().endsWith(".rar")) {
			fileList = unRarFile(zipPath);
		} 
		return fileList;
	}
	// 解压rar文件并获得文件名
	private List<String> unRarFile(String rarPath)  {
		String parentPath = parentFolder(rarPath);
		List<String> fileList = new ArrayList<String>();
		String fileName;
		File rarFile = new File(rarPath);
		Archive archive = null;
		FileOutputStream outputStream=null;
		try {
			archive = new Archive(rarFile);
			if (archive != null) {
				List<FileHeader> fileHeaders = archive.getFileHeaders();
				for (int i = 0; i < fileHeaders.size(); i++) {
					if (fileHeaders.get(i) != null) {
						if (fileHeaders.get(i).isDirectory()) {
							fileName = fileHeaders.get(i).getFileNameString();
							File folder = new File(parentPath + File.separator + fileName);
							folder.mkdirs();
						} else {
							fileName = fileHeaders.get(i).getFileNameString().trim();
							File unFile = new File(parentPath + File.separator + fileName);
							if (!unFile.exists()) {
								if (!unFile.getParentFile().exists()) {
									unFile.getParentFile().mkdirs();
								}
							}
							try {
								outputStream = new FileOutputStream(unFile);
								archive.extractFile(fileHeaders.get(i), outputStream);
							} finally {
								if (outputStream!=null) {
									outputStream.close();
								}
							}
						}
						fileList.add(fileName);
					}
				}
			} 
		} catch (Exception e) {
		}finally {
			safeClose(archive, null);
		}
		return fileList;
	}

	// 解压zip文件并获得文件名
	private List<String> unZipFile(String zipPath) {
		String parentPath = parentFolder(zipPath);
		List<String> fileList = new ArrayList<String>();
		ZipFile zipFile = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			zipFile = new ZipFile(zipPath);
			for (Enumeration<?> entries = zipFile.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				if (entry.isDirectory()) {
					File folder = new File(parentPath + File.separator + zipEntryName);
					folder.mkdirs();
				} else {
					 try {
						inputStream = zipFile.getInputStream(entry);
						outputStream = new FileOutputStream(parentPath + File.separator + zipEntryName);
						byte[] byteBuffer = new byte[1024];
						int length;
						while ((length = inputStream.read(byteBuffer)) > 0) {
							outputStream.write(byteBuffer, 0, length);
						} 
					} finally {
						if (inputStream!=null) {
							inputStream.close();
						}
						if (outputStream!=null) {
							outputStream.close();
						}	
					}
				}
				fileList.add(zipEntryName);
			} 
		} catch (Exception e) {
		}finally {
		safeClose(null, zipFile);	
		}
		return fileList;
	}
//	安全释放资源
private void safeClose(Archive archive,ZipFile zipFile) {
	try {
		if (zipFile != null) {
			zipFile.close();
		}
		if (archive != null) {
			archive.close();
		} 
	} catch (IOException e) {
		zipFile=null;
		archive=null;
		System.gc();
	}
}
//创建父级目录
	private String parentFolder(String path) {
		String parentPath = path.substring(0, path.lastIndexOf("."));
		File parentFolder = new File(parentPath);
		if (!parentFolder.exists() && !parentFolder.isDirectory()) {
			parentFolder.mkdirs();
		}
		return parentPath;
	}
}
