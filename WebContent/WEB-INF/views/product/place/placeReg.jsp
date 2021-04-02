<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Date regChkinDate = Date.valueOf(request.getParameter("regChkinDate"));
	int guestCount = Integer.parseInt(request.getParameter("guestCount"));
	String placeId = request.getParameter("placeId");
%>
<div class="sub_title">
<h3><strong>체험 예약</strong></h3>
<hr />
</div>
<div style="text-align:left; width:800px; margin:0 auto;">
	<form id="reg-frm"
		action="<%=request.getContextPath()%>/product/place/placeReg" method="post">
		<ul style="list-style:none";>
			<li>
				<label for="memberId">회원아이디</label>
				<input type="text" name="memberId" id="memberId" value="<%=memberLoggedIn.getMemberId()%>" readonly/>
			</li>
			<li>
				<label for="guestCount">게스트수</label>
				<input type="number" name="guestCount" id="guestCount" value="<%=guestCount%>" min="1" max="20" required/>
			</li>
			<li>
				<label for="regChkinDate">체크인날짜</label>
				<input type="date" name="regChkinDate" id="regChkinDate" value="<%=regChkinDate%>" required/>
			</li>
			<li>
				<label for="regChkoutDate">체크아웃날짜</label>
				<input type="date" name="regChkoutDate" id="regChkoutDate" value="" required/>
			</li>
		</ul>
		<input type="hidden" name="placeId" value="<%=placeId%>" />
		<input type="submit" value="예약신청" style="margin-left:100px;"/>
	</form>
</div>
<!-- <input type="button" value="test" id="test" /> -->
<script>
	//날짜 유효성 검사
	$("#reg-frm").submit(function(){
		var chkinDate = new Date($("#regChkinDate").val());
		var chkoutDate = new Date($("#regChkoutDate").val());
		var now = new Date();
		
		
		if(chkinDate > chkoutDate){
			alert('체크아웃 날짜를 확인해주세요.');
			return false;
		}
		
		if(chkinDate < now){
			alert('체크인 날짜를 확인해주세요.');
			return false;
		}
		
		return true;
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>