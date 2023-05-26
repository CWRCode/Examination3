package com.example.examinationslab3.controller;

import com.example.examinationslab3.model.CartItem;
import com.example.examinationslab3.model.Item;
import com.example.examinationslab3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ConcurrentModificationException;
import java.util.List;

@Controller
public class OrderAPI {

    private final OrderService orderService;


    @Autowired
    public OrderAPI(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/addcart")
    public String additemcart(@RequestParam long id, @RequestParam int amount, Model m){

        orderService.addItemToCart(id, amount);

        return "redirect:/store";
    }

    @GetMapping("/cart")
    public String itemsUpdate(Model m){

        List<CartItem> items = orderService.cartItems();
        m.addAttribute("cart", items);

        m.addAttribute("totalsum", orderService.totalCartCost());

        return "membercart";

    }

    @PostMapping("/changecartamount")
    public String changeCartAmount(@RequestParam String updatecartitem, @RequestParam int amount, Model m){

        try {
            orderService.changeCartAmount(updatecartitem, amount);
            return "redirect:/cart";
        }catch (ConcurrentModificationException e) {
            return "redirect:/store";
        }

    }








}

