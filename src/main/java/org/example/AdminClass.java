package org.example;

public class AdminClass {
    String email,password;

    public AdminClass(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AdminClass() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}