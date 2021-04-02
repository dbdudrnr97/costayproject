<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="sub_title">
<h3><strong>나의 정보</strong></h3>
<hr />
</div>

<div style="margin:0 auto; text-align:center; width:800px;">
	<table id="my-table" class="table   table-bordered">
		<tr >
			<td>
				<a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>">
				<i class="fas fa-id-badge fa-5x"></i><br> 내정보보기</a>		
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/member/memberUpdate?memberId=<%=memberLoggedIn.getMemberId()%>">
				<i class="fas fa-wrench fa-5x"></i><br>정보수정</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/product/place/myPlace?memberId=<%=memberLoggedIn.getMemberId()%>">
				<i class="fab fa-readme fa-5x"></i><br>내 숙소정보</a>
			</td>
		</tr>
		<tr style="height:150px; vertical-align:middle;">
			<td>				
				<a href="<%=request.getContextPath()%>/member/placeRegView">
				<i class="fas fa-home fa-5x"></i><br>예약된 숙소</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/member/playRegView">
				<i class="fas fa-running fa-5x"></i><br>예약된 체험</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/product/place/productEnroll">
				<i class="fas fa-plus-square fa-5x"></i><br>숙소등록</a>
			</td>
		</tr>
	</table>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>