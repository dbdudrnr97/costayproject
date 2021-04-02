<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Member member = (Member)request.getAttribute("member"); 
%>
<style>
    #update-Frm{
        text-align: center;
    }

    #profileImg {
        max-width: 200px;
    }
</style>

<div class="sub_title">
<h1>정보수정</h1>
<hr />
</div>

<div style="margin:0 auto; text-align:center; width:630px;">
<form action="<%=request.getContextPath()%>/member/memberUpdate" id="update-Frm" method="POST" enctype="multipart/form-data">
    <table class="table  table-bordered">
    	<tr>
    		<td>프로필사진</td>
    		<td><img id="profileImg" src="<%=request.getContextPath()%>/upload/memberProfile/<%=member.getMemberProfileRenamedFileName()%>"></td>
        	<td  style="vertical-align:middle;"><input type="file" name="upProfile" id="upProfile"></td>
        	<input type="hidden" name="delFile" value="<%=member.getMemberProfileRenamedFileName()%>"/>
        	<%if(member.getMemberProfileOriginalFileName() != null){ %>
        		<input type="hidden" name="profileOriginalFileName" value="<%=member.getMemberProfileOriginalFileName()%>"/>
        		<input type="hidden" name="profileRenamedFileName" value="<%=member.getMemberProfileRenamedFileName()%>"/>
        	<%} %>
    	</tr>
        <tr>
            <td><label for="memberId">아이디</label></td>
            <td><input type="text" name="memberId" id="memberId" value="<%=member.getMemberId()%>" readonly></td>                    
        </tr>
        <tr>
            <td><label for="memberName">이름</label></td>
            <td><input type="text" name="memberName" id="memberName" value="<%=member.getMemberName()%>" readonly></td>                    
        </tr>
        <tr>
            <td><label for="password">비밀번호</label></td>
            <td><input type="password" name="password" id="password" required></td>
        </tr>
        <tr>
            <td><label for="password2">비밀번호확인</label></td>
            <td><input type="password" name="password2" id="password2" required></td>
        </tr>
        <tr>
            <td><label for="passport">전화번호</label></td>
            <td><input type="text" name="phone" id="phone" value="<%=member.getPhone()%>" required></td>
        </tr>
        <tr>
            <td><label for="passport">여권번호</label></td>
            <td><input type="text" name="passport" id="passport" value="<%=member.getMemberPassport()%>" required></td>
        </tr>
        <tr>
            <td><label for="address">주소</label></td>
            <td><input type="text" name="address" id="address" value="<%=member.getMemberAddress()%>" required></td>
        </tr>
        <tr>
            <td><label for="email">이메일</label></td>
            <td><input type="email" name="email" id="email" value="<%=member.getMemberEmail()%>" required></td>
        </tr> 
    </table>
    <input type="submit" value="정보변경하기">
    <input type="button" value="취소"/>
</form>
</div>
<script>
    $('#upProfile').change(function(){
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
    
    <%-- 유효성검사--%>
    $("#update-Frm").submit(function(){
    	if($(password).val().length < 4){
    		alert("비밀번호는 4글자 이상이어야 합니다.");
            $(password).select();
            return false;
    	}
    	
    	if($(password).val() != $(password2).val()){
            alert("비밀번호가 일치하지 않습니다.");
            $(password).val('');
            $(password2).val('');
            $(password).select();
            return false;
        }
    	
    	if(/^010[0-9]{8}$/.test($(phone).val()) == false){
        	alert("유효한 전화번호가 아닙니다.");
        	$(phone).select();
        	return false;
        }
    	
    	return true;
    });
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>