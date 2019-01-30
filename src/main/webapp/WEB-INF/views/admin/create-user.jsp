<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
                    <c:if test="${error != null}">
                        <div class="alert alert-danger">
                            <strong>${error}</strong>
                        </div>
                    </c:if>
                    <form:form action="${contextPath}/admin/QuanLyUser/${action}" method="post" class="form-horizontal" id="validation-form">
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
                                        <form:input path="username" class="form-control no-resize" maxlength="50" required="required"/>
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
                        <c:if test="${action eq str}">
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="description">Role<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <div class="form-group">
                                    <div class="input-group">
                                        <div class="form-line">
                                            <label class="form-control">${command.role.name}</label>
                                        </div>
                                        <span class="input-group-addon" style="padding: 0 !important">
                                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Set Permission</button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </c:if>
                        <div class="row clearfix">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                <form:button name="save" class="btn btn-success m-t-15 w-90 waves-effect">Save</form:button>
                                <a id="btn-cancel" class="btn btn-warning m-t-15 w-90 waves-effect" href="${contextPath}/admin/QuanLyUser/list">Cancel</a>
                            </div>
                        </div>
                        <!-- Modal -->
                        <div id="myModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Set permission</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row clearfix">
                                             <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                                 <div class="form-group">
                                                    <c:if test="${permissionList != null}">
                                                        <form:hidden path="role.roleId"/>
                                                        <form:hidden path="role.name"/>
                                                        <form:hidden path="role.roleLevel"/>
                                                        <form:hidden path="role.description"/>
                                                        <c:forEach items="${permissionList}" var="item" varStatus="status">
                                                            <form:checkbox path="permissionList[${status.index}].permissionId" value="${item.permissionId}" label="${item.name}"/>
                                                            <br/>
                                                        </c:forEach>
                                                    </c:if>
                                                 </div>
                                             </div>
                                         </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-success" data-dismiss="modal">Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
    <!-- #END# Horizontal Layout -->
</section>