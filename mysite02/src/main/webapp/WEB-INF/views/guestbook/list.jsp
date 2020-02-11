<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLine", "\n"); %>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url = "/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form action="${pageContext.request.contextPath }/guestbook" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<c:set var = 'listCount' value='${fn:length(list) }'/>
						<c:forEach items='${list}' var='gb'  varStatus='status'>
						<table>
							<tr>
								<td>[${listCount-status.index}]</td>
								<td>${gb.name }</td>
								<td>${gb.regDate }</td>
								<td><a href="${pageContext.request.contextPath }/guestbook?a=deleteform&no=${gb.no }">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
								${fn:replace(gb.contents, newLine,"<br>" )}
								</td>
							</tr>
						</table>
						<br>
						</c:forEach>
						
						<table>
							<tr>
								<td>[0]</td>
								<td>안대혁</td>
								<td>2015-11-10 11:22:30</td>
								<td><a href="">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4>
								안녕하세요. ^^;<br>
								하하하하	
								</td>
							</tr>
						</table>
						
						<br>
					</li>
				</ul>
			</div>
		</div>
		<c:import url = "/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url = "/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>