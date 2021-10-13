package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getProduct(@RequestParam("username") String username, Model model) {
        Account account = userService.getAccountByName(username);
        model.addAttribute("Account", account);
        return "profile";
    }
    @PostMapping
    public String saveAccount(Account account) {
        userService.saveAccount(account);
        return "redirect:/";
    }
}