<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="header-container">

    <div class="site-name">Ordering system</div>

    <div class="header-bar">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            Hello
            <a href="${pageContext.request.contextPath}/accountInfo">
                    ${pageContext.request.userPrincipal.name} </a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.contextPath}/logout">Logout</a>

        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/login">Login</a>
        </c:if>
    </div>
</div>

<div class="menu-container">

    <a href="${pageContext.request.contextPath}/">Home</a>
    |
    <a href="${pageContext.request.contextPath}/productList">
        Product List
    </a>
    |
    <a href="${pageContext.request.contextPath}/shoppingCart">
        My Cart
    </a>
    |
    <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
        <a href="${pageContext.request.contextPath}/orderList">
            Order List
        </a>
        |
    </security:authorize>

    <security:authorize  access="hasRole('ROLE_MANAGER')">
        <a href="${pageContext.request.contextPath}/product">
            Create Product
        </a>
        |
    </security:authorize>
</div>