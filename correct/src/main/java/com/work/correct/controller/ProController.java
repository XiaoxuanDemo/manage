package com.work.correct.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.work.correct.model.HomeWork;
import com.work.correct.model.Pro;
import com.work.correct.model.StuClass;
import com.work.correct.service.HomeWorkService;
import com.work.correct.service.ProService;
import com.work.correct.service.StuClassService;
import com.work.correct.service.StudentService;
import com.work.correct.tool.StringUitls;
import com.work.correct.tool.UnFileHelper;
import com.work.correct.tool.UploadFileHelper;
/**
 * 作业相关
 * @author 10789
 *
 */
@Controller
@RequestMapping("/stupro")
public class ProController {
	@Resource
	private ProService proService;
	@Resource
	private StuClassService scService;
	@Resource
	private HomeWorkService homeService;
	/**
	 * 发布作业
	 * @param workname
	 * @param workcontent
	 * @param lasttime
	 * @param classid
	 * @return
	 */
	@RequestMapping(value="/addhomework",method=RequestMethod.POST)
	@ResponseBody
	public Object addWork(String workname,String workcontent,String lasttime,Integer classid){
		Map m=new HashMap();
		HomeWork work = new HomeWork();
		if(classid==null||classid<0){
			m.put(StringUitls.MSG, StringUitls.CLASS_NULL);
			return m;
		}
		if(lasttime==null||lasttime.equals("")){
			m.put(StringUitls.MSG, StringUitls.HOMEWORKLASTTIME_NULL);
			return m;
		}
		if(workcontent==null||workcontent.equals("")){
			m.put(StringUitls.MSG, StringUitls.HOMEWORKCONTENT_NULL);
			return m;
		}
		if(workname==null||workname.equals("")){
			m.put(StringUitls.MSG, StringUitls.HOMEWORKNAME_NULL);
			return m;
		}
		work.setClassid(classid);
		work.setLasttime(lasttime);
		work.setWorkname(workname);
		work.setWorkcontent(workcontent);
		if(homeService.addHomeWork(work)>0){
			m.put(StringUitls.MSG, StringUitls.INSERT_SUCCESS);
			m.put("data", work);
		}else{
			m.put(StringUitls.MSG, StringUitls.INSERT_FAIL);
		}
		return m;
	}
	/**
	 * 通过查找作业
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/selectHomeByname",method=RequestMethod.POST)
	@ResponseBody
	public Object selectHomeWorkByname(String name){
		Map m=new HashMap();
		if(name==null||name.equals("")){
			m.put(StringUitls.MSG, StringUitls.HOMEWORKNAME_NULL);
			return m;
		}
		List<HomeWork> list = homeService.selectByname(name);
		m.put(StringUitls.MSG, StringUitls.SELECT_SUCCESS);
		m.put("data", list);
		return m;
	}
	@Resource
	private StuClassService stuService;
	/**
	 * 查询作业
	 * @param classid 班级ID
	 * @param stuid 学号
	 * @return
	 */
	@RequestMapping(value="/selectHome",method=RequestMethod.POST)
	@ResponseBody
	public Object selectHome(Integer classid,String stuid){
		Map m=new HashMap();
		m.put(StringUitls.MSG,StringUitls.SELECT_SUCCESS);
		if(classid!=null){
			List<HomeWork> work = homeService.getHomeWork(classid);
			m.put("data", work);
			return m;
		}
		if(stuid!=null){
			Integer classid2 = stuService.getStuClass(stuid).getClassid();
			List<HomeWork> work = homeService.getHomeWork(classid2);
			m.put("data", work);			
			return m;
		}
	
		return m;
		
	}
}
