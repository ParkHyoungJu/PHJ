package com.acorn.dto;

public class MemberDto {
	//멤버필드
	private int empNo;
	private String eName;
	private String sal;
	private String hiredate;
	private String dName;
	private String loc;
	//디폴트 생성자
	public MemberDto(){}
	public MemberDto(int empNo, String eName, String sal, String hiredate, String dName, String loc) {
		super();
		this.empNo = empNo;
		this.eName = eName;
		this.sal = sal;
		this.hiredate = hiredate;
		this.dName = dName;
		this.loc = loc;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getSal() {
		return sal;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
}
