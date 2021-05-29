package com.member.model.service;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.rollback;
import static com.common.JDBCTemplate.commit;
import java.sql.Connection;


import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;


public class MemberService {

	private MemberDao dao=new MemberDao();
	
	public Member login(String userId,String password) {
		Connection conn=getConnection();
		Member m=dao.login(conn,userId,password);
		close(conn);
		return m;
	}
	
	public Member selectMemberId(String userId) {
		Connection conn=getConnection();
		Member m=dao.selectMemberId(conn,userId);
		close(conn);
		return m;
	}
	
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn,m);
		if(result>0) 
			commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int editProfile(Member member) {
		
		Connection conn = getConnection();
		
		int result = dao.editProfile(conn, member);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
	
		return result;
	}
	
}
