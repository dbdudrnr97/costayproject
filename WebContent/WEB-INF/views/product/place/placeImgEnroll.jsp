<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="product.model.vo.Place"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Place place = (Place)request.getAttribute("place");
%>

<body>
<div style="width:800px; margin:0 auto; text-align:center;">
    <form id="placeImgEnroll-Frm"
        action="<%=request.getContextPath()%>/product/place/placeImgEnroll" method="POST" enctype="multipart/form-data">
        <table class="table  table-bordered">
            <tr>
                <th colspan="3">숙소사진업로드</th>
            </tr>
            <tr>
                <td><input type="file" class="upPlace" name="upPlace1" id="upPlace1"></td>
                <td><input type="file" class="upPlace" name="upPlace2" id="upPlace2"></td>
                <td><input type="file" class="upPlace" name="upPlace3" id="upPlace3"></td>
            </tr>
            <tr>
                <td><img id="palceImg1" name="placeimg" src="" style="max-width:250px; max-height:250px;"></td>
                <td><img id="palceImg2" name="placeimg" src="" style="max-width:250px; max-height:250px;"></td>
                <td><img id="palceImg3" name="placeimg" src="" style="max-width:250px; max-height:250px;"></td>
            </tr>
            <tr>
                <th colspan="3">
                    <input type="submit" value="사진등록하기">
                </th>
            </tr>
        </table>
        <input type="hidden" name="placeId" value="<%=place.getPlaceId()%>" />
    </form>
</div>
    <script>
    $('.upPlace').change(function(){
        if(!/([^\s]+(?=\.(jpg|gif|png))\.\2)/.test($(this).val())){
            alert('썸네일사진은 jpg|png|gif 형식의 파일만 가능합니다.');
            $(this).val("");
        }
    });
    $('#upPlace1').change(function(){
        setProfile(this, '#palceImg1');
    });
    $('#upPlace2').change(function(){
        setProfile(this, '#palceImg2');
    });
    $('#upPlace3').change(function(){
        setProfile(this, '#palceImg3');
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
    $("#placeImgEnroll-Frm").submit(function(e){
        var uplaceArr = $('.upPlace');
        $.each(uplaceArr, function(index, elem){
            if(!$(elem).val()){
                alert("3개의 사진을 모두 등록해주세요.");
                e.preventDefault();
                return false;
            }
        });
        return true;
    });
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>