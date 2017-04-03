<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>
<%@ attribute name="lastPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="search" required="false" type="java.lang.String" %>
<%@ attribute name="column" required="false" type="java.lang.String" %>
<%@ attribute name="order" required="false" type="java.lang.String" %>


<ul class="pagination">
    <c:if test="${page!=1}" >
        <li>
            <tags:link page="${page-1}" limit="${limit}" search="${search}" order="${order}" column="${column}" var="url"/>
            <a href="${url}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    </c:if>
    <c:choose>
        <c:when test="${lastPage <= 10}">
            <c:set var="begin" value="1" />
            <c:set var="end" value="${lastPage}" />
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${page <= 5}">
                    <c:set var="begin" value="1" />
                    <c:set var="end" value="10" />
                </c:when>
                <c:when test="${page >= lastPage - 5 }">
                    <c:set var="begin" value="${lastPage-10}" />
                    <c:set var="end" value="${lastPage}" />
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${page-5}" />
                    <c:set var="end" value="${page+5}" />
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="${begin}" end="${end}">
        <tags:link page="${i}" limit="${limit}" search="${search}" order="${order}" column="${column}" var="url"/>
        <c:choose>
            <c:when test="${i == page}">
                <li class="active">
                    <a href="${url}" >${i}</a>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="${url}">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${page!= lastPage}" >
        <li>
            <tags:link page="${page+1}" limit="${limit}" search="${search}" order="${order}" column="${column}" var="url"/>
            <a href="${url}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </c:if>
</ul>

<div class="btn-group btn-group-sm pull-right" role="group" >
    <tags:link page="1" limit="10" search="${search}" order="${order}" column="${column}" var="url"/>
    <c:choose>
        <c:when test="${limit == 10}">
            <a href="${url}" type="button" class="btn btn-default active">10</a>
        </c:when>
        <c:otherwise>
            <a href="${url}" type="button" class="btn btn-default">10</a>
        </c:otherwise>
    </c:choose>

    <tags:link page="1" limit="50" search="${search}" order="${order}" column="${column}" var="url"/>
    <c:choose>
        <c:when test="${limit == 50}">
            <a href="${url}" type="button" class="btn btn-default active">50</a>
        </c:when>
        <c:otherwise>
            <a href="${url}" type="button" class="btn btn-default">50</a>
        </c:otherwise>
    </c:choose>
    <tags:link page="1" limit="100" search="${search}" order="${order}" column="${column}" var="url"/>
    <c:choose>
        <c:when test="${limit == 100}">
            <a href="${url}" type="button" class="btn btn-default active">100</a>
        </c:when>
        <c:otherwise>
            <a href="${url}" type="button" class="btn btn-default">100</a>
        </c:otherwise>
    </c:choose>
</div>