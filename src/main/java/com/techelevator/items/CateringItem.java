package com.techelevator.items;

import java.util.Objects;

/*
    This represents a single catering item in the system
 */
public abstract class CateringItem {

    private String productCode;
    private String productName;
    private double productPrice;
    private int productQuantityAvailable = 25;
    private String productType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CateringItem that = (CateringItem) o;
        return Double.compare(that.productPrice, productPrice) == 0 && Objects.equals(productCode, that.productCode) && Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, productName, productPrice);
    }

    public CateringItem(String productCode, String productName) {
        this.productCode = productCode;
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantityAvailable() {
        return productQuantityAvailable;
    }

    public void setProductQuantityAvailable(int productQuantityAvailable) {
        this.productQuantityAvailable = productQuantityAvailable;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
