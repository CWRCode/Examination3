package com.example.examinationslab3.service;

import com.example.examinationslab3.dao.AdminDAO;
import com.example.examinationslab3.dao.ItemDAO;
import com.example.examinationslab3.dao.MemberDAO;
import com.example.examinationslab3.dao.OrderDAO;
import com.example.examinationslab3.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class WebsiteServiceTest {

    @Mock
    private AdminDAO adminDAO;
    @Mock
    private ItemDAO itemDAO;
    @Mock
    private MemberDAO memberDAO;
    @Mock
    private OrderDAO orderDAO;

    @Mock
    private AdminService adminService;
    @Mock
    private ItemService itemService;
    @Mock
    private MemberService memberService;
    @Mock
    private OrderService orderService;

    @BeforeEach
    void initialize() {
        adminService = new AdminService(adminDAO);
        itemService = new ItemService(itemDAO);
        memberService = new MemberService(memberDAO);
        orderService = new OrderService(orderDAO, itemService, memberService);
    }

    @Test
    void findItem() {
        Mockito.when(itemDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Item("Apple", 20, "Fruit")));

        assertInstanceOf(Item.class, itemService.findItemById(1L));
    }

    @Test
    void addItemToCart() {
        Mockito.when(itemDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Item("Cola", 18, "soda")));

        orderService.addItemToCart(1L, 1);
        assertEquals(1, orderService.cartItems().size());

        Mockito.verify(itemDAO, Mockito.times(1)).findById(1L);
    }

    @Test
    void removeItemsFromCart() {
        Mockito.when(itemDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(new Item("Potato", 9, "Vegetable")));


        orderService.addItemToCart(1L, 1);
        orderService.clearCart();


        assertEquals(0, orderService.cartItems().size());
    }

    @Test
    void placeOrder() {

        Mockito.when(itemDAO.findById(1L))
                .thenReturn(Optional.of(new Item("Perfume", 200, "Cosmetics")));

        Mockito.when(itemDAO.findById(2L))
                .thenReturn(Optional.of(new Item("Bottle", 10, "Plastic")));

        Mockito.when(itemDAO.findById(3L))
                .thenReturn(Optional.of(new Item("TV", 200, "Electronics")));


        orderService.addItemToCart(1L, 1);
        orderService.addItemToCart(2L, 1);
        orderService.addItemToCart(3L, 1);


        assertEquals(3, orderService.cartItems().size());
        orderService.createOrder(orderService.cartItems());


        assertEquals(3, orderService.cartItems().size());
        orderService.clearCart();
        assertEquals(0, orderService.cartItems().size());

    }


}
