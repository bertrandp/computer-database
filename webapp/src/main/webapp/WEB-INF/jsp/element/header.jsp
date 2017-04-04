<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard"><spring:message
                code="label.title"/></a>
        <a href="${pageContext.request.contextPath}/logout">
            <button type="button" class="btn btn-default navbar-btn pull-right"><spring:message
                    code="label.logout"/></button>
        </a>
    </div>
</header>