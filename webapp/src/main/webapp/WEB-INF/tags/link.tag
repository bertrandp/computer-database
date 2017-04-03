<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="page" required="false" type="java.lang.Integer" %>
<%@ attribute name="limit" required="false" type="java.lang.Integer" %>
<%@ attribute name="search" required="false" type="java.lang.String" %>
<%@ attribute name="order" required="false" type="java.lang.String" %>
<%@ attribute name="column" required="false" type="java.lang.String" %>
<%@ attribute name="var" required="true" type="java.lang.String" %>


<c:set var="url">
    <c:url value="/dashboard">
        <c:if test="${not empty page}">
            <c:param name="page" value="${page}" />
        </c:if>
        <c:if test="${not empty limit}">
            <c:param name="limit" value="${limit}" />
        </c:if>
        <c:if test="${not empty search}">
            <c:param name="search" value="${search}" />
        </c:if>
        <c:if test="${not empty order}">
            <c:param name="order" value="${order}" />
        </c:if>
        <c:if test="${not empty column}">
            <c:param name="column" value="${column}" />
        </c:if>
    </c:url>
</c:set>
${pageContext.request.setAttribute("url", url)}