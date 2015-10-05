<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

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

<form name="madeOrder" method="post" action="changePassword">

    <table id="madeOrder">

        <tr>
            <td>Адрес -</td>
            <td><input type="text" name="address"/></td>
        </tr>

        <tr>
            <td>Дата и время -</td>
            <td><input type="datetime" name="orderTime"/></td>
        </tr>

        <tr>
            <td>Контактное имя -</td>
            <td><input type="text" name="contactName"/></td>
        </tr>

        <tr>
            <td>Требования к автомобилю -</td>
            <td><input type="text" name="specialCarOption"/></td>
        </tr>

        <tr>
            <td>Пожелания -</td>
            <td><input type="text" name="secondaryPassword"/></td>
        </tr>

        <tr>
            <td><br/>
                <br/>
                <input type='submit' value='Сохранить'/></td>
        </tr>

    </table>
</form>

<%@include file="../jspf/footer.jspf" %>
</html>