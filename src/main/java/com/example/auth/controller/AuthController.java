package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login";
    }

    @PostMapping("/rtlogin")
    public String returntologin(@Valid @ModelAttribute("user") User user, BindingResult result) {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        System.out.println("Открыта страница логина");

        if (error != null) {
            System.out.println("Ошибка входа!");
            model.addAttribute("errorMessage", "Неверное имя пользователя или пароль!");
        }
        if (logout != null) {
            System.out.println("Выход выполнен");
            model.addAttribute("successMessage", "Вы успешно вышли из системы.");
        }

        return "login";
    }


}
