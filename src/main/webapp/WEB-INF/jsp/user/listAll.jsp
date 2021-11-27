<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
        <div class="card-header">
            <div class="row">
                <div class="col-md-8">
                    <h5 class="card-title">User List</h5>
                </div>
                <div class="col-md-4">
                    <a href="/user/add" class="btn btn-info">Add New User</a>
                </div>
            </div>
        </div>
        <div class="card-body">
            <table class="table">
                <thead class=" text-primary">
                <tr>
                    <th>
                        fullName
                    </th>
                    <th>
                        contact
                    </th>
                    <th>
                        email
                    </th>
                    <th>
                        userName
                    </th>
                    <th>
                        tools
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${user.isAdmin}">
                                    admin:
                                </c:when>
                            </c:choose> ${user.fullName}
                        </td>
                        <td>
                                ${user.contact}
                        </td>
                        <td>
                                ${user.email}
                        </td>
                        <td>
                                ${user.userName}
                        </td>
                        <td>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="../template/footer.jsp" %>