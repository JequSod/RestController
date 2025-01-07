package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.model.Role;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> allUsers() {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> allRoles() {
        List<Role> roleList = userService.findAllRoles();
        return ResponseEntity.ok(roleList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.showById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);

    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping(value = "/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable("id") Long id, Model model) {
        userService.save(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
    }
}


