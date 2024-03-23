package org.example;
import DataB.ServiceProviderData;
import DataB.SuperSPData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperSPClass {


    SuperSPData cc=new SuperSPData();


    static ServiceProviderData serviceProviderData = new ServiceProviderData();

   //  ArrayList<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();

    private String budget;

boolean freeFlag=true;

    private List<List<String>> freeDates;


    private List<List<String>> bookedDate;


    public SuperSPClass(String budget, boolean freeFlag, List<List<String>> freeDates, List<List<String>> bookedDate) {
        this.budget = budget;
        this.freeFlag = freeFlag;
        this.freeDates = cc.getAllFreeDates();
this.bookedDate=cc.getAllBookedDates();
    }

    public String getBudget() {
        return budget;
    }

    public boolean isFreeFlag() {
        return freeFlag;
    }

    public List<List<String>> getFreeDates() {
        return freeDates;
    }

    public List<List<String>> getBookedDate() {
        return bookedDate;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setFreeFlag(boolean freeFlag) {
        this.freeFlag = freeFlag;
    }

    public void setFreeDates(ArrayList<String> freeDates) {
        this.freeDates = Collections.singletonList(freeDates);
    }

    public void setBookedDate(ArrayList<String> bookedDate) {
        this.bookedDate = Collections.singletonList(bookedDate);
    }

    public static void run(int i) {

        // for (int i = 0; i < serviceProviderData.getServiceProviderList().size(); i++) {
     /*   ServiceProviderClass serviceProvider = serviceProviderData.getServiceProviderList().get(i);
        System.out.println("Name: " + serviceProvider.getName());
        System.out.println("ID: " + serviceProvider.getId());
        System.out.print("Services: ");
        for (String service : serviceProvider.getServicesList()) {
            System.out.print(service + " ");
        }
        System.out.println();
   // }
*/

    }

}
