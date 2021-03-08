package com.example.myapplication;

public class Account {

    public String id, account_name, amount, iban, currency;

    public Account(){

    }

    public Account(String id, String account_name, String amount, String iban, String currency)
    {
        this.id=id;
        this.account_name=account_name;
        this.amount=amount;
        this.iban=iban;
        this.currency=currency;
    }

}
