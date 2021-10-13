package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping
    public String index( @RequestParam("page") Optional<Integer> page,@RequestParam("id") int id , Model model) {


        int currentPage = page.orElse(0);
        int pageSize = 5;
        if (currentPage == 0) {

        } else {
            currentPage = currentPage - 1;
        }

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Product> resultPage = null;

        if (id== 0) {
            resultPage = productService.findAll(pageable);
        }else {
            resultPage = productService.getProductByCategory(id,pageable);
        }
        int totalPages = resultPage.getTotalPages();
//        if (totalPages > 0) {
//            int start = Math.max(1, currentPage - 2);
//            int end = Math.min(currentPage + 2, totalPages);
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
        model.addAttribute("productPage", resultPage);
        model.addAttribute("categoryID", id);
        model.addAttribute("Category", new Category());
        List<Category> listC = categoryService.getAllCategory();
        model.addAttribute("listC", listC);
        return "category";
    }
}
