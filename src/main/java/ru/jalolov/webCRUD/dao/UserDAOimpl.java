package ru.jalolov.webCRUD.dao;

import org.springframework.stereotype.Component;
import ru.jalolov.webCRUD.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOimpl implements UserDAO {
    private List<User> users;
    private static int USER_COUNT;

    {
        users = new ArrayList<>();

        users.add(new User(++USER_COUNT, "Mukhammad"));
        users.add(new User(++USER_COUNT, "Tom"));
        users.add(new User(++USER_COUNT, "Alex"));
        users.add(new User(++USER_COUNT, "Jane"));

    }

    @Override
    public List<User> index() {
        return users;
    }

    @Override
    public User show(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    @Override
    public void update(int id, User updateUser) {
        User oldUser = show(id);

        oldUser.setName(updateUser.getName());
    }

    @Override
    public void delete(int id) {
        users.removeIf(u -> u.getId()==id);
    }
}
