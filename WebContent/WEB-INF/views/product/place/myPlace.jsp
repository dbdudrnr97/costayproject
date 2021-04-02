<%@page import="product.model.vo.Place"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Place> placeList = (List<Place>)request.getAttribute("list");
%>
<style>
	table img { width: 300px; height: 300px;}
</style>
<div class="sub_title">
<h3><strong>내 숙소 정보</strong></h3>
<hr />
</div>
<div id="resultDiv" style="margin:0 auto; text-align:center; width:800px;">
	<table id="resultTable" border="1"  class="table  table-bordered">
		<tr>
			<td>숙소썸네일</td>
			<td>숙소이름</td>
			<td>숙소지역</td>
		</tr>
		<%int i = 0; %>
		<%for(Place p : placeList){ %>
		<tr>
			<td style="margin: 0 auto; text-align:center; vertical-align:middle;"><img src="<%=request.getContextPath()%>/upload/productProfile/<%=p.getPlaceThumbnailRenamedFileName()%>" style="width:150px; height:150px;"alt="placeThumbnail" /></td>
			<td style="vertical-align:middle;"><a href="<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=<%=p.getPlaceId()%>"><%=p.getPlaceName()%></a></td>
			<td style="vertical-align:middle;"><%=p.getPlaceLoc() %></td>
			<td style="vertical-align:middle;">
				<input type="button" value="수정" onclick="location.href='<%=request.getContextPath()%>/product/place/productUpdate?placeId=<%=p.getPlaceId()%>';" />
				<input type="button" value="삭제" onclick="location.href='<%=request.getContextPath()%>/product/place/productDelete?placeId=<%=p.getPlaceId()%>';"/>
				<input type="button" value="예약확인" id="regChk-btn<%=i%>"/>
				<script>
					$("#regChk-btn<%=i++%>").click(function(){
						$.ajax({
							url:"<%=request.getContextPath()%>/product/place/myReg",
							method : "GET",
							dataType : "json",
							data : {"placeId":"<%=p.getPlaceId()%>"},
							success: function(data){
								var $table = $("<table id='reg-table' border='1'><tr><th>예약날짜</th><th>체크인날짜</th><th>체크아웃날짜</th><th>인원</th><th>게스트아이디</th></tr></table>");
								$.each(data, function(index, elem){
									var html = "<tr>";
									html += "<td>"+elem.regDate+"</td>";
									html += "<td>"+elem.regChkinDate+"</td>";
									html += "<td>"+elem.regChkOutDate+"</td>";
									html += "<td>"+elem.guestCount+"</td>";
									html += "<td>"+elem.regMemberId+"</td>";
									html += "<td><input type='button' value='승인' onclick="+"location.href='<%=request.getContextPath()%>/product/place/regAccept?regId="+elem.regId+"';"+" /></td>";
									html += "<td><input type='button' value='거절' onclick="+"location.href='<%=request.getContextPath()%>/product/place/regCancel?regId="+elem.regId+"';"+" /></td>";		
									html += "</tr>";
									
									$table.append(html);
								});
								
								$("#resultDiv").append($table);
							},
							error:function(xhr, status, err){
								console.log(xhr, status, err);
							}
						});
					});
				</script>
			</td>
		</tr>
		<%}%>
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>