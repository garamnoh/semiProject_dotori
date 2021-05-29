package com.member.model.dao;

import static com.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;






import com.member.model.vo.Member;


public class MemberDao {

	private Properties prop=new Properties();
	
	{
		String path=MemberDao.class.getResource("/sql/member_sql.properties").getPath();
		
		try{
			prop.load(new FileReader(path));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member login(Connection conn,String userId,String password) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("login"));
			pstmt.setString(1,userId);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setNickname(rs.getString("nickname"));
				m.setBirthDate(rs.getDate("birth_date"));
				m.setGender(rs.getString("gender"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setDotori(rs.getInt("dotori"));
				m.setEnrollDate(rs.getDate("enroll_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);close(pstmt);
		}
		return m;
	}
	
	
	public Member selectMemberId(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectMemberId"));
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setNickname(rs.getString("nickname"));
				m.setBirthDate(rs.getDate("birth_date"));
				m.setGender(rs.getString("gender"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setDotori(rs.getInt("dotori"));
				m.setEnrollDate(rs.getDate("enroll_date"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	
	
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertMember"));
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getNickname());
			pstmt.setDate(5, m.getBirthDate());
			pstmt.setString(6, m.getGender());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			//pstmt.setInt(9, m.getDotori());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int editProfile(Connection conn, Member member) {
		
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			
			ps = conn.prepareStatement(prop.getProperty("editProfile"));
			
			ps.setString(1, member.getMemberName());
			ps.setString(2, member.getNickname());
			ps.setString(3, member.getGender());
			ps.setString(4, member.getPhone());
			ps.setDate(5, member.getBirthDate());
			ps.setString(6, member.getAddress());
			ps.setString(7, member.getMemberId());

			result = ps.executeUpdate();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(ps);
			
		} return result;
	}
	
}
