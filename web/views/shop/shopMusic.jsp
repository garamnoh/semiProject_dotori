<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.shop.model.vo.Music"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/shop/shopProductMusic.css">

<%
List<Music> list=(List<Music>)request.getAttribute("musicList");
	Music m=(Music)request.getAttribute("music");
%> 
<%@ include file="/views/common/shopheader.jsp"%>

<form action="<%=request.getContextPath()%>/shop/musicTable" method="post">
	<%if(list==null&&list.isEmpty()){ %>
		<p>조회된 데이터가 없습니다</p>
	<%}else{%> 
		<table>
		<% for(int i=0;i<list.size()/3;i++){%> 
		<tr>
			<td>
				<div class="productMusic">
					<div class="MusicPicture">
						<img alt="음악사진" src="<%=request.getContextPath()%>/upload/MUSIC/<%=list.get(i).getImgFilepath()%>"> 
					</div>
					<br>
	                <div class="MusicInfo">
	                <input class="shopMusicCheck" type="checkbox">
	                <ul>
	                    <li><b>[<%=list.get(i).getSinger()%>]</b></li>
	                    <li><b><%=list.get(i).getMusicTitle() %></b></li>
	                    <li><b>도토리 <%=list.get(i).getPrice() %>개</b></li>
	                </ul>
	                <input class="shopMusicCheck" type="checkbox">
	                </div>
				</div>
			</td>
			<%if(list.get(i+1)!=null) {%>
			<td>
				<div class="productMusic">
					<div class="MusicPicture">
						<img alt="음악사진" src="<%=request.getContextPath()%>/upload/MUSIC/<%=list.get(i+1).getImgFilepath()%>"> 
					</div>
					<br>
	                <div class="MusicInfo">
	                <input class="shopMusicCheck" type="checkbox">
	                <ul>
	                    <li><b>[<%=list.get(i+1).getSinger()%>]</b></li>
	                    <li><b><%=list.get(i+1).getMusicTitle() %></b></li>
	                    <li><b>도토리 <%=list.get(i+1).getPrice() %>개</b></li>
	                </ul>
	                <input class="shopMusicCheck" type="checkbox">
	                </div>
				</div>
			</td>
			<%} %>
			<%if(list.get(i+2)!=null) {%>
			<td>
				<div class="productMusic">
					<div class="MusicPicture">
						<img alt="음악사진" src="<%=request.getContextPath()%>/upload/MUSIC/<%=list.get(i+2).getImgFilepath()%>"> 
					</div>
					<br>
	                <div class="MusicInfo">
	                <input class="shopMusicCheck" type="checkbox">
	                <ul>
	                    <li><b>[<%=list.get(i+2).getSinger()%>]</b></li>
	                    <li><b><%=list.get(i+2).getMusicTitle() %></b></li>
	                    <li><b>도토리 <%=list.get(i+2).getPrice() %>개</b></li>
	                </ul>
	                <input class="shopMusicCheck" type="checkbox">
	                </div>
				</div>
			</td>
			<%} %>
		</tr>
		
	<%} 
	}%>
</form>

