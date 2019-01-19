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
                    <form:form action="${contextPath}/admin/ConvertAction" method="post" class="form-horizontal" id="validation-form" enctype="multipart/form-data">
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="tieuDe">Chọn file<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-10 col-md-10 col-sm-8 col-xs-7">
                                <div class="form-group">
                                    <div class="form-line">
                                        <form:input path="file" type="file" class="form-control" required="required" autofocus="autofocus"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                <label for="description">Chọn loại convert:<span class="col-red">(*):</span></label>
                            </div>
                            <div class="col-lg-5 col-md-5 col-sm-4 col-xs-4">
                                <div class="form-group">
                                    <form:select path="convertType" class="form-control">
                                   		<form:option value="1">XML -> Word</form:option>
                                        <form:option value="2">Word -> XML</form:option>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                        <div class="row clearfix">
                            <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-4 col-xs-offset-5">
                                <form:button type="submit" name="convert" class="btn btn-success m-t-15 w-90 waves-effect">Convert</form:button>
                            </div>
                        </div>
                        <c:if test="${res != null}">
                            <div class="row clearfix">
                                <div class="col-lg-2 col-md-2 col-sm-4 col-xs-5 form-control-label">
                                    <label for="description">File đã convert:</label>
                                </div>
                                <div class="col-lg-5 col-md-5 col-sm-4 col-xs-4">
                                    <a href="<c:url value="/resources/uploads/${res.filePath}" />" target="blank">Download file</a>
                                </div>
                            </div>
                        </c:if>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
                