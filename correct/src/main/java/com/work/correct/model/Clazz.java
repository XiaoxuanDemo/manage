package com.work.correct.model;

public class Clazz {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.classname
     *
     * @mbg.generated
     */
    private String classname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.curriculumname
     *
     * @mbg.generated
     */
    private String curriculumname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column class.stunum
     *
     * @mbg.generated
     */
    private Integer stunum;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.id
     *
     * @return the value of class.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.id
     *
     * @param id the value for class.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.classname
     *
     * @return the value of class.classname
     *
     * @mbg.generated
     */
    public String getClassname() {
        return classname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.classname
     *
     * @param classname the value for class.classname
     *
     * @mbg.generated
     */
    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.curriculumname
     *
     * @return the value of class.curriculumname
     *
     * @mbg.generated
     */
    public String getCurriculumname() {
        return curriculumname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.curriculumname
     *
     * @param curriculumname the value for class.curriculumname
     *
     * @mbg.generated
     */
    public void setCurriculumname(String curriculumname) {
        this.curriculumname = curriculumname == null ? null : curriculumname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column class.stunum
     *
     * @return the value of class.stunum
     *
     * @mbg.generated
     */
    public Integer getStunum() {
        return stunum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column class.stunum
     *
     * @param stunum the value for class.stunum
     *
     * @mbg.generated
     */
    public void setStunum(Integer stunum) {
        this.stunum = stunum;
    }
    private String teacherid;

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}
    
}