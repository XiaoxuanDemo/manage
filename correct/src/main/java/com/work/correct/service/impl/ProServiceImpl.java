package com.work.correct.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.correct.mapper.ProMapper;
import com.work.correct.model.Pro;
import com.work.correct.service.ProService;
@Service("ProService")
public class ProServiceImpl implements ProService{
@Resource
private ProMapper mapper;
	@Override
	public int addPro(Pro pro) {
		return mapper.insert(pro);
	}

	@Override
	public int deletePro(int id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int resetPro(Pro pro) {
		return mapper.updateByPrimaryKeySelective(pro);
	}

	@Override
	public List<Pro> getProByStuid(String stuid) {
		return mapper.selectByStuid(stuid);
	}

	@Override
	public List<Pro> getProByHomeid(int homeid) {
		return mapper.selectByHomeid(homeid);
	}

	@Override
	public List<Pro> getProByClassid(int classid) {
		return mapper.selectByClassid(classid);
	}

	@Override
	public List<Pro> getAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

	@Override
	public Pro getPro(int id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Pro> getPros(Pro pro) {
		// TODO Auto-generated method stub
		return mapper.selectPros(pro.getStuid(), pro.getHomeid(), pro.getClassid());
	}
    
}
