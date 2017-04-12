package com.work.correct.mapper;

import java.util.List;

import com.work.correct.model.StuClass;

public interface StuClassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stuclass
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stuclass
     *
     * @mbg.generated
     */
    int insert(StuClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stuclass
     *
     * @mbg.generated
     */
    int insertSelective(StuClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stuclass
     *
     * @mbg.generated
     */
    StuClass selectByPrimaryKey(String stuid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stuclass
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StuClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stuclass
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StuClass record);
    List<StuClass> selectStuClassByClassId(Integer classid);
}