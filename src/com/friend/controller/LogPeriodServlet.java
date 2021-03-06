package com.friend.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.friend.model.service.FriendService;
import com.friend.model.vo.Log;
import com.member.model.vo.Member;

/**
 * Servlet implementation class LogPeriodServlet
 */
@WebServlet("/friends/logPeriod")
public class LogPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogPeriodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String myId = (String)((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		
		int period = Integer.parseInt(request.getParameter("period"));
		
		ArrayList<Log> myLog = new FriendService().myLog(myId, period);
		ArrayList<Log> friendsLog = new FriendService().friendsLog(myId, period);
		
		request.setAttribute("myId", myId);
		request.setAttribute("myLog", myLog);
		request.setAttribute("friendsLog", friendsLog);
		request.getRequestDispatcher("/views/friends/sub/logResult.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
