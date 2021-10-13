package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Product;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;
import java.util.Set;

@Controller

public class LoginController {
    private final AccountService accountService;

    @Autowired
    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("logout")
//    public String logout(HttpSession session){
//        session.removeAttribute();
//
//        return "redirect:/login";
//    }
}
