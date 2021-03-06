package com.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	public static Connection getConnection() {
		Connection conn=null;
		Properties prop=new Properties();
		try {
			String path=JDBCTemplate.class.getResource("/driver/driver.properties").getPath();
			prop.load(new FileReader(path));
			Class.forName(prop.getProperty("driver"));
			System.out.println(prop.getProperty("driver"));
			System.out.println(prop.getProperty("url"));
			conn=DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("pwd"));
			conn.setAutoCommit(false);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try{
			if(conn!=null&&!conn.isClosed()) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null&&!stmt.isClosed()) stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null&&!rs.isClosed()) rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn!=null&&!conn.isClosed()) conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
