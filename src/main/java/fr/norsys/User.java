package fr.norsys;

import java.util.Optional;

public class User {

    private String email;
    private Address address;

    public User() {
    }

    public User(String email) {
        this.email = email;
        System.out.println("creating new user using email constructor");
    }

    public User(String email, Address address) {
        this.email = email;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
