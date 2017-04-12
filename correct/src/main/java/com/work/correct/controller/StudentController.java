package com.work.correct.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.work.correct.model.StuClass;
import com.work.correct.model.Student;
import com.work.correct.service.StuClassService;
import com.work.correct.service.StudentService;
import com.work.correct.tool.StringUitls;

import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
@RequestMapping("/student")
@Controller
public class StudentController {
	@Resource
	private StudentService stuservice;
	@Resource
	private StuClassService sclzs;
	/**
	 * 添加学生
	 * @param stuid
	 * @param stuname
	 * @return
	 */
	@RequestMapping(value="/addStu",method=RequestMethod.POST)
	@ResponseBody
	public Object addStuden(String stuid,String stuname,int classid){
		Map ret=new HashMap();
		Student stu = new Student();
		stu.setStuid(stuid);
		stu.setPassword("123456");
		stu.setStuname(stuname);
		int i = stuservice.addStudent(stu);
		if(i>0){
			StuClass stuClass = new StuClass();
			stuClass.setClassid(classid);
			stuClass.setStuid(stuid);
			int j = sclzs.addStuClass(stuClass);
			if(j>0){
				ret.put(StringUitls.MSG, StringUitls.INSERT_SUCCESS);
			}
			else{
				ret.put(StringUitls.MSG, StringUitls.INSERT_FAIL);
			}
		}else
		{
			ret.put(StringUitls.MSG, StringUitls.INSERT_FAIL);
		}
		return ret;
	}
	/**
	 * 删除学生
	 * @param stuid
	 * @return
	 */
	@RequestMapping(value="/deleteStu",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteStu(String stuid){
		Map m=new HashMap();
		StuClass stuClass = sclzs.getStuClass(stuid);
		if(stuClass!=null){
			sclzs.deleteStuClass(stuClass.getId());
		}
		int student = stuservice.deleteStudent(stuid);
		if(student>0){
			m.put(StringUitls.MSG,StringUitls.DELETE_SUCCESS);
		}
		else{
			m.put(StringUitls.MSG,StringUitls.DELETE_FAIL);
		}
		return m;
	}
	/**
	 * 查找学生
	 * @param stuid
	 * @return
	 */
	@RequestMapping(value="/selectstubyid",method=RequestMethod.GET)
	@ResponseBody
	public Student selectStuByid(String stuid){
		String string = JSON.toJSONString(stuservice.getStudent(stuid));
		System.out.println(string);
		return stuservice.getStudent(stuid);
	}
	/**+
	 * 通过名字查找学生
	 * @param stuname
	 * @return
	 */
	@RequestMapping(value="/selectstubyname",method=RequestMethod.GET)
	@ResponseBody
	public Student selectStuByname(String stuname){
		System.out.println(stuname);
		return stuservice.getStudentByname(stuname);
	}
	@RequestMapping(value="/selectall",method=RequestMethod.GET)
	@ResponseBody
	public List<Student> selectall(){
		return stuservice.getAll();
	}
	/**
	 * 学生修改密码
	 * @param stuid
	 * @param oldpwd
	 * @param newpwd
	 * @return
	 */
	@RequestMapping(value="/stuChange",method=RequestMethod.POST)
	@ResponseBody
	public Object updateStudent(String stuid,String oldpwd,String newpwd){
		Map m=new HashMap();
		if(stuid==null||stuid.equals("")){
			m.put(StringUitls.MSG, StringUitls.STUID_NULL);
			return m;
		}
		if(oldpwd==null||oldpwd.equals("")){
			m.put(StringUitls.MSG, StringUitls.PASSWORD_NULL);
			return m;
		}
		if(newpwd==null||newpwd.equals("")){
			m.put(StringUitls.MSG, "新密码为空");
			return m;
		}
		Student student = stuservice.getStudent(stuid);
		if(student==null){
			m.put(StringUitls.MSG, StringUitls.STUDENT_NOTFOUND);
			return m;
		}
		if(student.getPassword().equals(oldpwd)){
			student.setPassword(newpwd);
			int num = stuservice.resetStudent(student);
			if(num>0){
				m.put("data", student);
				m.put(StringUitls.MSG,StringUitls.UPDATE_SUCCESS);
				return m;
			}else{
				m.put(StringUitls.MSG,StringUitls.UPDATE_FAIL);
				return m;
			}
		}else{
			m.put(StringUitls.MSG, StringUitls.PASSWORD_FAIL);
			return m;
		}
	}
	

}
