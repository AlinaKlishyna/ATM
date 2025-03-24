package com.gmail.alinakotova102.database;

import com.gmail.alinakotova102.Client;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public static Client client = new Client();

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public ResultSet getClient(Client client) {
        ResultSet resultSet = null;
        //sql-select view
        //SELECT * FROM account a JOIN clients c ON a.id_clients = c.id_clients WHERE c.id_clients = ? AND a.pincode =?
        String select = "SELECT * FROM account a " +
                "JOIN client c ON a.id_client = c.id_client " +
                "WHERE c.id_client = ? AND a.pincode =?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);
            statement.setString(1, String.valueOf(client.getIdClient()));
            statement.setString(2, String.valueOf(client.getPincode()));
            resultSet = statement.executeQuery(); // позволяет получить данные из БД
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.client = addDataClient();
        return resultSet;
    }

    public Client addDataClient() {
        String firstLastName = "";
        ResultSet resultSet = null;
        Client client = new Client();
        String select = "SELECT * FROM " + ConstClientsDB.CLIENT_TABLE + " WHERE " +
                ConstClientsDB.CLIENTS_ID + "=?";

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
