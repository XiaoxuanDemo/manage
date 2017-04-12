package com.work.correct.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.correct.mapper.TeacherMapper;
import com.work.correct.model.Teacher;
import com.work.correct.service.TeacherService;

@Service("TeacherService")
public class TeacherServiceImpl implements TeacherService {
	@Resource
	private TeacherMapper mapper;

	@Override
	public Teacher getTeacher(String teacherid) {
		return mapper.selectByPrimaryKey(teacherid);
	}

	@Override
	public int resetTeacher(Teacher teacher) {
		return mapper.updateByPrimaryKeySelective(teacher);
	}

	@Override
	public Teacher Login(String teacherid, String password) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(teacherid);
		
	}

}
