package com.work.correct.service;

import java.util.List;

import com.work.correct.model.HomeWork;

public interface HomeWorkService {
	public int addHomeWork(HomeWork homeWork);

	public int deleteHomeWork(Integer id);

	public int resetHomeWork(HomeWork homeWork);

	public List<HomeWork> getHomeWork(Integer classid);

	public List<HomeWork> getAll();
	public List<HomeWork> selectByname(String name);
}
