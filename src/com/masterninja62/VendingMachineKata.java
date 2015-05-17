package com.masterninja62;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineKata {
    private static Map<String, Float> valid_coins = new HashMap<String, Float>() {{
        put("Quarter", .25f);
        put("Dime", .1f);
        put("Nickel", .05f);
    }};
    private Map<String, Integer> current_inventory_counts = new HashMap<String, Integer>() {{
        put("Cola", 3);
        put("Chips", 3);
        put("Candy", 3);
    }};
    private Map<String, Integer> current_change_in_machine = new HashMap<String, Integer>() {{
        put("Quarter", 5);
        put("Dime", 5);
        put("Nickel", 5);
    }};


    public String check_display() {
        return "INSERT COINS";
    }
    public String check_coin_return() {
        return "INSERT COINS";
    }
    public void return_coins(){

    }
    public void stock_products(Integer cola_count, Integer chip_count, Integer candy_count){

    }
    public void insert_coin(String coin) {

    }
    public String select_product(String product) {
        return "INSERT COINS";
    }
    public void set_change_in_machine(Integer quarter_count, Integer dime_count, Integer nickel_count) {

    }
}
