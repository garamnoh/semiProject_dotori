package com.page.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.vo.Member;

@WebServlet("/page/sidebarFriends")
public class PageToSidbarFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PageToSidbarFriendsServlet() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String sender = (String)((Member)request.getSession().getAttribute("loginMember")).getMemberName();
		request.setAttribute("sender", sender);
		
		request.getRequestDispatcher("/views/friends/aside_friends.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
