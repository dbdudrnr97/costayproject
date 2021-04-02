<%@page import="member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>
<%
	String msg = (String)session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");	
	String title = (String)session.getAttribute("title");
	if(title != null) session.removeAttribute("title");
	
 	System.out.println("msg@header.jsp = " + msg);
	String loc = (String) request.getAttribute("loc");
 	System.out.println("loc@header.jsp = " + loc);
	Member memberLoggedIn = (Member) session.getAttribute("memberLoggedIn");
 	System.out.println("memberLoggedIn@header.jsp = " + memberLoggedIn);

	//서버로 전송된 쿠키값 확인
	String saveId = null;
	Cookie[] cookies = request.getCookies(); //쿠키 배열을 리턴
	if(cookies!=null){
		for(Cookie c : cookies){
 			System.out.println(c.getName() + " : " + c.getValue());
			if("saveId".equals(c.getName())){
				saveId = c.getValue();
				break;
			}
		}
 		System.out.println("saveId@header.jsp = " + saveId);
	}	
	
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COSTAY</title>
<%-- application별칭(context-root, context-path)를 동적으로 얻어오기  /mvc --%>
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" /> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquerycssmenu.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquerycssmenu.js"></script>
<script>
<% if(msg != null) { %> alert("<%= msg %>"); <% } %>
<% if(loc != null) { %> location.href = "<%= loc %>"; <% } %>

$("#menu").change(function(){
	var type = $(this).val(); // memberId | memberName | gender
	var $divSearchTypes = $(".search-type");
	$divSearchTypes.hide().filter("#search-" + type).css('display','inline-block');
});


function getCookie(name) {
    var cookie = document.cookie;
    if (document.cookie != "") {
        var cookie_array = cookie.split("; ");
        for (var index in cookie_array) {
            var cookie_name = cookie_array[index].split("=");
            if (cookie_name[0] == "popupYN") {
                return cookie_name[1];
            }
        }
    }
    return;
}

function openPopup(url) {
    var cookieCheck = getCookie("popupYN");
    var popupWidth = 350;
    var popupHeight = 350;
	
    var popupX = (window.screen.width / 2) - (popupWidth / 2);
    // 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

    var popupY= (window.screen.height / 2) - (popupHeight / 2);
    // 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

    if (cookieCheck != "N") 
	    window.open(url, '', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
}

</script>
</head>
<!-- 팝업은 코딩할 때는 잠시 주석 처리하고, 나중에 주석 풀기 -->
<body> <!--  onload="javascript:openPopup('views/popup.html')">-->
	<header class="sub">

	<form class="form-inline my-2 my-lg-0">
		<div style="position:absolute;">
			<div style="position:relative;  top:105px; width:200px;">
			<img src="<%=request.getContextPath()%>/images/mainLogo.png" onclick="location.href='<%=request.getContextPath()%>';" width="100px" height="100px">
			</div>
			<div style="position:relative;  top:30px; left:120px; width:200px;">
			<%if(title == null){ %>
			<h1>COSTAY</h1>
			<%}else {%>
			<h1><%= title%></h1>
			<%} %>
			</div>		
			<!-- 로그인 안 된 경우 -->
			<div style="position:relative; top:-20px; left:720px; width:120px;" id="myjquerymenu" class="jquerycssmenu">
			<%if(memberLoggedIn == null){ %>
		    <a href="<%=request.getContextPath()%>/member/login">로그인</a>
			<a href="<%=request.getContextPath()%>/member/enroll">회원가입</a>		
			</div>			
			<div style="position:relative; top:-35px; left:850px; width:100px;" id="myjquerymenu" class="jquerycssmenu">
			<ul>
			<li><a href="#">메뉴</a>
			  <ul>
				  <li><a href="<%= request.getContextPath()%>/corpInfo">회사소개</a></li>
				  <li><a href="<%= request.getContextPath()%>/board/review/boardList">커뮤니티</a></li>
			 	  <li><a href="<%= request.getContextPath()%>/board/notice/boardList">고객센터</a></li>
			  </ul>
			</li>
			</ul>
			</div>
			<%} else{ %>
			<%-- 로그인 성공 --%>
       		<div style="float: left;">
       		<br />
       		<%=memberLoggedIn.getMemberName()%> 님
       		</div>
		     <div style="float: right;">
       		<img style="width: 40px; height: 40px; border-radius: 20px;"
				src="<%=request.getContextPath()%>/upload/memberProfile/<%=memberLoggedIn.getMemberProfileRenamedFileName()%>"/>
     		 </div>
			<div style="position:relative; top:-7px; right:-115px; width:100px;" id="myjquerymenu" class="jquerycssmenu">
			<ul>
			<li><a href="#">메뉴</a>
			  <ul>
				  <li><a href="<%= request.getContextPath()%>/corpInfo">회사소개</a></li>
				  <li><a href="<%=request.getContextPath()%>/member/myPage">나의 정보</a></li>
				  <li><a href="<%=request.getContextPath()%>/member/logout">로그아웃</a></li>
				  <li><a href="<%=request.getContextPath()%>/member/delete">회원탈퇴</a></li>
			 	  <li><a href="<%= request.getContextPath()%>/board/review/boardList">커뮤니티</a></li>
			 	  <li><a href="<%= request.getContextPath()%>/board/notice/boardList">고객센터</a></li>
	  		 	  <%if(MemberService.ADMIN_MEMBER_ROLE == memberLoggedIn.getMemberRole()){ %>
	              <li><a href="<%=request.getContextPath()%>/admin/adminPage">관리자페이지</a></li>
	              <%} %>
			  </ul>
			</li>
			</ul>
			</div>
			<%} %>
		</div>
	</form>


	<!-- 메인메뉴 시작 -->
	<%-- <nav>
		<ul class="main-nav">
			<li class="home"><a href="<%=request.getContextPath()%>">Home</a></li>
			<li id="notice"><a href="#">공지사항</a></li>
			<li id="board"><a href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
			<!-- 관리자메뉴 : 관리자만 노출 --!>
			<%if(memberLoggedIn != null && MemberService.ADMIN_MEMBER_ROLE.equals(memberLoggedIn.getMemberRole())){ %>
			<li id="admin-memberList"><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li>
			<%} %>
		</ul>
	</nav> --%>
	<!-- 메인메뉴 끝-->
	
</header>
<section id="content">
