package com.gmail.alinakotova102.database.client;

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
