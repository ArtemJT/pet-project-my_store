package com.example.my_store_spring.controller;

import com.example.my_store_spring.dto.UsersDto;
import com.example.my_store_spring.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final UsersService usersService;

    @GetMapping("ping")
    public String ping(Model model) {
        log.info("ping page");
        model.addAttribute("msg", "OK");
        return "ping";
    }

    @GetMapping
    public String index() {
        Locale locale = LocaleContextHolder.getLocale();
        log.info("index page");
        log.info("LOCALE: {}", locale);
        return "index";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        log.info("admin page");
        List<UsersDto> allUsers = usersService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "admin";
    }
}
