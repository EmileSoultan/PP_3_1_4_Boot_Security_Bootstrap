package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> listAll() {
        return userRepo.findAll();
    }

    public List<Role> listRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findById(long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User with id %s not found", id));
        } else {
            return user.get();
        }
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Transactional
    public void edit(long id, User user) {
        User userToBeUpdated = userRepo.findById(id).orElseThrow();
        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setSurname(user.getSurname());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setRoles(user.getRoles());
        userToBeUpdated.setPassword(user.getPassword());
        userRepo.save(userToBeUpdated);
    }

    @Override
    public void deleteById(long id) {
        userRepo.deleteById(id);
    }
}
