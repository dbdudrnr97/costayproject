<%@page import="product.model.vo.Play"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Play play = (Play)request.getAttribute("play");
%>
<form action="<%=request.getContextPath()%>/product/play/productUpdate" id="Enroll-Frm" method="POST" enctype="multipart/form-data">
        <table border="1">
            <tr>
                <td>썸네일사진</td>
                <td><img id="profileImg" src=""></td>
                <td><input type="file" name="upProfile" id="upProfile"></td>
                <input type="hidden" name="delFile" value="<%=play.getPlayThumbnailRenamedFileName()%>"/>
	        	<%if(play.getPlayThumbnailOriginalFileName() != null){ %>
	        		<input type="hidden" name="profileOriginalFileName" value="<%=play.getPlayThumbnailOriginalFileName()%>"/>
	        		<input type="hidden" name="profileRenamedFileName" value="<%=play.getPlayThumbnailRenamedFileName()%>"/>
	        	<%} %>
            </tr>
            </tr>
            <tr>
                <td><label for="company">주최회사</label></td>
                <td><input type="text" name="company" id="company" value="<%=play.getCompany()%>"readonly></td>
            </tr>
            
            <tr>
                <td><label for="playName">체험활동이름</label></td>
                <td><input type="text" name="playName" id="playName" value="<%=play.getPlayName()%>" readonly></td>                    
            </tr>
            <tr>
                <td><label for="playAddr">상세주소</label></td>
                <td><input type="text" name="playAddr" id="playAddr" value="<%=play.getPlayAddr()%>" required></td>
            </tr>
            <tr>
                <td><label for="availCount">수용인원</label></td>
                <td><input type="number" name="availCount" id="availCount" value="<%=play.getPlayAvailCount()%>" min="1" max="1000" required></td>
            </tr>
            <tr>
                <td><label for="playPrice">체험활동가격</label></td>
                <td><input type="number" name="playPrice" id="playPrice" value="<%=play.getPlayPrice()%>" min="0" required></td>
            </tr>
            <tr>
                <td><label for="playNote">체험활동소개</label></td>
                <td><textarea name="playNote" id="playNote" cols="40" rows="15"><%=play.getPlayNote()%></textarea></td>
            </tr> 
            <tr>
                <td><label for="playLanguage">체험언어</label></td>
                <td><input type="text" name="playLanguage" id="playLanguage" value="<%=play.getPlayLanguage()%>" required></td>
            </tr>
            <tr>
                <td><label for="playTime">체험시간</label></td>
                <td><input type="number" name="playTime" id="playTime" value="<%=play.getPlayTime()%>" min="0" max="20" required></td>
            </tr>
            <tr>
                <td><label for="playFood">제공음식</label></td>
                <td><input type="text" name="playFood" id="playFood" value="<%=play.getPlayFood()%>" required></td>
            </tr>
            <tr>
                <td><label for="playEquipment">준비물</label></td>
                <td><input type="text" name="playEquipment" id="playEquipment" value="<%=play.getPlayEquipment()%>" required></td>
            </tr>
            <tr>
                <td><label for="playTransfort">교통편</label></td>
                <td><input type="text" name="playTransfort" id="playTransfort" value="<%=play.getPlayTransport()%>" required></td>
            </tr>
        </table>
        <input type="submit" value="체험활동 수정" />
        <input type="hidden" name="playId" value="<%=play.getPlayId()%>"/>
    </form>
    <script>
        $('#upProfile').change(function(){
            if(!/([^\s]+(?=\.(jpg|gif|png))\.\2)/.test($("#upProfile").val())){
                alert('썸네일사진은 jpg|png|gif 형식의 파일만 가능합니다.');
                $(this).val("");
                return;
            }
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
        };

        
        $("#Enroll-Frm").submit(function(){
            if(!/([^\s]+(?=\.(jpg|gif|png))\.\2)/.test($("#upProfile").val())){
                alert('썸네일사진은 jpg|png|gif 형식의 파일만 가능합니다.');
                return false;
            }
            
            if($(playName).val().length < 2){
            	alert('체험활동이름은 2글자 이상만 가능합니다');
            	return false;
            }
            
            if($(playNote).val().length < 10){
            	alert('체험소개는 최소 10글자 이상만 가능합니다.');
            	return false;
            }
            return true;
        });
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>