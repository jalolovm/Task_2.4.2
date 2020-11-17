package ru.jalolov.webCRUD.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Component
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "name", nullable = false)
    private String username;

    @Column (name = "age", nullable = false)
    private int age;

    @Column (name = "password", nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(String role) {
        if(role.equals("ADMIN")){
            this.roles = Set.of(new Role(1L, "ROLE_USER"),
                                new Role(2L, "ROLE_ADMIN"));
        } else if (role.equals("USER")){
            this.roles = Set.of(new Role(1L, "ROLE_USER"));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRoleString(){
        Set<String> roles = getRoles().stream()
                .map(role -> role.getRole())
                .collect(Collectors.toSet());

        if(roles.contains("ROLE_ADMIN")){
            return "ADMIN";
        } else {
            return "USER";
        }
    }

    @Override
    public String toString() {

        return "Username: " + username +
                "\n age:" + age +
                "\n roles: " + getRoleString();
    }
}
