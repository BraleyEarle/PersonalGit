/*
  Created by MightyJoeYoung on 7/22/2017.
  Last modified: 8/10/17
  Purpose: Main class for debt project
  Description: // fill in later
 */

import java.util.Scanner;  // import for user input
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class main_Class
{

    public static void main(String[] args) throws IOException
    {

        Scanner user_input;
        int choice;
        int whatIfAcct;
        double curr_income;
        double net_inc_ratio;
        boolean exit = false;
        assets[] asset_Array;
        liabilities[] liability_Array;

        System.out.println("Enter in the number of asset accounts followed by" +
                " the amount of liability accounts. DO NOT ENTER IN ANY SENSITIVE DATA/NAMES/NUMBERS "
                + " OF ANY KIND.");

        asset_Array = set_Asset_Accounts();
        liability_Array = set_Liability_Accounts();

        do
        {

            print_directions();  // prints directions
            user_input = new Scanner(System.in);
            choice = user_input.nextInt();

            switch (choice) {
                case 1:
                    curr_income = compute_net_income(asset_Array, liability_Array);
                    System.out.println("Your current net income is " + curr_income + "\n");
                    break;
                case 2:
                    net_inc_ratio = compute_net_inc_ratio(asset_Array, liability_Array);
                    System.out.println("Your current financial health is " + net_inc_ratio + " " +
                        "note net income ratio should be greater than or equal to one.");
                    break;
                case 3:
                    System.out.println("Enter in the liability account to process 'what if' + " +
                            " then enter in the new payment amount");
                    whatIfAcct = select_what_if(liability_Array);
                    System.out.println("Amount paid down over 12 months " + compute_change_in_payment(whatIfAcct, liability_Array));
                    break;
                case 4:
                    System.out.println("Function currently Testing");
                    save_to_file(asset_Array, liability_Array);  // thinking about removing; see if its needed by user
                    break;
                case 5:
                    System.out.println("Function currently unavailable");
                    print_to_graph_(asset_Array, liability_Array);
                    break;
                case 6:
                    print_help_menu_();
                    break;
                case 7:
                    System.out.println("Exiting program");
                    exit = true;
                    break;
                default:
                    break;
                }
        } while(!exit);
    }

    private static void print_directions()
    {
        System.out.println("---------------------------------------");
        System.out.println("1) Compute net income/loss");
        System.out.println("2) Compute asset to liability ratio");
        System.out.println("3) What if");
        System.out.println("4) Save to file");
        System.out.println("5) Print to graph");
        System.out.println("6) Help menu");
        System.out.println("7) Exit");
        System.out.println("---------------------------------------");
        System.out.println("Enter in your choice: ");
    }

    private static void print_help_menu_()
    {
        System.out.println("Select from the following options: ");
        System.out.println("1) Compute net income/loss : computes assets - liabilities");
        System.out.println("2) Compute asset to liability ratio : computes assets / liabilities ");
        System.out.println("3) What if : allows you to select an account and change the payment" +
                " then computes the resulting amount to pay off in one year");
        System.out.println("4) Save to file : will save your current settings to a .txt file. " +
                " Note: if you process a what if then the current what if will be saved to the file");
        System.out.println("5) Print to graph : FEATURE IS UNAVAILABLE CURRENTLY");
        System.out.println("6) Help menu : Prompts the help menu to print to screen");
        System.out.println("7) Exit : Will exit and close the program");
    }

    private static assets [] set_Asset_Accounts()
    {
        int num_asset_accounts;
        double amount;
        String name;
        Scanner user_inputTwo;

        System.out.println("Enter in the number of asset accounts: ");
        user_inputTwo = new Scanner(System.in); // assumes an int; will need to protect against invalid entries
        num_asset_accounts = user_inputTwo.nextInt();

        assets[] assetArray = new assets[num_asset_accounts];  // creates an array of accounts

        for (int i = 0; i < assetArray.length; i++)
        {
            System.out.println("Enter in the name of the account followed by the amount in the account: ");
            user_inputTwo = new Scanner(System.in);
            name = user_inputTwo.nextLine();
            user_inputTwo = new Scanner(System.in);
            amount = user_inputTwo.nextDouble();
            assetArray[i] = new assets(name, amount);
        }
        return assetArray;
    }

    private static liabilities [] set_Liability_Accounts()
    {
        int num_liability_accounts;
        double payment;
        double amount;
        String name;
        Scanner user_inputTwo;

        System.out.println("Enter in the number of liability accounts: \n");
        user_inputTwo = new Scanner(System.in);
        num_liability_accounts = user_inputTwo.nextInt();

        liabilities[] liabilityArray = new liabilities[num_liability_accounts];

        for (int i = 0; i < liabilityArray.length; i++)
        {
            System.out.println("Enter in the name of the account followed by the amount due in the account" +
                    " followed by the payment due: ");  // may want to take out println from the class and place in main
            user_inputTwo = new Scanner(System.in);
            name = user_inputTwo.nextLine();
            user_inputTwo = new Scanner(System.in);
            amount = user_inputTwo.nextDouble();
            user_inputTwo = new Scanner(System.in);
            payment = user_inputTwo.nextDouble();
            liabilityArray[i] = new liabilities(name, amount, payment);  // THINK ABOUT REFACTORING; CAN WE REMOVED ALL THE NEW STATEMENTS AND USE ONE?
        }
        return liabilityArray;
    }

    private static double compute_net_income(assets assetArray[], liabilities liabilityArray[])
    {
        double net_income = 0;
        double total_assets = 0;
        double total_liabilities = 0;

        for (int i = 0; i < assetArray.length; i++)
        {
            total_assets = net_income + assetArray[i].getAmount();
        }

        for (int j = 0; j < liabilityArray.length; j++)
        {
            total_liabilities = total_liabilities + liabilityArray[j].getAmount_in_account();
        }

        net_income = total_assets - total_liabilities;

        return net_income;
    }

    private static double compute_net_inc_ratio(assets[] assetArray, liabilities[] liabilityArray)
    {
        double income_ratio;
        double net_assets = 0;
        double net_liabilities = 0;

        for (int i = 0; i < assetArray.length; i++)
        {
            net_assets = net_assets + assetArray[i].getAmount();
        }

        for (int i = 0; i < liabilityArray.length; i++)
        {
            net_liabilities = net_liabilities + liabilityArray[i].getAmount_in_account();
        }

        income_ratio = (net_assets / net_liabilities);

        return income_ratio;
    }

    private static int select_what_if(liabilities[] liabilityArray)
    {
        Scanner user_input;
        boolean validInput = false;
        int choice;

        user_input = new Scanner(System.in);
        choice = user_input.nextInt();

        while(!validInput)
        {
            if (choice > liabilityArray.length)
            {
                System.out.println("That is not a valid input.");
            }
            else if (choice <= liabilityArray.length)
            {
                choice = choice - 1;
                validInput = true;
            }
        }
        return choice;
    }

    private static double compute_change_in_payment(int whatIfAcct ,liabilities [] liabilityArray)
    {
        double new_payment;
        double amountPaidDown = 0;
        Scanner new_user_input_ = new Scanner(System.in);

        new_payment = new_user_input_.nextDouble();
        liabilityArray[whatIfAcct].set_Payment(new_payment);

        for (int i  = 0; i < 12; i++)  // change 12 to a variable or a constant
        {
            amountPaidDown = liabilityArray[whatIfAcct].getAmount_in_account() - liabilityArray[whatIfAcct].getPayment();
            liabilityArray[whatIfAcct].set_amount(amountPaidDown);
        }

        return amountPaidDown;
    }
    private static void save_to_file(assets[] asset_array, liabilities[] liability_array) throws IOException
    {
        String asset_file = "assets.txt";
        String liability_file = "liabilities.txt";
        String direc_name = "C:\\Users\\Earle\\Desktop\\testFolder\\Locker";  // WILL WANT TO REPLACE AND NOT HAVE HARDCODED

        File direc_file_write = new File(direc_name);

        FileWriter write_assets = new FileWriter(new File(direc_file_write,asset_file));
        BufferedWriter asset_buffer = new BufferedWriter(write_assets);
        PrintWriter print_assets = new PrintWriter(asset_buffer);

        FileWriter write_liabilities = new FileWriter(new File(direc_file_write,liability_file));
        BufferedWriter liability_buff_ = new BufferedWriter(write_liabilities);
        PrintWriter print_liability = new PrintWriter(liability_buff_);

        for (int i = 0; i < asset_array.length; i++)
        {
            print_assets.println(asset_array[i].getName() + " " + asset_array[i].getAmount());
        }  // end asset print
        print_assets.close();


        for (int i = 0; i < liability_array.length; i++)
        {
            print_liability.println(liability_array[i].getAccount_name()+ " " + liability_array[i].getAmount_in_account()+ " " +
                    liability_array[i].getPayment());
        } // end liability print
        print_liability.close();
    }

    private static void print_to_graph_(assets[] asset_array, liabilities[] liability_array)
    {
        // would like only one function call; maybe 2
        // i dont want any user input
        // functions in graph class should be public members
    }
}
