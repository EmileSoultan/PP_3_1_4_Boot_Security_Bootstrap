package ru.kata.spring.boot_security.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 20, message = "Имя можно задать от 2-х до 20-ти букв")
    @Column(nullable = false)
    private String name;
    @NotEmpty(message = "Фамилия не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя можно задать от 2-х до 30-ти букв")
    @Column(nullable = false)
    private String surname;
    @NotEmpty(message = "Электронный адрес не может быть пустым")
    @Email(message = "Электронный адрес должен быть валидным")
    @Column(nullable = false, unique = true, length = 64)
    private String email;
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 3, message = "Пароль не может быть меньше 3-ти символов")
    @Column(nullable = false)
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String email, String password, Set<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, String surname, String email, String password, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", roles=" + roles +
               '}';
    }
}
