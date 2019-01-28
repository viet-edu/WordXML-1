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
                    <c:if test="${res != null}">
                        <c:if test="${res.type == 'success'}">
                            <div class="alert alert-info">
                                <strong>${res.message}</strong> <a href="${contextPath}/admin/QuanLyFile/download/${res.fileId}" style="color: red" target="blank">Tải xuống file tại đây</a>
                            </div>
                        </c:if>
                        <c:if test="${res.type == 'error'}">
                            <div class="alert alert-danger">
                                <strong>${res.message}</strong>
                            </div>
                        </c:if>
                    </c:if>
                    
                    <form:form action="${contextPath}/admin/ConvertAction" method="post" class="form-horizontal" id="validation-form" enctype="multipart/form-data">
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="description">Chọn loại convert:<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-5 col-md-5 col-sm-4 col-xs-4">
                                <div class="form-group">
                                    <form:select path="convertType" class="form-control"  autofocus="autofocus">
                                        <form:option value="1">XML -> Word</form:option>
                                        <form:option value="2">Word -> XML</form:option>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="tieuDe">Chọn file<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-5 col-md-5 col-sm-4 col-xs-4">
                                <div class="form-group">
                                    <div class="form-line">
                                        <form:input path="file" type="file" class="form-control" required="required"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="tieuDe">Tags<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-5 col-md-5 col-sm-4 col-xs-4">
                                <div class="form-group">
                                    <div class="form-line">
                                        <form:input path="tags" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                <form:button type="submit" name="convert" class="btn btn-success m-t-15 w-90 waves-effect">Convert</form:button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(function(){
        $('#convertType').val(2);
        $('#file').prop('accept', '.docx');
        $('#convertType').on('change', function(){
            var convertType = $(this).val();
            if (convertType == '1') {
                $('#file').prop('accept', '.xml');
            } else {
                $('#file').prop('accept', '.docx');
            }
        });
        $('#tags').tagsinput({
            confirmKeys: [13, 188],
            cancelConfirmKeysOnEmpty: true,
            trimValue: true
        });
    });
</script>
