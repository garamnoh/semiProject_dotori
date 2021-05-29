<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.vo.Member, java.util.ArrayList" %>
<%
	ArrayList<Member> membersList = (ArrayList<Member>)request.getAttribute("membersList");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = (String)request.getAttribute("searchType");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
%>
<div id="membersListContainer">
    <table>
        <tr>
            <th>Name</th>
            <th>NickName</th>
            <th>Email</th>
            <th>Gender</th>
            <th>Phone</th>
            <th>Dotori</th>
        </tr>
        <% if(membersList != null) { %>
        	<% for(Member member : membersList) { %>
		        <tr>
		            <td><%= member.getMemberName() %></td>
		            <td><%= member.getNickname() %></td>
		            <td><%= member.getMemberId() %></td>
		            <td><%= member.getGender() %></td>
		            <td><%= member.getPhone() %></td>
		            <td><%= member.getDotori() %></td>
		        </tr>
        	<% } %>
        <% } else { %>
        	<tr>
        		<td colspan='6'>조회된 데이터가 없습니다.</td>
        	</tr>
        <% } %>
    </table>
    <div id="pageBar">
        <%= pageBar %>
    </div>
</div>

<style>
	#membersListContainer>table{
	    width: 100%;
	    border-top: 3px solid #eee;
	    border-bottom: 3px solid #eee;
	    border-collapse: collpase;
	    margin-bottom: 20px;
	}
	
	#membersListContainer>table th{
	    height: 40px;
	    border-bottom: 1px solid #eee;
	    vertical-align: middle;
	}
	
	#membersListContainer>table td{
	    height: 30px;
	    border-bottom: 1px solid #eee;
	    vertical-align: middle;
	}
	
	#membersListContainer>#pageBar span{
	    margin: 0 10px;
	}
	
	.hide{
		display: none;
	}
</style>