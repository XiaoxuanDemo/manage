package com.work.correct.service;

import java.util.List;

import com.work.correct.model.StuClass;

public interface StuClassService {
public int addStuClass(StuClass stuClass);
public int resetStuClass(StuClass stuClass);
public int deleteStuClass(int id);
public StuClass getStuClass(String stuid);
public List<StuClass> getStuClassByClassid(Integer classid);
}
