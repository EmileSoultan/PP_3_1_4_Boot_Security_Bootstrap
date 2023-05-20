package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(@ModelAttribute("user") User user, Principal principal,Model model) {
        long id = userService.findByEmail(principal.getName()).orElseThrow().getId();
        model.addAttribute("admin", userService.findById(id));
        model.addAttribute("users", userService.listAll());
        model.addAttribute("roles", userService.listRoles());
        return "admin";
    }
//    @GetMapping("/new")
//    public String createNewUser(Model model, User user) {
//        List<Role> roles = userService.listRoles();
//        model.addAttribute("user", user);
//        model.addAttribute("roles", roles);
//        return "admin";
//    }

    @PostMapping("/new")
    public String createNewUser(User user, Model model) {
            model.addAttribute("user", user);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String edit( Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        List<Role> roles = userService.listRoles();
        model.addAttribute("roles", roles);
        return "redirect:/admin";
    }
//    @PostMapping("/save")
//    public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            List<Role> roles = userService.listRoles();
//            model.addAttribute("roles", roles);
//            return "admin";
//        }
//        userService.save(user);
//        return "redirect:/admin";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.edit(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}

