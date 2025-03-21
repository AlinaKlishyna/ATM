package com.gmail.alinakotova102;

public class Client {
    public static final int idClient = 1;
    private String firstname;
    private String lastname;
    private String address;
    private int contact;
    private short pincode;

    public Client() {
    }

    public Client(String firstname, String lastname, String address, int contact, short pincode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.contact = contact;
        this.pincode = pincode;
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

    public short getPincode() {
        return pincode;
    }

    public void setPincode(short pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return getFirstname() + " " + getLastname();
    }
}
