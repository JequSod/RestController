package ru.kata.spring.boot_security.demo.web.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.web.model.Role;

public interface RoleDao extends JpaRepository <Role,Long> {
}
