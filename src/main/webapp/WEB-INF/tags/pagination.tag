<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>
<%@ attribute name="lastPage" required="true" type="java.lang.Integer" %>


<ul class="pagination">
    <c:if test="${page!=1}" >
        <li>
            <c:set var="url">
                <c:url value="/dashboard">
                    <c:param name="page" value="${page-1}" />
                    <c:param name="limit" value="${limit}" />
                </c:url>
            </c:set>
            <a href="${url}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    </c:if>
    <c:choose>
        <c:when test="${lastPage <= 10}">
            <c:forEach var="i" begin="1" end="${lastPage}">
                <c:set var="url">
                    <c:url value="/dashboard">
                        <c:param name="page" value="${i}" />
                        <c:param name="limit" value="${limit}" />
                    </c:url>
                </c:set>
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
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${page <= 5}">
                    <c:forEach var="i" begin="1" end="10">
                        <c:set var="url">
                            <c:url value="/dashboard">
                                <c:param name="page" value="${i}" />
                                <c:param name="limit" value="${limit}" />
                            </c:url>
                        </c:set>
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
                </c:when>
                <c:when test="${page >= lastPage - 5 }">
                    <c:forEach var="i" begin="${lastPage-10}" end="${lastPage}">
                        <c:set var="url">
                            <c:url value="/dashboard">
                                <c:param name="page" value="${i}" />
                                <c:param name="limit" value="${limit}" />
                            </c:url>
                        </c:set>
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
                </c:when>
                <c:otherwise>
                    <c:forEach var="i" begin="${page-5}" end="${page+5}">
                        <c:set var="url">
                            <c:url value="/dashboard">
                                <c:param name="page" value="${i}" />
                                <c:param name="limit" value="${limit}" />
                            </c:url>
                        </c:set>
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
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:if test="${page!= lastPage}" >
        <li>
            <c:set var="url">
                <c:url value="/dashboard">
                    <c:param name="page" value="${page+1}" />
                    <c:param name="limit" value="${limit}" />
                </c:url>
            </c:set>
            <a href="${url}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </c:if>
</ul>

<div class="btn-group btn-group-sm pull-right" role="group" >
    <c:set var="url">
        <c:url value="/dashboard">
            <c:param name="page" value="1" />
            <c:param name="limit" value="10" />
        </c:url>
    </c:set>
    <a href="${url}" type="button" class="btn btn-default">10</a>
    <c:set var="url">
        <c:url value="/dashboard">
            <c:param name="page" value="1" />
            <c:param name="limit" value="50" />
        </c:url>
    </c:set>
    <a href="${url}" type="button" class="btn btn-default">50</a>
    <c:set var="url">
        <c:url value="/dashboard">
            <c:param name="page" value="1" />
            <c:param name="limit" value="100" />
        </c:url>
    </c:set>
    <a href="${url}" type="button" class="btn btn-default">100</a>
</div>