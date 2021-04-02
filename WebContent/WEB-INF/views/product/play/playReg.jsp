<%@page import="product.model.vo.Play"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Date regChkinDate = Date.valueOf(request.getParameter("regChkinDate"));
	int guestCount = Integer.parseInt(request.getParameter("guestCount"));
	Play play = (Play)request.getAttribute("play");
%>
<div class="sub_title">
<h3><strong>체험 예약</strong></h3>
<hr />
</div>
<div style="margin:0 auto; text-align:center; width:800px; margin:0 auto;">
	<ul>
		<li>
			<label for="memberId">회원아이디</label>
			<input type="text" name="memberId" id="memberId" value="<%=memberLoggedIn.getMemberId()%>" readonly/>
		</li>
		<li>
			<label for="guestCount">게스트수</label>
			<input type="number" name="guestCount" id="guestCount" value="<%=guestCount%>" min="1" max="20" required/>
		</li>
		<li>
			<label for="regChkinDate">체험날짜</label>
			<input type="date" name="regChkinDate" id="regChkinDate" value="<%=regChkinDate%>" required/>
		</li>
	</ul>
	<input type="hidden" id="playId" name="playId" value="<%=play.getPlayId()%>" />
	<input type="button" value="결제하기" id="pay-btn"/>
</div>
<script>
	//날짜 유효성 검사
	$("#pay-btn").click(function(){
		var chkinDate = new Date($("#regChkinDate").val());
		var now = new Date();
		if(chkinDate < now){
			alert('체험 날짜를 확인해주세요.');
			return false;
		}
		
		var IMP = window.IMP; // 생략가능
		IMP.init('imp93640304'); 
		
		IMP.request_pay({
		    pg : 'inicis', // version 1.1.0부터 지원.
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '<%=play.getPlayName()%>',
		    amount : <%=play.getPlayPrice()%>,
		    buyer_name : '<%=memberLoggedIn.getMemberName()%>',
		    buyer_tel : '<%=memberLoggedIn.getPhone()%>',
		    buyer_addr : '<%=memberLoggedIn.getMemberAddress()%>',
		    buyer_postcode : '123-456',
		    m_redirect_url : '<%=request.getContextPath()%>'
		}, function(rsp) {
		    if ( rsp.success ) {
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        $.ajax({
		        	url:"<%=request.getContextPath()%>/product/play/playReg",
					method : "POST",
					dataType : "json",
					data : {"memberId": $(memberId).val(),
							"guestCount" : $(guestCount).val(),
							"regChkinDate" : $(regChkinDate).val(),
							"playId" : $(playId).val()},
					success: function(data){
						console.log(data);
					},
					error : function(xhr, status, err){
						console.log(xhr, status, err);
					}
		        });
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
		    
		    location.href='<%=request.getContextPath()%>';
		});
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>