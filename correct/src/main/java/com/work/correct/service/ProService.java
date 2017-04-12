package com.work.correct.service;

import java.util.List;

import com.work.correct.model.Pro;

public interface ProService {
public int addPro(Pro pro);
public int deletePro(int id);
public int resetPro(Pro pro);
public List<Pro> getProByStuid(String stuid);
public List<Pro> getProByHomeid(int homeid);
public List<Pro> getProByClassid(int classid);
public List<Pro> getAll();
public Pro getPro(int id);
public List<Pro> getPros(Pro pro);
}
