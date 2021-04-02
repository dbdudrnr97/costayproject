<%@page import="java.util.Arrays"%>
<%@page import="product.model.vo.Place"%>
<%@page import="product.model.vo.ImageStore"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Place place = (Place)request.getAttribute("place");
	Registration reg = (Registration)request.getAttribute("reg");
	String placeId = (String)request.getAttribute("placeId");
	String regId = (String)request.getAttribute("regId");
	List<ImageStore> list = (List<ImageStore>)request.getAttribute("list");

	String facility = (String)request.getAttribute("facility");
	System.out.println("facility@jsp="+ facility);
	
	String[] facArr = facility.split(",");
	System.out.println("facArr@jsp="+ facArr);
	
	for(int i = 0; i < facArr.length; i++){
		System.out.println(facArr[i]);
	}
	

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>숙소 상세보기</title>
<style>

#placeVD-table{
	border: none;
	border-color: white;
	width: 1024px;
	margin: 50px auto;
	margin-bottom: 0px;
}

#plaveVD-table tr td{
	
}

.placeImgVD{
	width: 100%;
	height: 270px;
	object-fit: cover;
}
#placeVDName{
	padding: 15px 20px 0px 0px;
	font-weight: bold;
	font-size: 25px;
	font-color: black;
	pointer-events: none;
	cursor: default;
}
#placeVDAddr{
	padding-left: 10px;
}
.placeRegDate{
	padding: 5px auto;
}
.facility{
	width: 1024px;
	margin: 5px auto;
}
#btn-placeViewDt{
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
#placeNote{
	width: 1024px;
	margin: 10px auto;
}


img.facility-image{
		width: 100px;
		height: 100px;
}

ul#Facility {
		list-style:none;
		margin:0px;
		padding:0px;
	}
li {
	display:inline;
}
img {
	float:left;
} 

#facility-img > div {
	display:inline-block;
	margin:auto;
}
</style>
</head>
<body>
	<table id="placeVD-table" border="1">
		<tr>
			<!-- 숙소 이미지 -->
			<%for(ImageStore i : list) {%>
				<td><img class="placeImgVD" src="<%=request.getContextPath()%>/upload/productFile/<%=i.getRenamedFilename()%>"></td>
			<%} %>
		</tr>
		<tr>
			<!-- 숙소이름 -->
			<td colspan="3" id="placeVDName"><a href="<%=request.getContextPath()%>/product/place/productView?placeId=<%=place.getPlaceId()%>"><%=place.getPlaceName()%></a></td>
		</tr>
		
		<tr>
			<td colspan="3" id="placeVDAddr"><img src="<%=request.getContextPath()%>/images/redStar.png" alt="" style="width: 17px; height: 14px;"/><%=place.getPlaceScore() %>&nbsp&nbsp<%=place.getPlaceAddr() %></td>
		</tr>
	</table>
<!-- <td ><strong>언제 떠나실 예정이신가요? &nbsp;</strong> -->
	<div style="margin:0 auto; text-align:center;"><form id="reg-frm"
		action="<%=request.getContextPath()%>/product/place/placeReg" method="get">
		<input class="placeDetails" style="width:170px; margin-right:10px;" name="regChkinDate" type="date" />
		<input style="width:170px; margin-right:10px;" type="number" min="1" max="10" name="guestCount" id=""/>
		<input type="hidden" name="placeId" value ="<%=place.getPlaceId()%>" />
		<label for="placePrice"><strong> ₩ <%= place.getPlacePrice() %>원 </strong><sub>/ 1박</sub></label> 
		<input type="submit" id="btn-placeViewDt" value="예약하기"/>			
	</form>
	</div>
	<hr />
	<h3><div id="welcome"><span><%=place.getHostMemberId() %></span>님의 숙소에 오신것을 환영합니다!</div></h3>
	<div id="placeNote">
		<%=place.getPlaceNote() %>
	</div>

	<!-- 편의시설 위치 보려고 회색으로 표시 해놨고 나중에 변경될 예정  -->
	<article class="facility">
	<div style="margin:0 auto; text-align:center;>
	<th colspan="3">편의시설</th>
	<tr>
		<!-- {"취식","와이파이","주차장","바베큐장","tx","컴퓨터","에어컨"}; -->
		<td colspan="3">
			<div id="facility-img">
				<%
					if (Arrays.asList(facArr).contains("취식")) {
				%>
				<div>
					<img class="facility-image" alt="첨부이미지"
						src="<%=request.getContextPath()%>/images/cooking.png"
						value="취식" width=100px height=100px><br />
				</div>
				<%
					}
				%>
				<%
					if (Arrays.asList(facArr).contains("와이파이")) {
				%>
				<div>
					<img class="facility-image" alt="첨부이미지"
						src="<%=request.getContextPath()%>/images/wifi.png" value="와이파이"
						width=100px height=100px><br />
				</div>
				<%
					}
				%>
				<%
					if (Arrays.asList(facArr).contains("주차장")) {
				%>
				<div>
					<img class="facility-image" alt="첨부이미지"
						src="<%=request.getContextPath()%>/images/parking.png"
						value="주차장" width=100px height=100px><br />
				</div>
				<%
					}
				%>
				<%
					if (Arrays.asList(facArr).contains("바베큐장")) {
				%>
				<div>
					<img class="facility-image" alt="첨부이미지"
						src="<%=request.getContextPath()%>/images/bbq.png" value="바베큐장"
						width=100px height=100px><br />
				</div>
				<%
					}
				%>
				<%
					if (Arrays.asList(facArr).contains("TV")) {
				%>
				<div>
					<img class="facility-image" alt="첨부이미지"
						src="<%=request.getContextPath()%>/images/tv.png" value="TV"
						width=100px height=100px><br />
				</div>
				<%
					}
				%>
				<%
					if (Arrays.asList(facArr).contains("컴퓨터")) {
				%>
				<div>
					<img class="facility-image" alt="첨부이미지"
						src="<%=request.getContextPath()%>/images/computer.png"
						value="컴퓨터" width=100px height=100px><br />
				</div>
				<%
					}
				%>
				<%
					if (Arrays.asList(facArr).contains("에어컨")) {
				%>
				<div>
					<img class="facility-image" alt="첨부이미지"
						src="<%=request.getContextPath()%>/images/aircon.png"
						value="에어컨" width=100px height=100px><br />
				</div>
				<%}%>
			</div>
			</td>
		</tr> 
		<!-- div> -->
	</div>
	<script>
		var i = 0;
		var placeFacility = '취식,와이파이,주차장,바베큐장,tv,컴퓨터,에어컨';
		var facArr = placeFacility.split(",");
		console.log(facArr);

		var imgArr = $("#facility-img > img");

		$.each(imgArr, function(index, elem) {
			console.log("imgArr : " + $(elem).getAttribute('value'));
			console.log("facArr : " + facArr[i++]);
			if (facArr[i] == imgArr.val) {
				console.log(elem);
				console.log("hello");
				$(elem).attr("type", "checkbox");
			}
		});
	</script>

  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>