package com.minihome.diary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.friend.model.service.FriendService;
import com.friend.model.vo.Friend;
import com.member.model.service.MemberService;
import com.member.model.vo.Member;
import com.minihome.diary.model.service.DiaryService;
import com.minihome.diary.model.vo.Diary;
import com.minihome.diary.model.vo.DiaryComment;
import com.minihome.diary.model.vo.DiaryFolder;
import com.minihome.model.service.MinihomeService;
import com.minihome.model.vo.Minihome;

@WebServlet("/page/minihomeRightPageToDiary.do")
public class MinihomeRightPageToDiary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService memberService=new MemberService();
	private MinihomeService minihomeService=new MinihomeService();
	
    public MinihomeRightPageToDiary() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		//if(session!=null) session.invalidate();
		if(session==null||session.getAttribute("loginMember")==null) {
			request.getRequestDispatcher("/views/minihome/errorpage.jsp").forward(request,response);
			return;
		}
		Member loginMember=(Member)session.getAttribute("loginMember");
		String hostMemberId=request.getParameter("hostMemberId");
		Member hostMember=null;
		if(hostMemberId!=null) {
			hostMember=memberService.selectMemberId(hostMemberId);
		}else {
			hostMember=loginMember;
		}
		hostMemberId=hostMember.getMemberId();
		Minihome minihome=minihomeService.getMinihome(hostMemberId);
		
		int cPage;
		int numPerpage;		
		
		try { 
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) { 
			cPage=1; 
		}		
		try { 
			numPerpage=Integer.parseInt(request.getParameter("numPerpage")); 
		}catch(NumberFormatException e) { 
			numPerpage=5; 
		}	
		
		int diaryFolderLevel;
		List<Diary> list=null;
		try {		
			diaryFolderLevel=(int)request.getAttribute("diaryFolderLevel");					
		}catch(NullPointerException e) {
			diaryFolderLevel=1; //처음엔 전체공개폴더의 게시물들만 보이게			
		}				
		list=new DiaryService().selectDiaryList(cPage, numPerpage, diaryFolderLevel, hostMemberId);	
		
		int totalData=new DiaryService().selectDiaryCount(diaryFolderLevel);		
		System.out.println(diaryFolderLevel+"/"+totalData);
		
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {			
			pageBar+="<a id='"+(pageNo-1)+"/"+numPerpage+"'>[이전]</a>";
		}		
		
		System.out.println(pageNo+"/"+pageEnd+"/"+totalPage);
		
		while(!(pageNo>pageEnd || pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {				
				pageBar+="<a id='"+pageNo+"/"+numPerpage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {			
			pageBar+="<a id='"+pageNo+"/"+numPerpage+"'>[다음]</a>";
		}
		
		List<DiaryComment> cList=new DiaryService().selectDiaryCommentList();
		List<DiaryFolder> fList=new DiaryService().selectFolderList(hostMemberId);
		
		String profilePath=new MemberService().profilePath(loginMember.getMemberId());
		
		request.setAttribute("loginMember",loginMember);
		request.setAttribute("hostMember",hostMember);		
		request.setAttribute("pageBar", pageBar);	
		request.setAttribute("list", list);
		request.setAttribute("cList", cList);
		request.setAttribute("fList", fList);
		request.setAttribute("profilePath", profilePath);
		request.setAttribute("diaryFolderLevel", diaryFolderLevel);
		
		request.getRequestDispatcher("/views/minihome/rightpage_diary.jsp").forward(request, response);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
