<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Вход в личный кабинет</title>
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