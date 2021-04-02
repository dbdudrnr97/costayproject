<%@page import="java.util.Iterator"%>
<%@page import="product.model.vo.Place"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<Place> list = (List<Place>)request.getAttribute("placeList");
	Integer totalContents = (Integer)request.getAttribute("totalContents");
	Integer type = (Integer)request.getAttribute("type");
	String location = (String)request.getAttribute("location");
	String price = (String)request.getAttribute("price");
%>

<div class="list">
	<h3 class="title"><strong>전체 숙소 &nbsp; <%=totalContents%>개</strong></h3>
	<hr />
	
  <div>
	<div>
	  <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath() %>/place/placeList" method="POST">
		<select class="form-control mr-sm-2" name="loc">
			<option value="notSelected">지역을 선택하세요.</option>
			<option value="서울" <%="서울".equals(location) ? "selected" : "" %>>서울</option>
			<option value="경기" <%="경기".equals(location) ? "selected" : "" %>>경기</option>
			<option value="강원" <%="강원".equals(location) ? "selected" : "" %>>강원</option>
			<option value="경상" <%="경상".equals(location) ? "selected" : "" %>>경상</option>
			<option value="전라" <%="전라".equals(location) ? "selected" : "" %>>전라</option>
			<option value="충청" <%="충청".equals(location) ? "selected" : "" %>>충청</option>
			<option value="제주" <%="제주".equals(location) ? "selected" : "" %>>제주</option>
		</select>
  		<select class="form-control mr-sm-2" name="price">
			<option value="notSelected">가격을 선택하세요.</option>
			<option value="30000" <%="30000".equals(price) ? "selected" : "" %>>30000 ~ </option>
			<option value="70000" <%="70000".equals(price) ? "selected" : "" %>>70000 ~ </option>
			<option value="100000" <%="100000".equals(price) ? "selected" : "" %>>100000 ~ </option>
			<option value="300000" <%="300000".equals(price) ? "selected" : "" %>>300000 ~ </option>
			<option value="500000" <%="500000".equals(price) ? "selected" : "" %>>500000 ~ </option>
		</select>
	    <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
	  </form>
  	 <br>
  	 </div>
		<table class="table table-borderless" id="place-table">

		<%int i = 0; %>
		<%for(Place p : list) { %>
		<%if(i==0){ %>
		<tr>
		<%} %>
			<td>
				<div class="listDiv">
					<img class="listImage" onclick="javascript:location.href='<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=<%=p.getPlaceId()%>';" src="<%=request.getContextPath()%>/upload/productProfile/<%=p.getPlaceThumbnailRenamedFileName()%>" alt="" />
					<div><img src="<%=request.getContextPath()%>/images/redStar.png" alt="" style="width: 17px; height: 14px;"/> <%=p.getPlaceScore() %></div>
					<h5><a href="<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=<%=p.getPlaceId()%>"><%=p.getPlaceName()%></a></h5>
					<strong>₩&nbsp;<%=p.getPlacePrice()%></strong>~/인
				</div>
			</td>
							
		<%
			i++;
			if(i==3){ %>
				</tr>
		<%i=0;
			}
		%>
		<%} %>
		</table>
		<div id="more-list">
		
		</div>
 	  </div>
 	  <%if(list.size() >= 9){%>
 	  	<input type="button" id="placeList-btn" class="btn btn-secondary my-2 my-sm-0" value="더보기" />
 	  <%} %>
 	  <script>
var cpage = 2;
var i = 0;
var html = "";
var finish = 0;
$("#placeList-btn").click(function(){
   var totalContents = <%=totalContents%>;
   $.ajax({
      url : "<%=request.getContextPath()%>/place/placeViewMore",
      method : "POST",
      data : { "type" : <%=type%>, "cpage" : cpage, "location" : "<%=location%>", "price" : "<%=price%>" },
      dataType : "json",
      success : function(data){

         
//          var $table = $("<table id='tbl-member'></table>");
         var $table = $("<table class='table table-borderless' id='place-table'></table>");
         $.each(data, function(index, elem){
        	 console.log(elem);
        	 console.log(data);
               if(i==0){ 
                  html += "<tr>";
               }
               html += "<td>";
               html += "<div class='listDiv'>";
               html += "<img class='listImage' src='<%=request.getContextPath()%>/upload/productProfile/" + elem.placeThumbnailRenamedFileName + "'/>";	
               html += "<div><img src='<%=request.getContextPath()%>/images/redStar.png' alt='' style='width: 17px; height: 14px;'/>" + elem.placeScore + "</div>";
               html += "<h5><a href='<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=";
               html +=  elem.placeId + "'>" +  elem.placeName + "</a></h5>";
               html += "<strong>₩&nbsp;" + elem.placePrice + "</strong>~/인";
               html += "</div>";
               html += "</td>";
               i++;
               if(i==3){ 
                  html += "</tr>";
                  i=0;
               }
         });
         if((totalContents - (cpage)*9) <= 0){
             $('#placeList-btn').remove();
         }
         $table.append(html);
         cpage++;
         $("div#more-list").html($table);
      },
      error : function(xhr, status, err){
         console.log(xhr, status, err);
      }
   });
});

</script>
</div>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>