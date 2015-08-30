package com.taxi.service.converter;

import com.taxi.service.entity.Order;
import com.taxi.service.entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConverterToEntity {

    public static User convertUserToEntity(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setClientName(resultSet.getString("client_name"));
                user.setClientLastName(resultSet.getString("client_last_name"));
                user.setPhone(resultSet.getString("phone"));
                user.setSkype(resultSet.getString("skype"));
                return user;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static List<User> convertListUserToEntity(ResultSet resultSet) {
        ArrayList userList = new ArrayList();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setClientName(resultSet.getString("client_name"));
                user.setClientLastName(resultSet.getString("client_last_name"));
                user.setPhone(resultSet.getString("phone"));
                user.setSkype(resultSet.getString("skype"));
                userList.add(user);
            }
            return userList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static Order convertOrderToEntity(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setTitle(resultSet.getString("title"));
                order.setNote(resultSet.getString("note"));
                order.setPrice(resultSet.getString("price"));
                order.setIdClient(resultSet.getLong("id_client"));
                order.setLocation(resultSet.getString("location"));
                order.setCreateDate(resultSet.getDate("create_date"));
                return order;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static List<Order> convertListOrderToEntity(ResultSet resultSet) {
        ArrayList orderList = new ArrayList();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setTitle(resultSet.getString("title"));
                order.setNote(resultSet.getString("note"));
                order.setPrice(resultSet.getString("price"));
                order.setIdClient(resultSet.getLong("id_client"));
                order.setLocation(resultSet.getString("location"));
                order.setCreateDate(resultSet.getDate("create_date"));
                orderList.add(order);
            }
            return orderList;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
