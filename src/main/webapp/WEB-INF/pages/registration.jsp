<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Регистрация</title>
</head>

<body>
<div id="main">

    <%--header for each page--%>
    <%@include file="../jspf/header.jspf" %>

    <div id="content">
        <div id="registrationBlock">
            <h1> РЕГИСТРАЦИЯ </h1>

            <form id='register-form' name='userForm' method='post'>

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
                        <td><input type='password' name='secondaryPassword' id="secondaryPassword"/></td>
                    </tr>

                    <tr>
                        <td>Адрес -</td>
                        <td><input type='text' name='address'/></td>
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
        </div>
    </div>

    <%@include file="../jspf/footer.jspf" %>

</div>

</body>
</html>