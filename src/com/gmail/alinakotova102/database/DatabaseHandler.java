package com.gmail.alinakotova102.database;

import com.gmail.alinakotova102.database.account.Account;
import com.gmail.alinakotova102.database.account.ConstAccountDB;
import com.gmail.alinakotova102.database.client.Client;
import com.gmail.alinakotova102.database.client.ConstClientDB;
import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public static Client client = new Client();
    public Account account = new Account();

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public ResultSet getClient(Client client, short pincode) {
        ResultSet resultSet;
        account.setClient(client);
        //sql-select view
        //SELECT * FROM account a JOIN clients c ON a.id_clients = c.id_clients WHERE c.id_clients = ? AND a.pincode =?
        String select = "SELECT * FROM " + ConstAccountDB.ACCOUNT_TABLE +" a "
                + "JOIN "+ ConstClientDB.CLIENT_TABLE +" c ON a." + ConstAccountDB.ACCOUNT_ID_CLIENT
                +" = c." + ConstClientDB.CLIENT_ID +
                " WHERE " + "c." + ConstClientDB.CLIENT_ID + "= ? AND a."+ ConstAccountDB.ACCOUNT_PINCODE + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            statement.setString(1, String.valueOf(client.getIdClient()));
            statement.setString(2, String.valueOf(pincode));
            resultSet = statement.executeQuery(); // позволяет получить данные из БД
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.client = addDataClient();
        this.account = addDataAccount(this.client);
        return resultSet;
    }

    public Account addDataAccount(Client client) {
        ResultSet resultSet;
        Account account = new Account();
        String select = "SELECT * FROM " + ConstAccountDB.ACCOUNT_TABLE
                + " WHERE " + ConstAccountDB.ACCOUNT_ID_CLIENT + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
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

    public Client addDataClient() {
        ResultSet resultSet;
        Client client = new Client();

        String select = "SELECT * FROM " + ConstClientDB.CLIENT_TABLE + " WHERE " +
                ConstClientDB.CLIENT_ID + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            statement.setString(1, String.valueOf(Client.idClient));

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client.setFirstname(resultSet.getString(2));
                client.setLastname(resultSet.getString(3));
                client.setAddress(resultSet.getString(4));
                client.setContact(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
}
