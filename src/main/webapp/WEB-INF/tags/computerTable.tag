<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="list" required="true" type="java.util.List" %>

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

        <c:forEach items="${list}" var="computer">
            <tr>
                <td class="editMode">
                    <input type="checkbox" name="cb" class="cb" value="0">
                </td>
                <td>
                    <c:set var="url">
                        <c:url value="/edit-computer">
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

