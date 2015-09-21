<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>
        Регистрация - Jean Taxi
    </title>
</head>

<form name='userForm' method='post'>

    <table id='tableForRegistration'>
        <tr>
            <td>E-mail* -</td>
            <td><input type='email' name='email'/></td>
        </tr>
        <tr>
            <td>Пароль* -</td>
            <td><input type='password' name='password' id="password"/></td>
        </tr>

        <tr>
            <td>Подтверждение пароля* -</td>
            <td><input type='password' name='secondaryPassword'/></td>
        </tr>

        <tr>
            <td>Фамилия -</td>
            <td><input type='text' name='clientLastName'/></td>
        </tr>

        <tr>
            <td>Имя* -</td>
            <td><input type='text' name='clientName'/></td>
        </tr>

        <tr>
            <td>Телефон -</td>
            <td><input class="numeric" type='text' name='phone'/></td>
        </tr>

        <tr>
            <td>Skype -</td>
            <td><input type='text' name='skype'/></td>
        </tr>

        <tr>
            <td><br/>
                <br/>
                <input type='submit' value='Зарегистрироваться'/></td>
        </tr>

    </table>
</form>

</html>