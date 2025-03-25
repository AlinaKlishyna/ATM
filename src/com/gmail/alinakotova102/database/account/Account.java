package com.gmail.alinakotova102.database.account;

import com.gmail.alinakotova102.database.DatabaseHandler;
import com.gmail.alinakotova102.database.client.Client;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private int idAccount;
    private short pincode;
    private static BigDecimal balance;
    public static Client client;

    public Account() {

    }

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

    public static BigDecimal getBalance() {
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

    public static Account complateAccountDB(Client client) {
        ResultSet resultSet;
        Account account = new Account();
        String select = "SELECT * FROM " + ConstAccountDB.ACCOUNT_TABLE
                + " WHERE " + ConstAccountDB.ACCOUNT_ID_CLIENT + "=?";

        try {
            PreparedStatement statement = DatabaseHandler.getDbConnection().prepareStatement(select);
            statement.setString(1, String.valueOf(client.idClient));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                account.setIdAccount(resultSet.getInt(1));
                account.setPincode(resultSet.getShort(3));
                account.setBalance(resultSet.getBigDecimal(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
