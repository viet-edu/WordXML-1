<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="vn.com.fsoft.model.HocSinh"%>
<%@page import="java.util.List"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<section class="content">
    <div class="block-header align-center">
        <h2>${title}</h2>
    </div>
    <!-- Horizontal Layout -->
    <div class="row clearfix">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="card">
                <div class="body">
                    <c:if test="${success != null}">
                        <div class="alert alert-success">
                            <strong>${success}</strong>
                        </div>
                    </c:if>
                    <c:if test="${error != null}">
                        <div class="alert alert-danger">
                            <strong>${error}</strong>
                        </div>
                    </c:if>
                    <form:form action="${contextPath}/auth/DoiThongTinAction" method="post" class="form-horizontal" id="validation-form">
                        <div class="row clearfix">
                            <c:set var = "str" value="updateAction"/>
                            <c:if test="${action eq str}">
                                <form:hidden path="maHocSinh" class="form-control" maxlength="5" required="required" autofocus="autofocus"/>
                            </c:if>
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="tieuDe">Họ tên<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
                                <div class="form-group">
                                    <div class="form-line">
                                        <form:input path="tenHocSinh" class="form-control" maxlength="50" required="required" autofocus="autofocus"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="description">Username<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
                                <div class="form-group">
                                    <div class="form-line">
                                        <form:input path="username" class="form-control no-resize" disabled="true"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="description">Password<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-4 col-xs-4">
                                <div class="form-group">
                                    <div class="form-line">
                                        <form:input path="password" class="form-control no-resize" maxlength="20" required="required"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                <form:button name="save" class="btn btn-success m-t-15 w-90 waves-effect">Save</form:button>
                                <a id="btn-cancel" class="btn btn-warning m-t-15 w-90 waves-effect" href="${contextPath}/admin">Cancel</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <!-- #END# Horizontal Layout -->
</section>