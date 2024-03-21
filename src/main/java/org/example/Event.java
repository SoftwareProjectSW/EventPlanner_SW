package org.example;

import DataB.ServiceProviderData;
import DataB.VenueData;

import java.util.Date;

public class Event {
    public enum Status {
        NOT_SEEN,
        APPROVED,
        DECLINED
    }

    private Status status = Status.NOT_SEEN;
    private String customer;
    private ServiceProviderClass SP ;
    private String venue ;
    private String date ;
    private String price ;

    public Event(String customer,ServiceProviderClass SP, String venue, String date, String price) {

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
        return ColoredOutput.ANSI_BLUE+
                "Event{" +'\n'+
                "status: " + status +'\n'+
                "customer email: " + customer + '\n' +
                "SP: "+'\n' +
                SP.toString() +ColoredOutput.ANSI_BLUE+
                "venue: " + venue + '\n' +
                "date: " + date + '\n' +
                "price: " + price + '\n' +
                "}"+'\n' +ColoredOutput.ANSI_RESET+"***";
    }

            public static void main(String[] args) {

                String name = "HALA";
                String id= "123";
                String email = "HI@GMAIL.COM";

                ServiceProviderClass sp = new ServiceProviderClass(name,id,email);
                sp.getServicesList().add("DJ");
                sp.getServicesList().add("RRRJ");
                sp.getServicesList().add("DWWWJ");

        Event e = new Event("hala",sp,"PALACE","20/2/2020","2000$");
                System.out.println(e.toString());


        }



}
