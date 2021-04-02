<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Member member = (Member)request.getAttribute("member"); 
%>
<style>
#view-table tr td:first-child{
	width: 90px;
}
</style>
<div class="sub_title">
<h1>나의 정보</h1>
<hr />
</div>
<div  style="margin:0 auto; text-align:center; width:400px;">
<table id="view-table" class="table  table-bordered">
	<tr>
		<td colspan="3">
		<img src="<%=request.getContextPath()%>/upload/memberProfile/<%=member.getMemberProfileRenamedFileName()%>"
		width="150px" height="150px"></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><%=member.getMemberId() != null ? member.getMemberId() : "" %></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><%=member.getMemberName() != null ? member.getMemberName() : "" %></td>
	</tr>
	<tr>
		<td>여권번호</td>
		<td><%=member.getMemberPassport() != null ? member.getMemberPassport() : "" %></td>
	</tr>
	<tr>
		<td>번호</td>
		<td><%=member.getPhone() != null ? member.getPhone() : "" %></td>
	</tr>
	<tr>
		<td>성별</td>
		<td><%=member.getGender() != null ? member.getGender() : "" %></td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td><%=member.getBirth() != null ? member.getBirth() : "" %></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><%=member.getMemberAddress() != null ? member.getMemberAddress() : "" %></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><%=member.getMemberEmail() != null ? member.getMemberEmail() : "" %></td>
	</tr>
	<tr>
		<td>가입일</td>
		<td><%=member.getMemberEnrollDate() != null ? member.getMemberEnrollDate() : "" %></td>
	</tr>
</table>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>