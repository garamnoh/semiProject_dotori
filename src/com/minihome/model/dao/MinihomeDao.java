package com.minihome.model.dao;

import static com.common.JDBCTemplate.close;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.minihome.model.vo.Minihome;
import com.minihome.model.vo.ProfileImg;

public class MinihomeDao {

	private Properties prop=new Properties();
	
	public MinihomeDao() {
		String path=MinihomeDao.class.getResource("/sql/minihome_sql.properties").getPath();
		File f=new File(path);
		try{
			prop.load(new FileReader(f));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Minihome getMinihome(Connection conn,String hostMemberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Minihome minihome=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMinihome"));
			pstmt.setString(1,hostMemberId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				minihome=new Minihome();
				minihome.setMemberId(rs.getString("member_id"));
				minihome.setToday(rs.getInt("today"));
				minihome.setTotal(rs.getInt("total"));
				minihome.setSkinItemNo(rs.getInt("skin_item_no"));
				minihome.setTodayDate(rs.getDate("today_date"));
				minihome.setProfileImgNo(rs.getInt("profile_img_no"));
				minihome.setProfileContent(rs.getString("profile_content"));
				minihome.setFeeling(rs.getString("feeling"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);close(pstmt);
		}
		return minihome;
	}
	
	public int addToday(Connection conn,String hostMemberId) {
		PreparedStatement pstmt=null;
		int todayResult=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("addToday"));
			pstmt.setString(1,hostMemberId);
			todayResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return todayResult;
	}
	
	public int todayToTotal(Connection conn,String hostMemberId) {
		PreparedStatement pstmt=null;
		int todayToTotalResult=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("todayToTotal"));
			pstmt.setString(1,hostMemberId);
			todayToTotalResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return todayToTotalResult;
	}
	
	public ProfileImg getMyProfileImg(Connection conn,String hostMemberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ProfileImg profileImg=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMyProfileImg"));
			pstmt.setString(1,hostMemberId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				profileImg=new ProfileImg();
				profileImg.setMemberId(rs.getString("member_id"));
				profileImg.setFilepath(rs.getString("filepath"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);close(pstmt);
		}
		return profileImg;
	}
	
	public int updateProfileContent(Connection conn,String hostMemberId,String profileContent) {
		PreparedStatement pstmt=null;
		int updateProfileContentResult=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updateProfileContent"));
			pstmt.setString(1,profileContent);
			pstmt.setString(2,hostMemberId);
			updateProfileContentResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateProfileContentResult;
	}
	
	public int changeFeeling(Connection conn,String hostMemberId,String changeFeeling) {
		PreparedStatement pstmt=null;
		int changeFeelingResult=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("changeFeeling"));
			pstmt.setString(1,changeFeeling);
			pstmt.setString(2,hostMemberId);
			changeFeelingResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return changeFeelingResult;
	}
	
}
