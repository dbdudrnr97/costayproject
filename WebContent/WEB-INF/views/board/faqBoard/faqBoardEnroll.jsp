<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<%
%>
<script>
/* 온로드 함수! 반드시 온로드 함수로 작성할것.html보다 위에있으니 여기서 먼저 실행되면 안됨 ->제일 하위에 작성하거나 온로드함수로 작성할것 */
$(function() {
	$("[name=faqBoardEnrollFrm]").submit(boardValidate);
});


/**
* boardEnrollFrm 유효성 검사
*/
function boardValidate(){
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	var $boardTitle = $("[name=boardTitle]");
	//아무글자 1개 이상
	if(/^.{3,}$/.test($boardTitle.val()) == false) {
		alert("제목을 입력하세요.");
		return false;
	}
	
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	var $boardContent = $("[name=boardContent]");
	// 아무글자 또는 개행문자가 10개 이상
	if(/^(.|\n){10,}$/.test($boardContent.val()) == false){
		alert("내용을 입력하세요");
		return false;
	}

	return true;
}
</script>
<section id="board-container">
<div class="sub_title">
<h3><strong>게시판 작성</strong></h3>
<hr />
</div>
<div style="margin:0 auto; text-align:center; width:800px;">
	<form
		name="faqBoardEnrollFrm"
		action="<%=request.getContextPath() %>/board/faq/boardEnroll" 
		method="post"
		encType="multipart/form-data">
		<!--  (post전송시!!) 파일업로드가 포함된 폼 전송시 필수 속성!! -->
		<table id="tbl-board-view" class="table   table-bordered">
		<tr>
			<th>제 목</th>
			<td><input type="text" name="boardTitle" required></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="boardWriter" value="<%= memberLoggedIn != null ? memberLoggedIn.getMemberId() : ""%>" readonly/>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>			
				<input type="file" name="upFile">
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="40" name="boardContent"></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="등록하기">
			</th>
		</tr>
	</table>
	</form>
</div>
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>
