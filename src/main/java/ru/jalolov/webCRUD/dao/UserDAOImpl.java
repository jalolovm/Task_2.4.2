package ru.jalolov.webCRUD.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.jalolov.webCRUD.models.Role;
import ru.jalolov.webCRUD.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> index() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void save(User user) throws Exception {
        User userExist = findByName(user.getUsername());
        if (userExist != null){
            throw new Exception("ПОЛЬЗОВАТЕЛЬ С ТАКИМ ИМЕНЕМ УЖЕ ЕСТЬ");
        }

        user.setRoles(Set.of(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User user) {
        User updateUser = show(id);
        updateUser.setUsername(user.getUsername());
        updateUser.setAge(user.getAge());
    }

    @Override
    public void delete(int id) {
        User deleteUser = show(id);
        entityManager.remove(deleteUser);
    }

    @Override
    public User findByName(String name) {

        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.username = :name", User.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findAny().orElse(null);
    }
}
