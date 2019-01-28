<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<section class="content">
    <div class="container-fluid">
        <div class="block-header align-center">
            <h2>QUẢN LÝ USER</h2>
        </div>
        <!-- Basic Table -->
        <div class="row clearfix">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <c:if test="${success != null}">
                        <div class="alert alert-success">
                            <strong>${success}</strong>
                        </div>
                    </c:if>
                    <div class="header">
                        <a href="${contextPath}/admin/QuanLyUser/create" class="btn btn-success waves-effect">Đăng kí user</a>
                    </div>
                    <div class="body table-responsive">
                        <table id="data-table" class="table table-bordered table-striped">
                            <thead class="btn-success">
                                <tr>
                                	<th style="">STT</th>
                                    <th style="">Mã</th>
                                    <th style="">Họ tên</th>
                                    <th style="">Username</th>
                                    <th style=""></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${userList}" var="item" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${item.maHocSinh}</td>
                                        <td>${item.tenHocSinh}</td>
                                        <td>${item.username}</td>
                                        <td>
                                            <a href="${contextPath}/admin/QuanLyUser/delete/${item.maHocSinh}"><span class="glyphicon glyphicon-trash"></span></a>
                                            <a href="${contextPath}/admin/QuanLyUser/update/${item.maHocSinh}"><span class="glyphicon glyphicon-edit"></span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- #END# Basic Table -->
    </div>
</section>