package org.example;

public class Organizer {

    private String email;
    private String password;

    public Organizer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
