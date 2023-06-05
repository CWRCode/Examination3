package com.example.examinationslab3.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToMany
    private List<Item> items = new ArrayList<>();

    private String expediteStatus;

    private boolean isPaid = true;

    public Order(List<Item> items, Member member) {
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

    public String getExpediteStatus() {
        return expediteStatus;
    }

    public void setExpediteStatus(String expediteStatus) {

        if(expediteStatus.equalsIgnoreCase("PENDING")){
            this.expediteStatus = expediteStatus;

        } else if (expediteStatus.equalsIgnoreCase("COMPLETE")) {
            this.expediteStatus = expediteStatus;

        }else if (expediteStatus.equalsIgnoreCase("REMOVED")) {
            this.expediteStatus = expediteStatus;

        }

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

    public Long getId() {
        return id;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
