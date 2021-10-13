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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SearchController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public SearchController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/search/paginated")
    public String search(@RequestParam("name") String name, Model model,
                         @RequestParam("page") Optional<Integer> page
    ) {
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
//            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
//                    .boxed()
//                    .collect(Collectors.toList());
//
//        }
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);

        if (name.isEmpty()){
            name = "";
        }
        model.addAttribute("productPage", resultPage);
        model.addAttribute("name",name);

        List<Category> listC = categoryService.getAllCategory();
        model.addAttribute("Category", new Category());
        model.addAttribute("listC", listC);
        return "index";
    }
}
