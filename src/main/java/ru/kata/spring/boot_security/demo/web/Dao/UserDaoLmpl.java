package ru.kata.spring.boot_security.demo.web.Dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoLmpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        if (user.getId() != null) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery(
                        "SELECT u from User u WHERE u.email = :username", User.class).
                setParameter("username", username).getSingleResult();
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public void removeUser(Long id) {
        entityManager.remove(getUser(id));
        entityManager.flush();
    }
}
