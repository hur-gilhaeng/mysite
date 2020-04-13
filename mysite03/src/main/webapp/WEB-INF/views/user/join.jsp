<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn-checkemail").click(function(){
		var email = $("#email").val();
		if(email == ''){
			return;
		}
		$.ajax({
			url: '${pageContext.request.contextPath }/api/user/checkemail?email=' + email,
			type: 'get',
			// contentType: 'application/json'
			data:'',
			dataType: 'json',
			success: function(response){
				if(response.result == 'exist'){
					// alert('존재하는 이메일입니다.'); // 경고창 띄우기
					$('#msg-checkemail').show();
					$("#email")
						.val('')
						.focus();
					return;
				}
				
				$('#btn-checkemail').hide();
				$('#img-checkemail').show();			
			},
			error: function(XHR, status, e){
				console.error(status + ":" + e);
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		</div>
		<div id="content">
			<div id="user">
				<form:form 
					modelAttribute="userVo"
					id="join-form" 
					name="join" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join">
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">
					<p style="font-weight: bold; color: #f00; text-align: left; padding: 0">
						<spring:hasBindErrors name="userVo">
							<c:if test='${errors.hasFieldErrors("name") }'>
								<strong>${errors.getFieldError( 'name' ).defaultMessage }</strong>
								<br/>
								<strong><spring:message code='${errors.getFieldError("name").codes[0] }' /></strong>
							</c:if>
						</spring:hasBindErrors>
					</p>

					<label class="block-label" for="email">이메일</label>
					<form:input path="email"/>
					<input type="button" id="btn-checkemail" value="이메일확인" >
					<img id='img-checkemail' style='width:16px; display:none;' src='${pageContext.request.contextPath }/assets/images/check.png' />
					
					<p id='msg-checkemail' style="font-weight:bold; color:#f00; text-align:left; padding:0; display:none;" >
						존재하는 이메일입니다.
					</p>
					
					<p style="font-weight:bold; color:#f00; text-align:left; padding:0" >
						<form:errors path="email"/>
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password"/>
					<p style="font-weight:bold; color:#f00; text-align:left; padding:0" >
						<form:errors path="password"/>
					</p>

					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form:form>
			</div>
		</div>
		<div id="navigation">
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		</div>
		<div id="footer">
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
		</div>
	</div>
</body>
</html>