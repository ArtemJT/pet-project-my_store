package com.example.my_store_spring.controller;

import com.example.my_store_spring.dto.UsersDto;
import com.example.my_store_spring.model.enums.UserRole;
import com.example.my_store_spring.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthenticationController {

    private final UsersService usersService;

    @GetMapping("login")
    public String login() {
        log.info("Login page");
        return "auth/login";
    }

    @GetMapping("403")
    public String error403() {
        log.info("403 page");
        return "auth/403Error";
    }

    @GetMapping("registration")
    public String registration() {
        log.info("registration page");
        return "auth/registration";
    }

    @PostMapping("registration")
    public String signUp(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String email, Model model) {
        log.info("registration post page");
        String msg = null;
        if (username.equals("") || password.equals("") || email.equals("")) {
            msg = "Username and password must be filled";
        } else if (usersService.isUserNameExists(username)) {
            msg = "User exists!";
        }
        if (msg != null) {
            model.addAttribute("message", msg);
            return "auth/registration";
        }
        UsersDto userInfoDto = new UsersDto(null, LocalDateTime.now(), username, password, email, UserRole.USER);
        usersService.createUser(userInfoDto);
        return "redirect:/auth/login";
    }
}
