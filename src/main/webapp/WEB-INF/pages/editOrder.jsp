<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>

<head>
    <title>
        JeanTaxi - Личный кабинет
    </title>
</head>
<%--header for each page--%>
<%@include file="../jspf/header.jspf" %>
<h5>Редактировать заказ:</h5>

<form name="editOrder" method="get" action="updateOrder">
    <input type="hidden" name="orderId" value="${requestScope.order.id}"/>

    <table id='updateOrder'>
        <tr>
            <td>Название -</td>
            <td><input type="text" name="title" value="${requestScope.order.title}"/></td>
        </tr>

        <tr>
            <td>Телефон -</td>
            <td><input type="text" name="phone" value="${requestScope.order.phone}"/></td>
        </tr>

        <tr>
            <td>Контактное имя -</td>
            <td><input type="text" name="contactName" value="${requestScope.order.contactName}"/></td>
        </tr>

        <tr>
            <td>Цена -</td>
            <td><input type="text" name="price" value="${requestScope.order.price}"/></td>
        </tr>

        <tr>
            <td>Начальный Адрес -</td>
            <td><input type="text" name="beginAddress" value="${requestScope.order.beginAddress}"/></td>
        </tr>

        <tr>
            <td>Номер дома -</td>
            <td><input type="text" name="houseNumber" value="${requestScope.order.houseNumber}"/></td>
        </tr>

        <tr>
            <td>Номер подъезда -</td>
            <td><input type="text" name="porchNumber" value="${requestScope.order.porchNumber}"/></td>
        </tr>

        <tr>
            <td>Пункт назначения -</td>
            <td>
                <c:set var="count" value="1" scope="page"/>
                <c:forEach var="orderAddress" items="${requestScope.order.addressList}">
                    Маршрут №${count}:<br/>
                    Адресс:<input type="text" name="destinationAddress" value="${requestScope.order.porchNumber}"/><br/>
                    Номер дома:<input type="text" name="destinationHouseNumber" value="${requestScope.order.houseNumber}"/><br/>
                    Номер подъезда:<input type="text" name="destinationHouseNumber" value="${requestScope.order.houseNumber}"/><br/>
                    <br/>
                    <c:set var="count" value="${count + 1}" scope="page"/>
                </c:forEach>
            </td>
        </tr>

        <tr>
            <td><br/>
                <br/>
                <input type='submit' value='Сохранить'/></td>
        </tr>

    </table>
</form>
</body>

<%@include file="../jspf/footer.jspf" %>

</html>