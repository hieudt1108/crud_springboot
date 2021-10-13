package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartSevice {

    private final ProductService productService;

    @Autowired
    public CartSevice(ProductService productService) {
        this.productService = productService;
    }

    public Map<Integer, CartItem> add(CartItem item, Map<Integer, CartItem> map) {
        CartItem existItem = map.get(item.getProductId());
        if (existItem != null) {
            existItem.setQuantity(item.getQuantity() + existItem.getQuantity());
            existItem.setPrice(item.getPrice() + existItem.getPrice());
        } else {
            map.put(item.getProductId(), item);
        }
        return map;
    }

    public void remove(int productId, Map<Integer, CartItem> map) {
        map.remove(productId);
    }

    public Collection<CartItem> getCartItem(Map<Integer, CartItem> map) {
        return map.values();
    }

    public void clear(Map<Integer, CartItem> map) {
        map.clear();
    }

    public Map<Integer, CartItem> update(int productId, int quantity, Map<Integer, CartItem> map) {
        CartItem item = map.get(productId);
        Product p = productService.getProductById(productId);
        item.setQuantity(quantity);
        item.setPrice(item.getQuantity()*p.getPrice());
        if (item.getQuantity() <= 0) {
            map.remove(productId);
        }
        return map;
    }

    public double getAmout(Map<Integer, CartItem> map) {
        return map.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }


}
