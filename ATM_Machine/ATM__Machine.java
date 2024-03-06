
package ATM_Machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM__Machine {
    
    //for All accounts annualInterestRate is 4.5%
    static double annualInterestRate = 4.5;

    public static void main(String[] args) 
    {
        ArrayList<String> listofid = new ArrayList<>();
        double[] balances = {20.000,15.000,30.000,25.000,10.000,40.000,32.000,18.000};
        HashMap<String,Account> accounts = new HashMap<>();
        
        //
        try
        {
            listofid = listofid(new ArrayList<>(),8,3, 2 ,1);
            accounts = createAccount(listofid,balances);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        //
        
        //
        Scanner input = new Scanner(System.in);
        boolean restart;
        do{
            String id = displayId(listofid);
            Account mainAcc = accounts.get(id);
            passwordCheck();
            int choice= 0;boolean again;
            System.out.println("Please Enter a Choice :\n1-View the current balance\n2-withdraw\n3-deposit\n4-exit the main menu");
            //
            do
            {   do{
                    try
                    {
                        again = false;
                        choice = input.nextInt();
                        if(choice < 1 || choice > 4)
                        {
                            System.out.println("Please Enter a Number between 1-4");
                            again = true;
                        }
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Pleaser Enter an integer.");
                        again = true; 
                    }
                    input.nextLine();
                }while(again);
                //
               restart = executeTransaction(choice,mainAcc);
            }while(!restart);
        }while(restart);
    }
    
    public static boolean executeTransaction(int choice,Account acc)
    {
         switch(choice)
            {
                case 1 : 
                    displaybalance(acc);
                    break;
                case 2 :
                    displayDepositorWithdraw(acc,2);
                    break;
                case 3 :
                    displayDepositorWithdraw(acc,3);
                    break;
                case 4 :
                    return true;
            }
        return false; 
    }
    
    public static void displayDepositorWithdraw(Account acc,int i)
    {
        Scanner input = new Scanner(System.in);
        double amount;boolean again;
        System.out.println("Please Enter Amount : ");
        do{
            try
            {
                again = false;
                amount = input.nextDouble();
                switch(i)
                {
                    case 2 :
                        acc.withdraw(amount);
                        break;
                    case 3 :
                        acc.deposit(amount);
                        break;
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Pleaser Enter a valid amount.");
                again = true; 
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage()+"\nPlease re enter Nb between 1-4");
                again = false; 
            }
            input.nextLine();
        }while(again);
    }
    
    public static void displaybalance(Account acc)
    {
        double balance = acc.getBalance();
        if(balance == 0)
        {
            System.out.println("The Account "+acc.getId()+" Balance is Empty");
            return;
        }
        System.out.println(acc.accountdescription());
    }
    
    public static void passwordCheck()
    {
        boolean again;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter password : ");
        do{
            again = false;
            String password = input.nextLine();
            if(password == null || password.length() < 8 || !( password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")))
            {
                System.out.println("Password Conditions :\n1-Is not null or empty.\n2-Has a minimum length of 8 characters.\n3-Contains at least one digit.\n4-Contains at least one uppercase letter.\n5-Contains at least one lowercase letter.");
                again = true;
            }
        }while(again);
    }
    
    public static String displayId(ArrayList<String> listofid)
    {
        boolean again;String id;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ID: ");
        do{
            again= false;
            id = input.next();
            if(!validId(listofid,id))
            {
                System.out.println("Invalid. Try Again.");
                again = true;
            }
        }while(again);
        return id;
    }
    
    public static boolean validId(ArrayList<String> listofid,String id)
    {
        return listofid.contains(id);
    }
    
    public static HashMap<String,Account> createAccount(ArrayList<String> listofid,double[] balances)
    {
        String id;
        if(listofid == null || balances == null || listofid.isEmpty() || balances.length==0)
        {
            throw new IllegalArgumentException("Invalid inputs\n");
        }
        if(listofid.size() != balances.length)
        {
            throw new IllegalArgumentException("Inconsistency between the number of IDs and their corresponding balances.\n");
        }
        HashMap<String,Account> accounts = new HashMap<>();
        for(int i = 0; i < listofid.size(); i++)
        {
            id = listofid.get(i);
            if(id == null || Double.isNaN(balances[i]))
            {
                throw new IllegalArgumentException("Empty elements\n");
            }
            accounts.put(id,new Account(id,balances[i],annualInterestRate));
        }
        return accounts;
    }
    
    public static ArrayList<String> listofid(ArrayList<String> listid,int nbid,int digit, int max , int min)throws IllegalArgumentException
    {
        if(nbid <=0)
        {
            return listid;
        }
        if(Math.pow(max,digit) < nbid)
        {
            throw new IllegalArgumentException("Discrepancy between the requested number of IDs and the actual number of unique IDs generated.\n");
        }
        String id =  idGenerator(digit,max,min);
        if(!listid.contains(id))
            {
                listid.add(id);
                return listofid(listid,nbid-1,digit,max ,min);
            }
        else
        {
            return listofid(listid,nbid,digit,max ,min);
        }
        
    }
    
    public static String idGenerator(int digit, int max , int min)throws IllegalArgumentException
    {
        if(digit == 0)
        {
            return "";
        }
        if(digit<0 | max <=0 | min <=0 )
        {
            throw new IllegalArgumentException("Invalid digit\n");
        }
        return ((int)(Math.random()*(max-min+1))+min)+ idGenerator(digit-1, max ,min);
    }
}