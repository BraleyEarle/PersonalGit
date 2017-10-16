/*
 * Created by MightyJoeYoung on 7/22/2017.
 * Last modified 7/22/17
 * Purpose: assets class for getOutOfDebt
 * Description: // fill in
 */

public class assets extends main_Class {

    private String name;
    private double amount_in_acct;

   public  assets(String name, double amount_in_acct){
        this.name = name;
        this.amount_in_acct = amount_in_acct;
    }
    public String getName(){

       return name;
    }
    public double getAmount(){

        return amount_in_acct;
    }
}
