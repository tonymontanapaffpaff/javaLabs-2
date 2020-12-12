<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Enter Customer Information</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styles.css">

</head>
<body>
<jsp:include page="_header.jsp" />

<div class="page-title">Enter Customer Information</div>

<form:form method="POST" modelAttribute="customerForm"
           action="${pageContext.request.contextPath}/shoppingCartCustomer">

    <table>
        <tr>
            <td>Name *</td>
            <td><form:input path="name" value="${pageContext.request.userPrincipal.name}" /></td>
            <td><form:errors path="name" class="error-message" /></td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Submit" /> <input type="reset"
                                                              value="Reset" /></td>
        </tr>
    </table>

</form:form>

<jsp:include page="_footer.jsp" />

</body>
</html>