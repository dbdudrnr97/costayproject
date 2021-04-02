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
<form action="<%=request.getContextPath()%>/product/place/productEnroll" id="Enroll-Frm" method="POST" enctype="multipart/form-data">
<div style="margin:0 auto; text-align:center; width:800px;">
        <table border="1" class="table  table-bordered">
            <tr>
                <td>썸네일사진</td>
                <td><img id="profileImg" src="" style="max-width:300px; max-height:300px;"></td>
                <td  style="vertical-align:middle; margin:0 auto; text-align:center;"><input type="file" name="upProfile" id="upProfile" style="margin:0 auto;"></td>
            </tr>
            <tr>
                <td><label for="placeName">숙소이름</label></td>
                <td><input type="text" name="placeName" id="placeName" value="" required></td>                    
            </tr>
            <tr>
	            <td>
	            	숙소지역
	            </td>
	            <td>
	            	<select name="placeLoc" id="placeLoc">
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
                <td><label for="placeAddr">상세주소</label></td>
                <td><input type="text" name="placeAddr" id="placeAddr" required></td>
            </tr>
            <tr>
                <td><label for="availCount">수용인원</label></td>
                <td><input type="number" name="availCount" id="availCount" value="" min="1" max="30" required></td>
            </tr>
            <tr>
                <td><label for="placePrice">숙소가격</label></td>
                <td><input type="number" name="placePrice" id="placePrice" value="" min="0" required></td>
            </tr>
            <tr>
                <td><label for="placeNote">숙소소개</label></td>
                <td><textarea name="placeNote" id="placeNote" cols="40" rows="15"></textarea></td>
            </tr> 
            <tr>
                <td><label for="roomCount">방갯수</label></td>
                <td><input type="number" name="roomCount" id="roomCount" value="" min="0" max="20" required></td>
            </tr>
            <tr>
                <th colspan="3">편의시설</th>
            </tr>
            <tr> 
                <td colspan="3"> 
                    <ul id="chkFacility">
                        <li>
                            <input type="checkbox" name="chkF" id="meal" value="취식">
                            <label for="meal">취식</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="wifi" value="와이파이">
                            <label for="wifi">와이파이</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="parking" value="주차장">
                            <label for="parking">주차장</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="bbq" value="바베큐장">
                            <label for="bbq">바베큐장</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="tv" value="TV">
                            <label for="tv">TV</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="computer" value="컴퓨터">
                            <label for="computer">컴퓨터</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="aircon" value="에어컨">
                            <label for="aircon">에어컨</label>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr id="result">
                <td colspan="3"><p id="facility_" name="facility_"></p></td>
                <input type="hidden" name="facility" id="facility" />
            </tr>
            <td><input type="submit" value="숙소등록" /></td>
            <%--히든속성 --%>
            <input type="hidden" name="memberId" id="memberId" value="<%=memberLoggedIn.getMemberId()%>" />
        </table>
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

        $("#chkFacility input:checkbox").change(function(e){
            var facilityStr = "";
            $("[name=chkF]:checked").each(function(index, elem){
                facilityStr += elem.value + ",";
            });
            facilityStr = facilityStr.slice(0,-1);
            
            $("#facility_").html(facilityStr);
            $("#facility").val(facilityStr);
        });
        
        $("#Enroll-Frm").submit(function(){
            if(!/([^\s]+(?=\.(jpg|gif|png))\.\2)/.test($("#upProfile").val())){
                alert('썸네일사진은 jpg|png|gif 형식의 파일만 가능합니다.');
                return false;
            }
            
            if($(placeName).val().length < 2){
            	alert('숙소이름은 2글자 이상만 가능합니다');
            	return false;
            }
            
            if($(placeNote).val().length < 10){
            	alert('숙소소개는 최소 10글자 이상만 가능합니다.');
            	return false;
            }
            return true;
        });
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>