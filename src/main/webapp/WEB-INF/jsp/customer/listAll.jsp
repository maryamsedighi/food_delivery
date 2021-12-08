<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
        <div class="card-header">
            <div class="row">
                <div class="col-md-8">
                    <h5 class="card-title">customer List</h5>
                </div>
            </div>
        </div>
        <div class="card-body">
            <table class="table">
                <thead class=" text-primary">
                <tr>
                    <th>
                        firstName
                    </th>
                    <th>
                        lastName
                    </th>
                    <th>
                        email
                    </th>
                    <th>
                        phoneNumber
                    </th>
                    <th>
                        address
                    </th>
                    <th>
                        userName
                    </th>
                    <th>
                        accountStatus
                    </th>
                    <th>
                        photo
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${customerList}" var="customer">
                    <tr>
                        <td>
                                ${customer.firstName}
                        </td>
                        <td>
                                ${customer.lastName}
                        </td>
                        <td>
                                ${customer.email}
                        </td>
                        <td>
                                ${customer.phoneNumber}
                        </td>
                        <td>
                                ${customer.address}
                        </td>
                        <td>
                                ${customer.userName}
                        </td>
                        <td>
                                ${customer.accountStatus}
                        </td>
                        <td>
                                ${customer.photo}
                        </td>
                        <td>
                            <a href="/customer/edit/${customer.id}" class="btn btn-info">Edit</a>
                            <a href="/customer/delete/${customer.id}" class="btn btn-info">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="../template/footer.jsp" %>