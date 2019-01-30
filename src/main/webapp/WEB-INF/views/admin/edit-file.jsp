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
                    <c:if test="${success != null}">
                        <div class="alert alert-success">
                            <strong>${success}</strong>
                        </div>
                    </c:if>
                    <c:if test="${command != null}">
                        <form:form action="${contextPath}/admin/QuanLyFile/editAction" method="post" class="form-horizontal" id="validation-form">
                            <div class="row clearfix">
                                <div class="col-md-8">
                                    <b>Tags</b><span class="col-red">(*):</span>
                                    <div class="input-group m-b-0-i">
                                        <div class="form-line input-border">
                                            <form:hidden path="fileId"/>
                                            <form:hidden path="type"/>
                                            <form:input path="strTags" class="form-control" required="required" autofocus="autofocus" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row clearfix">
                                <div class="col-md-12">
                                    <div class="input-group">
                                        <div class="pull-left">
                                            <form:button type="submit" class="btn btn-success w-90 waves-effect">Save</form:button>
                                            <a href="${contextPath}/admin/QuanLyFile/Converted/${command.type}" class="btn btn-warning w-90 waves-effect">Cancel</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </c:if>
                    <c:if test="${command == null}">
                        <h3>Data not found!</h3>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(function(){
        $('#strTags').tagsinput({
            confirmKeys: [13, 188],
            cancelConfirmKeysOnEmpty: true,
            trimValue: true
        });
    })
</script>
