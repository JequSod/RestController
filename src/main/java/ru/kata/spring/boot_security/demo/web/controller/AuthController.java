package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {

    public String loginPage() {
        return "login";
    }
}
