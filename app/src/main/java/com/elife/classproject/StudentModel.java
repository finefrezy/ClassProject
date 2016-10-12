package com.elife.classproject;

public class StudentModel {
	private String stuName;
	private String stuSno;
	private String stuPwd;
	private String gender;
	private int age;
	private String avatar;
	
	public StudentModel() {
		super();
	}
	public StudentModel(String stuName, String stuSno, String stuPwd, String gender, int age, String avatar) {
		super();
		this.stuName = stuName;
		this.stuSno = stuSno;
		this.stuPwd = stuPwd;
		this.gender = gender;
		this.age = age;
		this.avatar = avatar;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSno() {
		return stuSno;
	}
	public void setStuSno(String stuSno) {
		this.stuSno = stuSno;
	}
	public String getStuPwd() {
		return stuPwd;
	}
	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


}
