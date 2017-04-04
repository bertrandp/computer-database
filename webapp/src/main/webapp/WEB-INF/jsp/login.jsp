<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="label.login"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.contextPath}/static/css/bootstrapValidator.min.css" rel="stylesheet"
          media="screen">
</head>
<body>

<%@include file="element/header.jsp" %>

<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1><spring:message code="label.login"/></h1>
                <form action="login" method="POST" id="form">
                    <fieldset>
                        <div id="form-login" class="form-group">
                            <label for="username"> <spring:message code="label.username"/></label>
                            <input type="text" class="form-control" id="username" name="username">
                        </div>
                        <div id="form-password" class="form-group">
                            <label for="password"> <spring:message code="label.password"/></label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                    </fieldset>
                    <div class="form-group">
                        <c:if test="${not empty error}">
                            <div id="messages" class="alert alert-danger"><spring:message code="label.error"/></div>
                        </c:if>
                    </div>
                    <div class="actions pull-right">
                        <spring:message code="label.log-in" var="log"/>
                        <input type="submit" value="${log}" class="btn btn-primary">
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