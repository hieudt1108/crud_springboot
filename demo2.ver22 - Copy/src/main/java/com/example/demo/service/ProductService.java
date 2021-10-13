package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchProduct(String name) {
        return productRepository.findAllByNameContains(name);
    }

    public Page<Product> getProductByCategory(int id,Pageable pageable) {

        return productRepository.findByCategoryID(id,pageable);
    }

    public Page<Product> findByNameContaining(String name, Pageable pageable) {
        System.out.println("check1");
        return productRepository.findByNameContaining(name,pageable);
    }
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }




}
