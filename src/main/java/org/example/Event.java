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
    private ServiceProviderClass SP ;
    private String venue ;
    private String date ;
    private Double price ;




//        public static void main(String[] args) {
//            Status status1 = Status.NOT_SEEN;
//            Status status2 = Status.APPROVED;
//            Status status3 = Status.DECLINED;
//
//            System.out.println("Status 1: " + status1);
//            System.out.println("Status 2: " + status2);
//            System.out.println("Status 3: " + status3);
//
//        }



}
