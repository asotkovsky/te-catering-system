package com.techelevator.items;

public class Beverage extends CateringItem {
    public Beverage(String productCode, String productName) {
        super(productCode, productName);
        setProductType("Beverage");
    }
}
