<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.computerDatabase"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>

<%@include file="element/header.jsp" %>

<section id="main">
    <div class="container">
        <c:if test="${error == 403}">
            <div id="messages" class="alert alert-danger"><spring:message code="label.403"/></div>
        </c:if>
        <h1 id="homeTitle">
            ${pager.count} <spring:message code="label.computersFound"/>
        </h1>
        <div id="actions" class="form-horizontal">
            <div class="pull-left">
                <form id="searchForm" action="" method="GET" class="form-inline">
                    <input type="hidden" value="${pager.limit}" id="limit" name="limit"/>
                    <spring:message code="label.filter" var="filter"/>
                    <spring:message code="label.search" var="search"/>
                    <input type="search" id="searchbox" name="search" class="form-control" placeholder="${search}"
                           value="${pager.search}"/>
                    <input type="submit" id="searchsubmit" value="${filter}"
                           class="btn btn-primary"/>
                </form>
            </div>
            <div class="pull-right">
                <a class="btn btn-success" id="addComputer"
                   href="${pageContext.request.contextPath}/computer/add"><spring:message code="label.addComputer"/></a>
                <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
                        code="label.edit"/></a>
            </div>
        </div>
    </div>

    <form id="deleteForm" action="${pageContext.request.contextPath}/computer/delete" method="POST">
        <input type="hidden" name="selection" value="">
    </form>

    <div class="container" style="margin-top: 10px;">

        <tags:computerTable list="${pager.list}" limit="${pager.limit}" search="${pager.search}" order="${pager.order}"
                            column="${pager.column}"/>

    </div>
</section>

<footer class="navbar-fixed-bottom">
    <div class="container text-center">

        <c:set var="lastPage" value="${pager.count / pager.limit + 1}"/>
        <tags:pagination page="${pager.page}" limit="${pager.limit}" lastPage="${lastPage}" search="${pager.search}"
                         order="${pager.order}" column="${pager.column}"/>

    </div>
</footer>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dashboard.js"></script>

</body>
</html>