package org.example;

import DataB.ServiceProviderData;
import DataB.VenueData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    public static int serialNumberCounter = 1;


    public Event(String email, ArrayList<String> servicesList, String venueSelected, String date, String chosenBudget1) {
        this.customer = email;
        this.SP = new ServiceProviderClass(email, servicesList);
        this.venue = venueSelected;
        this.date = date;
        this.price = chosenBudget1;
    }

    public Event(String email, String name, String id, String email1, ArrayList<String> servicesList, String venName, String date, String chosenBudget1) {
            this.customer = email;
            this.SP = new ServiceProviderClass(name, id, email1);
            this.SP.setServicesListPackage(servicesList);
            this.venue = venName;
            this.date = date;
            this.price = chosenBudget1;
        }


    public enum Status {
        NOT_SEEN,
        APPROVED,
        DECLINED
    }

    private Status status = Status.NOT_SEEN;
    private String customer;

    public void setStatus(Status status) {
        this.status = status;
    }

    public static int getSerialNumberCounter() {
        return serialNumberCounter;
    }

    private ServiceProviderClass SP;
    private String venue;
    private String date;
    private String price;

    public Event(String customer, ServiceProviderClass SP, String venue, String date, String price) {
      //  this.serialNumber = generateSerialNumber(); // Generate and assign the serial number
        this.customer = customer;
        this.SP = SP;
        this.venue = venue;
        this.date = date;
        this.price = price;
        this.status = Status.NOT_SEEN;
    }

    public Event(String status, String customer, ServiceProviderClass SP, String venue, String date, String price) {

        this.status = Status.valueOf(status);
        this.customer = customer;
        this.SP = SP;
        this.venue = venue;
        this.date = date;
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public ServiceProviderClass getSP() {
        return SP;
    }

    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        int serialNumber = serialNumberCounter++; // Increment and assign the serial number
        return  serialNumber + ")" +
                ColoredOutput.ANSI_BLUE +
                "Event{" + '\n' +
                "status: " + status + '\n' +
                "customer email: " + customer + '\n' +
                "SP: " + '\n' +
                SP.toString() + '\n' +
                "ID: " + SP.getId() + '\n' +
                ColoredOutput.ANSI_BLUE + '\n' +
                "venue: " + venue + '\n' +
                "date: " + date + '\n' +
                "price: " + price + '\n' +
                "}" + '\n' + ColoredOutput.ANSI_RESET + "***";
    }



  /*  public String serialize() {
        // Serialize the event information into a formatted string
        StringBuilder sb = new StringBuilder();
        sb.append("Event{\n");
        sb.append("status: ").append(status).append("\n");
        sb.append("customer email: ").append(customer).append("\n");
        sb.append("SP: \n");
        sb.append("Service Provider's INFO {\n");
        sb.append("NAME: ").append(SP.getName()).append("\n");
        sb.append("ID: ").append(SP.getId()).append("\n");
        sb.append("Email: ").append(SP.getEmail()).append("\n");
        sb.append("servicesList: ").append(SP.getServicesListPackage()).append("\n");
        sb.append("}\n");
        sb.append("venue: ").append(venue).append("\n");
        sb.append("date: ").append(date).append("\n");
        sb.append("price: ").append(price).append("$\n");
        sb.append("}\n***");
        return sb.toString();
    }*/
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{\n");
        sb.append("status: ").append(status).append('\n');
        sb.append("customer email: ").append(customer).append('\n');
        sb.append("SP: \n");
        sb.append("Service Provider's INFO {\n");
        sb.append("NAME: ").append(SP.getName()).append('\n');
        sb.append("ID: ").append(SP.getId()).append('\n');
        sb.append("Email: ").append(SP.getEmail()).append('\n');
        sb.append("servicesList: ").append(SP.getServicesListPackage()).append('\n');
        sb.append("}\n");
        sb.append("venue: ").append(venue).append('\n');
        sb.append("date: ").append(date).append('\n');
        sb.append("price: ").append(price).append('\n');
        sb.append("}\n***\n");
        return sb.toString();
    }




    public static void writeEventToFile(Event event) {
        try (FileWriter fw = new FileWriter("DataForEvents.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // Append the serialized event information to the file
            out.println(event.serialize());
            System.out.println("Event information written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing event information to file: " + e.getMessage());
        }
    }
}

