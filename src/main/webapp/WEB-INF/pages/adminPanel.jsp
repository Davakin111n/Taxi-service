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

        <h3>Список обычных клиентов:</h3>
        <table id="allClientsTable" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <td>Email</td>
                <td>Время/Дата регистрации</td>
                <td>Контактное имя</td>
                <td>Номер телефона</td>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.registrationDate}</td>
                    <td>${user.clientName}</td>
                    <td>${user.phone}</td>
                    <td><a href="<c:url value='/madeModerator?id=${user.id}' />">Сделать модератором</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <form method="post">
            <h3>Неактивные заказы:</h3>
        <table id="unactiveOrderTable" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <td>Время/Дата</td>
                <td>Контактное имя</td>
                <td>Номер телефона</td>
                <td>Статус</td>
            </tr>
            </thead>

            <tbody>

            <c:forEach var="order" items="${notActiveOrderList}">
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
                    <td><a href="<c:url value='/order?id=${order.id}' />">Просмотр</a></td>
                    <td><a href="<c:url value='/editOrderFromAdmin?id=${order.id}' />">Редактировать</a></td>
                    <td><a href="<c:url value='/activateOrder?id=${order.id}' />">Активировать</a></td>
                    <td><a href="<c:url value='/deleteOrderFromAdmin?id=${order.id}' />">Удалить</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        </form>
        <form method="post">
            <h3>Список активных заказов:</h3>
            <table id="activeOrderTable" class="display" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <td>Время/Дата</td>
                    <td>Контактное имя</td>
                    <td>Номер телефона</td>
                    <td>Статус</td>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="order" items="${activeOrderList}">
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
                        <td><a href="<c:url value='/order?id=${order.id}' />">Просмотр</a></td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </form>

    </div>

    <%@include file="../jspf/footer.jspf" %>

</div>

</body>
</html>