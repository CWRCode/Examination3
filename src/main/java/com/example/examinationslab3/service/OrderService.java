package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.OrderDAO;
import com.example.examinationslab3.model.Admin;
import com.example.examinationslab3.model.Item;
import com.example.examinationslab3.model.Member;
import com.example.examinationslab3.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class OrderService {

    private final OrderDAO orderDAO;
    private final ItemService itemService;
    private final MemberService memberService;
    private final List<Item> cart;


    @Autowired
    public OrderService(OrderDAO orderDAO, ItemService itemService, MemberService memberService) {
        this.orderDAO = orderDAO;
        this.itemService = itemService;
        this.memberService = memberService;
        this.cart = new ArrayList<>();

    }

    public void createOrder(List<Item> list){

        if(memberService.getActiveMember() != null){
            Member member = memberService.getActiveMember();
            member = memberService.save(member);

            Order order = new Order(list, member);
            order.setPaid(false);
            order.setExpediteStatus("PENDING");
            order = orderDAO.save(order);
            member.getOrders().add(order);
            memberService.save(member);
        }

    }
    public void addItemToCart(Long id, int amount){

        Item item = itemService.findItemById(id);

        boolean status = updateAmountIfExist(item, amount);

        if (!status) {
            item.setAmount(amount);
            cart.add(item);
        }


    }

    public boolean updateAmountIfExist(Item item, int amount){
        for (Item c : cart) {
            if(c.equals(item)){
                c.setAmount(amount + c.getAmount());
                return true;
            }
        }
        return false;
    }

    public List<Item> cartItems(){
        return cart;
    }

    public String totalCartCost(){
        int sum = 0;


        for (Item c : cart) {
            sum = sum + c.getAmount() * ((int) c.getPrice());
        }

        return String.valueOf(sum + " sek");
    }
    public int totalCartCostInt(){
        int sum = 0;

        for (Item c : cart) {
            sum = sum + c.getAmount() * ((int) c.getPrice());
        }


        return sum;
    }

    public void clearCart(){
        cart.clear();
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

        for (Item c : cart) {
            if(c.equals(item)){
                c.setAmount(amount);
                if(amount == 0){
                    cart.remove(c);
                }
            }
        }
    }

    public void updatePaid(long id) {

        Order order = checkOrderExists(id);

        if(order != null){
            order = changePaid(order);
            orderDAO.save(order);
        }

    }

    public Order checkOrderExists(long id){
        Optional<Order> order = orderDAO.findById(id);
        return order.orElse(null);
    }

    public Order changePaid(Order order) {
        if(order.isPaid()) {
            order.setPaid(false);
        }else {
            order.setPaid(true);
        }

        return order;
    }


    public void updateExpedite(long id) {

        Order order = checkOrderExists(id);

        if(order != null){
            order = changeExpedite(order);
            orderDAO.save(order);
        }

    }

    public Order changeExpedite(Order order) {
        if(order.getExpediteStatus().equals("PENDING")) {
            order.setExpediteStatus("COMPLETE");
        }else if (order.getExpediteStatus().equals("COMPLETE")) {
            order.setExpediteStatus("REMOVED");
        } else {
            order.setExpediteStatus("PENDING");
        }

        return order;
    }





}



