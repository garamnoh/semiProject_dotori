package com.friend.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.friend.model.vo.Friend;

import static com.common.JDBCTemplate.close;

public class FriendDao {
	
	private Properties prop = new Properties();
	
	public FriendDao() {

		String path = FriendDao.class.getResource("/sql/friend_sql.properties").getPath();
		
		try{
			prop.load(new FileReader(path));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Friend> requestedList(Connection conn, String memberList){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Friend> requestedList = new ArrayList<Friend>();
		
		try {
			ps = conn.prepareStatement(prop.getProperty("requestedList"));
			ps.setString(1, memberList);
			ps.setString(2, "신청");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Friend f = new Friend();
				
				f.setFollowee(rs.getString("FOLLOWEE"));
				f.setFollower(rs.getString("FOLLOWER"));
				f.setFriendshipName(rs.getString("FRIENDSHIP_NAME"));
				f.setVisitCount(rs.getInt("VISIT_COUNT"));
				f.setFollowerProfileName(rs.getString("MEMBER_NAME"));
				f.setFollowerProfilePath(rs.getString("PROFILE_PATH"));
				f.setFollowerProfilePhone(rs.getString("PHONE"));
				
				requestedList.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		} return requestedList;
	}
	
	public ArrayList<Friend> friendsList(Connection conn, String memberList){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Friend> friendsList = new ArrayList<Friend>();
		
		try {
			ps = conn.prepareStatement(prop.getProperty("requestedList"));
			ps.setString(1, memberList);
			ps.setString(2, "일촌");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Friend f = new Friend();
				
				f.setFollowee(rs.getString("FOLLOWEE"));
				f.setFollower(rs.getString("FOLLOWER"));
				f.setFriendshipName(rs.getString("FRIENDSHIP_NAME"));
				f.setVisitCount(rs.getInt("VISIT_COUNT"));
				f.setFollowerProfileName(rs.getString("MEMBER_NAME"));
				f.setFollowerProfilePath(rs.getString("PROFILE_PATH"));
				f.setFollowerProfilePhone(rs.getString("PHONE"));
				
				friendsList.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		} return friendsList;
	}
	
	public int deleteFriend(Connection conn, String followee, String follower) {
		
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			ps = conn.prepareStatement(prop.getProperty("deleteFriend"));
			ps.setString(1, followee);
			ps.setString(2, follower);
			ps.setString(3, follower);
			ps.setString(4, followee);
			
			result = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		} return result;
	}
	
	public int acceptFriend(Connection conn, String followee, String follower) {
		
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		int result = 0;
		
		try {
			ps1 = conn.prepareStatement(prop.getProperty("acceptFriend"));
			ps1.setString(1, "일촌");
			ps1.setString(2, followee);
			ps1.setString(3, follower);
			
			result = ps1.executeUpdate();
			
			if(result > 0) {
				
				ps2 = conn.prepareStatement(prop.getProperty("addFollowerRow"));
				ps2.setString(1, follower);
				ps2.setString(2, followee);
				ps2.setString(3, "일촌");
				
				result = ps2.executeUpdate();
			} else {
				result = 0;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps1);
			close(ps2);
		} return result;
	}
}