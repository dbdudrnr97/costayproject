<%@page import="member.model.vo.Registration"%>
<%@page import="product.model.vo.Place"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	Registration reg = (Registration)request.getAttribute("reg");
	Place place = (Place)request.getAttribute("place");
%>
<style>
	div.detailPlace{
		margin-top:300px;
		width:500px;
		height:400px;
	    margin: 100px auto;
	    display:float;
	    text-align:center;
	    position:relative;
	}
	div.detailMain{
		width:345px;
		height:380px;
	    display:inline-block;
	    position:absolute;
	    left:10px;
	}
	div.detailSub{
		width:125px;
		height:150px;
	    display:inline-block;
	    text-align: left;
	    padding-left:30px;
	    position:absolute;
	    right:10px;
	    top: 75px;
	}
	img.detailImage{
		width:300px;
	 	height:150px;
	 	border-radius: 30px 30px 30px 30px;
	}
</style>

  <div class="sub_title">
	  <h3><strong><a href="<%=request.getContextPath()%>/member/placeRegView">숙소</a> &nbsp;&nbsp;>&nbsp;예정된 숙소</strong></h3>
	  <hr />
  </div>
  
  <div class="detailPlace">
  	  <div class="detailMain">
		  <div style="margin-top:10px; padding-left:10px; text-align:left">
	  	  	  <h5><strong>숙소 상세 정보</strong></h5>
	  	  	  <span><%=place.getPlaceAddr()%></span><br>
  	  	  </div>
  	  	  <hr />
  	  	  <!-- 추후 숙소 사진으로 변경 요망 -->
		  <img class="detailImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=place.getPlaceThumbnailRenamedFileName()%>" alt="" />
	      <div style="text-align:left; padding-left:10px;">
		      <h5 style="margin-top:10px;"><strong><%=place.getPlaceName()%></strong></h5>
		      <h3 style="margin-top:10px;"><strong><%=reg.getRegChkinDate()%></strong></h3>
	      </div>
	      <hr />
	      <div style="width:170px; height: 50px; display:inline-block; text-align:left;">
	      	<span>게스트 <%=reg.getGuestCount()%>명</span><br>
	      	<span><%=reg.getGuestCount()*place.getPlacePrice()%> 원</span>
	      </div>
	      <div style="width:170px; height: 50px; display:inline-block; text-align:right;">
   	      	<span><strong>예약번호</strong></span><br>
	      	<span><%=reg.getRegId()%></span>
	      </div>  
  	  </div>
  	  <div class="detailSub">
  	      <span>상태</span><br>
  	      <div style="text-align:right; font-size:10px; color:orange; padding-right:30px;">
	  	      <span>
	  	      <%if(reg.getRegPayYN()=='Y'){%>
	  	      	결제완료
	  	      <%}else if(reg.getRegPayYN()=='N'){ %>
	  	      	결제대기
	  	      <%}else if(reg.getRegDelFlag()=='C'){%>
	  	               승인대기
	  	      <%}%>
  	    	  </span><br>
  	      </div>
  	      <span>호스트</span><br>
  	      <span>위치안내</span><br>
  	      <span>영수증 보기</span><br>
  	      <span>예약변경</span>
  	      <p>예약취소</p>
  	  </div>
  </div>
  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>