package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.service.ProductService;
import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class RegisterController {


    private final RegisterService registerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @GetMapping
    public String register(Model model) {
        model.addAttribute("Account", new Account());
        return "register";
    }

    @PostMapping
    public String saveAccount(Model model, Account account) {
        if (registerService.accountExist(account.getUsername())) {
            model.addAttribute("Error", "Tài khoản đã tồn tại");
            model.addAttribute("Account", new Account());
            return "register";
        } else {
            Account saveAccount = new Account();
            saveAccount.setUsername(account.getUsername());
            saveAccount.setFullname(account.getFullname());
            saveAccount.setPassword(passwordEncoder.encode(account.getPassword()));
            saveAccount.setEmail(account.getEmail());
            saveAccount.setPhonenumber(account.getPhonenumber());
            saveAccount.setAddress(account.getAddress());
            registerService.saveAccount(saveAccount);
            return "redirect:/";
        }
    }
}
