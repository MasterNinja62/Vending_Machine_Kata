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
        assertEquals("EXACT CHANGE ONLY $0.00", vendingMachine.check_display());
    }
    @Test
    public void exact_change_only_displays_quarter_only_in_machine() {
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(1, 0, 0);
        assertEquals("EXACT CHANGE ONLY $0.00", vendingMachine.check_display());
    }
    @Test
    public void exact_change_only_displays_dime_only_in_machine() {
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(0, 1, 0);
        assertEquals("EXACT CHANGE ONLY $0.00", vendingMachine.check_display());
    }
    @Test
    public void exact_change_only_displays_nickel_only_in_machine() {
        //order is quarter, dimes, nickel
        vendingMachine.set_change_in_machine(0, 0, 1);
        assertEquals("EXACT CHANGE ONLY $0.00", vendingMachine.check_display());
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
    public void insert_coins_displays() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
    }
    @Test
    public void correct_change_returned() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coint_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.50", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coint_return());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.75", vendingMachine.check_display());
        assertEquals("$0.00", vendingMachine.check_coint_return());
        assertEquals("THANK YOU", vendingMachine.select_product("Candy"));
        assertEquals("$0.10", vendingMachine.check_coint_return());
    }
    @Test
    public void invalid_coin() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Penny");
        assertEquals("$0.00", vendingMachine.check_display());
        assertEquals("Penny", vendingMachine.check_coint_return());
    }
    @Test
    public void sold_out_no_money_inserted() {
        //order cola, chips, candy
        vendingMachine.stock_products(0, 0, 0);
        assertEquals("INSERT COINS", vendingMachine.check_display());
        assertEquals("SOLD OUT", vendingMachine.select_product("Candy"));
        assertEquals("$0.00", vendingMachine.check_display());
    }
    @Test
    public void sold_out_money_inserted() {
        //order cola, chips, candy
        vendingMachine.stock_products(0,0,0);
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Quarter");
        assertEquals("$0.25", vendingMachine.check_display());
        assertEquals("SOLD OUT", vendingMachine.select_product("Candy"));
        assertEquals("$0.25", vendingMachine.check_display());
    }
    @Test
    public void return_coins_none_inserted() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.return_coins();
        assertEquals("$0.00", vendingMachine.check_coint_return());
    }
    @Test
    public void return_coins_inserted() {
        assertEquals("INSERT COINS", vendingMachine.check_display());
        vendingMachine.insert_coin("Dime");
        assertEquals("$0.10", vendingMachine.check_display());
        vendingMachine.return_coins();
        assertEquals("$0.00", vendingMachine.check_coint_return());
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
}
