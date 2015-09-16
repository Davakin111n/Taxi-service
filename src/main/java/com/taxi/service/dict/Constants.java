package com.taxi.service.dict;

public class Constants {

    /**
     * entities (attribute's name)
     */
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String ORDER = "order";
    public static final String ORDERS = "orders";
    public static final String MODERATORS = "moderators";

    public static final String ORDER_LIST_BY_CLIENT = "orderList";
    public static final String NOT_ACTIVE_ORDER_LIST_BY_CLIENT = "notActiveOrderListByClient";
    public static final String NOT_ACTIVE_ORDER_LIST = "notActiveOrderList";

    /**
     * Jsp pages signatures & path
     */
    public static final String INDEX = "WEB-INF/pages/index.jsp";
    public static final String LOGIN = "WEB-INF/pages/login.jsp";
    public static final String REGISTRATION = "WEB-INF/pages/registration.jsp";
    public static final String PRIVATE_AREA = "WEB-INF/pages/privateArea.jsp";
    public static final String REWIEWS_LIST = "WEB-INF/pages/rewiews.jsp";
    public static final String ADMIN_PANEL = "WEB-INF/pages/adminPanel.jsp";
    public static final String ERROR = "WEB-INF/pages/error.jsp";

    /**
     * Exception UI messages
     */
    public static final String USER_NOT_FOUND = "Пользователь с таким id не найден!";
    public static final String ORDER_NOT_FOUND = "Заказ с таким id не найден!";
}
