package com.gmail.alinakotova102.database;

import com.gmail.alinakotova102.database.account.Account;
import com.gmail.alinakotova102.database.account.ConstAccountDB;
import com.gmail.alinakotova102.database.client.Client;
import com.gmail.alinakotova102.database.client.ConstClientDB;

import java.sql.*;

public class DatabaseHandler extends Configs {
    public static Client client = new Client();
    public static Account account = new Account();

    public static Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        Connection dbConnection;
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public ResultSet findClientDB(Client client, short pincode) {
        ResultSet resultSet;
        account.setClient(client);
        //sql-select view
        //SELECT * FROM account a JOIN clients c ON a.id_clients = c.id_clients WHERE c.id_clients = ? AND a.pincode =?
        String select = "SELECT * FROM " + ConstAccountDB.ACCOUNT_TABLE + " a "
                + "JOIN " + ConstClientDB.CLIENT_TABLE + " c ON a." + ConstAccountDB.ACCOUNT_ID_CLIENT
                + " = c." + ConstClientDB.CLIENT_ID +
                " WHERE " + "c." + ConstClientDB.CLIENT_ID + "= ? AND a." + ConstAccountDB.ACCOUNT_PINCODE + "=?";

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
        this.client = Client.complateClientDB();
        this.account = Account.complateAccountDB(this.client);
        return resultSet;
    }
}
