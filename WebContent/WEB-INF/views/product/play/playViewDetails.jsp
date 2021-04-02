<%@page import="java.util.Arrays"%>
<%@page import="product.model.vo.Play"%>
<%@page import="product.model.vo.ImageStore"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	Play play = (Play)request.getAttribute("play");
	List<ImageStore> list = (List<ImageStore>)request.getAttribute("list");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>체험 상세보기</title>
<style>
#btn-placeViewDt{
	background-color: #FFCD12;
}


#playVD-table{
	border: none;
	border-color: white;
	width: 1024px;
	margin: 50px auto;
	margin-bottom: 0px;
}


.playImgVD{
	max-width: 100%;
	height: 270px;
	object-fit: cover;
}
#playVDName{
	padding: 15px 20px 0px 0px;
	font-weight: bold;
	font-size: 25px;
	font-color: black;
	pointer-events: none;
	cursor: default;
}
#playVDAddr{
	padding-left: 10px;
}
.playRegDate{
	padding: 5px auto;
}
.facility{
	width: 1024px;
	margin: 5px auto;
}
#btn-playViewDt{
	background-color: #FFCD12;
	border-color: white;
	outline: 0;
	margin: 10px auto;
	padding: 7px 40px;
	border-radius: 10px
}
#reg-frm{
	width: 1024px;
	height: 60px;
	margin: 5px auto;
	background-color: #EAEAEA;
}
#welcome{
	width: 1024px;
	margin: 20px auto;
	font-weight: bold;
}
#playNote{
	width: 1024px;
	margin: 10px auto;
}

/* img.facility-image{
		width: 100px;
		height: 100px;
}

ul#Facility {
		list-style:none;
		margin:0px;
		padding:0px;
	} */
/* li {
	display:inline;
}
img {
	float:left;
} 

#facility-img > div {
	display:inline-block;
	margin:auto;
} */

</style>
</head>
<body>
	<table id="playVD-table" border="1">	
		<tr>
			<!-- 체험 이미지 -->
				<%if(list != null){
					for(ImageStore i : list) {%>
					<td><img class="playImgVD" img src="<%=request.getContextPath()%>/upload/productFile/<%=i.getRenamedFilename()%>"></td>
				<%} 
			}%>
		</tr>
		<tr>
			<!-- 체험이름 -->
		<td colspan="3" id="playVDName"><strong><a href="<%=request.getContextPath()%>/product/play/productView?playId=<%=play.getPlayId()%>"><%=play.getPlayName()%></a></strong></td>
		</tr>
		
		<tr>
			<!-- 숙소별점과 주소
			select round(avg(place_score),2) from place where place_ = ""
			 -->
			 <td colspan="3" id="playVDLoc"><img src="<%=request.getContextPath()%>/images/redStar.png" alt="" style="width: 17px; height: 14px;"/><%=play.getPlayScore() %>&nbsp&nbsp<%=play.getPlayLoc() %></td>
		  </tr>
			</table>
			<div style="margin:0 auto; text-align:center;">
			   <form id="reg-frm"
				action="<%=request.getContextPath()%>/product/play/playReg" method="get">
				<input class="playDetails" style="width:170px; margin-right:10px;" name="regChkinDate" type="date" />
				<input style="width:170px; margin-right:10px;" type="number" min="1" max="10" name="guestCount"/>
				<input type="hidden" name="playId" value="<%=play.getPlayId()%>" />		
				<label for="playPrice"><strong>₩<%= play.getPlayPrice() %>원 </strong><sub>/ 1회</sub></label>
				<input type="submit" id="btn-playViewDt" value="예약하기"/>			
			</form>
			</div>
		<hr />
	<h3><div id="welcome"><span><%=play.getCompany() %></span>님의 체험에 오신것을 환영합니다!</div></h3>
	<div id="playNote">
		<%=play.getPlayNote() %>
	</div>
	<div sytle="margin: 0 auto;">
	<article class="foodNequipment" style="width: 1024px; margin: 0 auto;">
	
	<%if(play.getPlayFood() != null){ %>
		<%=play.getPlayFood()%>
	<%}%>
	<%if(play.getPlayEquipment() != null){ %>
		<%=play.getPlayEquipment()%>
	<%}%>
	<%if(play.getPlayTransport() != null){ %>
		<%=play.getPlayTransport()%>
	<%}%>
	</article>
	</div>
  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>