package com.result.manage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.result.manage.tool.UnFileHelper;
import com.result.manage.tool.UploadFileHelper;
@Controller
public class UploadFileController {
@ResponseBody
@RequestMapping("/SaveFile")
public List<String> saveFiles(@RequestParam CommonsMultipartFile file) throws Exception
{
	UploadFileHelper helper=UploadFileHelper.getUploadFileHelper();
	UnFileHelper unHelper=UnFileHelper.getUnFileHelper();
	return unHelper.unFile(helper.saveFile(file));
}
}
