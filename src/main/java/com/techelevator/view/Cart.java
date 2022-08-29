package com.techelevator.view;

import com.techelevator.items.CateringItem;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Cart {

    Map<String, Integer> cart = new TreeMap<>();

    public void addItemToCart(String productCode, Integer quantity) {
        Integer quantityInCart = quantity;
        if (cart.containsKey(productCode)){
            quantityInCart = cart.get(productCode);
            quantityInCart += quantity;
        }
        cart.put(productCode, quantityInCart);
    }

    public Map<String, Integer> getCart() {
        return cart;
    }

    public void clearCart(Map<String, Integer> cart) {
        cart.clear();
    }

}
