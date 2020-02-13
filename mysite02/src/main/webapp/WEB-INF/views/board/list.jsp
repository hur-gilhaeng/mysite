<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board?a=search" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items='${boardlist}' var='b' varStatus='status'>
					<c:choose>
						<c:when test='${!b.shows}'>
							<tr>
							<td>&nbsp;</td>
							<td>삭제된 글입니다</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
							<td>${b.no }</td>
							<td style="text-align:left; padding-left:${20*(b.depth) }px">
								<c:if test='${b.depth>0 }'>
									<img src='/mysite02/assets/images/reply.png'>
								</c:if> 
								<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${b.no }">
									${b.title }
								</a>
							</td>
							<td>${b.userName }</td>
							<td>${b.hit }</td>
							<td>${fn:substring(b.regDate,0,19)}</td>
							<c:choose>
								<c:when test="${b.userNo == authUser.no }">
									<td><a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${b.no }&uno=${b.userNo}" class="del">삭제</a></td>
								</c:when>
								<c:otherwise>
									<td>&nbsp;</td>
								</c:otherwise>
							</c:choose>
						</tr>
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<c:if test='${not empty authUser }'>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=writeform" id="new-book">
							글쓰기
						</a>
					</div>
				</c:if>
							
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>