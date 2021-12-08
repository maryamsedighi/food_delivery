<%@ page import="com.myproject.fooddelivery.model.UserTable" %>
<%@ page import="com.myproject.fooddelivery.code.FoodDeliveryCode" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar" data-color="white" data-active-color="danger">
    <div class="logo">
        <a href="https://www.linkedin.com/in/maryamsedighi/" class="simple-text logo-mini">
            <div class="logo-image-small">
                <img src="../assets/img/food1.png">
            </div>
        </a>
        <p class="simple-text logo-normal">
            FOOD DELIVERY
        </p>
    </div>
    <div class="sidebar-wrapper">
        <ul class="nav">
            <%
                UserTable user = (UserTable) session.getAttribute(FoodDeliveryCode.USER_SESSION);
                if (Objects.nonNull(user)) {
            %>
            <li class="active ">
                <a href="/">
                    <i class="nc-icon nc-bank"></i>
                    <p>Dashboard</p>
                </a>
            </li>
            <li>
                <a href="/user/list">
                    <i class="nc-icon nc-satisfied"></i>
                    <p>user manager</p>
                </a>
            </li>
            <li>
                <a href="/customer/list">
                    <i class="nc-icon nc-single-02"></i>
                    <p>customer manager</p>
                </a>
            </li>
            <li>
                <a href="/restaurant/list">
                    <i class="nc-icon nc-shop"></i>
                    <p>restaurant manager</p>
                </a>
            </li>
            <li>
                <a href="/order/list">
                    <i class="nc-icon nc-delivery-fast"></i>
                    <p>order manager</p>
                </a>
            </li>
            <li>
                <a href="/user/logout">
                    <i class="nc-icon nc-touch-id"></i>
                    <p>logout</p>
                </a>
            </li>
            <%
                }
            %>
        </ul>
    </div>
</div>
<div class="main-panel">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
        <div class="container-fluid">
            <div class="navbar-wrapper">
                <div class="navbar-toggle">
                    <button type="button" class="navbar-toggler">
                        <span class="navbar-toggler-bar bar1"></span>
                        <span class="navbar-toggler-bar bar2"></span>
                        <span class="navbar-toggler-bar bar3"></span>
                    </button>
                </div>
                <a class="navbar-brand" href="javascript:;">food delivery app</a>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation"
                    aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-bar navbar-kebab"></span>
                <span class="navbar-toggler-bar navbar-kebab"></span>
                <span class="navbar-toggler-bar navbar-kebab"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navigation">
                <form>
                    <div class="input-group no-border">
                        <input type="text" value="" class="form-control" placeholder="Search...">
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <i class="nc-icon nc-zoom-split"></i>
                            </div>
                        </div>
                    </div>
                </form>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link btn-magnify" href="javascript:;">
                            <i class="nc-icon nc-layout-11"></i>
                            <p>
                                <span class="d-lg-none d-md-block">Stats</span>
                            </p>
                        </a>
                    </li>
                    <li class="nav-item btn-rotate dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="nc-icon nc-bell-55"></i>
                            <p>
                                <span class="d-lg-none d-md-block">Some Actions</span>
                            </p>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn-rotate" href="javascript:;">
                            <i class="nc-icon nc-settings-gear-65"></i>
                            <p>
                                <span class="d-lg-none d-md-block">Account</span>
                            </p>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- End Navbar -->

    <div class="content">