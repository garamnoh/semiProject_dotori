package com.friend.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friend.model.service.FriendService;
import com.friend.model.vo.Friend;
import com.member.model.vo.Member;

/**
 * Servlet implementation class RequestedListServlet
 */
@WebServlet("/friends/requestedList")
public class RequestedListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestedListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		ArrayList<Friend> requestedList = new FriendService().requestedList(memberId);
		ArrayList<Friend> requestToList = new FriendService().requestToList(memberId);
		
		request.setAttribute("requestToList", requestToList);
		request.setAttribute("requestedList", requestedList);
		request.getRequestDispatcher("/views/friends/section_friends_recommend.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
