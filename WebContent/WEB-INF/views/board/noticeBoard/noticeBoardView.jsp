<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% Board b = (Board)request.getAttribute("Board"); %>
<script>
function fileDownload(oName, rName){
	console.log(oName, rName);
	location.href = "<%= request.getContextPath() %>/board/notice/fileDownload?oName=" + oName + "&rName=" + rName; 
}
</script>
<div class="sub_title">
<h3><strong>게시글 보기</strong></h3>
<hr />
</div>
<div style="margin:0 auto; text-align:center; width:800px;">
	<table border="1" class="table   table-bordered">
	
		<tr>
				<th>글번호</th>
				<td><%= b.getBoardNo() %></td>
			</tr>
			<tr>
				<th>제 목</th> 
				<td><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= b.getBoardWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%= b.getBoardReadCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시!  없을떄는 안보이게!--%>
					<% if(b.getBoardOriginalFileName()!= null) { %>
					<a href="javascript:fileDownload('<%=b.getBoardOriginalFileName() %>','<%= b.getBoardRenamedFileName()%>')">
						<img alt="첨부파일" src="<%=request.getContextPath() %>/images/test.jpg" width=16px>
					<%= b.getBoardOriginalFileName() %>
					</a>
					<% } %>
				</td>
			</tr>
			<tr style="text-align:left;">
				<%-- content --%>
				<td colspan="5">
					<% if(b.getBoardOriginalFileName() != null) { %>
						<img src="<%=request.getContextPath() %>/upload/board/<%=b.getBoardRenamedFileName() %>" alt="미리보기" />
					<% } %>
					<br />
					<%=b.getBoardContent()%>			
				</td>
			</tr>
		<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<% if(
				memberLoggedIn != null
			 && (memberLoggedIn.getMemberId().equals(b.getBoardWriter())
			 	|| MemberService.ADMIN_MEMBER_ROLE==memberLoggedIn.getMemberRole())
			){ %>
		<tr>
			<th colspan="2">
				<input type="button" value="수정하기" onclick="updateNoticeBoard();"/> 
				<input type="button" value="삭제하기" onclick="deleteNoticeBoard();"/>
			</th>
		</tr>		
		<script>
			function updateNoticeBoard() {
				location.href = "<%= request.getContextPath() %>/board/notice/boardUpdate?boardNo=<%=b.getBoardNo()%>";
			
			/* 	location.href는 get방식 */
			}
			
				
			function deleteNoticeBoard() {
				if(confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
					$("[name=noticeBoardDelFrm]").submit();
				}
			}
			</script>
			<form 
				action="<%= request.getContextPath() %>/board/notice/boardDelete"
				method="post"
				name="noticeBoardDelFrm">
				<input type="hidden" name="boardNo" value="<%= b.getBoardNo() %>"/>
				<input type="hidden" name="rName" value="<%= b.getBoardRenamedFileName() != null ? b.getBoardRenamedFileName() : "" %>"/>
			</form>
			<% } %>
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>