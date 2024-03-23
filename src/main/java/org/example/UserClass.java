package org.example;

public class UserClass {
    String email,password;

    public UserClass(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserClass() {

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
