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
<h5>Изменить личные данные:</h5>

<form name='savePersonData' method='post' action="savePersonData">

    <table id='savePersonData'>
        <tr>
            <td>Имя -</td>
            <td><input type="text" name="clientName" value="${user.clientName}"/></td>
        </tr>

        <tr>
            <td>Фамилия -</td>
            <td><input type="text" name="clientLastName" value="${user.clientLastName}"/></td>
        </tr>

        <tr>
            <td>Адрес -</td>
            <td><input type="text" name="address" value="${user.address}"/></td>
        </tr>

        <tr>
            <td>Телефон -</td>
            <td><input type="text" name="phone" value="${user.phone}"/></td>
        </tr>

        <tr>
            <td>Skype -</td>
            <td><input type="text" name="skype" value="${user.skype}"/></td>
        </tr>

        <tr>
            <td><br/>
                <br/>
                <input type='submit' value='Сохранить'/></td>
        </tr>

    </table>
</form>

<h5>Изменить пароль:</h5>

<form name="changePassword" method="post" action="changePassword">

    <table id="changePassword">

        <tr>
            <td>Старый пароль -</td>
            <td><input type="text" name="password"/></td>
        </tr>

        <tr>
            <td>Новый пароль -</td>
            <td><input type="text" name="newPassword"/></td>
        </tr>

        <tr>
            <td>Новый пароль повторно -</td>
            <td><input type="text" name="secondaryPassword"/></td>
        </tr>

        <tr>
            <td><br/>
                <br/>
                <input type='submit' value='Сохранить'/></td>
        </tr>

    </table>
</form>

<form name="madeOrder" method="get" action="createNewOrder">

    <table id="madeOrder">

        <tr>
            <td>Адрес *-</td>
            <td><input type="text" name="beginAddress"/></td>
        </tr>

        <tr>
            <td>Номер дома -</td>
            <td><input type="text" name="houseNumber"/></td>
        </tr>

        <tr>
            <td>Номер подъезда -</td>
            <td><input type="text" name="porchNumber"/></td>
        </tr>

        <tr>
            <td>Телефон *-</td>
            <td><input type="text" name="phone"/></td>
        </tr>

        <tr>
            <td>Контактное имя *-</td>
            <td><input type="text" name="contactName"/></td>
        </tr>

        <tr>
            <td>Пункт назначения *-</td>
            <td><input type="text" name="destinationAddress"/></td>
        </tr>

        <tr>
            <td>Номер дома -</td>
            <td><input type="text" name="destinationHouseNumber"/></td>
        </tr>

        <tr>
            <td>Номер поъезда -</td>
            <td><input type="text" name="destinationPorchNumber"/></td>
        </tr>

        <tr>
            <td>Требования к автомобилю -</td>
            <td><input type="text" name="carOption"/></td>
        </tr>

        <tr>
            <td>Пожелания -</td>
            <td><input type="text" name="note"/></td>
        </tr>

        <tr>
            <td><br/>
                <br/>
                <input type='submit' value='Сохранить'/></td>
        </tr>

    </table>
</form>

<h3>Ваши заказы:</h3>

<table id="orderTable" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <td>Время/Дата</td>
        <td>Контактное имя</td>
        <td>Номер телефона</td>
        <td>Статус</td>
    </tr>
    </thead>

    <tbody>

    <c:forEach var="order" items="${clientOrders}">
        <tr>
            <td>${order.createDate}</td>
            <td>${order.contactName}</td>
            <td>${order.phone}</td>
            <td>
                <c:if test="${order != null}">
                    <c:choose>
                        <c:when test="${order.accomplished == true}">
                            Выполнен
                        </c:when>
                        <c:when test="${order.active == true}">
                            Активирован
                        </c:when>
                        <c:otherwise>
                            Ожидает активации
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>

<%@include file="../jspf/footer.jspf" %>

</html>