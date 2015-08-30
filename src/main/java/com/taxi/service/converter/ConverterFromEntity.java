package com.taxi.service.converter;

import com.taxi.service.entity.Order;
import com.taxi.service.entity.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ConverterFromEntity {
    public static void convertNewOrderEntity(Order order, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, order.getTitle());
            preparedStatement.setString(2, order.getNote());
            preparedStatement.setString(3, order.getPrice());
            preparedStatement.setLong(4, order.getIdClient());
            preparedStatement.setString(5, order.getLocation());
            preparedStatement.setDate(6, (Date) order.getCreateDate());
            preparedStatement.setLong(7, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void convertUpdateOrderEntity(Order order, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, order.getTitle());
            preparedStatement.setString(2, order.getNote());
            preparedStatement.setString(3, order.getPrice());
            preparedStatement.setLong(4, order.getIdClient());
            preparedStatement.setString(5, order.getLocation());
            preparedStatement.setLong(6, order.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void convertNewClientEntity(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getClientName());
            preparedStatement.setString(5, user.getClientLastName());
            preparedStatement.setString(6, user.getSkype());
            preparedStatement.setDate(7, (Date) user.getRegistrationDate());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void convertUpdateClientEntity(User user, PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getClientName());
            preparedStatement.setString(5, user.getClientLastName());
            preparedStatement.setString(6, user.getSkype());
            preparedStatement.setLong(7, user.getId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void convertBySearchQuery(PreparedStatement preparedStatement, String searchQuery) {
        try {
            preparedStatement.setString(1, searchQuery);
            preparedStatement.setString(2, searchQuery);
            preparedStatement.setString(3, searchQuery);
            preparedStatement.setString(4, searchQuery);
            preparedStatement.setString(5, searchQuery);
            preparedStatement.setString(6, searchQuery);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
