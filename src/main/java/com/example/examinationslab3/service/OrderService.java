package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.OrderDAO;
import com.example.examinationslab3.model.CartItem;
import com.example.examinationslab3.model.Item;
import com.example.examinationslab3.model.Member;
import com.example.examinationslab3.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class OrderService {

    private final OrderDAO orderDAO;
    private final ItemService itemService;
    private final MemberService memberService;
    private final List<CartItem> cart;


    @Autowired
    public OrderService(OrderDAO orderDAO, ItemService itemService, MemberService memberService) {
        this.orderDAO = orderDAO;
        this.itemService = itemService;
        this.memberService = memberService;
        this.cart = new ArrayList<>();
        System.out.println(cart.size() + "....ny");
    }

    public void createOrder(Member member, List<Item> list){
        Order order = new Order(member, list);
        orderDAO.save(order);
    }
    public void addItemToCart(Long id, int amount){

        Item item = itemService.findItemById(id);
        CartItem cartItem = new CartItem(item, amount);

        boolean status = updateAmountIfExist(item, amount);

        if (!status) {
            cart.add(cartItem);
            System.out.println("Tillagd");
        }


    }

    public boolean updateAmountIfExist(Item item, int amount){
        CartItem cartItem = new CartItem(item, amount);
        for (CartItem c : cart) {
            if(c.equals(cartItem)){
                c.setAmount(amount);
                return true;
            }
        }
        return false;
    }

    public List<CartItem> cartItems(){
        return cart;
    }

    public String totalCartCost(){
        int sum = 0;

        for (CartItem c : cart) {
            sum = sum + c.getAmount() * (int)c.getItem().getPrice();
        }

        return String.valueOf(sum + " " + "sek");
    }


    public Order findOrderById(Long id){
        return orderDAO.findById(id).orElseThrow(NullPointerException::new);
    }

    public void deleteOrder(Long id){
        orderDAO.delete(findOrderById(id));
    }

    public List<Order> findOrders(){
        return orderDAO.findAll();
    }

    public List<Item> findItems(){
        return itemService.findItems();
    }

    public void changeCartAmount(String name, int amount){
        Item item = new Item();
        item.setName(name);

        CartItem cartItem = new CartItem(item);

        for (CartItem c : cart) {
            if(c.equals(cartItem)){
                c.setAmount(amount);
                if(amount == 0){
                    cart.remove(c);
                }
            }
        }
    }
}



