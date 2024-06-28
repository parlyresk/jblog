<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header2.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<!-- post.title -->
					<h4>${post.title}</h4>
					
					<!-- post.contents -->
					<p>
						${post.contents}
					<p>
				</div>
				<ul class="blog-list">
				<!-- post 리스트의 .title 전부 출력 -->
					<c:forEach var="p" items="${posts}">
						<li><a href="${pageContext.request.contextPath}/${blog.id}/${category.no}/${p.no}">${p.title}</a> 
						<span><fmt:parseDate value="${p.regDate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate"/>
								<fmt:formatDate value="${parsedDate}" pattern="yyyy/MM/dd"/>

						</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<!-- blog.logo -->
				<!-- <img src="${pageContext.request.contextPath}/${blog.logo}"> -->
				<img src="${pageContext.request.contextPath}${blog.logo}">
			</div>
		</div>

		<div id="navigation">
		<!-- category 리스트 전부 출력 -->
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="c" items="${categories}">
					<li><a href="${pageContext.request.contextPath}/${blog.id}/${c.no}">${c.name}</a></li>
				</c:forEach>
			</ul>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>