package com.work.correct.service;

import com.work.correct.model.Teacher;

public interface TeacherService {
public Teacher getTeacher(String teacherid);
public int resetTeacher(Teacher teacher);
public Teacher Login(String teacherid,String password);
}
