<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id="enroll-container">
<script>
$(function(){
	/*
	*	회원가임폼 유효성 검사	
	*/
	var frm = document.querySelector("[name=memberEnrollFrm]");
    frm.onsubmit = function(){
    	//프로필 파일형식검사
    	if(!/([^\s]+(?=\.(jpg|gif|png))\.\2)/.test($("#upProfile").val())){
            alert('프로필사진은 jpg|png|gif 형식의 파일만 가능합니다.');
            return false;
        }
        //1. id 검사
        //문자열 길이 length는 속성. 메소드가 아님.
        if(memberId_.value.length < 4){
            alert("아이디는 4글자 이상이어야 합니다.");
            memberId_.select();
            return false;
        }
        //아이디 중복검사
    var $idValid = $("#idValid");
    	if($idValid.val() != 1){
    	alert("아이디 중복검사 해주세요.");
    	$idValid.focus();
    	return false;
    	}

        //2. 이름 검사
        if(/^[a-zA-Z가-힣]{2,}$/.test(memberName.value)==false){
            alert("이름은 한글 2글자 이상이어야 합니다.");
            memberName.select();
            return false;
        }

        //3. 비밀번호 검사 : 4글자 이상 && (pwd.value == pwdCheck.value)
        if(password_.value.length < 4){
            alert("비밀번호는 4글자 이상이어야 합니다.");
            password_.select();
            return false;
        }

        if(password_.value != password2.value){
            alert("비밀번호가 일치하지 않습니다.");
            password_.value = '';   
            password2.value = '';
            password_.focus();
            return false;
        }
        
	var $phone = $("#phone");
        //-제거하기
        $phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
        
        if(/^010[0-9]{8}$/.test($phone.val()) == false){
        	alert("유효한 전화번호가 아닙니다.");
        	$phone.select();
        	return false;
        }
        
        return true;
    }
	/**
	* 중복 검사 이후 아이디를 변경한 경우, 다시 중복검사를 해야한다.
	*/
	//아이디 중복검사 여부
	//생성자, 자바객체 모두 가능
	$("#memberId_").change(function(){
		$("#idValid").val(0);
		});
	
	$('#upProfile').change(function(){
        setProfile(this, '#profileImg');
        if(!/([^\s]+(?=\.(jpg|gif|png))\.\2)/.test($("#upProfile").val())){
            alert('프로필사진은 jpg|png|gif 형식의 파일만 가능합니다.');
        }
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
	});
	
	 /**
	 * 아이디 중복 검사
	 */
	function checkIdDuplicate(){
		//1. 아이디 유효성 검사
		var $memberId = $(memberId_);
		if(/^[a-zA-Z0-9_]{4,}$/.test($memberId.val()) == false){
			alert("유효한 아이디를 입력해주세요.");
			$memberId.select();
			return false;
		}
		
		//2. 팝업을 통해 중복검사
		//폼제출 + 팝업
		var title = "checkIdDuplicatePopup";
		var spec = "left=500px, top=300px, width=300px, height=200px";
		open("", title, spec); 
		//url, title, spec
		
		var $frm = $(document.checkIdDuplicateFrm);// name값은 document에서 바로 접근가능
		//아이디값 세팅
		$frm.find("[name=memberId]")
			.val($memberId.val());
		$frm.attr("action", "<%=request.getContextPath()%>/member/checkIdDuplicate")
					.attr("method", "POST").attr("target", title) //폼과 팝업 연결 설정
					.submit();
		}
</script>

<!-- 아이디 중복 검사용 폼 따로 작성 -->
<form name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
</form>
<div class="sub_title">
 <h3 style="margin-top:40px;"><strong>회원가입 정보 입력</strong></h3>
 <hr />
</div>
<div style="text-align:center; width:800px; margin:0 auto;">
	<form name="memberEnrollFrm" 
		  action="<%=request.getContextPath() %>/member/enroll" 
		  method="post"
		  enctype="multipart/form-data">
		<table  class="table table-bordered" >
			<tr>
                <td>프로필사진</td>
                <td><img id="profileImg" src="" style="max-width:150px; max-height:150px;"></td>
                <td style="vertical-align:middle; margin:0 auto;"><input type="file" name="upProfile" id="upProfile" ></td>
            </tr>
			<tr>
				<th>이름<sup>*</sup></th>
				<td>
					<input type="text" placeholder="name" name="memberName" id="memberName" required />
				</td>
			</tr>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="ID 4글자 이상 입력" name="memberId" id="memberId_" required>
					<input type="button" value="중복검사" onclick="checkIdDuplicate();" />
					<input type="hidden" id="idValid" value="0" />
					<!-- 중복검사 통과인 경우 1 / 통과하지 못한 경우 0 -->
				</td>
			</tr>
			<tr>
				<th>이메일<sup>*</sup></th>
				<td>
					<input type="email" placeholder="email@domain.com" name="memberEmail" id="memberEmail" required />
				</td>
			</tr>
			<tr>
				<th>비밀번호<sup>*</sup></th>
				<td>
				<input type="password" placeholder="password" name="password" id="password_" required />
				</td>
			</tr>		
			<tr>
				<th>비밀번호 확인<sup>*</sup></th>
				<td>
					<input type="password" name="password" id="password2" required />
				</td>
			</tr>
			<tr>
				<th>주소<sup>*</sup></th>
				<td>
					<input type="text" placeholder="address" name="memberAddress" id="memberAddress" required/>
				</td>
			</tr>
			<tr>
				<th>여권 번호<sub>*</sub></th>
				<td>
					<input type="text" placeholder="passport number" name="memberPassport" id="memberPassport" required />
				</td>
			</tr>
			<tr>
				<th>성별<sup>*</sup></th>
				<td>
				<input type="radio" name="gender" id="gender0" value="M" checked/>
				<label for="gender0">남</label>
				<input type="radio" name="gender" id="gender1" value="F" />
				<label for="gender1">여</label>
				</td>
			</tr> 
			<tr>
				<th>전화번호<sup>*</sup></th>
				<td>
				<input type="tel" placeholder="-없이 입력" name="phone" id="phone" maxlength="11" required />
				</td>
			</tr>
			<tr>
				<th>생일<sup>*</sup></th>
				<td>
				<input type="date" name="birth" id="birth" required />
				</td>
			</tr>
					
		</table>	
		<input type="submit" value="회원가입" />
	</form>
</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>