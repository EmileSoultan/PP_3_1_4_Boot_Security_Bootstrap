package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.security.CustomUserDetails;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user.get());
    }
}
