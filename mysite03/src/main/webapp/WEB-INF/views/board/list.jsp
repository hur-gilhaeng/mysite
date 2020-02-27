<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.servletContext.contextPath }/board"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
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
							<c:when test='${b.shows =="deleted"}'>
								<tr>
									<td>&nbsp;</td>
									<td style="text-align:left; padding-left:${20*(b.depth) }px">
										<c:if test='${b.depth>0 }'>
											<img src='/mysite03/assets/images/reply.png'>
										</c:if>
										(삭제된 글입니다.)
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<c:choose>
										<c:when test="${b.userNo == authUser.no }">
											<td><a href="${pageContext.servletContext.contextPath }/board/delete/${b.no }" class="del">삭제</a></td>
										</c:when>
										<c:otherwise>
											<td>&nbsp;</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:when>
							<c:when test='${b.shows =="visible"}'>
								<tr>
									<td>${b.no }</td>
									<td style="text-align:left; padding-left:${20*(b.depth) }px">
										<c:if test='${b.depth>0 }'>
											<img src='/mysite03/assets/images/reply.png'>
										</c:if> <a
										href="${pageContext.servletContext.contextPath }/board/view/${b.no }">
											${b.title } </a>
									</td>
									<td>${b.userName }</td>
									<td>${b.hit }</td>
									<td>${fn:substring(b.regDate,0,19)}</td>
									<c:choose>
										<c:when test="${b.userNo == authUser.no }">
											<td><a href="${pageContext.servletContext.contextPath }/board/delete/${b.no }" class="del">삭제</a></td>
										</c:when>
										<c:otherwise>
											<td>&nbsp;</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:when>
						</c:choose>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${(pp-1)<0}">
								<li>◀</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath }/board?page=${(pp-1)*5+5 }">◀</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach items='${pagelist}' var='p'>
							<c:choose>
								<c:when test="${ p > maxPage }">
									<li>${p }</li>
								</c:when>
								<c:when test="${ p == page }">
									<li class="selected">${p }</li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.servletContext.contextPath }/board?page=${p }">${p }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${maxPage<(pp+1)*5+1}">
								<li>▶</li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageContext.servletContext.contextPath }/board?page=${(pp+1)*5+1 }">▶</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- pager 추가 -->

				<c:if test='${not empty authUser }'>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board/write" id="new-book"> 글쓰기 </a>
					</div>
				</c:if>

			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>