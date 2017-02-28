<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>
<%@ attribute name="search" required="false" type="java.lang.String" %>
<%@ attribute name="var" required="true" type="java.lang.String" %>


<c:set var="url">
    <c:url value="/dashboard">
        <c:param name="page" value="${page}" />
        <c:param name="limit" value="${limit}" />
        <c:if test="${not empty search}">
            <c:param name="search" value="${search}" />
        </c:if>
    </c:url>
</c:set>
${pageContext.request.setAttribute("url", url)}