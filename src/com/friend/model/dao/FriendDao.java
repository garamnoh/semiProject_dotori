package com.friend.model.dao;

import static com.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.friend.model.vo.FeedAlbum;
import com.friend.model.vo.FeedDiary;
import com.friend.model.vo.Friend;
import com.friend.model.vo.SearchF;

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

	public ArrayList<Friend> requestedList(Connection conn, String memberId){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Friend> requestedList = new ArrayList<Friend>();
		
		try {
			ps = conn.prepareStatement(prop.getProperty("requestedList"));
			ps.setString(1, memberId);
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
				f.setGender(rs.getString("GENDER"));
				f.setBirth(rs.getDate("BIRTH_DATE"));
				f.setAddress(rs.getString("ADDRESS"));
				
				requestedList.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		} return requestedList;
	}
	
	public ArrayList<Friend> requestToList(Connection conn, String memberId){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Friend> requestToList = new ArrayList<Friend>();
		
		try {
			ps = conn.prepareStatement(prop.getProperty("requestToList"));
			ps.setString(1, memberId);
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
				f.setGender(rs.getString("GENDER"));
				f.setBirth(rs.getDate("BIRTH_DATE"));
				f.setAddress(rs.getString("ADDRESS"));
				
				requestToList.add(f);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		} return requestToList;
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
	
	public ArrayList<SearchF> searchList(Connection conn, String myId, String searchKeyword){
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ArrayList<SearchF> searchList = new ArrayList<SearchF>();
		ArrayList<SearchF> resultList = new ArrayList<SearchF>();
		
		try {
			
			ps = conn.prepareStatement(prop.getProperty("searchList"));
			ps.setString(1, "%"+searchKeyword+"%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SearchF mId = new SearchF();
				
				mId.setMemberId(rs.getString("MEMBER_ID"));
				searchList.add(mId);
			}
			
			if(searchList != null) {
				
				ps2 = conn.prepareStatement(prop.getProperty("resultList1"));
				ps3 = conn.prepareStatement(prop.getProperty("resultList2"));
				ps4 = conn.prepareStatement(prop.getProperty("resultList3"));
				
				for(SearchF list : searchList) {
					ps2.setString(1, myId);
					ps2.setString(2, list.getMemberId());
					rs = ps2.executeQuery();
					
					if(rs.next()) {
						SearchF m = new SearchF();
						
						m.setFollower(rs.getString("FOLLOWER"));
						m.setMemberId(rs.getString("MEMBER_ID"));
						m.setMemberName(rs.getString("MEMBER_NAME"));
						m.setNickname(rs.getString("NICKNAME"));
						m.setBirth(rs.getDate("BIRTH_DATE"));
						m.setGender(rs.getString("GENDER"));
						m.setAddress(rs.getString("ADDRESS"));
						m.setPhone(rs.getString("PHONE"));
						m.setProfilePath(rs.getString("PROFILE_PATH"));
						m.setFriendship(rs.getString("FRIENDSHIP_NAME"));
						
						resultList.add(m);
					} else {
						
						ps4.setString(1, list.getMemberId());
						ps4.setString(2, myId);
						rs2 = ps4.executeQuery();
						
						if(rs2.next()) {
							SearchF m = new SearchF();
							
							m.setFollower(rs2.getString("FOLLOWER"));
							m.setMemberId(rs2.getString("MEMBER_ID"));
							m.setMemberName(rs2.getString("MEMBER_NAME"));
							m.setNickname(rs2.getString("NICKNAME"));
							m.setBirth(rs2.getDate("BIRTH_DATE"));
							m.setGender(rs2.getString("GENDER"));
							m.setAddress(rs2.getString("ADDRESS"));
							m.setPhone(rs2.getString("PHONE"));
							m.setProfilePath(rs2.getString("PROFILE_PATH"));
							m.setFriendship(rs2.getString("FRIENDSHIP_NAME"));
							
							resultList.add(m);
					
						} else {
							
							ps3.setString(1, list.getMemberId());
							rs1 = ps3.executeQuery();
							
							if(rs1.next()) {
								
								SearchF m = new SearchF();
								
								m.setMemberId(rs1.getString("MEMBER_ID"));
								m.setMemberName(rs1.getString("MEMBER_NAME"));
								m.setNickname(rs1.getString("NICKNAME"));
								m.setBirth(rs1.getDate("BIRTH_DATE"));
								m.setGender(rs1.getString("GENDER"));
								m.setAddress(rs1.getString("ADDRESS"));
								m.setPhone(rs1.getString("PHONE"));
								m.setProfilePath(rs1.getString("PROFILE_PATH"));
								m.setFriendship("");
								
								resultList.add(m);
							}
						}
					}
				}
			}
			for(SearchF list : resultList) System.out.println("두번쨰 검색 : " + list);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(rs1);
			close(ps);
			close(ps2);
			close(ps3);
		} return resultList;
	}
	
	public int requestTo(Connection conn, String memberId, String requestTo) {
		
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			
			ps = conn.prepareStatement(prop.getProperty("requestTo"));
			ps.setString(1, requestTo);
			ps.setString(2, memberId);
			ps.setString(3, "신청");
			
			result = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		} return result;
	}
	
	public int reject(Connection conn, String memberId, String reject) {
		
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			
			ps = conn.prepareStatement(prop.getProperty("reject"));
			ps.setString(1, memberId);
			ps.setString(2, reject);
			ps.setString(3, "신청");
			
			result = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		} return result;
	}
	
	public int cancel(Connection conn, String memberId, String cancel) {
		
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			
			ps = conn.prepareStatement(prop.getProperty("cancel"));
			ps.setString(1, cancel);
			ps.setString(2, memberId);
			ps.setString(3, "신청");
			
			result = ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		} return result;
	}
	
	public int requestedFromCount(Connection conn, String memberId) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int requestedFromCount = 0;
		
		try {
			
			ps = conn.prepareStatement(prop.getProperty("requestedFromCount"));
			ps.setString(1, memberId);
			ps.setString(2, "신청");
			
			rs = ps.executeQuery();
			
			if(rs.next()) requestedFromCount = rs.getInt(1);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		} return requestedFromCount;
	}
	
	public int feedAlbumCount(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int feedAlbumCount = 0;
		
		try {
			ps = conn.prepareStatement(prop.getProperty("feedAlbumCount"));
			rs = ps.executeQuery();
			if(rs.next()) feedAlbumCount = rs.getInt(1);

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		} return feedAlbumCount;
	}
	
	public int feedDiaryCount(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int feedDiaryCount = 0;
		
		try {
			ps = conn.prepareStatement(prop.getProperty("feedDiaryCount"));
			rs = ps.executeQuery();
			if(rs.next()) feedDiaryCount = rs.getInt(1);

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		} return feedDiaryCount;
	}
	
	public HashMap<String, Object> newFeed(Connection conn, String myId, int startNum, int endNum) {
		
		PreparedStatement ps = null;

		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ArrayList<FeedDiary> newFeedDiary = new ArrayList<FeedDiary>();
		ArrayList<FeedAlbum> newFeedAlbum = new ArrayList<FeedAlbum>();
		
		ArrayList<String> temp = new ArrayList<String>();
		
		HashMap<String, Object> newFeed = new HashMap<String, Object>();
		
		try {
			
			ps = conn.prepareStatement(prop.getProperty("who"));
			ps.setString(1, myId);
			ps.setString(2, "일촌");
			
			rs = ps.executeQuery();
				
			while(rs.next()) temp.add(rs.getString("FOLLOWER"));
			
			ps1 = conn.prepareStatement(prop.getProperty("newFeedDiary"));
			ps1.setInt(1, startNum);
			ps1.setInt(2, endNum);
			
			rs1 = ps1.executeQuery();
			
			while(rs1.next()) {
				FeedDiary d = new FeedDiary();
				
				d.setDiaryNo(rs1.getInt("DIARY_NO"));
				d.setMemberId(rs1.getString("MEMBER_ID"));
				d.setWriter(rs1.getString("WRITER"));
				d.setFolderNo(rs1.getInt("FOLDER_NO"));
				d.setContent(rs1.getString("CONTENT"));
				d.setPostDate(rs1.getDate("POST_DATE"));
				d.setMemberName(rs1.getString("MEMBER_NAME"));
				d.setProfilePath(rs1.getString("PROFILE_PATH"));
				for(int i = 0; i < temp.size(); i++) {
					if(rs1.getString("WRITER")==temp.get(i)) {
						d.setFriendshipName("일촌");
						break;
					}
				}
				newFeedDiary.add(d);
			}
			
			ps2 = conn.prepareStatement(prop.getProperty("newFeedAlbum"));
			ps2.setInt(1, startNum);
			ps2.setInt(2, endNum);
			
			rs2 = ps2.executeQuery();
			
			while(rs2.next()) {
				FeedAlbum a = new FeedAlbum();
				
				a.setImgNo(rs2.getInt("IMG_NO"));
				a.setMemberId(rs2.getString("MEMBER_ID"));
				a.setTitle(rs2.getString("TITLE"));
				a.setFilepath(rs2.getString("FILEPATH"));
				a.setFolder(rs2.getString("FOLDER"));
				a.setPostDate(rs2.getDate("POST_DATE"));
				a.setHashTag(rs2.getString("HASH_TAG"));
				a.setContent(rs2.getString("CONTENT"));
				a.setProfilePath(rs2.getString("PROFILE_PATH"));
				a.setMemberName(rs2.getString("MEMBER_NAME"));
				for(int i = 0; i < temp.size(); i++) {
					if(rs2.getString("MEMBER_ID")==temp.get(i)) {
						a.setFriendshipName("일촌");
						break;
					}
				}
				
				newFeedAlbum.add(a);
			}
	
			newFeed.put("feedDiary", newFeedDiary);
			newFeed.put("feedAlbum", newFeedAlbum);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); close(rs1); close(rs2);
			close(ps); close(ps1); close(ps2);
		}
		return newFeed;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
