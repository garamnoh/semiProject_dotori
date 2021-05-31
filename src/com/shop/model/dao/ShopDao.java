package com.shop.model.dao;

import static com.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.member.model.vo.Member;
import com.shop.model.vo.Minimi;
import com.shop.model.vo.Music;
import com.shop.model.vo.Skin;

public class ShopDao {
private Properties prop=new Properties();
	
	public ShopDao() {
		String path=ShopDao.class.getResource("/sql/shop_sql.properties").getPath();
		try {
			prop.load(new FileReader(path));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public List<Music> musicList(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Music> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("musicList"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Music m=new Music();
				m.setMusicNo(rs.getInt("music_no"));
				m.setMusicTitle(rs.getString("music_title"));
				m.setSinger(rs.getString("singer"));
				m.setFilepath(rs.getString("filepath"));
				m.setPrice(rs.getInt("price"));
				m.setImgFilepath(rs.getString("img_filepath"));
				list.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public List<Minimi> minimiList(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Minimi> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("minimiList"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Minimi mini=new Minimi();
				mini.setItemNo(rs.getInt("item_no"));
				mini.setFilepath(rs.getString("filepath"));
				mini.setPrice(rs.getInt("price"));
				mini.setTitle(rs.getString("title"));
				list.add(mini);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	public List<Skin> skinList(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Skin> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("skinList"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Skin sk=new Skin();
				sk.setItemNo(rs.getInt("item_no"));
				sk.setSkinTitle(rs.getString("skin_title"));
				sk.setPrice(rs.getInt("price"));
				sk.setFilepath(rs.getString("filepath"));
				sk.setCssFilepath(rs.getString("css_filepath"));
				list.add(sk);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	public List<String> searchTitle(Connection conn,int cPage,int numPerpage,String type,String keyword){
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		List<String> list =new ArrayList();
//		if(type=="skin") {
//			String sql=prop.getProperty("searchSkinList");
//		}else if(type=="mini") {
//			String sql=prop.getProperty("searchMiniList");	
//		}else if(type=="music") {
//			String sql=prop.getProperty("searchMusicList");
//		}
		String sql=prop.getProperty("searchList");
		try {
			if(type=="skin") {
				//pstmt=conn.prepareStatement(sql.replace("#", type));
				pstmt.setString(1,"skin");
				pstmt=conn.prepareStatement(sql.replace("#", "skin_title"));
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, (cPage-1)*numPerpage+1);
				pstmt.setInt(4, cPage*numPerpage);
					
			}else if(type=="mini") {
				pstmt.setString(1,"minimi");
				pstmt=conn.prepareStatement(sql.replace("#", "title"));
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, (cPage-1)*numPerpage+1);
				pstmt.setInt(4, cPage*numPerpage);
				
			}else if(type=="music") {
				pstmt.setString(1,"music");
				pstmt=conn.prepareStatement(sql.replace("#", "music_title"));
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, (cPage-1)*numPerpage+1);
				pstmt.setInt(4, cPage*numPerpage);
					
			}
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				String s= "";
				System.out.println(rs.getString("title"));
				rs.getString("title");
				list.add(s);
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	

}
