<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ attribute name="page" required="true" type="java.lang.Integer" %>
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>
<%@ attribute name="lastPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="search" required="false" type="java.lang.String" %>


<ul class="pagination">
    <c:if test="${page!=1}" >
        <li>
            <tags:link page="${page-1}" limit="${limit}" search="${search}" var="url"/>
            <a href="${url}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    </c:if>
    <c:choose>
        <c:when test="${lastPage <= 10}">
            <c:forEach var="i" begin="1" end="${lastPage}">
                <tags:link page="${i}" limit="${limit}" search="${search}" var="url"/>
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
                        <tags:link page="${i}" limit="${limit}" search="${search}" var="url"/>
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
                        <tags:link page="${i}" limit="${limit}" search="${search}" var="url"/>
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
                        <tags:link page="${i}" limit="${limit}" search="${search}" var="url"/>
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
            <tags:link page="${i}" limit="${limit}" search="${search}" var="url"/>
            <a href="${url}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </c:if>
</ul>

<div class="btn-group btn-group-sm pull-right" role="group" >
    <tags:link page="1" limit="10" search="${search}" var="url"/>
    <a href="${url}" type="button" class="btn btn-default">10</a>
    <tags:link page="1" limit="50" search="${search}" var="url"/>
    <a href="${url}" type="button" class="btn btn-default">50</a>
    <tags:link page="1" limit="100" search="${search}" var="url"/>
    <a href="${url}" type="button" class="btn btn-default">100</a>
</div>