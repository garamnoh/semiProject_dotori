package com.minihome.album.model.dao;

import static com.common.JDBCTemplate.close;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.minihome.album.model.vo.Album;
import com.minihome.album.model.vo.AlbumComment;

public class AlbumDao {

	private Properties prop=new Properties();
	
	public AlbumDao() {
		String path=AlbumDao.class.getResource("/sql/album_sql.properties").getPath();
		File f=new File(path);
		try{
			prop.load(new FileReader(f));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Album> getMyPhotos(Connection conn,String hostMemberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Album> albumList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMyPhotos"));
			pstmt.setString(1,hostMemberId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Album l=new Album();
				l.setImgNo(rs.getInt("img_no"));
				l.setMemberId(rs.getString("member_id"));
				l.setTitle(rs.getString("title"));
				l.setFilepath(rs.getString("filepath"));
				l.setFolder(rs.getString("folder"));
				l.setPostDate(rs.getDate("post_date"));
				l.setHashTag(rs.getString("hash_tag"));
				l.setContent(rs.getString("content"));
				albumList.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);close(pstmt);
		}
		return albumList;
	}
	
	public List<String> getMyFolders(Connection conn,String hostMemberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<String> folderList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMyFolders"));
			pstmt.setString(1,hostMemberId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				folderList.add(rs.getString("folder"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);close(pstmt);
		}
		return folderList;
	}
	
	public List<Album> getMyPhotosOnFolder(Connection conn,String hostMemberId,String folder) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Album> albumList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMyPhotosOnFolder"));
			pstmt.setString(1,hostMemberId);
			pstmt.setString(2,folder);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Album l=new Album();
				l.setImgNo(rs.getInt("img_no"));
				l.setMemberId(rs.getString("member_id"));
				l.setTitle(rs.getString("title"));
				l.setFilepath(rs.getString("filepath"));
				l.setFolder(rs.getString("folder"));
				l.setPostDate(rs.getDate("post_date"));
				l.setHashTag(rs.getString("hash_tag"));
				l.setContent(rs.getString("content"));
				albumList.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);close(pstmt);
		}
		return albumList;
	}
	
	public int changeFolder(Connection conn,String hostMemberId,String changeFolderTarget,String targetPhotoStr) {
		PreparedStatement pstmt=null;
		int changeFolderResult=0;
		String sql=prop.getProperty("changeFolder").replace("#","("+targetPhotoStr+")");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,changeFolderTarget);
			pstmt.setString(2,hostMemberId);
			changeFolderResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return changeFolderResult;
	}
	
	public int addFolder(Connection conn,String hostMemberId,String addFolderTitle) {
		PreparedStatement pstmt=null;
		int addFolderResult=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("addFolder"));
			pstmt.setString(1,hostMemberId);
			pstmt.setString(2,addFolderTitle);
			addFolderResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return addFolderResult;
	}
	
	public int deleteFolder(Connection conn,String hostMemberId,String deleteFolderTarget) {
		PreparedStatement pstmt=null;
		int deleteFolderResult=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deleteFolder"));
			pstmt.setString(1,"기본폴더");
			pstmt.setString(2,hostMemberId);
			pstmt.setString(3,deleteFolderTarget);
			deleteFolderResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteFolderResult;
	}
	
	public List<AlbumComment> getMyComments(Connection conn,String hostMemberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AlbumComment> commentList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMyComments"));
			pstmt.setString(1,hostMemberId);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AlbumComment c=new AlbumComment();
				c.setCommentNo(rs.getInt("comment_no"));
				c.setCommentLevel(rs.getInt("comment_level"));
				c.setCommentWriter(rs.getString("comment_writer"));
				c.setCommentContent(rs.getString("comment_content"));
				c.setAlbumRef(rs.getInt("album_ref"));
				c.setAlbumCommentRef(rs.getInt("album_comment_ref"));
				c.setCommentDate(rs.getDate("comment_date"));
				commentList.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);close(pstmt);
		}
		return commentList;
	}
	
	public int insertComment(Connection conn,String commentLevel,String loginMemberId,String commentContent,String albumRef,String albumCommentRef) {
		PreparedStatement pstmt=null;
		int insertCommentResult=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertComment"));
			pstmt.setString(1,commentLevel);
			pstmt.setString(2,loginMemberId);
			pstmt.setString(3,commentContent);
			pstmt.setString(4,albumRef);
			pstmt.setString(5,albumCommentRef=="0"?null:albumCommentRef);
			insertCommentResult=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertCommentResult;
	}
	
	public int albumCount(Connection conn,String hostMemberId,String folder) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int totalData=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("albumCount"));
			pstmt.setString(1,hostMemberId);
			pstmt.setString(2,folder);
			rs=pstmt.executeQuery();
			if(rs.next()) totalData=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return totalData;
	}
	
	public List<Album> getMyPagingPhotosOnFolder(Connection conn,int cPage,int numPerPage,String hostMemberId,String folder) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Album> albumList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMyPagingPhotosOnFolder"));
			pstmt.setString(1,hostMemberId);
			pstmt.setString(2,folder);
			pstmt.setInt(3,(cPage-1)*numPerPage+1);
			pstmt.setInt(4,cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Album l=new Album();
				l.setImgNo(rs.getInt("img_no"));
				l.setMemberId(rs.getString("member_id"));
				l.setTitle(rs.getString("title"));
				l.setFilepath(rs.getString("filepath"));
				l.setFolder(rs.getString("folder"));
				l.setPostDate(rs.getDate("post_date"));
				l.setHashTag(rs.getString("hash_tag"));
				l.setContent(rs.getString("content"));
				albumList.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return albumList;
	}
	
	public List<Album> getMyPagingPhotos(Connection conn,int cPage,int numPerPage,String hostMemberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Album> albumList=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("getMyPagingPhotos"));
			pstmt.setString(1,hostMemberId);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);
			pstmt.setInt(3,cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Album l=new Album();
				l.setImgNo(rs.getInt("img_no"));
				l.setMemberId(rs.getString("member_id"));
				l.setTitle(rs.getString("title"));
				l.setFilepath(rs.getString("filepath"));
				l.setFolder(rs.getString("folder"));
				l.setPostDate(rs.getDate("post_date"));
				l.setHashTag(rs.getString("hash_tag"));
				l.setContent(rs.getString("content"));
				albumList.add(l);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return albumList;
	}
	
}