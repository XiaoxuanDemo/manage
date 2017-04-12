package com.work.correct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.work.correct.model.Clazz;
import com.work.correct.model.StuClass;
import com.work.correct.model.Student;
import com.work.correct.model.StudentClass;
import com.work.correct.service.ClazzService;
import com.work.correct.service.StuClassService;
import com.work.correct.service.StudentService;
import com.work.correct.tool.StringUitls;

@Controller
public class StuClassController {
	private static final String msg="message";
@Resource
private StuClassService service;
@Resource
private ClazzService clzs;
/**
 * 添加一个班级
 * @param classname 班级名字
 * @param curriculumname 课程名字
 * @param stunum 选课人数
 * @return
 */
	@RequestMapping(value="/addClass",method=RequestMethod.POST)
	@ResponseBody
	public Object addClass(String classname,String curriculumname,Integer stunum){
		Map m=new HashMap();
		if(classname==null||classname.equals("")){
			m.put(msg, "班级名为空");
			return m;
		}
		if(curriculumname==null||curriculumname.equals("")){
			m.put(msg, "课程名为空");
			return m;
		}
		if(stunum==null||stunum<=0){
			m.put(msg, "选课人数不正确");
			return m;
		}
		Clazz clazz = new Clazz();
		clazz.setClassname(classname);
		clazz.setCurriculumname(curriculumname);
		clazz.setStunum(stunum);
		if(clzs.addClazz(clazz)>0){
			m.put("class", clazz);
			m.put(msg, "添加成功");
		}else {
			m.put(msg, "添加失败");
		}
		return m;
	}
	@RequestMapping(value="/getAllClass",method=RequestMethod.GET)
	@ResponseBody
	public Object getAllClazz(){
		Map m=new HashMap();
		List<Clazz> all = clzs.getAll();
		m.put(StringUitls.MSG,StringUitls.SELECT_SUCCESS);
		m.put("data", all);
		return m;
	}
	/**
	 * 学生选课
	 * @param stuid 学号
	 * @param classid 班级ID
	 * @return
	 */
	@RequestMapping(value="/selectClass",method=RequestMethod.POST)
	@ResponseBody
	public Object selectClass(String stuid,Integer classid){
		Map m=new HashMap();
		if(stuid==null||stuid.equals("")){
			m.put(msg, "学号为空");
			return m;
		}
		if(classid==null||classid<0){
			m.put(msg, "班级号为空");
			return m;
		}
		StuClass stuclazz = new StuClass();
		stuclazz.setStuid(stuid);
		stuclazz.setClassid(classid);
		if(service.getStuClass(stuid)==null){
			if(service.addStuClass(stuclazz)>0){
				m.put(msg, "选课成功");
				m.put("stuclass", stuclazz);
			}else {
				m.put(msg, "选课失败");
			}
		}else{
			m.put(msg, "当前记录已经存在");
		}
				
		return m;
	}
	/**
	 * 删除学生选课
	 * @param stuid
	 * @param classid
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/deleteClass",method=RequestMethod.POST)
	@ResponseBody
	public Object delectClass(String stuid,Integer classid,String password){
		Map m=new HashMap();
		if(stuid==null||stuid.equals("")){
			m.put(msg, "学号为空");
			return m;
		}
		if(classid==null||classid<0){
			m.put(msg, "班级号为空");
			return m;
		}
		if(password==null||password.equals("")){
			m.put(msg, StringUitls.PASSWORD_NULL);
		}
		StuClass stuClass = service.getStuClass(stuid);
		if(stuClass!=null){
			if(service.deleteStuClass(stuClass.getId())>0){
				m.put(msg, "删除成功");
				m.put("stuclass", stuClass);
			}else {
				m.put(msg, "删除失败");
			}
		}else {
			m.put(msg, "未查找到选课信息");
		}
		return m;
	}
	/**
	 * 通过班级号查询学生选课信息
	 * @param classid
	 * @return
	 */
	@RequestMapping(value="/selectstcbyid",method=RequestMethod.POST)
	@ResponseBody
	public Object selectStuClassByClassid(Integer classid){
		Map m=new HashMap();
		List<StuClass> list = service.getStuClassByClassid(classid);
		m.put(msg, StringUitls.SELECT_SUCCESS);
		m.put("data", list);
		return m;
	}
	@Resource
	private StudentService stus;
	@RequestMapping(value="/selectStuClass",method=RequestMethod.GET)
	@ResponseBody
	public Object selectAllStuClass(){
		Map m=new HashMap();
		m.put(StringUitls.MSG,StringUitls.SELECT_SUCCESS);
		List<StudentClass> list=new ArrayList<StudentClass>();
		List<Student> all = stus.getAll();
		for (int i = 0; i < all.size(); i++) {
			Student stu = all.get(i);
			StuClass stuClass = service.getStuClass(stu.getStuid());
			StudentClass stcl = new StudentClass();
			stcl.setStuid(stu.getStuid());
			stcl.setStuname(stu.getStuname());
			if(stuClass!=null){
				Clazz clz = new Clazz();
				clz.setId(stuClass.getClassid());
				List<Clazz> clazz = clzs.getClazz(clz);
				if(clazz.size()!=0){
					clz=clazz.get(0);
					stcl.setClassid(clz.getId());
					stcl.setClasscontent(clz.getCurriculumname());
					stcl.setClassname(clz.getClassname());
				}
				
			}
			list.add(stcl);
		}
		m.put("data", list);
		return m;
	}
}
