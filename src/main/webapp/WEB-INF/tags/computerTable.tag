<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ attribute name="list" required="false" type="java.util.List" %>
<%@ attribute name="order" required="false" type="java.lang.String" %>
<%@ attribute name="column" required="false" type="java.lang.String" %>
<%@ attribute name="limit" required="false" type="java.lang.Integer" %>
<%@ attribute name="search" required="false" type="java.lang.String" %>

<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <!-- Variable declarations for passing labels as parameters -->
        <!-- Table header for Computer Name -->

        <th class="editMode" style="width: 60px; height: 22px;">
            <input type="checkbox" id="selectall" />
            <span style="vertical-align: top;">
                                 -  <a href="" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
        </th>
        <th>
            <c:choose>
                <c:when test="${column == 'NAME'}">
                    <c:choose>
                        <c:when test="${order == 'ASC'}">
                            <tags:link limit="${limit}" search="${search}" order="DESC" column="NAME" var="url"/>
                            Computer name <a href="${url}" ><span class="glyphicon glyphicon-sort-by-alphabet pull-right"></span></a>
                        </c:when>
                        <c:otherwise>
                            <tags:link limit="${limit}" search="${search}" order="ASC" column="NAME" var="url"/>
                            Computer name <a href="${url}" ><span class="glyphicon glyphicon-sort-by-alphabet-alt pull-right"></span></a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <tags:link limit="${limit}" search="${search}" order="ASC" column="NAME" var="url"/>
                    Computer name <a href="${url}" ><span class="glyphicon glyphicon-sort pull-right"></span></a>
                </c:otherwise>
            </c:choose>
        </th>
        <th>
            <c:choose>
                <c:when test="${column == 'INTRODUCED'}">
                    <c:choose>
                        <c:when test="${order == 'ASC'}">
                            <tags:link limit="${limit}" search="${search}" order="DESC" column="INTRODUCED" var="url"/>
                            Introduced date <a href="${url}" ><span class="glyphicon glyphicon-sort-by-attributes pull-right"></span></a>
                        </c:when>
                        <c:otherwise>
                            <tags:link limit="${limit}" search="${search}" order="ASC" column="INTRODUCED" var="url"/>
                            Introduced date <a href="${url}" ><span class="glyphicon glyphicon-sort-by-attributes-alt pull-right"></span></a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <tags:link limit="${limit}" search="${search}" order="ASC" column="INTRODUCED" var="url"/>
                    Introduced date <a href="${url}" ><span class="glyphicon glyphicon-sort pull-right"></span></a>
                </c:otherwise>
            </c:choose>
        </th>
        <!-- Table header for Discontinued Date -->
        <th>
            <c:choose>
                <c:when test="${column == 'DISCONTINUED'}">
                    <c:choose>
                        <c:when test="${order == 'ASC'}">
                            <tags:link limit="${limit}" search="${search}" order="DESC" column="DISCONTINUED" var="url"/>
                            Discontinued date <a href="${url}" ><span class="glyphicon glyphicon-sort-by-attributes pull-right"></span></a>
                        </c:when>
                        <c:otherwise>
                            <tags:link limit="${limit}" search="${search}" order="ASC" column="DISCONTINUED" var="url"/>
                            Discontinued date <a href="${url}" ><span class="glyphicon glyphicon-sort-by-attributes-alt pull-right"></span></a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <tags:link limit="${limit}" search="${search}" order="ASC" column="DISCONTINUED" var="url"/>
                    Discontinued date <a href="${url}" ><span class="glyphicon glyphicon-sort pull-right"></span></a>
                </c:otherwise>
            </c:choose>
        </th>
        <!-- Table header for Company -->
        <th>
            <c:choose>
                <c:when test="${column == 'COMPANY_NAME'}">
                    <c:choose>
                        <c:when test="${order == 'ASC'}">
                            <tags:link limit="${limit}" search="${search}" order="DESC" column="COMPANY_NAME" var="url"/>
                            Company <a href="${url}" ><span class="glyphicon glyphicon-sort-by-alphabet pull-right"></span></a>
                        </c:when>
                        <c:otherwise>
                            <tags:link limit="${limit}" search="${search}" order="ASC" column="COMPANY_NAME" var="url"/>
                            Company <a href="${url}" ><span class="glyphicon glyphicon-sort-by-alphabet-alt pull-right"></span></a>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <tags:link limit="${limit}" search="${search}" order="ASC" column="COMPANY_NAME" var="url"/>
                    Company <a href="${url}" ><span class="glyphicon glyphicon-sort pull-right"></span></a>
                </c:otherwise>
            </c:choose>
        </th>

    </tr>
    </thead>
    <!-- Browse attribute computers -->
    <tbody id="results">

        <c:forEach items="${list}" var="computer">
            <tr>
                <td class="editMode">
                    <input type="checkbox" name="cb" class="cb" value="${computer.id}">
                </td>
                <td>
                    <c:set var="url">
                        <c:url value="/computer/edit">
                            <c:param name="id" value="${computer.id}" />
                        </c:url>
                    </c:set>
                    <a href="${url}" onclick="">${computer.name}</a>
                </td>
                <td>${computer.introduced}</td>
                <td>${computer.discontinued}</td>
                <td>${computer.companyName}</td>
            </tr>
        </c:forEach>

    </tbody>
</table>

