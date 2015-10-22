package com.jean.taxi.dict;

public class Constants {

    /**
     * entities (attribute's name)
     */
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String ORDER = "order";
    public static final String ORDERS = "orders";
    public static final String MODERATORS = "moderators";
    public static final String ALL_USERS = "allUsers";
    public static final String ALL_ORDERS = "allOrders";
    public static final String ORDER_LIST_BY_CLIENT = "clientOrders";
    public static final String ORDER_LIST_BY_FILTER = "orderListByFilter";
    public static final String CLIENT_LIST_BY_FILTER = "clientListByFilter";
    public static final String ACTIVE_ORDER_LIST = "activeOrderList";
    public static final String ACCOMPLISHED_ORDER_LIST = "accomplishedOrderList";
    public static final String NOT_ACTIVE_ORDER_LIST_BY_CLIENT = "notActiveOrderListByClient";
    public static final String NOT_ACTIVE_ORDER_LIST = "notActiveOrderList";
    public static final String BAN_LIST = "banList";

    /**
     * Jsp pages mapping name
     */
    public static final String INDEX = "index";
    public static final String LOGIN = "login";
    public static final String REGISTRATION = "registration";
    public static final String PRIVATE_AREA = "privateArea";
    public static final String ADMIN_PANEL = "adminPanel";
    public static final String ERROR = "error";

    /**
     * Jsp pages path name
     */
    public static final String INDEX_PATH = "WEB-INF/pages/index.jsp";
    public static final String ORDER_PATH = "WEB-INF/pages/order.jsp";
    public static final String ORDER_EDIT_PATH = "WEB-INF/pages/editOrder.jsp";
    public static final String LOGIN_PATH = "WEB-INF/pages/login.jsp";
    public static final String REGISTRATION_PATH = "WEB-INF/pages/registration.jsp";
    public static final String PRIVATE_AREA_PATH = "WEB-INF/pages/privateArea.jsp";
    public static final String REWIEWS_LIST_PATH = "WEB-INF/pages/rewiews.jsp";
    public static final String ADMIN_PANEL_PATH = "WEB-INF/pages/adminPanel.jsp";
    public static final String ERROR_PATH = "WEB-INF/pages/error.jsp";

    /**
     * Exception UI messages
     */
    public static final String USER_NOT_FOUND = "Пользователь с таким id не найден!";
    public static final String ORDER_NOT_FOUND = "Заказ с таким id не найден!";

    /**
     * Order types
     */
    public static final String ORDER_TYPES = "orderTypes";

    /**
     * Clinet types
     */
    public static final String CLIENT_TYPES = "clientTypes";

    /**
     * Date options
     */
    public static final String DATE_OPTIONS = "dateOptions";
    public static final String NO_LIMITS = "noLimits";
    public static final String BY_TODAY = "byToday";
    public static final String BY_WEEK = "byWeek";
    public static final String BY_MONTH = "byMonth";

    /**
     * Client types
     */
    public static final String USERS_ALL = "Все";
    public static final String USERS_MODERATORS = "Модераторы";
    public static final String USERS_ACTIVE = "Активные";
    public static final String USERS_BANNED = "Забаненные";
}
