<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
        $(function() {
            $("#btn-checkid").click(function() {
                var id = $("#id").val();
                if(id == '') {
                    return;
                }
                
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/api/checkid?id=" + id,
                    type: "get",
                    dataType: "json",
                    error: function(xhr, status, err){
                        console.error(err);            
                    },
                    success: function(response){
                        if(response.exist) {
                            alert("존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
                            $("#id").val("");
                            $("#id").focus();
                            return;
                        }
                        
                        // 사용할 수 있는 아이디
                        $("#btn-checkid").hide();
                        $("#img-checkid").show();
                    }
                });
            });
        });
    </script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		
		<form:form modelAttribute="userVo" 
			class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<p style="color:#f00; text-align:left; padding:0">
						<form:errors path="name" />
					</p>
			
			<label class="block-label" for="id">아이디</label>
			<form:input path="id" />
			<p style="color:#f00; text-align:left; padding:0">
						<form:errors path="id" />
					</p>
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:input path="password" type="password" />
			<p style="color:#f00; text-align:left; padding:0">
						<form:errors path="password" />
					</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
