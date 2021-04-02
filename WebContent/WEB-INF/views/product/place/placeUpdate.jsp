<%@page import="java.util.Arrays"%>
<%@page import="product.model.vo.Place"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Place place = (Place)request.getAttribute("place");
%>
<form action="<%=request.getContextPath()%>/product/place/productUpdate" id="Enroll-Frm" method="POST" enctype="multipart/form-data">
        <table border="1">
            <tr>
                <td>썸네일사진</td>
                <td><img id="profileImg" src="<%=request.getContextPath()%>/upload/productProfile/<%=place.getPlaceThumbnailRenamedFileName()%>" style="max-width:500px;"></td>
                <td><input type="file" name="upProfile" id="upProfile"></td>
                <input type="hidden" name="delFile" value="<%=place.getPlaceThumbnailRenamedFileName()%>"/>
	        	<%if(place.getPlaceThumbnailOriginalFileName() != null){ %>
	        		<input type="hidden" name="profileOriginalFileName" value="<%=place.getPlaceThumbnailOriginalFileName()%>"/>
	        		<input type="hidden" name="profileRenamedFileName" value="<%=place.getPlaceThumbnailRenamedFileName()%>"/>
	        	<%} %>
            </tr>
            <tr>
                <td><label for="placeName">숙소이름</label></td>
                <td><input type="text" name="placeName" id="placeName" value="<%=place.getPlaceName()%>" required></td>                    
            </tr>
            <tr>
                <td><label for="placeAddr">상세주소</label></td>
                <td><input type="text" name="placeAddr" id="placeAddr" value="<%=place.getPlaceAddr()%>" required></td>
            </tr>
            <tr>
                <td><label for="availCount">수용인원</label></td>
                <td><input type="number" name="availCount" id="availCount" min="1" max="30" value="<%=place.getPlaceAvailCount()%>" required></td>
            </tr>
            <tr>
                <td><label for="placePrice">숙소가격</label></td>
                <td><input type="number" name="placePrice" id="placePrice" min="0" value="<%=place.getPlacePrice()%>"required></td>
            </tr>
            <tr>
                <td><label for="placeNote">숙소소개</label></td>
                <td><textarea name="placeNote" id="placeNote" cols="40" rows="15" ><%=place.getPlaceNote()%></textarea></td>
            </tr> 
            <tr>
                <td><label for="roomCount">방갯수</label></td>
                <td><input type="number" name="roomCount" id="roomCount"min="0" max="20" value="<%=place.getPlaceRoomCount()%>"required></td>
            </tr>
            <tr>
                <th colspan="3">편의시설</th>
            </tr>
           
           
            <%String[] facArr = place.getPlaceFacility().split(","); %>
            <tr> 
                <td colspan="3"> 
                    <ul id="chkFacility">
                        <li>
                            <input type="checkbox" name="chkF" id="meal" value="취식" <%=Arrays.asList(facArr).contains("취식") ? "checked" : ""%>>
                            <label for="meal">취식</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="wifi" value="와이파이" <%=Arrays.asList(facArr).contains("와이파이") ? "checked" : ""%>>
                            <label for="wifi">와이파이</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="parking" value="주차장" <%=Arrays.asList(facArr).contains("주차장") ? "checked" : ""%>>
                            <label for="parking">주차장</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="bbq" value="바베큐장" <%=Arrays.asList(facArr).contains("바베큐장") ? "checked" : ""%>>
                            <label for="bbq">바베큐장</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="tv" value="TV" <%=Arrays.asList(facArr).contains("TV") ? "checked" : ""%>>
                            <label for="tv">TV</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="computer" value="컴퓨터" <%=Arrays.asList(facArr).contains("컴퓨터") ? "checked" : ""%>>
                            <label for="computer">컴퓨터</label>
                        </li>
                        <li>
                            <input type="checkbox" name="chkF" id="aircon" value="에어컨" <%=Arrays.asList(facArr).contains("에어컨") ? "checked" : ""%>>
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
            <input type="hidden" name="placeId"  value="<%=place.getPlaceId()%>"/>
            <input type="hidden" name="memberId" value="<%=memberLoggedIn.getMemberId()%>" />
        </table>
    </form>
    <script>
    	$(document).ready(function(){
    		var facilityStr = "";
            $("[name=chkF]:checked").each(function(index, elem){
                facilityStr += elem.value + ",";
            });
            facilityStr = facilityStr.slice(0,-1);
            
            $("#facility_").html(facilityStr);
            $("#facility").val(facilityStr);
    	});
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