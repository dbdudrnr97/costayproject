<%@page import="member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.model.vo.Member"%>
<%
   String msg = (String)session.getAttribute("msg");
   if(msg != null) session.removeAttribute("msg");   

    System.out.println("msg@header.jsp = " + msg);
   String loc = (String) request.getAttribute("loc");
    System.out.println("loc@header.jsp = " + loc);
   Member memberLoggedIn = (Member) session.getAttribute("memberLoggedIn");
    System.out.println("memberLoggedIn@header.jsp = " + memberLoggedIn);

   //서버로 전송된 쿠키값 확인
   String saveId = null;
   Cookie[] cookies = request.getCookies(); //쿠키 배열을 리턴
   if(cookies!=null){
      for(Cookie c : cookies){
          System.out.println(c.getName() + " : " + c.getValue());
         if("saveId".equals(c.getName())){
            saveId = c.getValue();
            break;
         }
      }
       System.out.println("saveId@header.jsp = " + saveId);
   }   
   
      
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>COSTAY</title><link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<%-- application별칭(context-root, context-path)를 동적으로 얻어오기  /mvc --%>
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" /> --%>
<link rel = "shortcut icon" href = "/favicon.ico">
<link rel = "icon" href = "/favicon.ico">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquerycssmenu.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquerycssmenu.js"></script>
<script>
<% if(msg != null) { %> alert("<%= msg %>"); <% } %>
<% if(loc != null) { %> location.href = "<%= loc %>"; <% } %>

$("#menu").change(function(){
   var type = $(this).val(); // memberId | memberName | gender
   var $divSearchTypes = $(".search-type");
   $divSearchTypes.hide().filter("#search-" + type).css('display','inline-block');
});

$(document).ready(function(){
    $('#search-place').show();
    $('#search-play').hide();
});

/* 숙소버튼 클릭 */
$(function(){
    $('#btn-place').click(function(){
        $('#btn-place').css('text-decoration','underline');
        $('#btn-play').css('text-decoration','none');
       $('#search-place').show();
        $('#search-play').hide();
    });
});

/* 체험버튼 클릭 */
$(function(){
    $('#btn-play').click(function(){
        $('#btn-place').css('text-decoration','none');
        $('#btn-play').css('text-decoration','underline');
       $('#search-place').hide();
        $('#search-play').show();
    });
});

function getCookie(name) {
    var cookie = document.cookie;
    if (document.cookie != "") {
        var cookie_array = cookie.split("; ");
        for (var index in cookie_array) {
            var cookie_name = cookie_array[index].split("=");
            if (cookie_name[0] == "popupYN") {
                return cookie_name[1];
            }
        }
    }
    return;
}

function openPopup(url) {
    var cookieCheck = getCookie("popupYN");
    var popupWidth = 350;
    var popupHeight = 350;
   
    var popupX = (window.screen.width / 2) - (popupWidth / 2);
    // 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

    var popupY= (window.screen.height / 2) - (popupHeight / 2);
    // 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

    if (cookieCheck != "N") 
       window.open(url, '', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
}

</script>
<style>

/* div#search-place {margin:0 0 10px 0; padding:3px; background-color: rgba(0, 188, 212, 0.3);} */
div#search-play {display: "none"}
header {
    width: 1024px;
    height: 400px;
    margin: 0 auto;
    text-align: center;
    background-image: url('/costay/images/korea.jpg');
    background-size: cover;
}
footer {
    width: 1024px;
    height: 300px;
    margin: 0 auto;
    text-align: center;   
}
button#btn-img{
   border: none;
   background-color : #fff;
}
.lang-btn {
     background: no-repeat;
     border: none;
     cursor: pointer;
     display: inline;
     float: left;
}

.lang-btn img {
   width: 20px; 
   height: 20px; 
   border: 0;
   border-radius: 10px;
}
</style>
</head>
<!-- 팝업은 코딩할 때는 잠시 주석 처리하고, 나중에 주석 풀기 -->
<body> <!--  onload="javascript:openPopup('views/popup.html')">-->
   <header class="main">
<!-- 쿠키는 사용자 정보가 서버로 전송되는 문제점이 있으므로,
이러한 문제점 때문에, WebStorage라는게 나옴. 이는 사용자 정보를 전송하지 않는다. -->
   <div>
      <div>
               <!-- <div style="position:fixed; top:0px; right: 10px;"> -->
         <div style="float: left;">
         <button type="button" id="btn-en" class="lang-btn"><img src="./images/usaflag.png" alt="ENG"/></button>
         <button type="button" id="btn-ko" class="lang-btn"><img src="./images/koreaflag.png" alt="KOR"/></button>
         </div>
         <div style="position:absolute; width:500px; left: 350px; top:60px;">
         <form class="form-inline my-2 my-lg-0">
   <%--       <img onclick="javascript:location.href='<%= request.getContextPath()%>';" src="./images/mainLogo.png" width="100px" height="100px"> --%>
         <img src="./images/mainLogo.png" style="margin-right: 20px;"width="100px" onclick="location.href='<%=request.getContextPath()%>';" height="100px">
         <h1>COSTAY</h1> &nbsp;&nbsp;&nbsp;
         <h4>STAY HOME, STAY KOREA</h4>

         <p id="language" style="display:none;"><span id="locale" style="display:none;"></span></p>
         <script>
         window.onload = function(){
        	// 언어별 JSON 파일 (간략화)
             const lang = {
                 en: {
                 	//mainheader before login
                 	login: "LOGIN",
                 	join: "JOIN",
                 	menu1: "MENU",
                 	aoubt: "ABOUT",
                 	community: "COMMUNITY",
                 	supprot: "SUPPORT",
                 	
                 	//mainheader after login
                 	menu2: "MENU",
                 	about: "ABOUT",
                 	my_page: "MYPAGE",
                 	logout: "LOGOUT",
                     unregister: "UNREGISTER",
                     community: "COMMUNITY",
                     support: "SUPPORT",
                     admin_page: "ADMIN PAGE",
                     
                     btn_place: "PLACE",
                     btn_play: "EXPERIENCE",
                     
                     option_place: "Where are you going to stay?",
                     seoul: "Seoul",
                     gyeonggi: "Gyeonggi",
                     gangwon: "Gangwon",
                     gyeongsang: "Gyeongsang",
                     jeolla: "Geolla",
                     chungcheong: "Chungcheong",
                     jeju: "Jeju",
                     option_price: "PRICE",
                     btn_search: "Search",
                     
                     option_play: "Where are you going?",
                     		
                     //index
                     best_stay: "Check our Best stay ever!",
                     btn_entirehome: "Entire Homes",
                     btn_entireplay: "Entire Experiences",
                 	
                 	//footer
                     about: "ABOUT",
                     community: "COMMUNITY",
                     support: "SUPPORT",
                     about_costay: "About COSTAY",
                     review: "Review",
                     notice: "Notice",
                     right_people: "Right People",
                     new_host: "New Host",
                     faq: "FAQ",
                     terms: "Terms",
                     info1: "© 2021 Costay, Inc. All rights reserved  | Director FSS | Business registration number 111-11-11111",
     				info2: "find us on...",
     				info3: "address (06234) 6, Teheran-ro 14-gil, Gangnam-gu, Seoul, Republic of Korea | email costay@fss.com | call 010-1111-1111",
                     now_sys_lang: "System locale"
                 },
                 ko: {
                 	//mainheader before login
                 	login: "로그인",
                 	join: "회원가입",
                 	menu1: "메뉴",
                 	about: "회사소개",
                     community: "커뮤니티",
                     support: "고객센터",
                     
                     //mainheader after login
                     menu2: "메뉴",
                     about: "회사소개",
                 	my_page: "나의 정보",
                     logout: "로그아웃",
                     unregister: "회원 탈퇴",
                     community: "커뮤니티",
                     support: "고객센터",
                     admin_page: "관리자페이지",
                     
                     btn_place: "숙소",
                     btn_play: "체험",
                     
                     option_place: "숙박할 지역을 선택하세요.",
                     seoul: "서울",
                     gyeonggi: "경기",
                     gangwon: "강원",
                     gyeongsang: "경상",
                     jeolla: "전라",
                     chungcheong: "충청",
                     jeju: "제주",
                     option_price: "가격을 선택하세요.",
                     btn_search: "검색",
                     
                     option_play: "체험할 지역을 선택하세요.",
                     
                     //index
                     best_stay: "코스테이 최고의 숙소를 확인하세요!",
                     btn_entirehome: "숙소 전체보기",
                     btn_entireplay: "체험 전체보기",
                     
                     
                 	//footer
                     about: "회사소개",
                     community: "커뮤니티",
                     support: "고객센터",
                     about_costay: "코스테이",
                     review: "후기게시판",
                     notice: "공지사항",
                     right_people: "인재상",
                     new_host: "신규호스트",
                     faq: "FAQ",
                     terms: "약관",
                     info1: "© 2021 Costay, Inc. All rights reserved | 대표 FSS | 사업자등록번호 111-11-11111",
               		info2: "find us on...",
               		info3: "주소 (06234) 서울 강남구 테헤란로14길 6 남도빌딩 | 이메일 costay@fss.com | 연락처 010-1111-1111",
                     now_sys_lang: "시스템 로캘"
                 }
             }

             // ** 현재 브라우저의 언어 가져오기 **
             function getLanguage() {
                 return navigator.language || navigator.userLanguage;
             }

             // 언어별 적용
             function init(localeStr) {
                 document.getElementById("locale").textContent = localeStr
             }

             // 초기 작업
             const currentLang = getLanguage()
             init(currentLang)
             render(currentLang.substr(0, 2))

             // 언어별 렌더링
             function render(locale) {
                 const $lang = document.querySelectorAll("[data-lang]")
                 $lang.forEach(el => {
                     const code = el.dataset.lang
                     el.textContent = lang[locale][code]
                 });
             }
             

             // 버튼 이벤트
             document.getElementById("btn-en").addEventListener("click", e => {
                 render("en");
             });
             document.getElementById("btn-ko").addEventListener("click", e => {
                 render("ko");
             });
       	}

        
    </script>   
 
         </div>
         <!-- 로그인 안 된 경우 -->
         <%if(memberLoggedIn == null){ %>
         <div style=" position:relative; width:200px; left:750px; top:90px; ">
          <a href="<%=request.getContextPath()%>/member/login" id="login" data-lang="login">[login]</a>
         <a href="<%=request.getContextPath()%>/member/enroll" id="join" data-lang="join">[join]</a>      
         </div>
         <div style=" position:relative; top:70px; left:910px; width:100px;" id="myjquerymenu" class="jquerycssmenu">
         <ul>
         <li><a href="#" id="menu1" data-lang="menu1">[menu1]</a>
           <ul>
              <li><a href="<%= request.getContextPath()%>/corpInfo" id="about" data-lang="about">[about]</a></li>
              <li><a href="<%= request.getContextPath()%>/board/review/boardList" id="community" data-lang="community">[community]</a></li>
               <li><a href="<%= request.getContextPath()%>/board/notice/boardList" id="support" data-lang="support">[support]</a></li>
           </ul>
         </li>
         </ul>
         </div>
      </div>
      <%} else{ %>
      <%-- 로그인 성공 --%>
      <div style=" position:relative; width:200px; left:750px; top:90px; ">
      <%=memberLoggedIn.getMemberName()%> 님
     <img style="width: 30px; height: 30px; border-radius: 20px;"
         src="<%=request.getContextPath()%>/upload/memberProfile/<%=memberLoggedIn.getMemberProfileRenamedFileName()%>"/>     
     </div>
      
      <div style="position:relative; left:850px; top:90px; width:200px;" id="myjquerymenu" class="jquerycssmenu">
      <ul>
      <li><a href="#" id="menu2" data-lang="menu2">[menu2]</a>
        <ul>
           <li><a href="<%= request.getContextPath()%>/corpInfo" id="about" data-lang="about">[about]</a></li>
           <li><a href="<%=request.getContextPath()%>/member/myPage" id="my_page" data-lang="my_page">[my_page]</a></li>
           <li><a href="<%=request.getContextPath()%>/member/logout" id="logout" data-lang="logout">[logout]</a></li>
           <li><a href="<%=request.getContextPath()%>/member/delete" id="unregister" data-lang="unregister">[unregister]</a></li>
            <li><a href="<%= request.getContextPath()%>/board/review/boardList" id="community" data-lang="community">[community]</a></li>
            <li><a href="<%= request.getContextPath()%>/board/notice/boardList" id="support" data-lang="support">[support]</a></li>
            <%if(MemberService.ADMIN_MEMBER_ROLE == memberLoggedIn.getMemberRole()){ %>
            <li><a href="<%=request.getContextPath()%>/admin/adminPage" id="admin_page" data-lang="admin_page">[admin_page]</a></li>
            <%} %>
        </ul>
      </li>
      </ul> 
      </div>

      <%} %>
      </form>
   </div>
    <div style="position:relative; top:220px; left:-30px;">
    <button type="button" id="btn-place" class="btn btn-secondary my-2 my-sm-0" data-lang="btn_place">[btn_place]</button>
    <button type="button" id="btn-play" class="btn btn-secondary my-2 my-sm-0" data-lang="btn_play">[btn_play]</button>
     </div>
     <div class = "search" style="position:relative; top:240px; left:280px;">
     <!-- 숙소의 경우 -->
        <div id="search-place">
        <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath() %>/place/placeList" method="POST">
         <select class="form-control mr-sm-2" name="loc">
            <option value="notSelected" id="option_place" data-lang="option_place">[option_place]</option>
            <option value="서울" id="seoul" data-lang="seoul">[seoul]</option>
            <option value="경기" id="gyeonggi" data-lang="gyeonggi">[gyeonggi]</option>
            <option value="강원" id="gangwon" data-lang="gangwon">[gangwon]</option>
            <option value="경상" id="gyeongsang" data-lang="gyeongsang">[gyeongsang]</option>
            <option value="전라" id="jeolla" data-lang="jeolla">[jeolla]</option>
            <option value="충청" id="chungcheong" data-lang="chungcheong">[chungcheong]</option>
            <option value="제주" id="jeju" data-lang="jeju">[jeju]</option>
         </select>
           <select class="form-control mr-sm-2" name="price">
            <option value="notSelected" id="option_price" data-lang="option_price">[option_price]</option>
            <option value="30000">30000 ~ </option>
            <option value="70000">70000 ~ </option>
            <option value="100000">100000 ~ </option>
            <option value="300000">300000 ~ </option>
            <option value="500000">500000 ~ </option>
         </select>
          <button class="btn btn-secondary my-2 my-sm-0" type="submit" id="btn-search" data-lang="btn_search">[btn_search]</button>
        </form>
         <br>
         </div>
   <!--   <div id="search-place"> -->
   <%--      <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath() %>/place/placeSearch" > --%>
   <!--       <select class="form-control mr-sm-2"> -->
   <!--          <option value="NOTSELECTED">지역을 선택하세요.</option> -->
   <!--          <option value="SEOUL">서울</option> -->
   <!--          <option value="INCHEON">인천</option> -->
   <!--          <option value="GYEONGGI">경기</option> -->
   <!--          <option value="BUSAN">부산</option> -->
   <!--          <option value="JEJU">제주</option> -->
   <!--       </select> -->
   <!--        <input class="form-control mr-sm-2" type="text" placeholder="체크인"> -->
   <!--        <input class="form-control mr-sm-2" type="text" placeholder="체크아웃"> -->
   <!--        <input class="form-control mr-sm-2" type="text" placeholder="인원"> -->
   <!--        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button> -->
   <!--      </form> -->
   <!--      <br> -->
   <!--   </div> -->
      <div id="search-play">
      <!-- 체험의 경우 -->
        <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath() %>/play/playList" method="POST">
         <select class="form-control mr-sm-2" name="loc">
            <option value="notSelected" id="option_play" data-lang="option_play">[option_play]</option>
            <option value="서울" id="seoul" data-lang="seoul">[seoul]</option>
            <option value="경기" id="gyeonggi" data-lang="gyeonggi">[gyeonggi]</option>
            <option value="강원" id="gangwon" data-lang="gangwon">[gangwon]</option>
            <option value="경상" id="gyeongsang" data-lang="gyeongsang">[gyeongsang]</option>
            <option value="전라" id="jeolla" data-lang="jeolla">[jeolla]</option>
            <option value="충청" id="chungcheong" data-lang="chungcheong">[chungcheong]</option>
            <option value="제주" id="jeju" data-lang="jeju">[jeju]</option>
         </select>
           <select class="form-control mr-sm-2" name="price">
            <option value="notSelected" id="option_price" data-lang="option_price">[option_price]</option>
            <option value="30000">30000 ~ </option>
            <option value="70000">70000 ~ </option>
            <option value="100000">100000 ~ </option>
            <option value="300000">300000 ~ </option>
            <option value="500000">500000 ~ </option>
         </select>
          <button class="btn btn-secondary my-2 my-sm-0" type="submit" id="btn_search" data-lang="btn_search">[btn_search]</button>
        </form>
         <br>
         </div>
      </div>
<!--   <div id="search-play"> -->
<!--       체험의 경우  -->
<%--      <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath() %>/play/playSearch"> --%>
<!--        <input class="form-control mr-sm-2" type="text" placeholder="어디로 여행가세요"> -->
<!--        <input class="form-control mr-sm-2" type="text" placeholder="원하는 날짜를 입력하세요"> -->
<!--        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button> -->
<!--      </form> -->
<!--      <br> -->
<!--   </div> -->

   <!-- 메인메뉴 시작 -->
   <%-- <nav>
      <ul class="main-nav">
         <li class="home"><a href="<%=request.getContextPath()%>">Home</a></li>
         <li id="notice"><a href="#">공지사항</a></li>
         <li id="board"><a href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
         <!-- 관리자메뉴 : 관리자만 노출 --!>
         <%if(memberLoggedIn != null && MemberService.ADMIN_MEMBER_ROLE.equals(memberLoggedIn.getMemberRole())){ %>
         <li id="admin-memberList"><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li>
         <%} %>
      </ul>
   </nav> --%>
   <!-- 메인메뉴 끝-->
   
</header>
   
         



</body>
</html>