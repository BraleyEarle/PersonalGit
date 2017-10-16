/*
 * Created by MightyJoeYoung on 7/22/2017.
 *  Last modified 7/22/17
 * Purpose: liabilities class for getOutOfDebt
 * Description: // fill in
 */

public class liabilities extends main_Class {
    private String account_name;
    private double amount_in_account;
    private double payment;

    liabilities(String name, double amount_in_account, double payment) {
        this.account_name = name;
        this.amount_in_account = amount_in_account;
        this.payment = payment;
    }
    public void set_amount(double amount_in_account)
    {
        this.amount_in_account = amount_in_account;
    }

    public void set_Payment(double payment)
    {
        this.payment = payment;
    }
    public String getAccount_name()
    {
        return account_name;
    }
    public double getAmount_in_account()
    {
        return amount_in_account;
    }
    public double getPayment()
    {
        return payment;
    }
}
