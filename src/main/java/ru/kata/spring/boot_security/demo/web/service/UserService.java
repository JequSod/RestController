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
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(userDao.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Юзер не найден"));
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Transactional
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Transactional
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = user.getRoles();
        roleDao.saveAll(roles);
        userDao.save(user);
    }
}
