package ru.jalolov.webCRUD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jalolov.webCRUD.dao.UserDAO;
import ru.jalolov.webCRUD.dao.UserDAOImpl;
import ru.jalolov.webCRUD.models.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    public List<User> index() {
        return userDAO.index();
    }

    public User show(int id) {
        return userDAO.show(id);
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public void update(int id, User user) {
        userDAO.update(id, user);
    }

    public void delete(int id) {
        userDAO.delete(id);
    }
}
