package com.work.correct.model;

public class Student {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.stuid
     *
     * @mbg.generated
     */
    private String stuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.stuname
     *
     * @mbg.generated
     */
    private String stuname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.stuid
     *
     * @return the value of student.stuid
     *
     * @mbg.generated
     */
    public String getStuid() {
        return stuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.stuid
     *
     * @param stuid the value for student.stuid
     *
     * @mbg.generated
     */
    public void setStuid(String stuid) {
        this.stuid = stuid == null ? null : stuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.password
     *
     * @return the value of student.password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.password
     *
     * @param password the value for student.password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.stuname
     *
     * @return the value of student.stuname
     *
     * @mbg.generated
     */
    public String getStuname() {
        return stuname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.stuname
     *
     * @param stuname the value for student.stuname
     *
     * @mbg.generated
     */
    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", password=" + password + ", stuname=" + stuname + "]";
	}
    
}