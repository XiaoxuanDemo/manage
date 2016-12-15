package com.result.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.result.manage.dao.StudentMapper;
import com.result.manage.model.Student;
import com.result.manage.service.StudentService;
@Service("StudentService")
public class StudentServiceImpl implements StudentService {
@Resource
private StudentMapper studetDao;
	public Student findStudent(long id) {
		return this.studetDao.selectByPrimaryKey(id);
	}

}
