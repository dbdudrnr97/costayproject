<%@page import="member.model.vo.Registration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="product.model.vo.Place"%>
<%@page import="java.util.Map"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="java.util.Set"%>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<%-- <%if(memberLoggedIn != null) {%>
	<p><%=memberLoggedIn.getMemberId() %> | <%=memberLoggedIn.getMemberEmail() %></p>
<%} else {%>로그인후에 이용해주세요 <%}%>
 --%>
<%
	Map<String, Place> expectedMap = (Map<String,Place>)request.getAttribute("expected");
	Map<String, Place> previousMap = (Map<String,Place>)request.getAttribute("previous");
	Map<String, Place> waitMap = (Map<String,Place>)request.getAttribute("wait");

	MemberService memberService = new MemberService();	
	Set<String> expectedKeySet = expectedMap.keySet();
	Set<String> previousKeySet = previousMap.keySet();	
	Set<String> waitKeySet = waitMap.keySet();	
%>

<script>

$(document).ready(function(){
    $('div.expetced').show();
    $('div.previous').hide();
    $('div.wait').hide();
});

/* 예정된 숙소 클릭 */
$(function(){
    $('#btn-expected').click(function(){
        $('#btn-expected').css('text-decoration','underline');
        $('#btn-previous').css('text-decoration','none');
        $('#btn-wait').css('text-decoration','none');
    	$('div.expected').show();
        $('div.previous').hide();
        $('div.wait').hide();
    });
});

/* 이전 숙소 클릭 */
$(function(){
    $('#btn-previous').click(function(){
        $('#btn-expected').css('text-decoration','none');
        $('#btn-previous').css('text-decoration','underline');
        $('#btn-wait').css('text-decoration','none');
    	$('div.expected').hide();
        $('div.previous').show();
        $('div.wait').hide();
    });
});

/* 승인대기 숙소 클릭 */
$(function(){
    $('#btn-wait').click(function(){
        $('#btn-expected').css('text-decoration','none');
        $('#btn-previous').css('text-decoration','none');
        $('#btn-wait').css('text-decoration','underline');
    	$('div.expected').hide();
        $('div.previous').hide();
        $('div.wait').show();
    });
});

</script>

  <div class="sub_title">
	  <h3 style="margin-top:40px;"><strong>숙소</strong></h3>
	  <button type="button" id="btn-expected" class="btn btn-link">예정된 예약</button>
	  <button type="button" id="btn-previous" class="btn btn-link">이전 예약</button>
	  <button type="button" id="btn-wait" class="btn btn-link">승인 대기중인 예약</button>
	  <hr />
  </div>
  <div class="expected">
<%--   	<img src="<%= request.getContextPath()%>/upload/productProfile/<%=expectedMap.get(key) .get%>" alt="" /> --%>
<!-- 	<form -->
<!-- 	name="goToDetailedFrm" -->
<%-- 	action="<%=request.getContextPath()%>/member/placeRegView/detailedView"  --%>
<!-- 	method="get"> -->
	<table class="table table-borderless">
			<thead>
			</thead>
			<tbody id="tableDiv">
		  <!-- entry 순회하면서 하나씩 만들어주게끔 -->
			<% 	
					Iterator<String> eIter = expectedKeySet.iterator();
					int eIndex = 0;
					Registration ereg = null;
					Place eplace = null;
					int i = 0;
					while(eIter.hasNext()) {
						String key = eIter.next();
						ereg = memberService.selectRegistrationOne(key);
						eplace = expectedMap.get(key);
					%>
				<%if(eIndex == 0) {%>
					<tr>
						<td>
							<div class="item">
							<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
								<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=eplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
								<%=ereg.getRegChkinDate()%>
								<h5><%=eplace.getPlaceLoc()%></h5>
								<strong><%=eplace.getPlaceName()%></strong>
								<hr/>
									<div style="margin-bottom:10px;">
										<button type="button" class="btn-secondary" onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=ereg.getRegId()%>&place=<%=eplace.getPlaceId()%>' ">상세 보기</button>
										<button type="button" class="btn-secondary" style="width:78px; height:28px;"id="pay<%=i%>">결제하기</button>
									</div>
									<script>
									$("#pay<%=i++%>").click(function(){
										var IMP = window.IMP; // 생략가능
										IMP.init('imp93640304'); 
										
										IMP.request_pay({
										    pg : 'inicis', // version 1.1.0부터 지원.
										    pay_method : 'card',
										    merchant_uid : 'merchant_' + new Date().getTime(),
										    name : '<%=eplace.getPlaceName()%>',
										    amount : <%=eplace.getPlacePrice()%>,
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
										        	url:"<%=request.getContextPath()%>/product/place/pay",
													method : "GET",
													dataType : "json",
													data : {"regId":"<%=ereg.getRegId()%>"},
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
										});
									});
								</script>											
							</div>
						</td>
				<%eIndex++;
				}else if(eIndex == 2){ %>
						<td>
							<div class="item">
							<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
								<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=eplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
								<%=ereg.getRegChkinDate()%>
								<h5><%=eplace.getPlaceLoc()%></h5>
								<strong><%=eplace.getPlaceName()%></strong>
								<hr/>
									<div style="margin-bottom:10px;">
										<button type="button" class="btn-secondary" onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=ereg.getRegId()%>&place=<%=eplace.getPlaceId()%>' ">상세 보기</button>
										<button type="button" class="btn-secondary" style="width:78px; height:28px;"id="pay<%=i%>">결제하기</button>
									</div>
									<script>
									$("#pay<%=i++%>").click(function(){
										var IMP = window.IMP; // 생략가능
										IMP.init('imp93640304'); 
										
										IMP.request_pay({
										    pg : 'inicis', // version 1.1.0부터 지원.
										    pay_method : 'card',
										    merchant_uid : 'merchant_' + new Date().getTime(),
										    name : '<%=eplace.getPlaceName()%>',
										    amount : <%=eplace.getPlacePrice()%>,
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
										        	url:"<%=request.getContextPath()%>/product/place/pay",
													method : "GET",
													dataType : "json",
													data : {"regId":"<%=ereg.getRegId()%>"},
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
										});
									});
								</script>												
							</div>						
						</td>
					</tr>
					
				<%eIndex=0;
				}else{ %>
					<td>
							<div class="item">
							<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
								<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=eplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
								<%=ereg.getRegChkinDate()%>
								<h5><%=eplace.getPlaceLoc()%></h5>
								<strong><%=eplace.getPlaceName()%></strong>
								<hr/>
									<div style="margin-bottom:10px;">
										<button type="button" class="btn-secondary" onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=ereg.getRegId()%>&place=<%=eplace.getPlaceId()%>' ">상세 보기</button>
										<button type="button" class="btn-secondary" style="width:78px; height:28px;"id="pay<%=i%>">결제하기</button>
									</div>
									<script>
									$("#pay<%=i++%>").click(function(){
										var IMP = window.IMP; // 생략가능
										IMP.init('imp93640304'); 
										
										IMP.request_pay({
										    pg : 'inicis', // version 1.1.0부터 지원.
										    pay_method : 'card',
										    merchant_uid : 'merchant_' + new Date().getTime(),
										    name : '<%=eplace.getPlaceName()%>',
										    amount : <%=eplace.getPlacePrice()%>,
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
										        	url:"<%=request.getContextPath()%>/product/place/pay",
													method : "GET",
													dataType : "json",
													data : {"regId":"<%=ereg.getRegId()%>"},
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
										});
									});
								</script>												
							</div>						
					</td>
				<%eIndex++;
				} %>
			 <%}%>

			</tbody>
		</table>
<!-- 		</form> -->
  </div>

  <div class="previous">  
		<table class="table table-borderless">
				<thead>
				</thead>
				<tbody id="tableDiv">
			  <!-- entry 순회하면서 하나씩 만들어주게끔 -->
				<% 	
					Iterator<String> pIter = previousKeySet.iterator();
					int pIndex = 0;
					Registration preg = null;
					Place pplace = null;
					while(pIter.hasNext()) {
						String key = pIter.next();
						preg = memberService.selectRegistrationOne(key);
						pplace = previousMap.get(key);
					%>
						
					<%if(pIndex == 0) {%>
						<tr>
							<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=pplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
									<%=preg.getRegChkinDate()%>
									<h5><%=pplace.getPlaceLoc()%></h5>
									<strong><%=pplace.getPlaceName()%></strong>
									<hr/>
									<button type="button"  class="btn-secondary" onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=preg.getRegId()%>&place=<%=pplace.getPlaceId()%>' ">상세 보기</button>												
								</div>						
							</td>
					<%pIndex++;
					}else if(pIndex == 2){ %>
							<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=pplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
									<%=preg.getRegChkinDate()%>
									<h5><%=pplace.getPlaceLoc()%></h5>
									<strong><%=pplace.getPlaceName()%></strong>
									<hr/>
									<button type="button" class="btn-secondary"  onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=preg.getRegId()%>&place=<%=pplace.getPlaceId()%>' ">상세 보기</button>												
								</div>							
							</td>
						</tr>
						
					<%pIndex=0;
					}else{ %>
						<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=pplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
									<%=preg.getRegChkinDate()%>
									<h5><%=pplace.getPlaceLoc()%></h5>
									<strong><%=pplace.getPlaceName()%></strong>
									<hr/>
									<button type="button" class="btn-secondary"  onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=preg.getRegId()%>&place=<%=pplace.getPlaceId()%>' ">상세 보기</button>												
								</div>					
						</td>
					<%pIndex++;
					} %>
				 <%}%>
					
				</tbody>
			</table>
  </div>
    <div class="wait">  
		<table class="table table-borderless">
				<thead>
				</thead>
				<tbody id="tableDiv">
			  <!-- entry 순회하면서 하나씩 만들어주게끔 -->
				<% 	
					Iterator<String> wIter = waitKeySet.iterator();
					int wIndex = 0;
					Registration wreg = null;
					Place wplace = null;
					while(wIter.hasNext()) {
						String key = wIter.next();
						wreg = memberService.selectWaitRegistrationOne(key);
						wplace = waitMap.get(key);
						System.out.println(wplace);
					%>
						
					<%if(wIndex == 0) {%>
						<tr>
							<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=wplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
									<%=wreg.getRegChkinDate()%>
									<h5><%=wplace.getPlaceLoc()%></h5>
									<strong><%=wplace.getPlaceName()%></strong>
									<hr/>
									<button type="button"  class="btn-secondary" onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=wreg.getRegId()%>&place=<%=wplace.getPlaceId()%>' ">상세 보기</button>												
								</div>						
							</td>
					<%wIndex++;
					}else if(wIndex == 2){ %>
							<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=wplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
									<%=wreg.getRegChkinDate()%>
									<h5><%=wplace.getPlaceLoc()%></h5>
									<strong><%=wplace.getPlaceName()%></strong>
									<hr/>
									<button type="button" class="btn-secondary"  onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=wreg.getRegId()%>&place=<%=wplace.getPlaceId()%>' ">상세 보기</button>												
								</div>							
							</td>
						</tr>
						
					<%wIndex=0;
					}else{ %>
						<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/upload/productProfile/<%=wplace.getPlaceThumbnailRenamedFileName()%>" alt="" />
									<%=wreg.getRegChkinDate()%>
									<h5><%=wplace.getPlaceLoc()%></h5>
									<strong><%=wplace.getPlaceName()%></strong>
									<hr/>
									<button type="button" class="btn-secondary"  onclick="location.href='<%=request.getContextPath()%>/member/placeRegView/detailedView?reg=<%=wreg.getRegId()%>&place=<%=wplace.getPlaceId()%>' ">상세 보기</button>												
								</div>					
						</td>
					<%wIndex++;
					} %>
				 <%}%>
					
				</tbody>
			</table>
  </div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>