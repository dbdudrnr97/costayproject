<%@page import="product.model.vo.Place"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Place> placeList = (List<Place>)request.getAttribute("list");
	Place place = (Place)request.getAttribute("place"); 	
%>
<style>
	table img { width: 300px; height: 300px;}
	.place{overflow:hidden; width:300px; height:200px; padding:0 10px 10px; margin:0 auto;}
/* 	.type li{overflow:hidden; clear:both: margin:10px 0 0; color:#2d2c2d; list-style:none; border-bottom:2px solid lightgray; position:relative;}
	.type li img{display;inline; float:left; position:absolute; width:200px; height:800px;}
	.type li a{text-decoration:none; margin-left:200px;} */
</style>
<%-- <ul class="type">
	<li>
		<span style='margin-left:50px'>숙소썸네일</span>
		<span>숙소이름</span>
		<span>숙소점수</span>
		<span>숙소방개수</span>
		<span>숙소시설</span>
		<span>숙소지역</span>
	</li>

<% for(Place p : placeList){ %>
<li>
<a href="#" style='margin-left:50px'></a>
<%=p.getPlaceThumbnailRenamedFileName()%>
<img src="<%=request.getContextPath()%>/upload/productProfile/20210302_225602537_851.jpg" alt="썸네일미리보기" />
<a href="#"><%=p.getPlaceName() %></a>
<a href="#"><%=p.getPlaceScore() %></a>
<a href="#"><%=p.getPlaceRoomCount() %></a>
<a href="#"><%=p.getPlaceFacility() %></a>
<a href="#"><%=p.getPlaceLoc() %></a>
</li>
<% } %>

</ul> --%>

<div id="localPlaceDiv"">
	 <table id="localPlaceTable" border="1" style="border:none; padding:0;">
<!-- 	 <thead>
		<tr>
			<td>숙소썸네일</td>
			<td>숙소이름</td>
			<td>숙소점수</td>
			<td>숙소방개수</td>
			<td>숙소시설</td>
			<td>숙소지역</td>
		</tr>
	 </thead> -->
	 <tbody id="localPlaceTbody">
	 
		<%for(Place p : placeList){ %>
		<tr>
			<td id="td01" style="border:none; border-bottom:2px solid lightgray;">
				<div id="place" class="place" style="border-radius: 30px 30px 30px 30px; margin: 20px;">
					<img src="<%=request.getContextPath()%>/upload/productProfile/20210302_225602537_851.jpg" alt="placeThumbnail" />
					<td style="border:none; border-bottom:2px solid lightgray;">
						<h1><strong><%=p.getPlaceName() %></strong></h1>
						침실 <%=p.getPlaceRoomCount() %>개 &nbsp&nbsp 	● &nbsp&nbsp 최대 인원 <%=p.getPlaceAvailCount() %>명
					
						<%=p.getPlaceFacility() %>
					</td>
					<td style="border:none; border-bottom:2px solid lightgray;"><%=p.getPlaceScore() %></td>

			
				</div>
			</td>
			<%-- <td><img src="<%=request.getContextPath()%>/upload/productProfile/20210302_225602537_851.jpg" alt="placeThumbnail" /></td>
			<td><%=p.getPlaceName() %></td>
			<td><%=p.getPlaceScore() %></td>
			<td><%=p.getPlaceRoomCount() %></td>
			<td><%=p.getPlaceFacility() %></td>
			<td><%=p.getPlaceLoc() %></td> --%>
		</tr>
		<%} %>
    </tbody>
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>