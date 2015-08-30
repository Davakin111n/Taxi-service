package com.taxi.service.service;

import com.taxi.service.entity.Order;
import com.taxi.service.filter.OrderFilter;

import java.util.List;

public interface OrderService extends GenericService<Order> {

    /**
     * Возвращает список заказов по определённому фильтру.
     * Returns list of orders by some filter values.
     *
     * @param orderFilter
     * @return
     */
    List<Order> listByFilter(OrderFilter orderFilter);

    /**
     * Добавляет новый заказ такси.
     * Adds new taxi order.
     *
     * @param order
     * @return
     */
    Order addOrder(Order order);

    /**
     * Возвращает список заказов от определённого пользователя по его id.
     * Returns order list by some user by it's id.
     *
     * @param clientId
     * @return
     */
    List<Order> ordersByClient(Long clientId);

    /**
     * Возвращает определённый заказ по Id клиента и Id заказа.
     * Return specific order by clients Id and orders Id.
     *
     * @param clientId
     * @param orderId
     * @return
     */
    Order getOrder(Long clientId, Long orderId);

    /**
     * Возвращает весь список не активных заказов.
     *
     * @return
     */
    List<Order> notActiveOrders();

    /**
     * Делает заказ активным и предаёт ему статус для выполнения.
     * Makes order active and make it should-to-be-done status.
     *
     * @return
     */
    void activateOrder(Long orderId);

    /**
     * Возвращает заказ по определённому поисковому запросу.
     * Returns order by some search query string.
     *
     * @return
     */
    Order returnOrderBySearchQuery(String searchQuery);


}
