package ru.jalolov.webCRUD.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.jalolov.webCRUD.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> index();
    User show(int id);
    void save(User user) throws Exception;
    void update(int id, User user);
    void delete(int id);
    User findByName(String name);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
