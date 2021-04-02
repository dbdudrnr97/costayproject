<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
String playId = (String)request.getAttribute("playId");
%>
<script>
$(document).ready(function(){
	var bool = confirm('이미지 수정작업도 진행 하시겠습니까?');
	
	if(bool){
		location.href="<%=request.getContextPath()%>/product/play/playImgUpdate?playId=<%=playId%>";
	} else{
		alert("숙소정보수정이 완료되었습니다.");
	}
})
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>