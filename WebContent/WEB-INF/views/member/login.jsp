<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String saveId = null;
	Cookie[] cookies = request.getCookies(); //쿠키 배열을 리턴
	if(cookies!=null){
		for(Cookie c : cookies){
	//			System.out.println(c.getName() + " : " + c.getValue());
			if("saveId".equals(c.getName())){
				saveId = c.getValue();
				break;
			}
		}
	//		System.out.println("saveId@header.jsp = " + saveId);
	}	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>로그인</title>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/latest/js/bootstrap.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/latest/css/bootstrap.min.css" rel="stylesheet">
<style>
.container{
	margin: 0 auto;
}
</style>
</head>
<body>
	<!-- 로그인메뉴 시작 -->
	<div style="margin:0 auto; width:100%; height:100%; position:absolute; background-image: url('/costay/images/korea.jpg');">
		<div style="position:relative; top:200px; left:250px;">
			<form 
				id="loginFrm"
				action="<%= request.getContextPath() %>/member/login"
				method="post">
				<input type="hidden" name="location" value="<%=request.getAttribute("location")%>" />
				<div class="container">
		    		<div class="col-md-6 col-md-offset-3 col-sm-3 col-sm-offset-2" style="background-color: rgb(233, 233, 233);">
				        <div class="panel panel-success">
					        <div class="panel-heading">
				                <div class="panel-title text-center">코스테이에 오신것을 환영합니다<br/></div>
		        		    </div>
		            		<div class="panel-body">
			                    <div>
			                    	<input type="text" class="form-control" name="memberId" id="memberId" placeholder="ID" value="<%=saveId != null ? saveId : ""%>" autofocus/>
			                    </div>
		                    	<div>
		                        	<input type="password" class="form-control" name="password" id="password" placeholder="Password"/>
			                        <input style="margin-left:80%" type="checkbox" name="saveId" id="saveId" <%= saveId != null ? "checked" : "" %>/>
					                <label for="saveId">아이디저장</label>
		        	                <br />
		            	        </div>
			                    <div>
			                        <button type="submit" class="form-control btn btn-primary">로그인</button><br/><br/>
		                    	</div>
		                    	<div onclick="location.href='<%=request.getContextPath()%>/member/enroll';">
		                			<input class="btn btn-primary form-control" type="button" value="회원가입" style="margin-bottom:10px;"/>
		                    	</div>
				            </div>
				        </div>
				    </div>
				</div>
	    	</form>
    	</div>
    </div>
	<!-- 로그인메뉴 끝-->

</body>
</html>