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

<form name='savePersonData' method='post' action="/savePersonData">

<table id='savePersonData'>
        <tr>
            <td>Имя -</td>
            <td><input type='email' name='тфьу'/></td>
        </tr>

        <tr>
            <td>Фамилия -</td>
            <td><input type='text' name='clientLastName'/></td>
        </tr>

        <tr>
            <td>Телефон -</td>
            <td><input type='text' name='phone'/></td>
        </tr>

        <tr>
            <td>Skype -</td>
            <td><input type='text' name='skype'/></td>
        </tr>

        <tr>
            <td><br/>
                <br/>
                <input type='submit' value='Сохранить'/></td>
        </tr>

    </table>
</form>

<h5>Изменить пароль:</h5>

<form name='changePassword' method='post' action="/changePassword">

<table id='changePassword'>

        <tr>
            <td>Пароль* -</td>
            <td><input type='text' name='newPassword'/></td>
        </tr>

        <tr>
            <td>Старый пароль -</td>
            <td><input type='text' name='password'/></td>
        </tr>

        <tr>
            <td>Старый пароль повторно -</td>
            <td><input type='text' name='secondaryPassword'/></td>
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