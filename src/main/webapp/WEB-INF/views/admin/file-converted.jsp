<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <section class="content">
        <div class="container-fluid">
            <div class="block-header align-center">
                <h2>DANH SÁCH FILE CONVERTED</h2>
            </div>
            <!-- Basic Table -->
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <div class="body table-responsive">
                            <c:if test="${error != null}">
                                <div class="alert alert-danger">
                                    <strong>${error}</strong>
                                </div>
                            </c:if>
                            <c:if test="${success != null}">
                                <div class="alert alert-success">
                                    <strong>${success}</strong>
                                </div>
                            </c:if>
                            <table id="data-table" class="table table-bordered table-striped">
                                <thead class="btn-success">
                                    <tr>
                                        <th style="width: 10%">Mã file</th>
                                        <th style="width: 10%">Tên file</th>
                                        <th style="width: 20%">Đường dẫn</th>
                                        <th style="width: 10%">Nhãn file</th>
                                        <th style="width: 5%"></th>
                                        <th style="width: 5%"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${fileConvertedList}" var="item">
                                        <tr>
                                            <td>${item.fileId}</td>
                                            <td>${item.fileName}</td>
                                            <td><c:url value="/resources/uploads/${item.filePath}" /></td>
                                            <td>
                                                <c:forEach items="${item.tagFileList}" var="tag">
                                                    <a href="${contextPath}/admin/QuanLyFile/search?tag=${tag.tagName}&type=${item.type}"><span class="label label-primary">${tag.tagName}</span></a>
                                                </c:forEach>
                                            </td>
                                            <td><a href="${contextPath}/admin/QuanLyFile/download/${item.fileId}" style="color: red" target="blank">Tải xuống</a></td>
                                            <td><a href="${contextPath}/admin/QuanLyFile/delete/${item.fileId}?type=${item.type}" style="color: red">Xóa file</a></td>
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
