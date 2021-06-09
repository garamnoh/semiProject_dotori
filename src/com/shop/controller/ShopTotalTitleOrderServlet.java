package com.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.vo.Member;
import com.shop.model.vo.Minimi;
import com.shop.model.vo.MinimiLike;
import com.shop.model.vo.Music;
import com.shop.model.vo.MusicLike;
import com.shop.model.vo.Skin;
import com.shop.model.vo.SkinLike;
import com.shop.service.ShopService;

/**
 * Servlet implementation class ShopTotalTitleOrderServlet
 */
@WebServlet("/ajax/shopTotalTitleOrder")
public class ShopTotalTitleOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopTotalTitleOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		String type =request.getParameter("type");
		
		String memberId=((Member)request.getSession().getAttribute("loginMember")).getMemberId();
		
		//현재 내가 전체를 정렬할 카테고리가 미니미인가 / 스킨인가 / 뮤직인가
		String view="";
		if(type.equals("minimi")) {
			List<Minimi> minimiTotalTitleOrder= new ShopService().ashopTotalOrder();
			List<MinimiLike> aheartList=new ShopService().aSearchHeartList();
			request.setAttribute("aheartList", aheartList);
			//내 하트가 포함된 아이템 목록임
			List<Integer> myHeartItemList=new ShopService().aMyHeartItemNoList(memberId);
			request.setAttribute("myHeartItemList", myHeartItemList);
			
			request.setAttribute("minimiList", minimiTotalTitleOrder);
			view="/views/shop/shopMinimi.jsp";
		}else if(type.equals("skin")) {
			List<Skin> skinTotalTitleOrder= new ShopService().bshopTotalOrder();
			List<SkinLike> bheartList=new ShopService().bSearchHeartList();
			request.setAttribute("bheartList", bheartList);
			List<Integer> myHeartItemList=new ShopService().bMyHeartItemNoList(memberId);
			request.setAttribute("myHeartItemList", myHeartItemList); 
			
			request.setAttribute("skinList", skinTotalTitleOrder);
			view="/views/shop/shopSkin.jsp";
		}else if(type.equals("music")) {
			List<Music> musicTotalTitleOrder= new ShopService().cshopTotalOrder();
			List<MusicLike> cheartList=new ShopService().cSearchHeartList();
			request.setAttribute("cheartList", cheartList);
			
			List<Integer> myHeartItemList=new ShopService().cMyHeartItemNoList(memberId);
			request.setAttribute("myHeartItemList", myHeartItemList); 
			
			request.setAttribute("musicList", musicTotalTitleOrder);
			view="/views/shop/shopMusic.jsp";
		}
				
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
