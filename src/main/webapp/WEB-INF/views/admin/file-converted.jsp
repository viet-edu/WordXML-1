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
                    <div class="header">
                        <form action="${contextPath}/admin/QuanLyFile/search" method="get">
                            <div class="row clearfix">
                                <div class="col-md-6">
                                    <div class="input-group m-b-0-i">
                                        <div class="form-line input-border">
                                            <input type="text"
                                                name="tags"
                                                id="tags"
                                                class="form-control"
                                                placeholder="Input tags"
                                                value="${param.tags eq null ? '' : param.tags}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="input-group m-b-0-i">
                                        <button type="submit" class="btn btn-teal waves-effect">Search</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
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
                                    <th style="width: 5%">Mã file</th>
			            <th style="width: 20%">Tên file</th>
                                    <th style="width: 10%">Trạng thái</th>
                                    <th style="width: 20%">Nhãn file</th>
				    <th style="width: 10%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${fileConvertedList}" var="item">
                                    <tr>
                                        <td>${item.fileId}</td>
                                        <td>${item.fileName}</td>
					<td>
                                            <c:if test="${item.status == '0'}">Chưa download</c:if>
                                            <c:if test="${item.status == '1'}">Đã download</c:if>
                                        </td>
                                        <td>
                                            <c:forEach items="${item.tagFileList}" var="tag">
                                                <a href="${contextPath}/admin/QuanLyFile/search?tags=${tag.tagName}&type=${item.type}"><span class="label label-primary">${tag.tagName}</span></a>
                                            </c:forEach>
                                        </td>
                                        <td class="text-center">
                                            <a href="${contextPath}/admin/QuanLyFile/download/${item.fileId}" class="p-r-5 delete-btn" title="Download file"><span class="glyphicon glyphicon-download-alt"></span></a>
                                            <a href="${contextPath}/admin/QuanLyFile/edit/${item.fileId}" class="p-r-5" title="Edit file"><span class="glyphicon glyphicon-edit"></span></a>
                                            <a href="${contextPath}/admin/QuanLyFile/delete/${item.fileId}?type=${item.type}" title="Delete file"><span class="glyphicon glyphicon-trash"></span></a>
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
<script>
	$(function(){
	    $('#tags').tagsinput({
            confirmKeys: [13, 188],
            cancelConfirmKeysOnEmpty: true,
            trimValue: true
        });
	})
</script>
