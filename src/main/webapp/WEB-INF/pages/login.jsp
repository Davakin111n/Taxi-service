<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>


    </title>
</head>

<form name="loginForm" method="post">
    <fieldset>
        <legend> E-mail</legend>
        <input type='text' name='email'/><br/>
    </fieldset>
    <br/>
    <fieldset>
        <legend> Пароль</legend>
        <input type='password' name='password'/>
    </fieldset>
    <br/> <input type='submit' value='Войти'>
</form>

</html>