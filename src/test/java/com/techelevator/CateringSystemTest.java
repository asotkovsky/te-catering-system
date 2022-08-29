package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.CateringItem;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.Map;

public class CateringSystemTest {
    CateringSystem cateringSystem;


    @Before

    public void setup() {
        cateringSystem = new CateringSystem();
    }

    @Test

    public void item_not_sold_out_returns_false() throws FileNotFoundException {
        InventoryFileReader inventoryFileReader = new InventoryFileReader("Testcateringsystem.csv");

        Map<String, CateringItem> inventory = inventoryFileReader.readInventory();

        boolean actualResult = cateringSystem.isItemSoldOut(inventory, "A1");
        Assert.assertEquals(false, actualResult);
    }

    @Test

    public void item_sold_out_returns_true() throws FileNotFoundException {

        InventoryFileReader inventoryFileReader = new InventoryFileReader("Testcateringsystem.csv");

        Map<String, CateringItem> inventory = inventoryFileReader.readInventory();
        inventory.get("A1").setProductQuantityAvailable(0);

        boolean actualResult = cateringSystem.isItemSoldOut(inventory, "A1");
        Assert.assertEquals(true, actualResult);
    }

    @Test

    public void quantity_requested_above_quantity_available_returns_false() throws FileNotFoundException {

        InventoryFileReader inventoryFileReader = new InventoryFileReader("Testcateringsystem.csv");

        Map<String, CateringItem> inventory = inventoryFileReader.readInventory();
        boolean actualResult = cateringSystem.isQuantityAvailable(26d, "A1", inventory);

        Assert.assertEquals(false, actualResult);
    }

    @Test

    public void quantity_requested_is_available_returns_true() throws FileNotFoundException {

        InventoryFileReader inventoryFileReader = new InventoryFileReader("Testcateringsystem.csv");

        Map<String, CateringItem> inventory = inventoryFileReader.readInventory();
        boolean actualResult = cateringSystem.isQuantityAvailable(5d, "A1", inventory);

        Assert.assertEquals(true, actualResult);
    }

    @Test

    public void adequate_funds_for_purchase_returns_true() {
        boolean actualResult= cateringSystem.isAdequateMoneyAvailableForPurchase(25d,5d);

        Assert.assertEquals(true, actualResult);
    }

    @Test

    public void inadequate_funds_for_purchase_returns_false() {
        boolean actualResult= cateringSystem.isAdequateMoneyAvailableForPurchase(5d,15d);

        Assert.assertEquals(false, actualResult);
    }

    @Test

    public void precise_amount_of_funds_returns_true() {
        boolean actualResult= cateringSystem.isAdequateMoneyAvailableForPurchase(25d,5d);

        Assert.assertEquals(true, actualResult);
    }

    @Test

    public void twenty_five_available_5_purchased_returns_20() throws FileNotFoundException {
        InventoryFileReader inventoryFileReader = new InventoryFileReader("Testcateringsystem.csv");
        Map<String, CateringItem> inventory = inventoryFileReader.readInventory();
        int actualQuantityAfterPurchase = cateringSystem.updateItemQuantity(inventory, 5, "A1");
        Assert.assertEquals(20, actualQuantityAfterPurchase);

    }


}