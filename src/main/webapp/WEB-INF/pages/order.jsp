<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Просмотр заказа</title>

    <link rel="stylesheet" href="<c:url value='/resources/stylesheet/common.css' />"/>
    <link rel="stylesheet" href="<c:url value='/resources/stylesheet/login.css' />"/>

    <%--Jquery lib --%>
    <script type="text/javascript" src="<c:url value='/resources/js/lib/jquery.min.2.0.0.js' />"></script>
    <%--Jquery lib --%>
    <script type="text/javascript" src="<c:url value='/resources/js/validators/jquery.validate.min.js' />"></script>

    <script type="text/javascript" src="<c:url value='/resources/js/validators/loginValidator.js' />"></script>

</head>
<body>
<div id="main">

    <%--header for each page--%>
    <%@include file="../jspf/header.jspf" %>

    <div id="content">

        <h3>Заказ такси #${requestScope.order.id}:</h3>
        <table>

            <tr>
                <td>Название:</td>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.order.title == null or empty order.title}">
                            <b>*Название отсутствует*</b>
                        </c:when>
                        <c:otherwise>
                            ${requestScope.order.title}
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>Номер телефона:</td>
                <td>${requestScope.order.phone}</td>
            </tr>

            <tr>
                <td>Контактное имя:</td>
                <td>${requestScope.order.contactName}</td>
            </tr>

            <tr>
                <td>Цена:</td>
                <td>
                    <c:choose>
                        <c:when test="${requestScope.order.price == null or empty requestScope.order.price}">
                            <b>*Цена не установлена*</b>
                        </c:when>
                        <c:otherwise>
                            ${requestScope.order.price}
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>Дата создания заявки:</td>
                <td> ${requestScope.order.createDate}</td>
            </tr>

            <tr>
                <td>Начальный адресс:</td>
                <td> ${requestScope.order.beginAddress}</td>
            </tr>

            <tr>
                <td>Начальный номер дома:</td>
                <td> ${requestScope.order.houseNumber}</td>
            </tr>

            <tr>
                <td>Номер подъезда:</td>
                <td> ${requestScope.order.porchNumber}</td>
            </tr>

            <tr>
                <td>Адреса достижения:</td>
                <td>
                    <c:forEach var="address" items="${requestScope.order.addressList}">
                        Адресс: ${address.destinationAddress}<br/>
                        Номер дома: ${address.destinationHouseNumber}<br/>
                        Номер поъезда: ${address.destinationPorchNumber}<br/>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </div>

    <%@include file="../jspf/footer.jspf" %>

</div>

</body>
</html>
