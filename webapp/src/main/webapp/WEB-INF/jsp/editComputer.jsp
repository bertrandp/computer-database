<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.editComputer"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>

<%@include file="element/header.jsp" %>

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <div class="label label-default pull-right">
                    id: ${computer.id}
                </div>
                <h1><spring:message code="label.editComputer"/></h1>

                <form action="" method="POST" id="form">
                    <input type="hidden" value="${computer.id}" id="id"/>
                    <fieldset>
                        <div class="form-group">
                            <label for="name"> <spring:message code="label.computerName"/></label>
                            <input type="text" class="form-control" id="name" name="name" placeholder="Computer name"
                                   value="${computer.name}">
                        </div>
                        <spring:message code="label.dateFormat" var="date"/>
                        <div class="form-group">
                            <label for="introduced"><spring:message code="label.introducedDate"/></label>
                            <input type="date" class="form-control" id="introduced" name="introduced"
                                   placeholder="${date}" value="${computer.introduced}">
                        </div>
                        <div class="form-group">
                            <label for="discontinued"><spring:message code="label.discontinuedDate"/></label>
                            <input type="date" class="form-control" id="discontinued" name="discontinued"
                                   placeholder="${date}" value="${computer.discontinued}">
                        </div>
                        <div class="form-group">
                            <label for="companyId"><spring:message code="label.company"/></label>
                            <select class="form-control" id="companyId" name="companyId">
                                <option value="0">--</option>
                                <c:forEach items="${companyList}" var="company">
                                    <c:choose>
                                        <c:when test="${company.id == computer.companyId}">
                                            <option value="${company.id}" selected>${company.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${company.id}">${company.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </fieldset>
                    <div class="form-group">
                        <div class="col-md-9 col-md-offset-3">
                            <div id="messages"></div>
                        </div>
                    </div>
                    <div class="actions pull-right">
                        <spring:message code="label.edit" var="edit"/>
                        <input type="submit" value="${edit}" class="btn btn-primary">
                        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-default"><spring:message
                                code="label.cancel"/></a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrapValidator.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/input_validation.js"></script>
</body>
</html>