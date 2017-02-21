<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<!DOCTYPE html>
<html>
<head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="../css/main.css" rel="stylesheet" media="screen">
</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
    </div>
</header>

<section id="main">
    <div class="container">
        <h1 id="homeTitle">
            ${count} Computers found
        </h1>
        <div id="actions" class="form-horizontal">
            <div class="pull-left">
                <form id="searchForm" action="#" method="GET" class="form-inline">

                    <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                    <input type="submit" id="searchsubmit" value="Filter by name"
                           class="btn btn-primary" />
                </form>
            </div>
            <div class="pull-right">
                <a class="btn btn-success" id="addComputer" href="/add-computer">Add Computer</a>
                <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
            </div>
        </div>
    </div>

    <form id="deleteForm" action="#" method="POST">
        <input type="hidden" name="selection" value="">
    </form>

    <custom:pagination page="${page}" limit="${limit}"/>
    <c:set var="lastPage" value="${count / limit + 1}"/>

    <div class="container" style="margin-top: 10px;">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <!-- Variable declarations for passing labels as parameters -->
                <!-- Table header for Computer Name -->

                <th class="editMode" style="width: 60px; height: 22px;">
                    <input type="checkbox" id="selectall" />
                    <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                </th>
                <th>
                    Computer name
                </th>
                <th>
                    Introduced date
                </th>
                <!-- Table header for Discontinued Date -->
                <th>
                    Discontinued date
                </th>
                <!-- Table header for Company -->
                <th>
                    Company
                </th>

            </tr>
            </thead>
            <!-- Browse attribute computers -->
            <tbody id="results">

            <c:forEach items="${computerList}" var="computer">
                <tr>
                    <td class="editMode">
                        <input type="checkbox" name="cb" class="cb" value="0">
                    </td>
                    <td>
                        <a href="editComputer.html" onclick="">${computer.name}</a>
                    </td>
                    <td>${computer.introduced}</td>
                    <td>${computer.discontinued}</td>
                    <td>${computer.companyName}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</section>

<footer class="navbar-fixed-bottom">
    <div class="container text-center">
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
            <c:forEach var="i" begin="1" end="${lastPage}">
                <li>
                    <c:set var="url">
                        <c:url value="/dashboard">
                            <c:param name="page" value="${i}" />
                            <c:param name="limit" value="${limit}" />
                        </c:url>
                    </c:set>
                    <c:choose>
                        <c:when test="${i == page}">
                            <a href="${url}" class="btn btn-primary">${i}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${url}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
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
    </div>
</footer>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/dashboard.js"></script>

</body>
</html>