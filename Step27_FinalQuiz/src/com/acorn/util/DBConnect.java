package com.acorn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 	Oracle DB 에 접속해서 Connection 객체를 제공해주는 
 * 	메소드를 갖는 클래스 정의하기
 * 
 */
public class DBConnect {
	//맴버필드
	private Connection conn;
	//생성자
	public DBConnect(){
		try{
			//오라클 드라이버 로딩하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//접속할 oracle DB 정보 구성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			//Connection 객체의 참조값 열어오기
			conn = DriverManager.getConnection(url,"scott","tiger");
			System.out.println("Oracle DB 접속 성공!");
		}catch(ClassNotFoundException cne){
			cne.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//Connection 객체를 리턴해주는 메소드
	public Connection getConn(){
		return conn;
	}
}