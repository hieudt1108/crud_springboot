package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final CartSevice cartSevice;
    private final OrdersSevice orderSevice;
    private final UserService userService;
    private final Order_detailsService order_detailsService;

    @Autowired
    public CartController(ProductService productService, CartSevice cartSevice, OrdersSevice orderSevice, UserService userService, Order_detailsService order_detailsService) {
        this.productService = productService;
        this.cartSevice = cartSevice;
        this.orderSevice = orderSevice;
        this.userService = userService;
        this.order_detailsService = order_detailsService;
    }

    @GetMapping("list")
    public String list(Model model, HttpServletRequest request) {
        Map<Integer, CartItem> map = (Map<Integer, CartItem>) request.getSession().getAttribute("map");
        if (map == null) {
            return "redirect:/";
        }
        Collection<CartItem> cartItems = cartSevice.getCartItem(map);
        double total = cartSevice.getAmout(map);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "cart";
    }

    @GetMapping("add")
    public String add(@RequestParam("id") Integer productId, HttpServletRequest request) {
        Map<Integer, CartItem> map = (Map<Integer, CartItem>) request.getSession().getAttribute("map");
        if (map == null) {
            map = new HashMap<Integer, CartItem>();
            Product product = productService.getProductById(productId);
            if (product != null) {
                CartItem item = new CartItem();
                item.setProductId(productId);
                item.setName(product.getName());
                item.setPrice(product.getPrice());
                item.setQuantity(1);
                map =cartSevice.add(item, map);
            }
        } else {
            Product product = productService.getProductById(productId);
            if (product != null) {
                CartItem item = new CartItem();
                item.setProductId(productId);
                item.setName(product.getName());
                item.setPrice(product.getPrice());
                item.setQuantity(1);
                map =cartSevice.add(item, map);
            }

        }
        request.getSession().setAttribute("map", map);
        return "redirect:/cart/list";
    }

    @GetMapping("remove")
    public String remove(@RequestParam("id") Integer productId, HttpServletRequest request) {
        Map<Integer, CartItem> map = (Map<Integer, CartItem>) request.getSession().getAttribute("map");
        if (map == null) {
            return "redirect:/cart/list";
        } else {
            cartSevice.remove(productId, map);
        }
        request.getSession().setAttribute("map", map);
        return "redirect:/cart/list";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") Integer productId, @RequestParam("quantity") Integer quantity, HttpServletRequest request) {
        Map<Integer, CartItem> map = (Map<Integer, CartItem>) request.getSession().getAttribute("map");
        if (map == null) {
            return "redirect:/cart/list";
        } else {
           map = cartSevice.update(productId, quantity, map);
        }
        request.getSession().setAttribute("map", map);
        return "redirect:/cart/list";
    }

    @GetMapping("clear")
    public String clear(HttpServletRequest request) {
        Map<Integer, CartItem> map = (Map<Integer, CartItem>) request.getSession().getAttribute("map");
        if (map == null) {
            return "redirect:/cart/list";
        } else {
            cartSevice.clear(map);
        }
        request.getSession().setAttribute("map", map);
        return "redirect:/cart/list";

    }

    @GetMapping("payment")
    public String checkOut(HttpServletRequest request, Model model) {
        Map<Integer, CartItem> map = (Map<Integer, CartItem>) request.getSession().getAttribute("map");
        if (map == null || map.size() == 0) {
            return "redirect:/";
        } else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            Account account = (Account) userService.getAccountByName(username);
            Collection<CartItem> cartItems = cartSevice.getCartItem(map);
            double amount = cartSevice.getAmout(map);
            String top1Id = orderSevice.getTop1Id();
            int id;
            if (top1Id == null || top1Id.equals("")) {
                id = 1;
                Orders order = new Orders();
                order.setId(id);
                order.setAmount(amount);
                order.setCustomer_address(account.getAddress());
                order.setCustomer_email(account.getEmail());
                order.setCustomer_name(account.getFullname());
                order.setCustomer_phone(account.getPhonenumber());
                System.err.println("check2 id :"+ order.getId());
                orderSevice.saveOrder(order);
            } else {
                id = Integer.parseInt(top1Id.trim()) + 1;
                Orders order = new Orders();
                order.setId(id);
                order.setAmount(amount);
                order.setCustomer_address(account.getAddress());
                order.setCustomer_email(account.getEmail());
                order.setCustomer_name(account.getFullname());
                order.setCustomer_phone(account.getPhonenumber());
                System.err.println("check1 id :"+ order.getId());
                orderSevice.saveOrder(order);

            }
            for (CartItem item : cartItems) {
                Order_details order_details = new Order_details();
                order_details.setAmount(item.getPrice() * item.getQuantity());
                order_details.setPrice(item.getPrice());
                order_details.setQuantity(item.getQuantity());
                order_details.setOrder_id(id);
                order_details.setProduct_id(item.getProductId());
                order_detailsService.saveOrder_details(order_details);
            }
        }
        map.clear();
        request.getSession().setAttribute("map", map);

        return "redirect:/";
    }

}
