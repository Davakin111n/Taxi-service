<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Вход в личный кабинет</title>
    <jsp:include page="../../cssLoader.jsp"></jsp:include>

</head>


<body>

<div id="main">

    <%--header for each page--%>
    <%@include file="../jspf/header.jspf" %>

    <div id="content">
        <div id="loginBlock">

            <h1> ВХОД </h1>

            <form name='loginForm' action="login" method='post' id='loginForm'>

                <fieldset>
                    <legend> E-mail</legend>
                    <input type='text' name='email' id="email"/><br/>
                </fieldset>
                <br/>
                <fieldset>
                    <legend> Пароль</legend>
                    <input type='password' name='password' id="password"/>
                </fieldset>
                <br/> <input type='submit' value='Войти'>
            </form>
        </div>
    </div>

    <%@include file="../jspf/footer.jspf" %>

</div>

</body>
</html>