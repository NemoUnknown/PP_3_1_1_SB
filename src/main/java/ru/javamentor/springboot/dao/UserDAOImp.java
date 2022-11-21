package ru.javamentor.springboot.dao;

import org.springframework.stereotype.Repository;
import ru.javamentor.springboot.model.User;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDAOImp implements UserDAO{

    private EntityManager entityManager;

    public UserDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(User user) {
        entityManager.joinTransaction();
        entityManager.persist(user);
    }

    @Override
    public List<User> getList() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findById(long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id=:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(long id) {
        entityManager.joinTransaction();
        try {
            entityManager.remove(findById(id));
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void change(long id, User user) {
        entityManager.joinTransaction();
        User rebuildUser = findById(id);
        rebuildUser.setFirstName(user.getFirstName());
        rebuildUser.setLastName(user.getLastName());
        rebuildUser.setEmail(user.getEmail());
        entityManager.persist(rebuildUser);
    }
}
