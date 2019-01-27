<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>
        <c:if test="${title != null}">
            ${title}
        </c:if>
        <c:if test="${title == null}">
            WEBSITE CONVERT DATA
        </c:if>
    </title>
    <link rel="icon" href="<c:url value="/resources/favicon.ico" />" type="image/x-icon">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/plugins/bootstrap/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/plugins/node-waves/waves.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/plugins/animate-css/animate.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/plugins/datatables/dataTables.bootstrap4.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/themes/theme-green.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/css/custom.css" />" rel="stylesheet">
    
    <script src="<c:url value="/resources/plugins/jquery/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.js" />"></script>
    <script src="<c:url value="/resources/plugins/jquery-slimscroll/jquery.slimscroll.js" />"></script>
    <script src="<c:url value="/resources/plugins/node-waves/waves.js" />"></script>
    <script src="<c:url value="/resources/plugins/datatables/jquery.dataTables.min.js" />"></script>
    <script src="<c:url value="/resources/plugins/datatables/dataTables.bootstrap4.js" />"></script>
    <script src="<c:url value="/resources/plugins/jquery-validation/jquery.validate.js" />"></script>
    <script src="<c:url value="/resources/js/admin.js"/>"></script>
    <script>
        var GLOBAL_VAR = {
            baseUrl: '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/'
        };
    </script>
    <script src="<c:url value="/resources/js/app.js"/>"></script>
</head>
<body>
    <body class="theme-green">
    <!-- Page Loader -->
    <div class="page-loader-wrapper">
        <div class="loader">
            <div class="preloader">
                <div class="spinner-layer pl-green">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
            <p>Please wait...</p>
        </div>
    </div>
    <!-- #END# Page Loader -->
    <!-- Top Bar -->
    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                <a href="javascript:void(0);" class="bars"></a>
                <a class="navbar-brand" href="${contextPath}"></a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <li><a href="${contextPath}/auth/DangNhap"><i class="material-icons">account_circle</i></a></li>
                    </c:if>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <li><a href="${contextPath}/auth/DoiThongTin">Hi ${pageContext.request.userPrincipal.name},</a></li>
                        <li><a href="${contextPath}/auth/DangXuat"><i class="material-icons">power_settings_new</i></a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
    <!-- #Top Bar -->
    <section>
        <!-- Left Sidebar -->
        <aside id="leftsidebar" class="sidebar">
            <!-- Menu -->
            <div class="menu">
                <ul id="nav" class="list">
                    <li>
                        <a href="${contextPath}/admin">
                            <span>Trang chủ</span>
                        </a>
                    </li>
                    <sec:authorize access="hasAuthority('ROLE_USER_MANAGEMENT')">
                        <li>
                            <a href="${contextPath}/admin/QuanLyUser/list">
                                <span>Quản lý user</span>
                            </a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('ROLE_FILE_MANAGEMENT')">
                       	<li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <span>Quản lý file</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="${contextPath}/admin/QuanLyFile/Converted/word">Word</a>
                                </li>
                                <li>
                                    <a href="${contextPath}/admin/QuanLyFile/Converted/xml">XML</a>
                                </li>
                            </ul>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('ROLE_CONVERT')">
                        <li>
                            <a href="${contextPath}/admin/Convert">
                                <span>Convert</span>
                            </a>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
            <!-- #Menu -->
        </aside>
        <!-- #END# Left Sidebar -->
    </section>

    <jsp:include page="${param.view}" />

    <script type="text/javascript">
    $('.btn-delete').on('click', function () {
        return confirm('Are you sure?');
    });//glyphicon-trash
    $('.glyphicon-trash').on('click', function () {
        return confirm('Are you sure?');
    });
    </script>
</body>
</html>