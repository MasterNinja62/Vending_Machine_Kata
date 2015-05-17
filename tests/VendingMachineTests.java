import com.masterninja62.VendingMachineKata;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendingMachineTests {
    VendingMachineKata vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new VendingMachineKata();
    }

    @Test
    public void exact_change_only_displays_no_change_in_machine() {
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(0, 0, 0);
        assertEquals("EXACT CHANGE ONLY", vendingMachine.check_display());
    }
    @Test
    public void exact_change_only_displays_quarter_only_in_machine() {
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(1, 0, 0);
        assertEquals("EXACT CHANGE ONLY", vendingMachine.check_display());
    }
    @Test
    public void exact_change_only_displays_dime_only_in_machine() {
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(0, 1, 0);
        assertEquals("EXACT CHANGE ONLY", vendingMachine.check_display());
    }
    @Test
    public void exact_change_only_displays_nickel_only_in_machine() {
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(0, 0, 1);
        assertEquals("EXACT CHANGE ONLY", vendingMachine.check_display());
    }
    @Test
    public void exact_change_only_does_not_display_when_minimum_change_in_machine() {
        //minimum determined by costs and acceptable currency
        //quarter and dime for chips means nickel returned as change
        //all quarters for candy means dime returned as change
        //must have both nickel and dime or potential for exact change occurs
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(0, 1, 1);
        assertEquals("INSERT COINS", vendingMachine.check_display());
    }
    @Test
     public void exact_change_only_does_not_display_when_minimum_nickels_in_machine() {
        //minimum determined by costs and acceptable currency
        //quarter and dime for chips means nickel returned as change
        //all quarters for candy means dime returned as change
        //must have both nickel and dime or potential for exact change occurs
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(0, 0, 3);
        assertEquals("INSERT COINS", vendingMachine.check_display());
    }
    @Test
    public void insert_coins_displays() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
    }
    @Test
    public void correct_change_returned() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.50", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.75", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("THANK YOU", vendingMachine.select_product("Candy"));
        assertEquals("$0.10", vendingMachine.check_coin_return());
    }
    @Test
    public void invalid_coin() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Penny");
        assertEquals("$0.00 Penny", vendingMachine.check_coin_return());
        assertEquals("INSERT COINS", vendingMachine.check_display());
    }
    @Test
    public void sold_out_no_money_inserted() {
        //order cola, chips, candy
        vendingMachine.stock_products(0, 1, 1);
        assertEquals("INSERT COINS", vendingMachine.check_display());
        assertEquals("SOLD OUT", vendingMachine.select_product("Cola"));
        assertEquals("INSERT COINS", vendingMachine.check_display());
    }
    @Test
    public void sold_out_money_inserted() {
        //order cola, chips, candy
        vendingMachine.stock_products(1, 0, 1);
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("SOLD OUT", vendingMachine.select_product("Chips"));
        assertEquals("$0.25", vendingMachine.check_display());
    }
    @Test
    public void return_coins_none_inserted() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.return_coins();
        assertEquals("$0.00", vendingMachine.check_coin_return());
    }
    @Test
    public void return_coins_inserted() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Dime");
        assertEquals("$0.10", vendingMachine.check_display());
        vendingMachine.return_coins();
        assertEquals("INSERT COINS", vendingMachine.check_display());
        assertEquals("$0.10", vendingMachine.check_coin_return());
    }
    @Test
    public void all_coins_added_correctly() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        vendingMachine.insert_coin("Dime");
        assertEquals("$0.35", vendingMachine.check_display());
        vendingMachine.insert_coin("Nickel");
        assertEquals("$0.40", vendingMachine.check_display());
    }
    @Test
    public void after_purchase_exact_change_now_required() {
        vendingMachine.set_change_in_machine(0, 1, 1);
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.50", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.75", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("THANK YOU", vendingMachine.select_product("Candy"));
        assertEquals("$0.10", vendingMachine.check_coin_return());
        assertEquals("EXACT CHANGE ONLY", vendingMachine.check_display());
    }
    @Test
    public void excess_coins_inserted_none_already_in_machine() {
        vendingMachine.set_change_in_machine(0, 0, 0);
        assertEquals("EXACT CHANGE ONLY", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.50", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.75", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Dime");
        assertEquals("$0.85", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Dime");
        assertEquals("$0.95", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Nickel");
        assertEquals("$1.00", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.return_coins();
        assertEquals("INSERT COINS", vendingMachine.check_display());
        assertEquals("$1.00", vendingMachine.check_coin_return());
    }
    @Test
    public void excess_coins_inserted_some_already_in_machine() {
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.50", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.75", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Dime");
        assertEquals("$0.85", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Dime");
        assertEquals("$0.95", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Nickel");
        assertEquals("$1.00", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.return_coins();
        assertEquals("INSERT COINS", vendingMachine.check_display());
        assertEquals("$1.00", vendingMachine.check_coin_return());
    }

    @Test
    public void not_enough_inserted_for_candy() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.50", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("PRICE $0.65", vendingMachine.select_product("Candy"));
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("$0.50", vendingMachine.check_display());
    }

    @Test
    public void not_enough_inserted_for_cola() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.50", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("PRICE $1.00", vendingMachine.select_product("Cola"));
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("$0.50", vendingMachine.check_display());
    }
    @Test
    public void not_enough_inserted_for_chips() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("PRICE $0.50", vendingMachine.select_product("Chips"));
        assertEquals("$0.00", vendingMachine.check_coin_return());
        assertEquals("$0.25", vendingMachine.check_display());
    }
}
