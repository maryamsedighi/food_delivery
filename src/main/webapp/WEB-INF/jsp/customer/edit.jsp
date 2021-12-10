<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-md-12">
        <div class="card-header">
            <h5 class="card-title">
                Edit customer
            </h5>
        </div>
        <div class="card-body">
            <form:form action="/customer/edit" method="post" modelAttribute="customer">
                <div class="row">
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="firstname">firstName:</form:label>
                            <form:input path="firstName" class="form-control" placeholder="firstName"/>
                        </div>
                    </div>
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="lastName">lastName:</form:label>
                            <form:input path="lastName" class="form-control" placeholder="lastName"/>
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
                            <form:label path="phoneNumber">phoneNumber:</form:label>
                            <form:input path="phoneNumber" class="form-control" placeholder="phoneNumber"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="address">address:</form:label>
                            <form:textarea path="address" class="form-control" placeholder="address"/>
                        </div>
                    </div>
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="accountStatus">accountStatus:</form:label>
                            <form:input path="accountStatus" class="form-control" placeholder="accountStatus"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 pr-1">
                        <div class="form-group">
                            <form:label path="userName">userName:</form:label>
                            <form:input path="userName" class="form-control" placeholder="userName"/>
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
            </form:form>
        </div>
    </div>
</div>

<%@include file="../template/footer.jsp" %>