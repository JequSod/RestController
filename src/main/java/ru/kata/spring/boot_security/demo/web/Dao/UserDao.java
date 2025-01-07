package ru.kata.spring.boot_security.demo.web.Dao;

import ru.kata.spring.boot_security.demo.web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void removeUser(Long id);

    void save(User user);

    User findByUsername(String username);

    User showById(Long id);
}
