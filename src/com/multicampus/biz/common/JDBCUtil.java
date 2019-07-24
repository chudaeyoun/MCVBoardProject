package com.multicampus.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {		
		try {
			// 1. Driver 로딩한다.
//			DriverManager.registerDriver(new org.h2.Driver());
			Class.forName("org.h2.Driver");
			
			// 2. DBMS와 커넥션을 연결한다.
			String url = "jdbc:h2:tcp://localhost/~/test";
			return DriverManager.getConnection(url, "sa",  "");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs != null) 
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
		
		try {
			if(stmt != null) 
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		
		try {
			if(!conn.isClosed() && conn != null) 
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
	
	public static void close(Statement stmt, Connection conn) {
		try {
			if(stmt != null) 
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt = null;
		}
		
		try {
			if(!conn.isClosed() && conn != null) 
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
}
