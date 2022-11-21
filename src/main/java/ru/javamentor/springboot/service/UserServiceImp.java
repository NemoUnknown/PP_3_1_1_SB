package ru.javamentor.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.springboot.dao.UserDAO;
import ru.javamentor.springboot.model.User;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private UserDAO userDAO;

    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        return userDAO.getList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDAO.delete(id);
    }

    @Override
    @Transactional
    public void change(long id, User user) {
        userDAO.change(id, user);
    }

    @PostConstruct
    private void init() {
        System.out.println("Получена зависимость: " + userDAO);
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Уничтожен компонент UserService");
    }

    @Override
    @Transactional
    public void addUsers() {
        userDAO.add(new User("Иван", "Иванов", "ivanoff@mail.ru"));
        userDAO.add(new User("Петр", "Петров", "petr94@ya.ru"));
        userDAO.add(new User("Александр", "Сидоров", "SidAlex@mail.ru"));
        userDAO.add(new User("Семен", "Семенов", "semsem99@ya.ru"));
    }
}
