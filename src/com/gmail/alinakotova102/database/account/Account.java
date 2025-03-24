package com.gmail.alinakotova102.database.account;

import com.gmail.alinakotova102.database.client.Client;

import java.math.BigDecimal;

public class Account {
    private int idAccount;
    private short pincode;
    private BigDecimal balance;
    Client client;

    public Account(Client client, int idAccount, short pincode, BigDecimal balance) {
        this.client = client;
        this.idAccount = idAccount;
        this.pincode = pincode;
        this.balance = balance;
    }
    public int getIdAccount() {
        return idAccount;
    }
    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public short getPincode() {
        return pincode;
    }

    public void setPincode(short pincode) {
        this.pincode = pincode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
