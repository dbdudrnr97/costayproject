<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="sub_title">
<h3><strong>관리자 페이지</strong></h3>
<hr />
</div>
<style>
	img{
		max-width: 200px;
		max-hegiht: 200px;
	}
	table#tbl-member tr td{
		vertical-align:middle;
	}
	input{
		width:300px;
	}
</style>
<div style="margin:0 auto; text-align:center; width:800px;">
<%--숙소등록 요청 리스트 출력 --%>
<input type="button" id="placeList-btn" class="btn btn-secondary my-2 my-sm-0" value="숙소등록  요청 리스트 출력" />
	<div id="placeList"></div><br><br>
	<script>
	$("#placeList-btn").click(function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/admin/placeList",
			method : "GET",
			dataType : "json",
			success : function(data){
				var $table = $("<table id='tbl-member' class='table table-bordered'></table>");
				$.each(data, function(index, elem){
					var html = "<tr>";
					html += "<td><img src='<%=request.getContextPath()%>/upload/productProfile/"+elem.placeThumbnailRenamedFileName+"' /></td>";
					html += "<td width='200px' nowrap>"+elem.placeName+"</td>";
					html += "<td>"+elem.placeId+"</td>";
					html += "<td>"+elem.hostMemberId+"</td>";
					html += "<td><input type='button' value='승인'  class='btn btn-success' onclick="+"location.href='<%=request.getContextPath()%>/admin/placeAccept?placeId="+elem.placeId+"';"+" /><br />";
					html += "<input type='button' value='거절' class='btn btn-danger' onclick="+"location.href='<%=request.getContextPath()%>/admin/placeCancel?placeId="+elem.placeId+"';"+" /></td>";		
					html += "</tr>";
					
					$table.append(html);
				});
				
				$("#placeList").html($table);
			},
			error : function(xhr, status, err){
				console.log(xhr, status, err);
			}
		});
	});
	</script>
<%-- --%>


<%--체험활동 리스트 --%>
<input type="button" id="playList-btn" class="btn btn-secondary my-2 my-sm-0" value="체험활동 리스트 출력" />
<div id="playList"></div><br><br>
<script>
$("#playList-btn").click(function(){
	$.ajax({
		url : "<%=request.getContextPath()%>/admin/playList",
		method : "GET",
		dataType : "json",
		success : function(data){
			var $table = $("<table id='tbl-member' class='table  table-bordered'></table>");
			$.each(data, function(index, elem){
				var html = "<tr>";
				html += "<td><img src='<%=request.getContextPath()%>/upload/productProfile/"+elem.playThumbnailRenamedFileName+"' /></td>";
				html += "<td width='200px' nowrap>"+elem.playName+"</td>";
				html += "<td>"+elem.playId+"</td>";
				html += "<td width='200px' nowrap>"+elem.company+"</td>";
				html += "<td><input type='button' value='수정' class='btn btn-success' onclick="+"location.href='<%=request.getContextPath()%>/product/play/productUpdate?playId="+elem.playId+"';"+" /><br />";
				html += "<input type='button' value='삭제' class='btn btn-danger' onclick="+"location.href='<%=request.getContextPath()%>/product/play/playDelete?playId="+elem.playId+"';"+" /></td>";		
				html += "</tr>";
				
				$table.append(html);
			});
			
			$("#playList").html($table);
		},
		error : function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});
</script>

<%--체험활동 등록 --%>
<input type="button" value="체험활동 등록" id="playEnroll-btn" class="btn btn-secondary my-2 my-sm-0" onclick="location.href='<%=request.getContextPath()%>/admin/play/productEnroll'"/>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>