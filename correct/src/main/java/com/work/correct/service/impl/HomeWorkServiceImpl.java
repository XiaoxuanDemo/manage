package com.work.correct.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.correct.mapper.HomeWorkMapper;
import com.work.correct.model.HomeWork;
import com.work.correct.service.HomeWorkService;
@Service("HomeWorkService")
public class HomeWorkServiceImpl implements HomeWorkService{
    @Resource
    private HomeWorkMapper mapper;
	@Override
	public int addHomeWork(HomeWork homeWork) {
		return mapper.insert(homeWork);
	}

	@Override
	public int deleteHomeWork(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int resetHomeWork(HomeWork homeWork) {
		return mapper.updateByPrimaryKeySelective(homeWork);
	}

	@Override
	public List<HomeWork> getHomeWork(Integer classid) {
		return mapper.selectByPrimaryKey(classid);
	}

	@Override
	public List<HomeWork> getAll() {
		return mapper.selectAll();
	}

	@Override
	public List<HomeWork> selectByname(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByname(name);
	}

}
