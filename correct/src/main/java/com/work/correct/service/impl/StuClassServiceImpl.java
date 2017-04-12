package com.work.correct.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.correct.mapper.StuClassMapper;
import com.work.correct.model.StuClass;
import com.work.correct.service.StuClassService;
@Service("StuClassService")
public class StuClassServiceImpl implements StuClassService{
@Resource
private StuClassMapper mapper;
	@Override
	public int addStuClass(StuClass stuClass) {
		return mapper.insert(stuClass);
	}

	@Override
	public int resetStuClass(StuClass stuClass) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(stuClass);
	}

	@Override
	public int deleteStuClass(int id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public StuClass getStuClass(String stuid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(stuid);
	}

	@Override
	public List<StuClass> getStuClassByClassid(Integer classid) {
		// TODO Auto-generated method stub
		return mapper.selectStuClassByClassId(classid);
	}

}
