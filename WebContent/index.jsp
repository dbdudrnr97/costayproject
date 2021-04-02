<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/mainheader.jsp" %>

<!-- 지역별 숙소 보기 -->
<!-- 클래스명 변경하면 적용 안되기때문에 변경하지 않는다. -->
<!-- 지역별 숙소 보기 -->
<div class="swiper-container">
   <ul class="swiper-wrapper">
       <li class="swiper-slide" >
          <img src="./images/seoul.jpg" onclick="javascript:location.href='<%= request.getContextPath()%>/product/place/localPlaceList?placeLoc=서울';" alt="서울이미지" />
         <div>
            <h2 id="seoul" data-lang="seoul">[seoul]</h2>
         </div>          
       </li>
       <li class="swiper-slide" >
          <img src="./images/jeju.jpg" onclick="javascript:location.href='<%= request.getContextPath()%>/product/place/localPlaceList?placeLoc=경기';" alt="제주이미지" />
          <div>
            <h2 id="gyeonggi" data-lang="gyeonggi">[gyeonggi]</h2>
         </div>   
       </li>
       
       
       
          <li class="swiper-slide" >
          <img src="./images/daegu.jpg" onclick="javascript:location.href='<%= request.getContextPath()%>/product/place/localPlaceList?placeLoc=강원';" alt="대구이미지" />
          <div>
            <h2 id="gangwon" data-lang="gangwon">[gangwon]</h2>
         </div>   
       </li>
       <li class="swiper-slide" >
          <img src="./images/daejeon.jpeg" onclick="javascript:location.href='<%= request.getContextPath()%>/product/place/localPlaceList?placeLoc=경상';" alt="대전이미지" />
          <div>
            <h2 id="gyeongsang" data-lang="gyeongsang">[gyeongsang]</h2>
         </div>   
       </li>
       <li class="swiper-slide" >
          <img src="./images/busan.jpg" onclick="javascript:location.href='<%= request.getContextPath()%>/product/place/localPlaceList?placeLoc=전라';" alt="부산이미지" />
          <div>
            <h2 id="jeolla" data-lang="jeolla">[jeolla]</h2>
         </div>   
       </li>
       <li class="swiper-slide" >
          <img src="./images/pohang.jpg" onclick="javascript:location.href='<%= request.getContextPath()%>/product/place/localPlaceList?placeLoc=충청';" alt="포항이미지" />
          <div>
            <h2 id="chungcheong" data-lang="chungcheong">[chungcheong]</h2>
         </div>   
       </li>
       <li class="swiper-slide" >
          <img src="./images/paju.jpg" onclick="javascript:location.href='<%= request.getContextPath()%>/product/place/localPlaceList?placeLoc=제주';" alt="파주이미지" />
          <div>
            <h2 id="jeju" data-lang="jeju">[jeju]</h2>
         </div>   
       </li>
   </ul>
      <!-- 밑에 사진 지우셔도 됩니다! -->
      <!-- <img class="swiper-slide" src="./images/daejeon.jpeg" alt="대전이미지" /> -->
      <!-- <img class="swiper-slide" src="./images/daegu.jpg" alt="대구이미지" /> -->
      <!-- <img class="swiper-slide" src="./images/tongyeong.jpg" alt="통영이미지" /> -->
      <!-- <img class="swiper-slide" src="./images/jeonju.jpg" alt="전주이미지" /> -->
       <!-- 네비게이션 -->
   <div class="swiper-button-next"></div>
   <div class="swiper-button-prev"></div>
      <!-- 페이징 처리 -->
   <div class="swiper-pagination"></div>
</div>

    
<!-- 베스트 숙소보기  -->
<div style="margin:0 auto; margin-top:20px; margin-bottom:10px; width: 1060px;" class="hr-sect" data-lang="best_stay">[best_stay]</div>
<div style="width:950px; margin:0 auto; text-align:right;">
   <button type="button" id="btn-place" onclick="location.href='<%=request.getContextPath()%>/place/placeList';" class="btn btn-secondary my-2 my-sm-0" data-lang="btn_entirehome">[btn_entirehome]</button>
</div>
<div class="stay-wrapper"> 
   <ul>
      <li class="stay test1"><img id="stay1" alt="" /></li>
<!--       <li class="stay test1"><img id="stay1" src="./images/stay1.jpg" alt="" /></li> -->
      <li class="stay test2"><img id="stay2" alt="" /></li>
<!--       <li class="stay test2"><img id="stay2" src="./images/stay2.jpg" alt="" /></li> -->
      <li class="stay test3"><img id="stay3" alt="" /></li>
<!--       <li class="stay test3"><img id="stay3" src="./images/stay3.jpg" alt="" /></li> -->
      <li class="stay test4"><img id="stay4" alt="" /></li>
<!--       <li class="stay test4"><img id="stay4" src="./images/stay4.jpg" alt="" /></li> -->
      <li class="stay test5"><img id="stay5" alt="" /></li>
<!--       <li class="stay test5"><img id="stay5" src="./images/stay5.jpg" alt="" /></li> -->
   </ul>
</div>
<!-- <div class="stay-wrapper">
   <div class="stay test1"><img src="./images/stay1.jpg" alt="" /></div>
   <div class="stay test2"><img src="./images/stay2.jpg" alt="" /></div>
   <div class="stay test3"><img src="./images/stay3.jpg" alt="" /></div>
   <div class="stay test4"><img src="./images/stay4.jpg" alt="" /></div>
   <div class="stay test5"><img src="./images/stay5.jpg" alt="" /></div>
</div> -->
<div style="width:950px; margin:0 auto; text-align:right; margin-bottom:15px;">
   <button type="button" id="btn-play" onclick="location.href='<%=request.getContextPath()%>/play/playList';" class="btn btn-secondary my-2 my-sm-0" data-lang="btn_entireplay">[btn_entireplay]</button></div>
<!-- 체험보기(전체보기) -->
<div class="grid-back">
   <div class="grid-container">
<!--        <div class="one imag"><img id="play1" src="./images/kite.jpg" alt="" /></div> -->
       <div class="one imag" ><img id="play1"/></div>
<!--        <div class="two imag"><img id="play2" src="./images/koreaCulture.jpg" alt="제례악체험" /></div> -->
       <div class="two imag"><img id="play2"/></div>
<!--        <div class="three imag"><img id="play3" src="./images/teaTime.jpg" alt="다도체험" /></div> -->
       <div class="three imag"><img id="play3" /></div>
<!--        <div class="four imag"><img id="play4" src="./images/cookingClass.jpg" alt="요리교실" /></div> -->
       <div class="four imag"><img id="play4"/></div>
<!--        <div class="five imag"><img id="play5" src="./images/hanbok.jpg" alt="" /></div> -->
       <div class="five imag"><img id="play5"/></div>
   </div>
</div>

<%--  <a href="<%=request.getContextPath()%>/place/placeList">숙소 전체보기</a> --%>
<%--  <a href="<%=request.getContextPath()%>/play/playList">체험 전체보기</a> --%>
<%@ include file="/WEB-INF/views/common/mainfooter.jsp" %>
<script>
// var Swiper = new Swiper('.swiper-container', {
//     slidesPerView: 3,   
//     paginationClickable: true,
//     autoplay: {
//        delay: 2000,
//      },
//    });
   
$( document ).ready(function() {
   $.ajax({
      url : "<%=request.getContextPath()%>/place/placeRandom",
       method : "GET",
       dataType : "json",
       success : function(data){
          var $arr = [$("#stay1"), $("#stay2"), $("#stay3"), $("#stay4"), $("#stay5")];
          $.each(data, function(index, elem){
             $arr[index].attr("src", "<%=request.getContextPath()%>/upload/productProfile/"+elem.placeThumbnailRenamedFileName);
             $($arr[index]).click(function(){
            	 location.href = "<%=request.getContextPath()%>/product/place/placeViewDetails?placeId="+elem.placeId;
            });
             	
          });   
       },
       error : function(xhr, status, err){
           console.log(xhr, status, err);
       }
   });
   $.ajax({
      url : "<%=request.getContextPath()%>/play/playRandom",
       method : "GET",
       dataType : "json",
       success : function(data){
          var $arr = [$("#play1"), $("#play2"), $("#play3"), $("#play4"), $("#play5")];
          $.each(data, function(index, elem){
            $arr[index].attr("src", "<%=request.getContextPath()%>/upload/productProfile/"+elem.playThumbnailRenamedFileName);
            $($arr[index]).click(function(){
            	location.href = "<%=request.getContextPath()%>/product/play/playViewDetails?playId="+elem.playId;
           	});
          });   
       },
       error : function(xhr, status, err){
           console.log(xhr, status, err);
       }
   });
});

new Swiper('.swiper-container', {

   slidesPerView : 3, // 동시에 보여줄 슬라이드 갯수
   spaceBetween : 26, // 슬라이드간 간격
   slidesPerGroup : 1, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음

   // 그룹수가 맞지 않을 경우 빈칸으로 메우기
   // 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
   loopFillGroupWithBlank : true,

   loop : true, // 무한 반복
   autoplay: {
       delay: 2000,
     },
   pagination : { // 페이징
      el : '.swiper-pagination',
      clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
   },
   navigation : { // 네비게이션
      nextEl : '.swiper-button-next', // 다음 버튼 클래스명
      prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
   }
});
</script>
