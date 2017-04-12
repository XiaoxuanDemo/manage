package com.work.correct.service;

import java.util.List;

import com.work.correct.model.Clazz;

public interface ClazzService {
	/**
	 * 添加一个班级
	 * 
	 * @param clazz
	 * @return
	 */
	public int addClazz(Clazz clazz);

	/**
	 * 删除一个班级
	 * 
	 * @param id
	 * @return
	 */
	public int deleteClazz(Integer id);

	/**
	 * 更新一个班级
	 * 
	 * @param clazz
	 * @return
	 */
	public int resetClazz(Clazz clazz);

	/**
	 * 获得一个班级
	 * 
	 * @param name
	 * @return
	 */
	public List<Clazz> getClazz(Clazz clazz);

	/**
	 * 得到所有班级
	 * 
	 * @return
	 */
	public List<Clazz> getAll();
}
