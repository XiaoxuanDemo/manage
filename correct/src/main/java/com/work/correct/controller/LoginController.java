package com.work.correct.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.work.correct.model.Student;
import com.work.correct.model.Teacher;
import com.work.correct.service.StudentService;
import com.work.correct.service.TeacherService;
import com.work.correct.tool.BuildCodeHelper;

@Controller
public class LoginController {
	@Resource
	private StudentService stuService;
	@Resource
	private TeacherService teaService;
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public void buildeCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BuildCodeHelper helper = BuildCodeHelper.INSTANCE;
		helper.buildCode(request, response);
	}
	@RequestMapping(value="studentLogin",method=RequestMethod.POST)
	@ResponseBody
	public Object studentLogin(String stuid,String password){
		Map ret=new HashMap();
		if (stuid==null||stuid.equals("")) {
			ret.put("message","用户名为空");
			return ret;
		}
		if(password==null||password.equals("")){
			ret.put("message", "密码为空");
			return ret;
		}
		Student stu = stuService.getStudent(stuid);
		if(stu.getPassword().equals(password)){
			ret.put("message", "登录成功");
			ret.put("student", stu);
		}else {
			ret.put("message", "密码错误");
		}
		return ret;
	}
	/**
	 * 教师登录
	 * @param teacherid
	 * @param password
	 * @return
	 */
	@RequestMapping(value="teacherLogin",method=RequestMethod.POST)
	@ResponseBody
	public Object teacherLogin(String teacherid,String password){
		Map ret=new HashMap();
		if(teacherid==null||teacherid.equals("")){
			ret.put("message","用户名为空");
			return ret;
		}
		if(password==null||password.equals("")){
			ret.put("message", "密码为空");
			return ret;
		}
		Teacher login = teaService.Login(teacherid, password);
		if(login.getPassword().equals(password)){
			ret.put("message","success");
			ret.put("teacher", login);
		}else{
			ret.put("message", "fail");
		}
		return ret;
	}
}
