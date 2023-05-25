package com.example.examinationslab3.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToMany
    private List<Item> items = new ArrayList<>();

    private Enum<OrderExpedite> expediteStatus = OrderExpedite.PENDING;

    public Order(Member member, List<Item> items) {
        this.member = member;
        this.items = items;
    }

    public Order() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Enum<OrderExpedite> getExpediteStatus() {
        return expediteStatus;
    }

    public void setExpediteStatus(Enum<OrderExpedite> expediteStatus) {
        this.expediteStatus = expediteStatus;
    }

    public Order(Member member) {
        this.member = member;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
