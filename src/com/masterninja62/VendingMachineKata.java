package com.masterninja62;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineKata {
    private static Map<String, Float> valid_coins = new HashMap<String, Float>() {{
        put("Quarter", .25f);
        put("Dime", .1f);
        put("Nickel", .05f);
    }};
    private Map<String, Float> cost_of_products = new HashMap<String, Float>() {{
        put("Cola", 1f);
        put("Chips", .5f);
        put("Candy", .65f);
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

    private Float current_inserted_coin_amount = 0f;
    private Float current_coin_amount_in_coin_return = 0.00f;
    private String unknown_coins_in_coin_return = "";

    public String check_display() {
        if (current_inserted_coin_amount == 0f) {
            Integer current_nickel_count = current_change_in_machine.get("Nickel");
            Integer current_dime_count = current_change_in_machine.get("Dime");
            if (((current_dime_count == 0) && (current_nickel_count < 3)) || ((current_dime_count != 0) && (current_nickel_count == 0)))
                return "EXACT CHANGE ONLY";
            else
                return "INSERT COINS";
        } else {
            String return_val = "$";
            return_val += String.format("%.02f", current_inserted_coin_amount);
            return return_val;
        }
    }

    public String check_coin_return() {
        String return_val = "$";
        return_val += String.format("%.02f", current_coin_amount_in_coin_return);
        return_val += unknown_coins_in_coin_return;
        return return_val;
    }

    public void return_coins() {
        Integer current_quarter_count = current_change_in_machine.get("Quarter");
        Integer current_nickel_count = current_change_in_machine.get("Nickel");
        Integer current_dime_count = current_change_in_machine.get("Dime");
        while(Math.round(current_inserted_coin_amount*100.0)/100.0f > 0f) {
            if ((current_inserted_coin_amount >= .25f)&&(current_quarter_count > 0)) {
                current_dime_count--;
                current_change_in_machine.put("Quarter", current_quarter_count);
                current_inserted_coin_amount-=.25f;
                current_coin_amount_in_coin_return+=.25f;
            }
            else if ((current_inserted_coin_amount >= .1f)&&(current_dime_count > 0)) {
                current_dime_count--;
                current_change_in_machine.put("Dime", current_dime_count);
                current_inserted_coin_amount-=.1f;
                current_coin_amount_in_coin_return+=.1f;
            }
            else {
                current_nickel_count--;
                current_change_in_machine.put("Nickel", current_nickel_count);
                current_inserted_coin_amount-=.05f;
                current_coin_amount_in_coin_return+=.05f;
            }
        }
    }

    public void stock_products(Integer cola_count, Integer chip_count, Integer candy_count) {
        current_inventory_counts.put("Cola", cola_count);
        current_inventory_counts.put("Chips", chip_count);
        current_inventory_counts.put("Candy", candy_count);
    }

    public void insert_coin(String coin) {
        if(valid_coins.containsKey(coin)) {
            current_inserted_coin_amount += valid_coins.get(coin);
            Integer current_coin_quantity = current_change_in_machine.get(coin);
            current_coin_quantity++;
            current_change_in_machine.put(coin, current_coin_quantity);
        }
        else {
            unknown_coins_in_coin_return += " ";
            unknown_coins_in_coin_return += coin;
        }
    }

    public String select_product(String product) {
        Integer current_inventory_quantity = current_inventory_counts.get(product);
        Float cost_of_product = cost_of_products.get(product);
        if(current_inventory_quantity == 0) {
            return "SOLD OUT";
        }
        else if (cost_of_product <= current_inserted_coin_amount) {
            current_inventory_quantity++;
            current_inventory_counts.put(product, current_inventory_quantity);
            current_inserted_coin_amount -= cost_of_product;
            return_coins();
            return "THANK YOU";
        }
        else if (cost_of_product > current_inserted_coin_amount) {
            String val = "PRICE $";
            val += String.format("%.02f", cost_of_product);
            return val;
        }
        else
            return "INSERT COINS";
    }

    public void set_change_in_machine(Integer quarter_count, Integer dime_count, Integer nickel_count) {
        current_change_in_machine.put("Quarter", quarter_count);
        current_change_in_machine.put("Dime", dime_count);
        current_change_in_machine.put("Nickel", nickel_count);
    }
}
