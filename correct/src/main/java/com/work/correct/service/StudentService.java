package com.work.correct.service;

import java.util.List;

import com.work.correct.model.Student;

public interface StudentService {
	/**
	 * 添加一个学生
	 * 
	 * @param student
	 * @return
	 */
	public int addStudent(Student student);

	/**
	 * 删除一个学生
	 * 
	 * @param stuid
	 * @return
	 */
	public int deleteStudent(String stuid);

	/**
	 * 更新一个学生
	 * 
	 * @param student
	 * @return
	 */
	public int resetStudent(Student student);

	/**
	 * 查询一个学生
	 * 
	 * @param stuid
	 * @return
	 */
	public Student getStudent(String stuid);

	/**
	 * 获得所有学生
	 * 
	 * @return
	 */
	public List<Student> getAll();
	/**
	 * 通过姓名查找学生
	 * @param name
	 * @return
	 */
	public Student getStudentByname(String name);

}
