package com.work.correct.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.correct.mapper.ClazzMapper;
import com.work.correct.model.Clazz;
import com.work.correct.service.ClazzService;
@Service("ClazzService")
public class ClazzServiceImpl implements ClazzService{
@Resource
private ClazzMapper mapper;
	@Override
	public int addClazz(Clazz clazz) {
		return mapper.insert(clazz);
	}

	@Override
	public int deleteClazz(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int resetClazz(Clazz clazz) {
		return mapper.updateByPrimaryKeySelective(clazz);
	}

	@Override
	public List<Clazz> getClazz(Clazz clazz) {
		return mapper.selectClazz(clazz.getClassname(), clazz.getId(), clazz.getCurriculumname());
	}

	@Override
	public List<Clazz> getAll() {
		return mapper.selectAll();
	}

}
