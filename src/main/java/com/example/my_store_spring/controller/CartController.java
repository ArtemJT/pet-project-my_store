package com.example.my_store_spring.controller;

import com.example.my_store_spring.dto.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
@Slf4j
@RequiredArgsConstructor
public class CartController {

    @GetMapping
    public String cart(HttpServletRequest request, Model model) {
        log.info("Cart called");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping
    public String remove(HttpServletRequest request, @RequestParam Integer id) {
        log.info("Removing item from cart");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.removeByProductId(id);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clear(HttpServletRequest request) {
        log.info("Cart clear called");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.clear();
        return "redirect:/cart";
    }
}
