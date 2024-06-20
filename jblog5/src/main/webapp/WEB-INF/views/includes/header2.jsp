<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<style>
        
        #header h1 a.header-link {
            color: white !important;
            text-decoration: none !important;
        }
        #header h1 a.header-link:hover {
            color: white !important;
            text-decoration: none !important;
        }
    </style>
</head>
<div id="header">
	<a href="${pageContext.request.contextPath}/user/login"></a>
    <h1>
                <a href="${pageContext.request.contextPath}/${blog.id}" class="header-link">${blog.title}</a>
            </h1>
    <ul>
        <c:choose>
            <c:when test="${empty authUser}">
                <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
                <li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/basic">블로그 관리</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
