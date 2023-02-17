package controller;

import com.example.my_store_spring.dto.UsersDto;
import com.example.my_store_spring.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        log.info("index page");
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
