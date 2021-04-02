<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<head>
<title>인재상</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.5.1.js"></script>
<style>
.wrapper{
	width: 1024px;
	margin: 0 auto;
	
}
/* tabs(좌측메뉴탭)영역 */
ul.tabs{
	width: 0px; /* 이걸 지워버리면 탭메뉴가 가로로 정렬됨 (?) */
    margin: 30px; /* wrapper와 떨어질 수 있도록 */
    padding: 0px;
    list-style: none;
}
ul.tabs li{
  /* 	display: inline-block; */
    width: 120px;  /* 탭메뉴 너비  */
    height: 45px; /* 탭메뉴 높이  */
    line-height: 45px;
    text-align: center;
    cursor: pointer;
	margin: 5px 0px; /* 탭끼리 안붙도록 함 */
	float: left; /* !!이걸 넣으니까 탭메뉴랑 content가 나란히!! */
}
.tabs li:hover{
	background-color: lightgray;
}
.tabs a{
	text-decoration: none; /* 밑줄제거 */
	color: black;
	font-weight: bold;
}

/* tab-content영역 */
#ccontent-title{
	font-weight: bold;
	
}
.tab-content{
	width: 700px;
    display: none;
    padding: 10px;
    height: 100%;
}
/* 현재 클릭된 탭메뉴 */
ul.tabs li.current{
    background: #f6b99d;
    color: #222;
}
.tab-content.current{
    display: inherit; /* 부모의 속성을 물려받을 수 있음 */
}
.inline{
	display: inline-block;
}
/*  ---------------회사소개 안 table------------------ */
table.corpInfo {
	margin: 0px auto;
    border-collapse: collapse;
    text-align: center;
    line-height: 1.5;

}
/* 회사정보 css */
table.corpInfo thead td {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: black;
    font-size: 20px;
    border-bottom: 4px solid #f6b99d;
}
/* 회사정보 분류 */
table.corpInfo tbody th {
    width: 200px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
/* 회사정보 내용 */
table.corpInfo td {
    width: 550px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}

/*  ---------------인재상------------------ */
/* 인재상 제목(new, dream, global, vision) */
.rightPeople h2 { 
	width: 0px;
	position: relative; 
	font-size: 32px; 
	color: black; 
	font-weight: bold;
	line-height: 1; /* 줄간격을 최대한 없애주기 위해 사용 */
}
/* ol 동그라미 css */
.rightPeople h2::before { 
	content: ""; /* 비포와 컨텐트는 한쌍임, 컨텐트가 없으면 안보임 */
	position: absolute; 
	left: -40px; /* h2랑 겹쳐져 있었기때문에 h2좌측으로 이동해야함(좌측에서 좌측으로 이동이기때문에 마이너스로 지정) */
	width: 17px; 
	height: 17px; 
	border-radius: 100%; /* 동그랗게 만들어줌 */
	background: #fff; /* 불릿의 배경은 흰색으로 */
	border: 5px solid #f6b99d;  /* 불릿에 보더줘서 동그라미 안이 투명하지 않게해줌 */
	box-sizing: border-box;  /* 보더와 w,h값이 들어가면 커지기때문에  */
}
/* 인재상 h2와 ol을 감싸는 div */
.rightPeople div { 
	position: relative; 
	padding: 0 0 0 41px; /* div 왼쪽 선과 제목+내용을 서로 띄워줌 */
}
/* div를 박스가 아닌 선으로 표현해줌 */
.rightPeople div::before { 
	content: ""; 
	position: absolute; 
	left: 8px; /* div를 감싸고 있는 div(tab-content)를 기준으로이동 */
	top: 0; 
	height: 100%; 
	border-left: 3px solid #ddd;
}

/* 맨위에 있는 회색선 지우는 코드 */
.rightPeople div:first-child:before { 
	top:-10px; 	
	height: clac(100%-10px); /* clac=계산기 (div::before의 height-위에 남은 회색) */
}
.rightPeople ol { 	
	list-style: none;
	padding: 0px 0px 7px; /* 제목과 내용 간격, 내용 밑부분의 간격처리) */ 
}
/* 인재상 안의 내용 */
.rightPeople li { 
	font-size: 15px; 
	color: #000; 
	line-height: 30px; /* 줄간격(글자 중간을 기준으로 15px로 나눠짐) */
}
</style>
<script>
/* 탭메뉴 script 
   클릭한 메뉴에 current 속성을 줘서 나타나게 하고,
   다른 부분은 removeClass 속성을 줘서 사라지게 해줌  
 */
$(document).ready(function(){
    
    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');
 
        $('ul.tabs li').removeClass('current');
        $('.tab-content').removeClass('current');
 
        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    })
 
});
</script>

</head>

<section>
	<article style="text-align: center;">
		<div class="wrapper inline">
			<!--좌측 메뉴 영역 -->
			<ul class="tabs">
				<li class="tab-link" data-tab="tab-1"><a href="#">회사소개</a></li>
				<li class="tab-link current" data-tab="tab-2"><a href="#">Costay 인재상</a></li>
			</ul>
			<!-- content영역 -->
			<!-- div.tab-content#tap-$*2 -->
			<div class="tab-content" id="tab-1">
				<h3 id="content-title">Costay를 소개합니다.</h3>
				<p>
					<span>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nam fugit tenetur pariatur quam quod tempore repellendus mollitia aut ipsam eaque corporis sapiente quia nemo repellat adipisci voluptas doloribus distinctio cumque.</span>
					<span>Ea quisquam voluptates iure. Culpa assumenda corrupti voluptas dolores aliquam hic id repudiandae optio quasi alias ratione deleniti libero aspernatur quos dolorem voluptates recusandae nisi ea architecto cum ipsa. Nam.</span>
					<span>Dolores aliquid at tempore ad magnam? At voluptates nobis repudiandae odit tenetur nisi eos reprehenderit eius. Quas illum atque ea consequatur nulla architecto quos quisquam voluptatum hic sit nemo distinctio!</span>
					<span>Quibusdam dolores eum aperiam ut iure vero harum ea totam nesciunt explicabo earum consequatur maxime numquam repudiandae pariatur quisquam consequuntur consectetur natus aliquid amet ipsum quidem necessitatibus eos sed reiciendis.</span>
					<span>Quibusdam dolores eum aperiam ut iure vero harum ea totam nesciunt explicabo earum consequatur maxime numquam repudiandae pariatur quisquam consequuntur consectetur natus aliquid amet ipsum quidem necessitatibus eos sed reiciendis.</span>
				</p>
				<table class="corpInfo">
				    <thead>
				    <tr>
				        <td colspan="4">회사정보</td>
				    </tr>
				    </thead>
				    <tbody>
				    <tr>
				        <th scope="row">기업명</th>
				        <td>주식회사 코스테이</td>
				        <th scope="row">주소</th>
				        <td>서울 강남구 테헤란로 14길 6길 남도빌딩</td>
				    </tr>
				    <tr>
				        <th scope="row">설립입자</th>
				        <td>2021년 02월 03일</td>
				        <th scope="row">대표전화</th>
				        <td>TEL. 010-7979-7979</td>
				    </tr>
				    <tr>
				        <th scope="row">대표이사</th>
				        <td>박테이</td>
				        <th scope="row">대표이메일</th>
				        <td>costay@fss.com</td>
				    </tr>
				    </tbody>
				</table>

			</div>
			<div class="tab-content rightPeople current" id="tab-2">
				<h3 id="content-title">Costay의 인재상</h3>
				<div>
					<h2>NEW</h2>
					<ol>
						<li>기존사업을 혁신하고자 하는 사람</li>
						<li>현실에 안주하지 않고 변화와 발전을 즐기며 늘 새로움을 추구하는 사람</li>
					</ol>
				</div>
				<div>
					<h2>Dream</h2>
					<ol>
						<li>자신의 분야에서 최고가 되기 위해 노력하는 사람</li>
						<li>가슴 속에 큰 꿈을 안고 살아가며 꿈의 성취를 위해 끊임없이 노력하는 사람</li>

					</ol>
				</div>
				<div>
					<h2>Global</h2>
					<ol>
						<li>창의력을 바탕으로 Global기업으로 성장을 주도하는 사람</li>
						<li>글로벌 마인드를 가지고 세계와 인류에 공헌하려는 마음을 가진 사람</li>
					</ol>
				</div>
				<div>
					<h2>Vision</h2>
					<ol>
						<li>조직의 비전 실현을 위한 추진력을 갖춘 사람</li>
						<li>미래의 비전을 내다볼 줄 알며 조직의 비전을 함께 공유하는 사람</li>
					</ol>
				</div>

			</div>
		</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>