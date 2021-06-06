<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.member.model.vo.Member,com.minihome.model.vo.Minihome,com.shop.model.vo.Music,com.shop.model.vo.Skin"%>
<%
	Member loginMember=(Member)request.getAttribute("loginMember");
	Member hostMember=(Member)request.getAttribute("hostMember");
	Minihome minihome=(Minihome)request.getAttribute("minihome");
	List<Music> musicList=(List<Music>)request.getAttribute("musicList");
	Skin mySkin=(Skin)request.getAttribute("mySkin");
	System.out.println(mySkin.getCssFilepath());
%>   
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=hostMember.getNickname()%>님의 미니홈피</title>
	<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/<%=mySkin.getCssFilepath()%>">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/leftpage_home.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/rightpage_home.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/leftpage_album.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/rightpage_album.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/leftpage_diary.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/rightpage_diary.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/leftpage_jukebox.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/minihome/rightpage_jukebox.css">
</head>
<body>
    
<div class="container">
    <div class="cover">
        <div class="sheet">
            <div class="today">TODAY <span id="today"><%=minihome.getToday()%></span> | TOTAL <span id="total"><%=minihome.getTotal()%></span></div>
            <div class="miniTitle"><%=loginMember.getNickname()%>님의 미니홈피</div>
            <div id="left-page"></div>
            <div id="right-page"></div>
        </div>
    </div>
    <nav class="miniMenu">
        <ul>
            <li class="unselected">홈</li>
            <li class="unselected">사진첩</li>
            <li class="unselected">다이어리</li>
            <li class="unselected">쥬크박스</li>
            <li class="unselected">설정</li>
        </ul>
    </nav>
    
    <div id="background-music-control-box">
	    <div id="musicInfo">
	        <%for(int i=0;i<musicList.size();i++) {%>
	            <span style="display:none;">
	                <%=musicList.get(i).getMusicTitle()%><br>
	                by <%=musicList.get(i).getSinger()%>
	            </span>
	            <input type="hidden" value="<%=i%>">
	        <%}%>
	    </div>
	    <div class="audio-controller-box">
	    	<img src="<%=request.getContextPath()%>/images/minihome/mute-icon.png" id="muteMusic" width="20px" onclick="fn_muteBackMusic();">
			<img src="<%=request.getContextPath()%>/images/minihome/play-icon.png" id="playMusic" width="30px" onclick="fn_pauseBackMusic();">
			<img src="<%=request.getContextPath()%>/images/minihome/pause-icon.png" id="pauseMusic" width="30px" onclick="fn_pauseBackMusic();">
			<img src="<%=request.getContextPath()%>/images/minihome/playNext-icon.png" id="playNextMusic" width="30px" onclick="fn_playNext();">
			<img src="<%=request.getContextPath()%>/images/minihome/playPrevious-icon.png" id="playPreviousMusic" width="30px" onclick="fn_playPrevious();">
			<img src="<%=request.getContextPath()%>/images/minihome/replay-icon.png" id="replayMusic" width="20px" onclick="fn_replay();">
		</div>
    </div>

    <div id="WeatherBox" style="border:1px solid black;position:absolute;left:1000px;top:550px;background-color:white;font-size:11px;">
    	<ul style="list-style-type:none;position:relative;left:-30px;">
    		<li><img src="" id="weather_icon" width="20px"></li>
    		<li id="weather_main"></li>
    		<li id="weather_description"></li>
    		<li id="feels_like"></li>
    		<li id="temp"></li>
    		<li id="temp_max"></li>
    		<li id="temp_min"></li>
    	</ul>
    </div>
    
    <div id="backgroundMusic">
    	<%for(int i=0;i<musicList.size();i++) {%>    
    		<audio src="<%=request.getContextPath()%>/audio/<%=musicList.get(i).getFilepath()%>"></audio>
		<%} %>
    </div>
</div>

	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
	<input type="hidden" id="loginMemberId" value="<%=loginMember.getMemberId()%>">
	<input type="hidden" id="hostMemberId" value="<%=hostMember.getMemberId()%>">

	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/minihome/minihome.js"></script>
	
</body>
</html>