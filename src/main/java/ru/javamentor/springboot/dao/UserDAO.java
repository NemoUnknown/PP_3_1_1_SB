package ru.javamentor.springboot.dao;



import ru.javamentor.springboot.model.User;

import java.util.List;

public interface UserDAO {
    void add(User user);

    List<User> getList();

    User findById(long id);

    void delete(long id);

    void change(long id, User user);


}
