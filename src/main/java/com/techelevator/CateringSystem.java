package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.CateringItem;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.util.Map;

/*
    This class should encapsulate all the functionality of the Catering system, meaning it should
    contain all the "work"
 */
public class CateringSystem {

    Menu menu = new Menu();

    public boolean isItemSoldOut(Map<String, CateringItem> inventory, String productCodeForPurchase) {

        if (inventory.get(productCodeForPurchase).getProductQuantityAvailable() == 0) {

            return true;
        }
        return false;

    }

    public boolean isQuantityAvailable(double quantityForPurchase, String productCode, Map<String, CateringItem> inventory) {
        double quantityAvailableOfItem = inventory.get(productCode).getProductQuantityAvailable();

        if (quantityAvailableOfItem >= quantityForPurchase) {
            return true;
        } else {
            return false;
        }

    }


    public boolean isAdequateMoneyAvailableForPurchase(double accountBalance, double totalCostOfItemPurchase) {
        if (accountBalance >= totalCostOfItemPurchase) {
            return true;
        }
        return false;
    }

    public int updateItemQuantity(Map<String, CateringItem> inventory, int quantityPurchased, String productCodePurchased) {

        int quantityAvailableBeforePurchase = inventory.get(productCodePurchased).getProductQuantityAvailable();
        int quantityAvailableAfterPurchase = quantityAvailableBeforePurchase - quantityPurchased;
        inventory.get(productCodePurchased).setProductQuantityAvailable(quantityAvailableAfterPurchase);
        return quantityAvailableAfterPurchase;
    }


}
