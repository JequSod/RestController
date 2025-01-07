package ru.kata.spring.boot_security.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.web.Dao.RoleDao;
import ru.kata.spring.boot_security.demo.web.Dao.UserDao;
import ru.kata.spring.boot_security.demo.web.model.Role;
import ru.kata.spring.boot_security.demo.web.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userDao.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Юзер не найден"));
    }

    @Transactional
    public User showById(Long id) {
        return userDao.showById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public User getUser(Long id) {
        return userDao.showById(id);
    }

    @Transactional
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Collection<Role> roles = user.getRoles();
        roleDao.saveAll(roles);
        userDao.save(user);
    }

    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }
}
