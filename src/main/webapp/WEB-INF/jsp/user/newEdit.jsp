<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
        <div class="card-header">
            <h5 class="card-title">
                <c:choose>
                    <c:when test="${user.id eq null}">
                        add new user
                    </c:when>
                    <c:otherwise>
                        edit user
                    </c:otherwise>
                </c:choose>
            </h5>
        </div>
        <div class="card-body">
            <form:form action="/user/add" method="post" modelAttribute="user">
                <div class="row">
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="fullName">fullName:</form:label>
                            <form:input path="fullName" class="form-control" placeholder="fullname"/>
                        </div>
                    </div>
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="contact">contact:</form:label>
                            <form:input path="contact" class="form-control" placeholder="contact"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="email">email:</form:label>
                            <form:input path="email" class="form-control" placeholder="email"/>
                        </div>
                    </div>
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="userName">userName:</form:label>
                            <form:input path="userName" class="form-control" placeholder="userName"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="isAdmin">Is Admin?</form:label>
                            <form:select path="isAdmin" class="form-control">
                                <form:option value="0">no</form:option>
                                <form:option value="1">yes</form:option>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="password">Password:</form:label>
                            <form:password path="password" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="update ml-auto mr-auto">
                        <button type="submit" class="btn btn-primary btn-round">Register</button>
                    </div>
                </div>
                <form:hidden path="id"/>
                <form:hidden path="userAdminId"/>
            </form:form>
        </div>
    </div>
</div>


<%@include file="../template/footer.jsp" %>