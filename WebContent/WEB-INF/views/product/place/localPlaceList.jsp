<%@page import="product.model.vo.Place"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<body>
<%
   String placeLoc = (String)request.getAttribute("placeLoc");
   Integer totalContents = (Integer)request.getAttribute("totalContents");
   List <Place> list = (List<Place>)request.getAttribute("placeList");
   Integer cpage = (Integer)request.getAttribute("cpage");
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
   table#tbl-localPlace img {border-radius: 30px 30px 30px 30px; margin: 20px; width: 120px; height: 120px;}
   table#tbl-localPlace tr {border:none; border-bottom:2px solid lightgray;}
   table#tbl-localPlace td {border:none;} 
   div.localDiv {margin-top: 20px; width: 1000px; margin: 0 auto; margin-top:40px; text-align: left;}
   div.sticky{top: 0; position: sticky;}

/*    div#localPlaceList {display:inline; float:left; margin-right: 10px;}
   div#map {display:inline; float:left; margin: 20px;} */
</style>



   <div class="localDiv">
   <h3 class="title"><strong><%=placeLoc%>의 숙소 &nbsp; <%=totalContents%>개</strong></h3>
   <%-- <%=cpage %> --%>
   <hr />

<!-- <button id="price" value="100000" onclick="price();"><span>가격</span></button> -->
<div style="margin:10px auto; text-align:center; width:1000px;">
<div id="localPlaceList" style="width:45%; float: left;"></div>     
     
<div id="map" class="sticky" style="width:50%; height: 480px; float: right;"></div>

<%-- <div id='pageBar'><%=request.getAttribute("pageBar") %></div> --%>
<!-- <div id="map" style="width:100px;height:350px; padding-right:20px"></div> -->

<div id="moreBtn" style="clear: both;">
<% if(totalContents > 3){ %>
<button type="button" id="addBtn" class="btn btn-secondary my-2 my-sm-0" onclick="moreList();"><span>더보기</span></button>
<% } %>
</div>

</div>
</div>



<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6532919606354bf57f848f10bc1860ce&libraries=services"></script>

<script>
var cpage = 2;
$(document).ready(function(){
   list();
   //price();
   //searchFilter();
});

//가격필터 검색
var prices = [];
function searchFilter(){
   $.ajax({
      url : "<%=request.getContextPath()%>/product/place/localPlaceList?placeLoc=<%=placeLoc%>",
       method : "POST",
       dataType : "json",
       success : function(data){
          var $table = $("<table id='tbl-localPlace'></table>");
          console.log(data);
          $.each(data, function(index, elem){
             if(price === elem.placePrice){
                
                var html = "<tr id='tr-localPlace'>";
                html += "<td><img src='<%=request.getContextPath()%>/upload/productProfile/" + elem.placeThumbnailRenamedFileName + "'/></td><br/>";
                html += "<td><strong>" + elem.placeName + "</strong><br/>" 
                      + "침실" + elem.placeRoomCount + "개&nbsp&nbsp&nbsp&nbsp"
                      + "최대인원&nbsp" + elem.placeAvailCount + "명<br/>"
                      + elem.placeFacility + "</td>";
                html += "<td><img style='width:20px; height: 20px;' src='<%=request.getContextPath()%>/images/red-star-.png/>'" + elem.placeScore + "</td>";
                html += "</tr>";
                $table.append(html);
                addr = elem.placeAddr;
                addrs.push(addr);
                kakaomap();
             } else {
                return;
             }
             
             });
             $("#localPlaceList").html($table);
         },
         async: false
   });
}


var addrs = [];
var names = [];
//카카오 지도
function kakaomap(){
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 7 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

for(var i = 0; i < addrs.length; i++){
   
   // 주소로 좌표를 검색합니다
   geocoder.addressSearch(addrs[i], function(result, status) {

       // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {

           var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

           // 결과값으로 받은 위치를 마커로 표시합니다
           var marker = new kakao.maps.Marker({
               map: map,
               position: coords
           });

           // 인포윈도우로 장소에 대한 설명을 표시합니다
           var infowindow = new kakao.maps.InfoWindow({
               content: '<div style="width:150px;text-align:center;padding:6px 0;">' + result[0].address_name + '</div>'

           })
           infowindow.open(map, marker);
         console.log(result[0]);
         console.log(names);
        
           // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
           map.setCenter(coords);
       }  //end of if
   });   //end of geocoder.addressSearch
} //end of for

}   //end of function

function list(){
      $.ajax({
         url : "<%=request.getContextPath()%>/product/place/localPlaceList?placeLoc=<%=placeLoc%>",
          method : "POST",
          dataType : "json",
          success : function(data){
             var $table = $("<table id='tbl-localPlace'></table>");
             console.log(data);
             $.each(data, function(index, elem){
                <%-- <img class="listImage" onclick="javascript:location.href='<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=<%=p.getPlaceId()%>';" src="<%=request.getContextPath()%>/upload/productProfile/<%=p.getPlaceThumbnailRenamedFileName()%>" alt="" /> --%>
                <%-- <h5><a href="<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=<%=p.getPlaceId()%>"><%=p.getPlaceName()%></a></h5> --%>
                   var html = "<tr id='tr-localPlace'>";
                   <%-- html += "<td><img onclick='javascript:location.href=" + "'<%request.getContextPath()%>/product/place/placeViewDetails?placeId=" + elem.placeId + "'" + "'" + "src='<%=request.getContextPath()%>/upload/productPorfile/" + elem.placeThumnailRenamedFileName + "'/></td><br/>"; --%>
                   html += "<td><img src='<%=request.getContextPath()%>/upload/productProfile/" + elem.placeThumbnailRenamedFileName + "'/></td><br/>";
                   html += "<td><strong><a href='<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=" + elem.placeId + "'>" + elem.placeName + "</a></strong><br/>" 
                         + "침실" + elem.placeRoomCount + "개&nbsp&nbsp&nbsp&nbsp"
                         + "최대인원&nbsp" + elem.placeAvailCount + "명<br/>"
                         + elem.placeFacility + "</td>";
                   html += "<td><img style='width:20px; height: 20px;' src='<%=request.getContextPath()%>/images/red-star-.png'/>" + elem.placeScore + "</td>";
                   html += "</tr>";
                   $table.append(html);
                
                   addr = elem.placeAddr;
                   //console.log(addr);
                   addrs.push(addr);
                   //console.log(addrs);
                  // prices = elem.placePrice;
                   //console.log(prices);
                   name = elem.placeName;
                   console.log(name);
                   names.push(name);
                   console.log(names);
                   kakaomap();
                });
                $("#localPlaceList").html($table);
                
            },
            async: false
      });

   };


   function price(){
      $('#price').click(function(){
         $.ajax({
            url : "<%=request.getContextPath()%>/product/place/localPlaceList?placeLoc=<%=placeLoc%>",   
            method : "POST",
            dataType : "json",
            success : function(data){
               $('#localPlaceList').empty();
               
                   var $table = $("<table id='tbl-localPlace'></table>");
                   console.log(data);
                   $.each(data, function(index, elem){
                   if($('#price').val() == elem.placePrice ){
                      
                         var html = "<tr id='tr-localPlace'>";
                         html += "<td><img src='<%=request.getContextPath()%>/upload/productPorfile/" + elem.placeThumbnailRenamedFileName + "'/></td><br/>";
                         html += "<td><h1><strong>" + elem.placeName + "</strong></h1><br/>" 
                               + "침실" + elem.placeRoomCount + "개&nbsp&nbsp&nbsp&nbsp"
                               + "최대인원&nbsp" + elem.placeAvailCount + "명<br/>"
                               + elem.placeFacility + "</td>";
                         html += "<td>" + elem.placeScore + "</td>";
                         html += "</tr>";
                         $table.append(html);
                      
                         prices = elem.placePrice;
                        console.log(prices);
                   }
                      });
                      $("#localPlaceList").html($table);
                  },
                  async: false
         })

      })
   }   
   

   
   function moreList(){
       var totalContents = <%=totalContents%>;
      var addListHtml = "";
         
      $.ajax({
         url : "<%=request.getContextPath()%>/product/place/localPlaceList?placeLoc=<%=placeLoc%>",   
         method : "POST",
         dataType : "json",
         data : {"cpage" : cpage},
         success : function(data){
            var $table = $("#tbl-localPlace");
            console.log(data);
            var addListHtml = "";
            if(data.length > 0){
               for(var i = 0; i < data.length; i++){
                  addListHtml += "<tr id='tr-localPlace'>";
                  addListHtml += "<td><img src='<%=request.getContextPath()%>/upload/productProfile/" + data[i].placeThumbnailRenamedFileName + "'/></td><br/>";
                  addListHtml += "<td><strong><a href='<%=request.getContextPath()%>/product/place/placeViewDetails?placeId=" + data[i].placeId + "'>" + data[i].placeName + "</a></strong><br/>" 
                            + "침실" + data[i].placeRoomCount + "개&nbsp&nbsp&nbsp&nbsp"
                            + "최대인원&nbsp" + data[i].placeAvailCount + "명<br/>"
                            + data[i].placeFacility + "</td>";           
                  addListHtml += "<td><img style='width:20px; height: 20px;' src='<%=request.getContextPath()%>/images/red-star-.png'/>" + data[i].placeScore + "</td>";
                  addListHtml += "</tr>";
                     
                      addr = data[i].placeAddr;
                      //console.log(addr);
                      addrs.push(addr);
                     // console.log(addrs);
                      
                     name = data[i].placeName;
                   console.log(name);
                   names.push(name);
                   console.log(names);
                     kakaomap();
                     
               }
               $table.append(addListHtml);
            }
            
            if((totalContents - (cpage)*3) <= 0){
                   $('#addBtn').remove();
              }

            cpage++;
              $("#localPlaceList").html($table);
              
         },
         async: false
      });
   }
   

</script>
</body>
</html>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>