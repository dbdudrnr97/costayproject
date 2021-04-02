<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<%
%>
<script>
/* 온로드 함수! 반드시 온로드 함수로 작성할것.html보다 위에있으니 여기서 먼저 실행되면 안됨 ->제일 하위에 작성하거나 온로드함수로 작성할것 */
$(function() {
	$("[name=reviewBoardEnrollFrm]").submit(boardValidate);
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
		name="reviewBoardEnrollFrm"
		action="<%=request.getContextPath() %>/board/review/boardEnroll" 
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
				<%-- 처음에 hidden으로 숨겨놓고 이미지가 생성되면 띄우기 --%>
				<img id="imgPreview" src="" style="max-width:300px; max-height:300px;" alt="이미지 미리보기" /><br>
				<input type="file" name="upFile" id="upFile" onchange="previewFile();">
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td><textarea rows="5" cols="70" name="boardContent"></textarea></td>
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

 <script>
$('#upFile').change(function(){
    setBoardFile(this, '#imgPreview');
    if(!/([^\s]+(?=\.(jpg|gif|png))\.\2)/.test($("#upFile").val())){
        alert('사진은 jpg|png|gif 형식의 파일만 가능합니다.');
    }
});

function setBoardFile(input, imgPreview){
    if(input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){
            $(img).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
};
 
 function previewFile() {
     var preview = document.querySelector('#imgPreview');
     var file = document.querySelector('input[type=file]').files[0];
     var reader = new FileReader();
     
     reader.addEventListener("load",function() {
         preview.src = reader.result; 
         console.log(preview.src)
     },false);
     
     if(file) {
         reader.readAsDataURL(file);
    
     }
 }

 <%--
function setThumbnail(event) { 
	var reader = new FileReader(); 
	
	reader.onload = function(event) { 
	var img = document.createElement("img"); 
	img.setAttribute("src", event.target.result); 
	document.querySelector("div#image_container").appendChild(img);
	 }; 
	reader.readAsDataURL(event.target.files[0]);
   }


	function readURL(input){
		if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                console.log(e);
            }
	}; 
	 --%>
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
