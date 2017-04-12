package com.work.correct.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.correct.mapper.StudentMapper;
import com.work.correct.model.Student;
import com.work.correct.service.StudentService;

@Service("StudentService")
public class StudentServiceImpl implements StudentService {
	@Resource
	private StudentMapper mapper;

	@Override
	public Student getStudent(String stuid) {
		Student student = null;
		student = mapper.selectByPrimaryKey(stuid);
		return student;
	}

	@Override
	public int addStudent(Student student) {
		return mapper.insertSelective(student);
	}

	@Override
	public int deleteStudent(String stuid) {
		return mapper.deleteByPrimaryKey(stuid);
	}

	@Override
	public int resetStudent(Student student) {
		return mapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public List<Student> getAll() {
		return mapper.selectAll();
	}

	@Override
	public Student getStudentByname(String name) {
		// TODO Auto-generated method stub
		Student student = mapper.selectStuByname(name);
		System.out.println(student);
		return student;
	}
	
}
