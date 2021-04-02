<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = request.getParameter("memberId");
	boolean available = (boolean)request.getAttribute("available");
%>
<!-- !!!!!!!!!!!!!아이디중복검사!!!!!!!!!! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.js"></script>
<script>
function checkIdDuplicate(){
	//1. 아이디 유효성 검사
	var $memberId = $(memberId_);
	if(/^[a-zA-Z0-9_]{4,}$/.test($memberId.val()) == false){
		alert("유효한 아이디를 입력해주세요.");
		$memberId.select();
		return;
	}
	
	//2. 중복검사 
	//var $frm = $("[name=checkIdDuplicateFrm]");
	var $frm = $(document.checkIdDuplicateFrm);// name값은 document에서 바로 접근가능
	//아이디값 세팅
	$frm.find("[name=memberId]")
		.val($memberId.val());
	$frm.submit();
}

function confirmMemberId(){
	/* opener : 팝업을 연 부모창의 window 객체를 리턴
	opener.document.memberEnrollFrm (부모창의 memberEnrollFrm 리턴)*/
	var $frm = $(opener.document.memberEnrollFrm);
	$frm.find("#memberId_").val("<%= memberId %>");
	$frm.find("#idValid").val(1);
	close();
}
</script>

<style>
	div#checkId-container{text-align:center; padding-top:50px;}
	span#duplicated{color:red; font-weight:bold;}
</style>
</head>
<body>
	<div id="checkId-container">
	<%if(available) {%>
		[<%= memberId %>]는 사용가능합니다.
		<br><br>
		<input type="button" value="사용하기" onclick="confirmMemberId();" />
		
	<%} else{ %>
		[<span id="duplicated"><%= memberId %></span>]는 이미 사용중인 아이디입니다.
		<br><br>
		<form action="<%=request.getContextPath()%>/member/checkIdDuplicate"
			  method="POST"
			  name="checkIdDuplicateFrm">
			  <input type="text" name="memberId" id="memberId_" placeholder="아이디"/>
			  <input type="button" value="중복검사" onclick="checkIdDuplicate();" />
		</form>
	<% }%>
	</div>
</body>
</html>
