<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	Board board = (Board)request.getAttribute("board");
	System.out.println("board@updateJsp="+board);
	
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<script>
function boardValidate(){
	var $content = $("[name=boardContent]");
	if(/^(.|\n)+$/.test($content.val()) == false){
		alert("내용을 입력하세요");
		return false;
	}
	return true;
}
<%--boardView로 이동 --%>
function boardView(){
	history.go(-1);
}

</script>
<section id="board-container">
<div class="sub_title">
<h3><strong>게시판 수정</strong></h3>
<hr />
</div>
<div style="margin:0 auto; text-align:center; width:800px;">
	<form 
		action="<%=request.getContextPath() %>/board/newHost/boardUpdate" 
		method="post"
		enctype="multipart/form-data"
		name="newHostUpdateFrm">
		<input type="hidden" name="boardNo" value="<%=board.getBoardNo() %>" />
		<table id="tbl-board-view" class="table   table-bordered">
		<tr>
			<th>제 목</th>
			<td><input type="text" name="boardTitle" value="<%=board.getBoardTitle()%>" required></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="hidden" name="boardWriter" value="<%=memberLoggedIn.getMemberId() %>" readonly/>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td >
				<!-- input:file의 value속성은 보안상의 이유로 임의조작 불가능  -->
				<input type="file" name="upFile" value="">
				<!-- 기존 첨부파일이 있을 떄만 작동 -->
				<% if(board.getBoardOriginalFileName() != null) { %>
				<input type="hidden" name="oldBoardOriginalFileName" value="<%=board.getBoardOriginalFileName()%>"/>
				<input type="hidden" name="oldBoardRenamedFileName" value="<%=board.getBoardRenamedFileName()%>"/>
				<p>
				<%= board.getBoardOriginalFileName() %>&nbsp;&nbsp;
				<%-- <label for="delFile">삭제</label>
				<input type="checkbox" name="delFile" id="delFile" value="<%=board.getBoardRenamedFileName() %>"/> --%>
				</p>
			<% } %>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="50" name="boardContent"><%=board.getBoardContent() %></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="수정하기"/>
				<input type="button" value="취소" onclick="boardView();"/>
			</th>
		</tr>
	</table>
	</form>
</div>
<!-- <script>
$('#upFile').change(function(){
    setProfile(this, '#profileImg');
});
function setProfile(input, profileImg){
    if(input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){
            $(profileImg).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}


</script> -->
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
    