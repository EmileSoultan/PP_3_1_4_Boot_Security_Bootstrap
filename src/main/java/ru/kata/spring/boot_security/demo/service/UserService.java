package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listAll();
    void deleteById(long id);
    Optional<User> findByEmail(String email);
    List<Role> listRoles();
    User findById(long id);
    void save(User user);
    void edit(long id, User user);
}
