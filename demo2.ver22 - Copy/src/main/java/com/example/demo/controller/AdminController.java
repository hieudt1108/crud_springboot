package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PaginatedService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final PaginatedService paginatedService;
    protected final UserService userService;


    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService, PaginatedService paginatedService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.paginatedService = paginatedService;
        this.userService = userService;
    }

    @RequestMapping(value = "/admin")
    public String admin(Model model, @RequestParam("page") Optional<Integer> page) {
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
        String name = "";
        model.addAttribute("name",name);
        return "admin";
    }

    @RequestMapping(value = "/admin/search")
    public String adminSearch(@RequestParam("name") String name, Model model,
                              @RequestParam("page") Optional<Integer> page) {
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage == 0) {

        } else {
            currentPage = currentPage - 1;
        }

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Product> resultPage = productService.findByNameContaining(name, pageable);
        System.out.println(resultPage);


        if (name.isEmpty()) {
            resultPage = productService.findAll(pageable);
        } else {
            resultPage = productService.findByNameContaining(name, pageable);
            model.addAttribute("name", name);
        }

        int totalPages = resultPage.getTotalPages();
//        if (totalPages > 0) {
//            int start = Math.max(1, currentPage - 2);
//            int end = Math.min(currentPage + 2, totalPages);
//
//
//            if (totalPages > 5) {
//                if (end == totalPages) start = end - 5;
//                else if (start == 1) end = start + 5;
//            }
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
//        }
        if (name.isEmpty()) {
            name = "";
        }
        model.addAttribute("productPage", resultPage);
        model.addAttribute("name", name);

        return "admin";
    }

    @RequestMapping("/admin/delete")
    public String deleteProduct(@RequestParam("id") int Id, Model model) {
        productService.deleteProduct(Id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String addProduct(Model model, HttpSession session) {
        model.addAttribute("Product", new Product());
        return "add";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/admin";
    }
    @RequestMapping(value = "/admin/user" , method = RequestMethod.GET)
    public String getUser(Model model, @RequestParam("page") Optional<Integer> page){
        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage == 0) {
        } else {
            currentPage = currentPage - 1;
        }
        Page<Account> resultPage = paginatedService.getPageUser(currentPage,pageSize);
        List<Integer> pageNumbers = paginatedService.getPageNumbers(currentPage, pageSize);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("userPage", resultPage);
        String name = "";
        model.addAttribute("name",name);
        return "manageUser";
    }

    @RequestMapping(value = "/admin/user/disable")
    public String disableUser(@RequestParam("id") int Id){
        Account account = userService.getAccountById(Id);
        account.setEnable(false);
        userService.saveAccount(account);
        return "redirect:/admin/user";
    }
    @RequestMapping(value = "/admin/user/enable")
    public String enableUser(@RequestParam("id") int Id){
        Account account = userService.getAccountById(Id);
        account.setEnable(true);
        userService.saveAccount(account);
        return "redirect:/admin/user";
    }


}
