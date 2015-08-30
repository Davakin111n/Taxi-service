package com.taxi.service.service;

import com.taxi.service.entity.User;

import java.util.List;

public interface ClientService extends GenericService<User> {

    /**
     * Добавляет нового пользователя в базу данных.
     * Adds new user to database.
     *
     * @param user
     * @return
     */
    User addUser(User user);

    /**
     * Проверяет правильность авторизации.
     * Checks correct autorization process.
     *
     * @param login
     * @param password
     * @return
     */
    boolean successLogin(String login, String password);

    /**
     * Возвращает пользователя по заданному email
     * Returns user by email address.
     *
     * @param email
     * @return
     */
    User getByEmail(String email);

    /**
     * Возвращает полный список всех существующих модераторов.
     * Returns all existing moderators as list.
     *
     * @return
     */
    List<User> getListOfModerators();

    /**
     * Делает пользователя модератором(со всеми правами модератора).
     * Make simple user as moderator (with all moderator rights).
     *
     * @param userId
     */
    void makeModerator(Long userId);

    /**
     * Изменяет пароль пользователя по заданному id и паролю.
     * Changes users password by enteting users id and new password string.
     *
     * @param userId
     * @param password
     * @return
     */
    User changeUserPassword(Long userId, String password);

    /**
     * Возвращает список всех обычных пользователей (с самым низкими правовыми рамками на ресурсе).
     * Returns the list of simple users (list of users which have no high-managin-rights in this resouce).
     *
     * @return
     */
    List<User> getListOfSimpleUsers();
}
