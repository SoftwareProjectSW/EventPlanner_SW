package org.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceProviderClass {

    private String name;
    private String id;
    private String email;
    private ArrayList<String> servicesList = new ArrayList<>();

    public ServiceProviderClass(String name, String id) {
        this.name = name;
        this.id = id;

    }
    public ServiceProviderClass( String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;

    }
    public ServiceProviderClass( String name, String id, String email,ArrayList<String> services) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.servicesList = services;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getServicesList() {
        return servicesList;
    }

    public void setServicesList(ArrayList<String> servicesList) {
        this.servicesList = servicesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ColoredOutput.ANSI_CYAN+"Service Provider's INFO {" +'\n'+
                "NAME: " + name + '\n' +
                "ID: " + id + '\n' +
                "Email: " + email + '\n' +
                "servicesList: " + servicesList +
                "  }"+'\n'+
                ColoredOutput.ANSI_RESET
                ;
    }


//            public static void main(String[] args) {
//
//                String name = "HALA";
//                String id= "123";
//                String email = "HI@GMAIL.COM";
//
//                ServiceProviderClass sp = new ServiceProviderClass(name,id,email);
//                sp.getServicesList().add("DJ");
//                sp.getServicesList().add("RRRJ");
//                sp.getServicesList().add("DWWWJ");
//
//                System.out.println(sp.toString());
//
//            }
}
