<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
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
        <form name="orderFilter" method="post" action="orderListByFilter">
            <h3>Вывод заказов по параметрам:</h3>
            <select name="orderType" aria-label="Тип заказа">
                <c:choose>
                    <c:when test="${requestScope.orderTypes != null}">
                        <c:forEach items="${requestScope.orderTypes}" var="orderType">
                            <option value="${orderType.title}">${orderType.title}</option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${orderTypes}" var="orderType">
                            <option value="${orderType.title}">${orderType.title}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>

            <select name="orderDateOption" aria-label="Ограничение времени">
                <c:choose>
                    <c:when test="${requestScope.dateOptions != null}">
                        <c:forEach items="${requestScope.dateOptions}" var="option">
                            <option value="${option.title}">${option.title}</option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${dateOptions}" var="option">
                            <option value="${option.title}">${option.title}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
            <input type='submit' value='Отобразить'/>
            <c:choose>
                <c:when test="${requestScope.orders != null}">
                    <table id="unactiveOrderTable" class="display" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <td>Время/Дата создания</td>
                            <td>Контактное имя</td>
                            <td>Номер телефона</td>
                            <td>Статус</td>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="order" items="${requestScope.orders}">
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
                                <td><a href="<c:url value='/editOrderFromAdmin?id=${order.id}' />">Редактировать</a>
                                </td>
                                <td><a href="<c:url value='/activateOrder?id=${order.id}' />">Активировать</a></td>
                                <td><a href="<c:url value='/deleteOrderFromAdmin?id=${order.id}' />">Удалить</a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <table id="unactiveOrderTable" class="display" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <td>Время/Дата создания</td>
                            <td>Контактное имя</td>
                            <td>Номер телефона</td>
                            <td>Статус</td>
                        </tr>
                        </thead>

                        <tbody>

                        <c:forEach var="order" items="${orders}">
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
                                <td><a href="<c:url value='/editOrderFromAdmin?id=${order.id}' />">Редактировать</a>
                                </td>
                                <td><a href="<c:url value='/activateOrder?id=${order.id}' />">Активировать</a></td>
                                <td><a href="<c:url value='/deleteOrderFromAdmin?id=${order.id}' />">Удалить</a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </form>

        <form name="clientFilter" method="post" action="clientListByFilter">
            <h3>Вывод юзеров по типу:</h3>
            <select name="clientType" aria-label="Тип пользователя">
                <c:choose>
                    <c:when test="${requestScope.clientTypes != null}">
                        <c:forEach items="${requestScope.clientTypes}" var="clientType">
                            <option value="${clientType.title}">${clientType.title}</option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${clientTypes}" var="clientType">
                            <option value="${clientType.title}">${clientType.title}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>

            <select name="clientDateOption" aria-label="Ограничение времени">
                <c:choose>
                    <c:when test="${requestScope.dateOptions != null}">
                        <c:forEach items="${requestScope.dateOptions}" var="option">
                            <option value="${option.title}">${option.title}</option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${dateOptions}" var="option">
                            <option value="${option.title}">${option.title}</option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>

            <input type='submit' value='Отобразить'/>
            <c:choose>
                <c:when test="${requestScope.users != null}">
                    <table id="users" class="display" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <td>Email</td>
                            <td>Время/Дата регистрации</td>
                            <td>Контактное имя</td>
                            <td>Номер телефона</td>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="user" items="${requestScope.users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.registrationDate}</td>
                                <td>${user.clientName}</td>
                                <td>${user.phone}</td>
                                <td><a href="<c:url value='/madeModerator?id=${user.id}' />">Сделать модератором</a>
                                </td>
                                <td><a href="<c:url value='/banUser?id=${user.id}' />">Забанить</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <table id="users" class="display" cellspacing="0" width="100%">
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
                                <td><a href="<c:url value='/madeModerator?id=${user.id}' />">Сделать модератором</a>
                                </td>
                                <td><a href="<c:url value='/banUser?id=${user.id}' />">Забанить</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>


        </form>

    </div>

    <%@include file="../jspf/footer.jspf" %>

</div>

</body>
</html>