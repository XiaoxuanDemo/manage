package com.work.correct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.correct.model.Clazz;
import com.work.correct.model.HomeWork;
import com.work.correct.model.Pro;
import com.work.correct.model.Student;
import com.work.correct.model.Teacher;
import com.work.correct.service.ClazzService;
import com.work.correct.service.HomeWorkService;
import com.work.correct.service.ProService;
import com.work.correct.service.StudentService;
import com.work.correct.service.TeacherService;
import com.work.correct.tool.StringUitls;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	private <K, V> Map<K, V> getMap() {
		return new HashMap<K, V>();
	}
	
	private ModelAndView check(HttpSession session) {
		ModelAndView mView = null;
		if (session.getAttribute("teaid") == null) {
			Map<String, String> model = getMap();
			model.put("error", "无此权限!");
			mView = new ModelAndView("index", model);
		}
		return mView;
	}
	@Resource
	private TeacherService teaService;
	@Resource
	private ProService proService;
	/**
	 * 教师批阅作业
	 * @param teacherid教师ID
	 * @param password密码
	 * @param proid作业id
	 * @param teacherresult教师评语
	 * @param score成绩
	 * @param isread是否已经阅读 0 未阅读 1手机批阅 2 电脑批阅
	 * @return
	 */
	public Object readPro(String teacherid,String password,Integer proid,String teacherresult,Integer score,Integer isread){
		Map m=new HashMap();
		if(teacherid==null||teacherid.equals("")){
			m.put(StringUitls.MSG, StringUitls.TEACHERID_NULL);
			return m;
		}
		if(password==null||password.equals("")){
			m.put(StringUitls.MSG, StringUitls.PASSWORD_NULL);
			return m;
		}
		if(proid==null){
			m.put(StringUitls.MSG, StringUitls.PROID_NULL);
			return m;
		}
		if(teacherresult==null||teacherresult.equals("")){
			m.put(StringUitls.MSG, "评语为空");
			return m;
		}
		if(score==null){
			m.put(StringUitls.MSG, "成绩为空");
		}
		Teacher teacher = teaService.getTeacher(teacherid);
		if(!teacher.getPassword().equals(password)){
			m.put(StringUitls.MSG, StringUitls.PASSWORD_FAIL);
			return m;
		}else{
			Pro pro = proService.getPro(proid);
			pro.setScore(score);
			pro.setIsread(isread);
			pro.setTeacherresult(teacherresult);
			if(proService.resetPro(pro)>0){
				m.put(StringUitls.MSG, StringUitls.UPDATE_SUCCESS);
				m.put("data",pro);
				return m;
			}else{
				m.put(StringUitls.MSG, StringUitls.UPDATE_FAIL);
				return m;
			}
		}
		
	}
	
}
