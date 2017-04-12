package com.work.correct.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.work.correct.model.HomeWork;
import com.work.correct.model.Pro;
import com.work.correct.service.HomeWorkService;
import com.work.correct.service.ProService;
import com.work.correct.tool.StringUitls;


@Controller
public class FileController {
	@Resource
	private HomeWorkService homeService;
	@Resource
	private ProService proService;
	private static final String FILEPATH = System.getProperty("evan.webapp")+File.separator+"homework";
	/**
	 * 上传作业
	 * @param stuid
	 * @param workname
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/uploadHome")
	@ResponseBody
	public Object upLoadHomework(String stuid,String workname,@RequestParam(value="file")MultipartFile file){
		Map m=new HashMap();
		if(stuid==null||stuid.equals("")){
			m.put("message", "学号为空");
			return m;
		}
		if(workname==null||workname.equals("")){
			m.put("message", StringUitls.HOMEWORKNAME_NULL);
			return m;
		}
		if(file==null){
			m.put("message", "文件为空");
			return m;
		}
		List<HomeWork> list = homeService.selectByname(workname);
		if(list.size()==0){
			m.put(StringUitls.MSG,"当前作业未发布");
			return m;
		}
		if(saveFile(file,stuid,list.get(0))){
			m.put("message", "上传成功");
		}else{
			m.put("message", "文件写入失败");
		}
		return m;
	}
	private boolean saveFile(MultipartFile file,String stuid,HomeWork work) {
		// TODO Auto-generated method stub
		String filename = file.getOriginalFilename();
		filename=work.getId()+"-"+filename;
		File root = new File(FILEPATH,stuid);
		if(!root.exists()){
			root.mkdirs();
		}
		File content = new File(root,filename);
		if(content.exists()){
			return false;
		}
		try {
			file.transferTo(content);
			Pro p = new Pro();
			p.setClassid(work.getClassid());
			p.setCreatetime(System.currentTimeMillis()+"");
			p.setFiles("/homework/"+stuid+"/"+content.getName());
			System.out.println("/homework/"+stuid+"/"+content.getName());
			p.setHomeid(work.getId());
			p.setIsread(2);
			p.setScore(0);
			p.setStuid(stuid);
			if(proService.addPro(p)>0){
				return true;
			}
			//TODO 存入数据库
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return false;
	}
	/**
	 * 
	 * @param homeid
	 * @param stuid
	 * @param classid
	 * @return
	 */
	@RequestMapping(value="/getAllpro",method=RequestMethod.GET)
	@ResponseBody
	public Object getAllPro(Integer homeid,String stuid,Integer classid){
		System.out.println("homeid="+homeid);
		System.out.println("stuid="+stuid);
		System.out.println("classid"+classid);
		Map m=new HashMap();
		Pro pro = new Pro();
		pro.setHomeid(homeid);
		pro.setClassid(classid);
		pro.setStuid(stuid);
		List<Pro> all = proService.getPros(pro);
		m.put(StringUitls.MSG, StringUitls.SELECT_SUCCESS);
		m.put("data", all);
		return m;
	}
	@RequestMapping(value="/downfile",method=RequestMethod.GET)
	@ResponseBody
	public Object download(String filepath){
		File file = new File(filepath+"/"+filepath);
		System.out.println("文件路径"+file.getAbsolutePath());
		if(file.exists()){
			HttpHeaders headers = new HttpHeaders();
			 headers.setContentDispositionFormData("attachment", file.getName());   
		     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
		     try {
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
				         headers, HttpStatus.CREATED);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}  
		}else {
			return "文件不存在";
		}
		return file;
	}
	@RequestMapping(value="/deletepro")
	public Object deletePro(String teacherid,String password,Integer homeid){
		Map m=new HashMap();
		if(teacherid==null||teacherid.endsWith("")){
			m.put(StringUitls.MSG, StringUitls.TEACHERID_NULL);
			return m;
		}
		if(password==null||password.equals("")){
			m.put(StringUitls.MSG, StringUitls.PASSWORD_NULL);
			return m;
		}
		return m;
	}
	
}
