package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/update")
public class UpdateProductController {

    private final ProductService productService;

    @Autowired
    public UpdateProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String updateProductForm(@RequestParam("id") int Id, Model model) {
        Product p = productService.getProductById(Id);
        model.addAttribute("Product", p);
        return "update";
    }
    @PostMapping
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/admin";
    }
}
