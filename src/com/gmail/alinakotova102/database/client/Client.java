package com.gmail.alinakotova102.database.client;

import com.gmail.alinakotova102.database.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {
    public static final int idClient = 1;
    private String firstname;
    private String lastname;
    private String address;
    private int contact;

    public Client() {
    }

    public Client(String firstname, String lastname, String address, int contact) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.contact = contact;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public static Client complateClientDB() {
        ResultSet resultSet;
        Client client = new Client();

        String select = "SELECT * FROM " + ConstClientDB.CLIENT_TABLE + " WHERE " +
                ConstClientDB.CLIENT_ID + "=?";

        try {
            PreparedStatement statement = DatabaseHandler.getDbConnection().prepareStatement(select);
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

    @Override
    public String toString() {
        System.out.println("Login completed: " + "Client{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                '}');
        return getFirstname() + " " + getLastname();
    }
}
