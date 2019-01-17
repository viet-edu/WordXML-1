<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
</head>
<style>body{margin:0;font-family:"Open Sans",sans-serif;height:100vh;background:url(https://i.imgur.com/HgflTDf.jpg) 50% fixed;background-size:cover}@keyframes spinner{0%{transform:rotateZ(0)}100%{transform:rotateZ(359deg)}}*{box-sizing:border-box}.wrapper{display:flex;align-items:center;flex-direction:column;justify-content:center;width:100%;min-height:100%;padding:20px;background:rgba(4,40,68,.85)}.login{border-radius:2px 2px 5px 5px;padding:10px 20px 80px;width:90%;max-width:320px;background:#fff;position:relative;box-shadow:0 1px 5px rgba(0,0,0,.3)}.login .error-message{color:red}.login input{display:block;padding:15px 10px;margin-bottom:10px;width:100%;border:1px solid #ddd;transition:border-width .2s ease;border-radius:2px;color:#ccc}.login input+i.fa{color:#fff;font-size:1em;position:absolute;margin-top:-47px;opacity:0;left:0;transition:all .1s ease-in}.login input:focus{outline:0;color:#444;border-color:#2196F3;border-left-width:35px}.login input:focus+i.fa{opacity:1;left:30px;transition:all .25s ease-out}.login a{font-size:.8em;color:#2196F3;text-decoration:none}.login .title{color:#444;font-size:1.2em;font-weight:700;margin:10px 0 30px;border-bottom:1px solid #eee;padding-bottom:20px}.login button{cursor:pointer;width:100%;height:100%;padding:10px;background:#2196F3;color:#fff;display:block;border:none;margin-top:20px;position:absolute;left:0;bottom:0;max-height:60px;border:0 solid rgba(0,0,0,.1);border-radius:0 0 2px 2px;transform:rotateZ(0);transition:all .1s ease-out;border-bottom-width:7px}footer{display:block;padding-top:50px;text-align:center;color:#ddd;font-weight:400;text-shadow:0 -1px 0 rgba(0,0,0,.2);font-size:.8em}footer a,footer a:link{color:#fff;text-decoration:none}</style>
<style>.login select {display: block;padding: 15px 10px;margin-bottom: 10px;width: 100%;border: 1px solid #ddd;transition: border-width .2s ease;border-radius: 2px;color: #ccc;}.login select:focus{outline:0;color:#444;border-color:#2196F3;border-left-width:35px}</style>
<style>
</style>
<body>
    <div class="wrapper">
    	<c:if test="${param.error == 'true'}">
	        <div style="color: red; margin: 10px 0px;">
	            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
	        </div>
	    </c:if>
        <form:form class="login text-center" action="${contextPath}/auth/DangKyAction" method="post" id="validation-form">
            <p class="title">Đăng ký</p>
            <div class="form-group">
	            <form:input path="username" placeholder="Tài khoản" autofocus="autofocus" required="required"/>
	            <i class="fa fa-user"></i> 
            </div>
            <div class="form-group">
	            <form:password path="password" placeholder="Mật khẩu" required="required"/>
	            <i class="fa fa-key"></i>
            </div>
            <div class="form-group">
	            <form:select path="maLop" class="form-control show-tick">
                     <option value="">-- Chọn lớp --</option>
                     <form:options items="${lopList}" itemLabel="tenLop" itemValue="maLop"/>
                </form:select>
            </div>
            <button type="submit">
                <span class="state">Đăng ký</span>
            </button>
            <span>Đã có tài khoản? <a href="${contextPath}/auth/DangNhap">Đăng nhập</a> | </span><a href="${contextPath}">Trang chủ</a></span>
        </form:form>
    </div>
</body>
</html>
