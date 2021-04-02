<%@page import="member.model.vo.Registration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="product.model.vo.Play"%>
<%@page import="java.util.Map"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="java.util.Set"%>
<%-- <%if(memberLoggedIn != null) {%>
	<p><%=memberLoggedIn.getMemberId() %> | <%=memberLoggedIn.getMemberEmail() %></p>
<%} else {%>로그인후에 이용해주세요 <%}%>
 --%>
<%
	Map<String, Play> expectedMap = (Map<String,Play>)request.getAttribute("expected");
	Map<String, Play> previousMap = (Map<String,Play>)request.getAttribute("previous");
// 	List<Registration> regList = new ArrayList<Registration>();
// 	List<Play> expectedList = new ArrayList<Play>();
// 	List<Play> previousList = new ArrayList<Play>();
	MemberService memberService = new MemberService();
	
	Set<String> expectedKeySet = expectedMap.keySet();
	Set<String> previousKeySet = previousMap.keySet();	
%>

<script>

$(document).ready(function(){
    $('div.expetced').show();
    $('div.previous').hide();
});

/* 예정된 체험 클릭 */
$(function(){
    $('#btn-expected').click(function(){
        $('#btn-expected').css('text-decoration','underline');
        $('#btn-previous').css('text-decoration','none');
    	$('div.expected').show();
        $('div.previous').hide();
    });
});

/* 이전 체험 클릭 */
$(function(){
    $('#btn-previous').click(function(){
        $('#btn-expected').css('text-decoration','none');
        $('#btn-previous').css('text-decoration','underline');
    	$('div.expected').hide();
        $('div.previous').show();
    });
});

</script>

  <div class="sub_title">
	  <h3 style="margin-top:40px;"><strong>체험</strong></h3>
	  <button type="button" id="btn-expected" class="btn btn-link">예정된 예약</button>
	  <button type="button" id="btn-previous" class="btn btn-link">이전 예약</button>
	  <hr />
  </div>
  <div class="expected">
<%--   	<img src="<%= request.getContextPath()%>/upload/productProfile/<%=expectedMap.get(key) .get%>" alt="" /> --%>

	<table class="table table-borderless">
			<thead>
			</thead>
			<tbody id="tableDiv">
		  <!-- entry 순회하면서 하나씩 만들어주게끔 -->
			<% 	
					Iterator<String> eIter = expectedKeySet.iterator();
					int eIndex = 0;
					Registration ereg = null;
					Play eplay = null;
					while(eIter.hasNext()) {
						String key = eIter.next();
						ereg = memberService.selectRegistrationOne(key);
						eplay = expectedMap.get(key);
					%>
				<%if(eIndex == 0) {%>
					<tr>
						<td>
							<div class="item">
							<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
								<img class="regImage" src="<%=request.getContextPath()%>/images/test.jpg" alt="" />
								<%=ereg.getRegChkinDate()%>
								<h5><%=eplay.getPlayLoc()%></h5>
								<strong><%=eplay.getPlayName()%></strong>
								<hr/>
								<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/playRegView/detailedView?reg=<%=ereg.getRegId()%>&play=<%=eplay.getPlayId()%>' ">예약 상세 보기</button>												
							</div>
						</td>
				<%eIndex++;
				}else if(eIndex == 2){ %>
						<td>
							<div class="item">
							<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
								<img class="regImage" src="<%=request.getContextPath()%>/images/test.jpg" alt="" />
								<%=ereg.getRegChkinDate()%>
								<h5><%=eplay.getPlayLoc()%></h5>
								<strong><%=eplay.getPlayName()%></strong>
								<hr/>
								<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/playRegView/detailedView?reg=<%=ereg.getRegId()%>&play=<%=eplay.getPlayId()%>' ">예약 상세 보기</button>												
							</div>						
						</td>
					</tr>
					
				<%eIndex=0;
				}else{ %>
					<td>
							<div class="item">
							<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
								<img class="regImage" src="<%=request.getContextPath()%>/images/test.jpg" alt="" />
								<%=ereg.getRegChkinDate()%>
								<h5><%=eplay.getPlayLoc()%></h5>
								<strong><%=eplay.getPlayName()%></strong>
								<hr/>
								<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/playRegView/detailedView?reg=<%=ereg.getRegId()%>&play=<%=eplay.getPlayId()%>' ">예약 상세 보기</button>												
							</div>						
					</td>
				<%eIndex++;
				} %>
			 <%}%>

			</tbody>
		</table>
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
				Play pplay = null;
				while(pIter.hasNext()) {
					String key = pIter.next();
					preg = memberService.selectRegistrationOne(key);
					pplay = previousMap.get(key);
				%>
						
					<%if(pIndex == 0) {%>
						<tr>
							<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/images/test.jpg" alt="" />
									<%=preg.getRegChkinDate()%>
									<h5><%=pplay.getPlayLoc()%></h5>
									<strong><%=pplay.getPlayName()%></strong>
									<hr/>
									<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/playRegView/detailedView?reg=<%=preg.getRegId()%>&play=<%=pplay.getPlayId()%>' ">예약 상세 보기</button>												
								</div>						
							</td>
					<%pIndex++;
					}else if(pIndex == 2){ %>
							<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/images/test.jpg" alt="" />
									<%=preg.getRegChkinDate()%>
									<h5><%=pplay.getPlayLoc()%></h5>
									<strong><%=pplay.getPlayName()%></strong>
									<hr/>
									<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/playRegView/detailedView?reg=<%=preg.getRegId()%>&play=<%=pplay.getPlayId()%>' ">예약 상세 보기</button>												
								</div>							
							</td>
						</tr>
						
					<%pIndex=0;
					}else{ %>
						<td>
								<div class="item">
								<!-- 숙소등록 기능 구현 이후, 숙소 이미지로 변경 필요 -->
									<img class="regImage" src="<%=request.getContextPath()%>/images/test.jpg" alt="" />
									<%=preg.getRegChkinDate()%>
									<h5><%=pplay.getPlayLoc()%></h5>
									<strong><%=pplay.getPlayName()%></strong>
									<hr/>
									<button type="button" onclick="location.href='<%=request.getContextPath()%>/member/playRegView/detailedView?reg=<%=preg.getRegId()%>&play=<%=pplay.getPlayId()%>' ">예약 상세 보기</button>												
								</div>					
						</td>
					<%pIndex++;
					} %>
				 <%}%>

				</tbody>
			</table>
  </div>
  
<%@ include file="/WEB-INF/views/common/footer.jsp" %>