package com.minihome.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;
import com.minihome.diary.model.service.DiaryService;
import com.minihome.diary.model.vo.Diary;
import com.minihome.model.service.MinihomeService;
import com.minihome.model.vo.Minihome;

/**
 * Servlet implementation class DiaryWriteServlet
 */
@WebServlet("/diary/diaryWrite")
public class DiaryWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	private MemberService memberService=new MemberService();
	private MinihomeService minihomeService=new MinihomeService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiaryWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		Diary d=new Diary();
		d.setMemberId(request.getParameter("hostMemberId"));
		d.setWriter(request.getParameter("loginMemberId"));		
		String folder=request.getParameter("diary_folder");
		
		String title=request.getParameter("diary_title_input");
		String content=request.getParameter("diary_content_input");
		String msg="";
		if(title!=null && content!=null) {
			d.setContent(content);
			d.setTitle(title);
		}else {
			/////////////////////////////////////////////
			msg="내용을 모두 입력하세요.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);			
		}
		
		switch(folder){
			case "전체공개" : d.setFolderNo(1);
			case "일촌공개" : d.setFolderNo(2);
			case "비공개" : d.setFolderNo(3);
		}
		
		int result=new DiaryService().insertDiary(d);		
		
		if(result>0) {
			request.getRequestDispatcher("/page/minihomeRightPageToDiary.do").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
