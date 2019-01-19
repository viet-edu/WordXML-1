<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>${errorCode} | WEBSITE CONVERT DATA</title>

    <!-- Favicon-->
    <link rel="icon" href="<c:url value="/resources/favicon.ico" />" type="image/x-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

    <!-- Bootstrap Core Css -->
    <link href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.css" />" rel="stylesheet">

    <!-- Waves Effect Css -->
    <link href="<c:url value="/resources/plugins/node-waves/waves.css" />" rel="stylesheet" />

    <!-- Custom Css -->
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>

<body class="four-zero-four">
    <div class="four-zero-four-container">
        <div class="error-code">${errorCode}</div>
        <div class="error-message">${errorMsg}</div>
        <div class="button-place">
            <a href="${contextPath}" class="btn btn-default btn-lg waves-effect">GO TO HOMEPAGE</a>
        </div>
    </div>

    <!-- Jquery Core Js -->
    <script src="<c:url value="/resources/plugins/jquery/jquery.min.js" />"></script>

    <!-- Bootstrap Core Js -->
    <script src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.js" />"></script>

    <!-- Waves Effect Plugin Js -->
    <script src="<c:url value="/resources/plugins/node-waves/waves.js" />"></script>
</body>

</html>