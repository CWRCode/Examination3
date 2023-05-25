package com.example.examinationslab3.model;

import java.util.Objects;

public class CartItem {
    private Item item;
    private int amount;

    public CartItem(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public CartItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }




    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(getItem(), cartItem.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItem());
    }


    @Override
    public String toString() {
        return  item + " Amount: " + amount;
    }
}
