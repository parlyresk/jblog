<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="header">
    <h1>${blog.title}</h1>
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
