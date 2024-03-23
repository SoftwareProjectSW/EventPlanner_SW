package org.example;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderClass {

    private String name;
    private String id;
    private String email;
    private  ArrayList<String> servicesList = new ArrayList<>();
    private  ArrayList<String> servicesListPackage = new ArrayList<>();


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

    public  ArrayList<String> getServicesList() {
        return servicesList;
    }
    public  ArrayList<String> getServicesListPackage() {
        return servicesListPackage;
    }//addition
    public ServiceProviderClass(String name, List<String> servicesList) {
        this.name = name;
        this.servicesList = (ArrayList<String>) servicesList;
        this.servicesListPackage = new ArrayList<>();
    }//addition
    @Override
    public String toString() {
        return "[" + String.join(", ", servicesList) + "]";
    }//addition
    public void setServicesListPackage(ArrayList<String> servicesList) {
        this.servicesListPackage = servicesList;
    }
//addition

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

}
