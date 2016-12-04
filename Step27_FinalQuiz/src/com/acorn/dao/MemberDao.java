package com.acorn.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.acorn.dto.MemberDto;
import com.acorn.util.DBConnect;

public class MemberDao {
	//1. 자신의 Type 을 private static 맴버필드로 정의한다.
	private static MemberDao dao;
	//2. 외부에서 객체 생성할수 없도록 생성자의 접근 지정자를 private로 지정
	private MemberDao(){}
	//3. 자신의 참조값을 리턴해주는 static 맴버 메소드를 정의한다.
	public static MemberDao getInstance(){
		if(dao==null){//최초 호출될때는 null 임으로
			dao=new MemberDao();//객체를 생성해서 필드에 지정한다.
		}
		//필드에 저장된 참조값을 리턴해준다.
		return dao;
	}
	
	//회원 목록을 리턴해주는 메소드
	public List<MemberDto> getList(){
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		//회원 목록을 담을 가변 배열 객체 생성하기
		List<MemberDto> list = new ArrayList<MemberDto>();
			
		try{
			conn=new DBConnect().getConn();
			//실행할 sql 문
			String sql="SELECT e.empno,ename,sal,hiredate,dname,loc "
					+ "FROM emp e, dept d "
					+ "WHERE e.deptno = d.deptno "
					+ "ORDER BY empno ASC";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt=conn.prepareStatement(sql);
			//PreparedStatement 객체를 이용해서 sql 문 수행하고
			//Query 의 결과 값은 ResultSet 객체로 리턴 받는다.
			rs=pstmt.executeQuery();
			while(rs.next()){
				int empNo;
				String eName,sal,hiredate,dName,loc;
				empNo = rs.getInt("empno");
				eName =rs.getString("ename");
				sal =rs.getString("sal");
				hiredate =rs.getString("hiredate");
				dName =rs.getString("dName");
				loc =rs.getString("loc");
				//회원 정보를 MemberDto 객체를 생성해서 담는다.
				MemberDto dto = new MemberDto(empNo,eName,sal,hiredate,dName,loc);
				//회원정보가 담긴 MemberDto 객체의 참조값을 배열에 누적
				list.add(dto);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		//회원정보의 참조값이 누적된 ArrayList 객체의 참조값 리턴해주기
		return list;
	}//getList()
	
	//인자로 전달된 번호에 해당하는 회원 한명의 정보만 리턴 하기
	public List<MemberDto> getData(int empNo){
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		MemberDto dto =null;
		List<MemberDto> list = new ArrayList<MemberDto>();
			
		try{
			conn=new DBConnect().getConn();
			//실행할 sql 문
			String sql="SELECT e.empno,ename,sal,hiredate,dname,loc "
					+ "FROM emp e, dept d "
					+ "WHERE e.deptno = d.deptno "
					+ "and empno = ?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, empNo);
			rs=pstmt.executeQuery();
			if(rs.next()){
				String eName,sal,hiredate,dName,loc;
				eName =rs.getString("ename");
				sal =rs.getString("sal");
				hiredate =rs.getString("hiredate");
				dName =rs.getString("dname");
				loc =rs.getString("loc");
				//회원 정보를 MemberDto 객체를 생성해서 담는다.
				dto = new MemberDto(empNo,eName,sal,hiredate,dName,loc);
				
				list.add(dto);
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		
		return list;
	}//getData(int)
	
	//인자로 전달된 번호에 해당하는 회원 한명의 정보만 리턴 하기
	public List<MemberDto> getData(String eName){
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		MemberDto dto =null;
		List<MemberDto> list = new ArrayList<MemberDto>();
			
		try{
			conn=new DBConnect().getConn();
			//실행할 sql 문
			String sql="SELECT e.empno,ename,sal,hiredate,dname,loc "
					+ "FROM emp e, dept d "
					+ "WHERE e.deptno = d.deptno "
					+ "and ename = ?";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, eName);
			rs=pstmt.executeQuery();
			if(rs.next()){
				int empNo;
				String sal,hiredate,dName,loc;
				empNo = rs.getInt("empno");
				sal =rs.getString("sal");
				hiredate =rs.getString("hiredate");
				dName =rs.getString("dname");
				loc =rs.getString("loc");
				//회원 정보를 MemberDto 객체를 생성해서 담는다.
				dto = new MemberDto(empNo,eName,sal,hiredate,dName,loc);
				
				list.add(dto);
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		
		return list;
	}//getData(String)
	
	//회원 1명의 정보를 삭제하는 메소드
	public boolean delete(int num){//삭제할 회원의 primary key 값
		Connection conn=null;
		PreparedStatement pstmt =null;
		int flag=0;
		try{
			conn=new DBConnect().getConn();
			//실행할 sql 문 준비
			String sql = "DELETE from emp where empno=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			flag = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}//delete()
	//회원정보를 수정하는 메소드 
	public boolean update(MemberDto dto ,int num){
		//필요한 객체의 참조값을 담을 변수 만들기 
		Connection conn=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		int flag1=0;
		int flag2=0;
		try{
			//Connection 객체의 참조값 얻어오기 
			conn=new DBConnect().getConn();
			//실행할 sql 문 준비
			String sql1="UPDATE emp SET empno=?, ename=?,"
					+ "sal=?, hiredate=?"
					+ "where empno=?";
			
			String sql2="UPDATE dept SET dname=?, loc=?"
					+ "where deptno = "
					+ "(select deptno from emp where empno=?)";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt1=conn.prepareStatement(sql1);
			pstmt2=conn.prepareStatement(sql2);
			// ? 에 값 바인딩하기
			pstmt1.setInt(1, dto.getEmpNo());
			pstmt1.setString(2, dto.geteName());
			pstmt1.setString(3, dto.getSal());
			
			
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
					.parse(dto.getHiredate());
			String newstring = new SimpleDateFormat("yyyy-MM-dd")
					.format(date);
			
			pstmt1.setString(4, newstring);
			
			
			pstmt1.setInt(5, num);
			
			pstmt2.setString(1, dto.getdName());
			pstmt2.setString(2, dto.getLoc());
			pstmt2.setInt(3, num);
			//sql 문 수행하기
			flag1=pstmt1.executeUpdate();
			flag2=pstmt2.executeUpdate();
		}catch(SQLException | ParseException se){
			se.printStackTrace();
		}finally{
			try{
				if(pstmt1!=null)pstmt1.close();
				if(pstmt2!=null)pstmt2.close();
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		if(flag2>0){
			return true;
		}else{
			return false;
		}
	}//update()
}//class()
