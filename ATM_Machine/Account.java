/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ATM_Machine;

import java.util.Date;

public class Account {
    //
    private String id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;
    //
    
    //
    public Account()
    {
        this.id = "Invalid Account";
        this.balance = 0;
        this.annualInterestRate = 0;
        this.dateCreated = new Date();
    }
    //
    public Account(String id,double balance,double annualInterestRate)
    {
        this.id = id;
        this.balance = balance;
        setAnnualInterestRate(annualInterestRate);
    }
    //
    
    //
    //
    public boolean validId(String id)
    {
        return this.id.equals(id);
    }
    //
    
    //
    public String accountdescription()
    {
        return "Account :\n Id : "+this.id+" \nBalance : "+this.balance+"$\n";
    }
    //
    
    //
    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", balance=" + balance + ", annualInterestRate=" + annualInterestRate + ", dateCreated=" + dateCreated + '}';
    }
    //
    
    //
    public void deposit(double amount) throws IllegalArgumentException {
        if(amount<=0)
        {
            throw new IllegalArgumentException("Deposit Failed : Invalid Amount.\n");
        }
        this.balance = this.balance + amount;
        System.out.println("Deposit Succeeded");
    }
    //
    
    //
    public void withdraw(double amount) throws IllegalArgumentException
    {
        if(amount<=0)
        {
            throw new IllegalArgumentException("Withdraw Failed : Invalid Amount.\n");
        }
        
        if( balance < amount)
        {
            throw new IllegalArgumentException("Withdraw Failed : Balance is less than Amount.\n");
        }
        if(balance == amount)
        {
            System.out.println("Balance is 0 now");
        }
        this.balance = this.balance - amount;
        System.out.println("Withdraw Succeeded");
    }
    //
    
    //
    public double getMonthlyInterest()
    {
       return this.balance*this.getMonthlyInterestRate();
    }
    //
    
    //
    public double getMonthlyInterestRate()
    {
       return (this.annualInterestRate/12);
    }
    //
    
    //
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = (annualInterestRate/100);
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
}
