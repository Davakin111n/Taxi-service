package com.taxi.service.dict;

public class SqlQueryList {
    /**
     * Database basic metadata.
     */
    public static final String ORDER_TABLE = "order_board.order";
    public static final String CLIENT_TABLE = "order_board.client";

    /**
     * Generic queries
     */
    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String INSERT_INTO = "INSERT INTO ";

    /**
     * SELECT statements for client table
     */
    public static final String CLIENTS_ID = "order_board.client WHERE id =?;";
    public static final String SELECT_ALL_MODERATORS = "SELECT * FROM order_board.client WHERE moderator = true;";
    public static final String SELECT_ALL_SIMPLE_USERS = "SELECT * FROM order_board.client WHERE moderator = false AND admin = false;";
    public static final String SELECT_CLIENTS_BY_SEARCH_QUERY = "SELECT * FROM order_board.client WHERE email LIKE '%?%' OR address LIKE '%?%' OR phone LIKE '%?%' OR client_name LIKE '%?%' OR client_last_name LIKE '%?%' OR skype LIKE '%?%';";
    public static final String SELECT_CLIENT_BY_EMAIL = "SELECT * FROM order_board.client WHERE email =?;";

    /**
     * SELECT statements for order table
     */
    public static final String ORDERS_ID = "order_board.order WHERE id = ?;";
    public static final String SELECT_FROM_ORDERS_BY_CLIENTS_ID = "SELECT * FROM order_board.order WHERE id_client =?;";
    public static final String SELECT_ALL_NOT_ACTIVE_ORDERS = "SELECT * FROM order_board.order WHERE active = FALSE;";
    public static final String SELECT_ALL_ORDERS_BY_CLIENT_ID = "SELECT * FROM order_board.order WHERE client_id = ?";
    public static final String SELECT_ORDER_BY_ = "SELECT * FROM Client WHERE email =";
    public static final String SELECT_ORDER_BY_NAME = "SELECT * FROM Client WHERE client_name=";

    /**
     * INSERT statements for client table
     */
    public static final String INSERT_NEW_USER = "INSERT INTO order_board.client(email, password, address, phone, client_name, client_last_name, skype) VALUES(?, ?, ?, ?, ?, ?, ?);";
    public static final String INSERT_NEW_ORDER = "INSERT INTO order(title, note, price, id_client, location, create_date) VALUES (?, ?, ?, ?, ?, ?);";

    /**
     * UPDATE statements for client table
     */
    public static final String UPDATE = "UPDATE ";
    public static final String UPDATE_CLIENT = "order_board.client SET email = ?, password = ?, address = ?, phone = ?, client_name = ?, client_last_name = ?, skype = ? WHERE id = ?;";
    public static final String UPDATE_ORDER = "order_board.order SET title = ?, note = ?, price = ?, id_client = ?, location = ?, create_date = ? WHERE id = ?;";
    public static final String MAKE_MODERATOR = "order_board.client SET moderator = ? WHERE id = ?";

    /**
     * DELETE statements for order table
     */
    public static final String DELETE_ORDER = "DELETE FROM order_board.order WHERE id = ?;";
}
