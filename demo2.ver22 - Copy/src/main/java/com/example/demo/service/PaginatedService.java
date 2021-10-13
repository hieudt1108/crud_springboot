package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaginatedService {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public PaginatedService(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    public Page<Product> getPageProduct(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Product> resultPage = null;
        resultPage = productService.findAll(pageable);
        return resultPage;
    }

    public Page<Account> getPageUser(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Account> resultPage = null;
        resultPage = userService.findAll(pageable);
        return resultPage;
    }
    
    

    public List<Integer> getPageNumbers(int currentPage, int pageSize) {
        Page<Product> resultPage = getPageProduct(currentPage, pageSize);
        int totalPages = resultPage.getTotalPages();
//        if (totalPages > 0) {
//            int start = Math.max(1, currentPage - 2);
//            int end = Math.min(currentPage + 2, totalPages);
//
////            if (totalPages > 5) {
////                if (end == totalPages) start = end - 5;
////                else if (start == 1) end = start + 5;
////            }
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            return pageNumbers;
//        }
//        return null;
    }


}
