<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<div class="sub_title">
<h3><strong>숙소 등록</strong></h3>
<hr />
</div>
<style>
ul{
   list-style:none;
   }
</style>

<form action="<%=request.getContextPath()%>/admin/play/productEnroll" id="Enroll-Frm" method="POST" enctype="multipart/form-data">
<div style="margin:0 auto; text-align:center; width:800px;">
        <table border="1" class="table  table-bordered">
            <tr>
                <td>썸네일사진</td>
                <td><img id="profileImg" src="" style="max-width:300px; max-height:300px;"></td>
                <td style="vertical-align:middle; margin:0 auto; text-align:center;"><input type="file" name="upProfile" id="upProfile" style="margin:0 auto;"></td>
            </tr>
            <tr>
                <td><label for="company">주최회사</label></td>
                <td><input type="text" name="company" id="company" value=""required></td>
            </tr>
            
            <tr>
                <td><label for="playName">체험활동이름</label></td>
                <td><input type="text" name="playName" id="playName" value="" required></td>                    
            </tr>
            <tr>
	            <td>
	            	체험활동지역
	            </td>
	            <td>
	            	<select name="playLoc" id="playLoc">
	            		<option value="서울">서울</option>
	            		<option value="경기">경기</option>
	            		<option value="강원">강원</option>
	            		<option value="경상">경상</option>
	            		<option value="전라">전라</option>
	            		<option value="충청">충청</option>
	            		<option value="제주">제주</option>
	            	</select>     
	            </td>        
            </tr>
            <tr>
                <td><label for="playAddr">상세주소</label></td>
                <td><input type="text" name="playAddr" id="playAddr" required></td>
            </tr>
            <tr>
                <td><label for="availCount">수용인원</label></td>
                <td><input type="number" name="availCount" id="availCount" value="" min="1" max="1000" required></td>
            </tr>
            <tr>
                <td><label for="playPrice">체험활동가격</label></td>
                <td><input type="number" name="playPrice" id="playPrice" value="" min="0" required></td>
            </tr>
            <tr>
                <td><label for="playNote">체험활동소개</label></td>
                <td><textarea name="playNote" id="playNote" cols="40" rows="15"></textarea></td>
            </tr> 
            <tr>
                <td><label for="playLanguage">체험언어</label></td>
                <td><input type="text" name="playLanguage" id="playLanguage" value="" required></td>
            </tr>
            <tr>
                <td><label for="playTime">체험시간</label></td>
                <td><input type="number" name="playTime" id="playTime" value="" min="0" max="20" required></td>
            </tr>
            <tr>
                <td><label for="playFood">제공음식</label></td>
                <td><input type="text" name="playFood" id="playFood" value=""></td>
            </tr>
            <tr>
                <td><label for="playEquipment">준비물</label></td>
                <td><input type="text" name="playEquipment" id="playEquipment" value=""></td>
            </tr>
            <tr>
                <td><label for="playTransfort">교통편</label></td>
                <td><input type="text" name="playTransfort" id="playTransfort" value=""></td>
            </tr>
        </table>
        <input type="submit" value="체험활동 등록" />
    </div>
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