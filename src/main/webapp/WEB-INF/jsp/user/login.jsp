<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/header.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="card card-user">
    <div class="card-header">
        <h5 class="card-title">login user</h5>
    </div>
    <div class="card-body">
        <form:form action="/user/login" method="post" modelAttribute="login">
            <div class="row">
                <div class="col-md-6 pr-1">
                    <div class="form-group">
                        <form:label path="userName">user name:</form:label>
                        <form:input path="userName" type="text" class="form-control" placeholder="user name"/>
                    </div>
                </div>
                <div class="col-md-6 pr-1">
                    <div class="form-group">
                        <form:label path="password">password:</form:label>
                        <form:password path="password" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="update ml-auto mr-auto">
                    <button type="submit" class="btn btn-primary btn-round">login</button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<%@include file="../template/footer.jsp" %>
