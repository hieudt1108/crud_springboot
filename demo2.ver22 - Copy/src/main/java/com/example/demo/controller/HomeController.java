package com.example.demo.controller;


import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PaginatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {


    private final CategoryService categoryService;
    private final PaginatedService paginatedService;

    @Autowired
    public HomeController(CategoryService categoryService, PaginatedService paginatedService) {
        this.categoryService = categoryService;
        this.paginatedService = paginatedService;
    }


    @GetMapping()
    public String index(@RequestParam("page") Optional<Integer> page, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal",principal);
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            System.out.println(username);
//        } else {
//            String username = principal.toString();
//            System.out.println(username);
//        }
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage == 0) {
        } else {
            currentPage = currentPage - 1;
        }
        Page<Product> resultPage = paginatedService.getPageProduct(currentPage, pageSize);
        List<Integer> pageNumbers = paginatedService.getPageNumbers(currentPage, pageSize);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("productPage", resultPage);
        List<Category> listC = categoryService.getAllCategory();
        model.addAttribute("Category", new Category());
        model.addAttribute("listC", listC);
        String name = "";
        model.addAttribute("name", name);
        return "index";
    }


}
