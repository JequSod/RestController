package ru.kata.spring.boot_security.demo.web.Dao;

import ru.kata.spring.boot_security.demo.web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();

    User getUser(Long id);

    void removeUser(Long id);

    void save(User user);

    User findByUsername(String username);
}
