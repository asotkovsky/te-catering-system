package com.techelevator;
import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.*;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InventoryFileReaderTest {
    private InventoryFileReader testTarget;

    @Before
    public void setup(){testTarget = new InventoryFileReader("Testcateringsystem.csv");}

    @Test

    public void if_inventory_maps_match_is_successful() throws FileNotFoundException {

        Map<String, CateringItem> actualReturnedMap = testTarget.readInventory();

        Map<String, CateringItem> expectedMap = new TreeMap<String, CateringItem>();

        Beverage testBeverage = new Beverage ("B1", "Sparkling Water");
        testBeverage.setProductPrice(1.35);
        expectedMap.put("B1", testBeverage);

        Appetizer testAppetizer = new Appetizer ("A1", "Fruit Bowl");
        testAppetizer.setProductPrice(2.50);
        expectedMap.put("A1", testAppetizer);

        Entree testEntree = new Entree("E1", "Roasted Chicken");
        testEntree.setProductPrice(7.85);
        expectedMap.put("E1", testEntree);


        Dessert testDessert = new Dessert("D1", "Brownies");
        testDessert.setProductPrice(1.15);
        expectedMap.put("D1", testDessert);

        Assert.assertEquals(expectedMap, actualReturnedMap);

    }



}
