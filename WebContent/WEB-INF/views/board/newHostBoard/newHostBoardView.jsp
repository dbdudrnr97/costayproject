<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="board.model.vo.Board"%>
<%@page import="board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<br/>
<br/>
<%
	Board board = (Board)request.getAttribute("board");
	System.out.println("board@viewJsp="+board);
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
	System.out.println("commentList@viewJsp="+commentList);   
	
%>
<script>
$(function() {
	/**
	* 댓글 삭제
	*
	*/
 $(".btn-delete").click(function(){
	if(confirm('정말 삭제하시겠습니까?')){
		var boardCommentNo = $(this).val();
		location.href = "<%= request.getContextPath() %>/board/newHost/CommentDelete?boardCommentNo=" +boardCommentNo + "&boardNo=<%=board.getBoardNo() %>";
	}
});

	/**
	* 대댓글(답글) 버튼 누르면 해당 tr누르면 새로운 tr태그를 생성해서 폼을 제공한다
	* one메소드 : 이벤트 핸들링 한번만 진행하는 메소드 -> 여러번 클릭해도 답글창이 여러개가 아닌 하나만 생성되어야함
	* 동적으로 새로 생긴 태그에 한해서 유효성검사,이벤트핸들링이 먹지않으므로 새롭게 만들어줘야함
	*/
	 $(".btn-reply").one('click', function() {
		<%if(memberLoggedIn == null){ %>
		<%--로그인 하지 않은경우 --%>
		loginAlert();
		<% }else { %>
		<%--로그인 하지 않은경우 --%>
		var $this = $(this); //현재 클릭한 btn-reply
		
		var html = '<tr>';
		html += '<td style="display:none; text-align:left"; colspan:"2">';
		html += '<form action="<%=request.getContextPath() %>/board/newHost/CommentInsert" method="post">';
		html += '<input type="hidden" name="boardRef" value="<%=board.getBoardNo() %>" />';
		html += '<input type="hidden" name="boardCommentWriter" value="<%= memberLoggedIn != null ? memberLoggedIn.getMemberId() : "" %>" />';
		html += '<input type="hidden" name="boardCommentLevel" value="2" />';
		html += '<input type="hidden" name="boardCommentRef" value="'+ $this.val() + '" />';
		html += '<textarea name="boardCommentContent" cols="60" rows="2"></textarea>';
		html += '<button type="submit" class="btn-insert2">등록</button>';
		html += '</form>';
		html += '</td>';
		html += '</tr>';
		
		//동적으로 새로 생긴 태그에 한해서 유효성검사,이벤트핸들링이 먹지않으므로 새롭게 만들어줘야함
		//기준 tr
		var $trFromBtnReply = $this.parent().parent(); 
		// 추가할 요소 기준
		$(html).insertAfter($trFromBtnReply)
			   .find("td")
			   .slideDown(800)
			   .find("form")
			   .submit(function(e) {
					var $textarea = $(e.target).children('textarea');
					if(/^(.|\n){1,}$/.test($textarea.val()) == false){
						alert("댓글 내용을 작성해주세요.");
						$textarea.focus();
						e.preventDefault();
			}
			
		});
	
		
		<% } %>
	}); 
	
	/**
	* 로그인 하지 않고 댓글 작성할 수 없다
	*/
	$("[name=boardCommentContent]").focus(function() {
		
		<%if(memberLoggedIn == null) { %>
		   loginAlert();
		<% } %>
		
	});
	
	//폼 제출 유효성 검사
	//1. return false
	//2. event.preventDefault();
	$("[name=boardCommentFrm]").submit(function(e){
		var $boardCommentContent = $("[name=boardCommentContent]");
		if(/^(.|\n){1,}$/.test($boardCommentContent.val()) == false){
			alert("댓글 내용을 작성해주세요.");
			$boardCommentContent.focus();
			e.preventDefault();
		}
	});
	
	
});

function loginAlert() {
	alert("로그인 후 댓글 작성 가능합니다.");
	$("#memberId").focus();
}

function fileDownload(oName, rName){
	console.log(oName, rName);
	location.href = "<%= request.getContextPath() %>/board/newHost/fileDownload?oName=" +oName + "&rName=" +rName;
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
		<td><%= board.getBoardNo() %></td>
	</tr>
	<tr>
		<th>제 목</th>
		<td><%= board.getBoardTitle() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%= board.getBoardWriter() %></td>
	</tr>
	<tr>
		<th>조회수</th>
		<td><%= board.getBoardReadCount() %></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시!  없을떄는 안보이게!--%>
			<% if(board.getBoardOriginalFileName()!= null) { %>
			<a href="javascript:fileDownload('<%=board.getBoardOriginalFileName() %>','<%= board.getBoardRenamedFileName()%>')">
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/fileLogo.png" width=16px>
			<%= board.getBoardOriginalFileName() %>
			</a> 
		<% } %>
		</td>
	</tr>
	<tr  style="text-align:left;">
		<td colspan="5">
		<% if(board.getBoardOriginalFileName() != null) { %>
		<img src="<%=request.getContextPath() %>/upload/board/<%=board.getBoardRenamedFileName() %>" alt="미리보기" />
		<% } %>
		<br />
		<%=board.getBoardContent()%>
		</td>
	</tr>
	<tr>
		<%-- 호스트와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
		 <%if(
			   memberLoggedIn != null	
			&& (memberLoggedIn.getMemberId().equals(board.getBoardWriter())
			|| MemberService.ADMIN_MEMBER_ROLE==(memberLoggedIn.getMemberRole()))
			) { %> 
		<%-- <%if(
		   memberLoggedIn != null	
		&& ((memberLoggedIn.getMemberId().equals(board.getBoardWriter())
		|| MemberService.HOST_MEMBER_ROLE==(memberLoggedIn.getMemberRole())
		|| MemberService.ADMIN_MEMBER_ROLE==(memberLoggedIn.getMemberRole()))
		)) { %> --%>
		<th colspan="2">
		<input type="button" value="수정하기" onclick="updateNewHostBoard();" /> 
		<input type="button" value="삭제하기" onclick="deleteNewHostBoard();" />
	</th>
	</tr>
	<script>
	
		function updateNewHostBoard(){
			location.href = "<%= request.getContextPath() %>/board/newHost/boardUpdate?boardNo=<%=board.getBoardNo()%>";
		/* 	location.href는 get방식 */
		}
		
		function deleteNewHostBoard(){
			if(confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
				$("[name=boardDelFrm]").submit();
			}
		}
	</script>
		
	<form 
		action="<%= request.getContextPath() %>/board/newHost/boardDelete"
		method="post" 
		name="boardDelFrm">
		<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" /> 
		<input type="hidden" name="rName" value="<%= board.getBoardRenamedFileName() != null ? board.getBoardRenamedFileName() : "" %>" />
		</form>
	<% } %>
	</table class="table   table-bordered">
	<hr style="margin-top: 30px;" />
	<div class="comment-container">
		<div class="comment-editor">
		<form
			action="<%=request.getContextPath()%>/board/newHost/CommentInsert"
			method="post" 
			name="boardCommentFrm">
			<input type="hidden" name="boardRef" value="<%=board.getBoardNo() %>" />
			<input type="hidden" name="boardCommentWriter" value="<%= memberLoggedIn != null ? memberLoggedIn.getMemberId() : "" %>" />
			<input type="hidden" name="boardCommentLevel" value="1" /> 
			<input type="hidden" name="boardCommentRef" value="0" />
			<textarea name="boardCommentContent" cols="60" rows="3"></textarea>
			<button type="submit" id="btn-insert">등록</button>
		</form>
	</div>
	<!--table#tbl-comment-->
	<% if(commentList == null || commentList.isEmpty()) { %>
	<%--댓글이 없는경우는 여기이이 --%>
	<% } else { %>
	<%for(BoardComment bc : commentList) {%>
	<table id="tbl-comment">
		<%-- 댓글인 경우 tr.level1 --%>
		<%if(bc.getBoardCommentLevel() == 1) { %>
		<tr class=level1>
			<td>
				<sub class=comment-writer><%=bc.getBoardCommentWriter() %></sub>
				<sub class=comment-date><%=bc.getBoardCommentEnrollDate()%></sub> 
			<br />
			<%-- 댓글내용 --%> 
			<%=bc.getBoardCommentContent() %>
			</td>
			<td>
				<button class="btn-reply" value="<%=bc.getBoardCommentNo()%>">답글</button>
				<% if(memberLoggedIn != null &&
					bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId())
                   || MemberService.ADMIN_MEMBER_ROLE == (memberLoggedIn.getMemberRole())
                   ){ %>
               <%--댓글작성자와 관리자에게만 댓글,답글 삭제버튼 노출 --%>
				<button class="btn-delete" value="<%= bc.getBoardCommentNo() %>">삭제</button>
				<% }%>
			</td>
		</tr>
		<% 	} 
           else { %>
		<tr class=level2>
			<td>
				<sub class=comment-writer><%=bc.getBoardCommentWriter() %></sub>
				<sub class=comment-date><%=bc.getBoardCommentEnrollDate()%></sub> 
				<br />
				<%=bc.getBoardCommentContent() %>
			</td>
			<td>
				 <% if(memberLoggedIn != null
                       	&&(bc.getBoardCommentWriter().equals(memberLoggedIn.getMemberId())
                       		|| MemberService.ADMIN_MEMBER_ROLE==(memberLoggedIn.getMemberRole()))
                       		){ %> 
                <%--  <%if(
					   memberLoggedIn != null	
					&& ((memberLoggedIn.getMemberId().equals(board.getBoardWriter())
					|| MemberService.HOST_MEMBER_ROLE==(memberLoggedIn.getMemberRole())
					|| MemberService.ADMIN_MEMBER_ROLE==(memberLoggedIn.getMemberRole()))
					)) { %> --%>
                 <%--댓글 작성자와 관리자권한이 있는 경우만 노출 --%>
				<button class="btn-delete" value="<%= bc.getBoardCommentNo() %>">삭제</button>
				<% }%>
			</td>
		</tr>
	   <%}
	
	        }	
		}
	 %>
	  </table>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>