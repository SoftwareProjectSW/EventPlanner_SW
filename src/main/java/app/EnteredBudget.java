package app;

import DataB.ServiceProviderData;
import DataB.SuperSPData;
import org.example.ServiceProviderClass;
import org.example.SuperSPClass;
import DataB.SuperSPData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static DataB.SuperSPData.readSPData;

public class EnteredBudget {
    static ServiceProviderData serviceProviderData = new ServiceProviderData();

    static SuperSPData object = new SuperSPData();
    public boolean theUserIsNotifiedOfTheBudgetInvalid(int price) throws IOException {
        System.out.println("\u001B[31mThe budget is not valid. Please make sure that you have enough budget,and you can choose one of this packages\u001B[0m");
        return true;
    }

    public boolean aSufficientBudgetProvidedByTheUser(String budg) {
        readSPData("sp_price_dates.txt");
        SuperSPData cc = new SuperSPData();
        Set<String> budgetSet = new HashSet<>(cc.getAllBudgets());
        int providedBudget = Integer.parseInt(budg.trim().replace("$", ""));

        int serviceProviderCount = 0;
        for (String budget : budgetSet) {
            int serviceProviderBudget = Integer.parseInt(budget.trim().replace("$", ""));
            if (serviceProviderBudget <= providedBudget) {
                serviceProviderCount++;
            }
        }

        if (serviceProviderCount > 0) {
            System.out.println("There are " + serviceProviderCount + " service provider(s) available with this budget or less.");
            return true;
        } else {
            System.out.println("No service providers available for this budget.");
            return false;
        }
    }


    public static boolean theSelectedServiceProviderHasAvailabilityOnTheRequiredDate(String budget) {
        readSPData("sp_price_dates.txt");
        List<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();
        SuperSPData object = new SuperSPData(); // Assuming you have an instance of SuperSPData
        boolean found = false;

        List<String> allBudgets = object.getAllBudgets();

        try {
            for (int i = 0; i < allBudgets.size(); i++) {
                String currentBudget = allBudgets.get(i);
                if (Integer.parseInt(currentBudget.trim().replace("$", "")) <= Integer.parseInt(budget.trim().replace("$", ""))) {
                    found = true;

                    ServiceProviderClass serviceProvider = serviceProviderList.get(i);
                    System.out.println("\u001B[34mName: " + serviceProvider.getName() + "\u001B[0m"); // Blue color for name
                    System.out.println("\u001B[34mID: " + serviceProvider.getId() + "\u001B[0m"); // Blue color for name
                    System.out.println("\u001B[32mBudget: " + currentBudget + "\u001B[0m"); // Green color for budget
                    System.out.print("Services: ");
                    for (String service : serviceProvider.getServicesList()) {
                        System.out.print(service + " ");
                    }
                    System.out.println("\n--------------------------------------------------");
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }


        if (!found) {
            System.out.println("No service provider found with a budget less than or equal to: " + budget);
            return found;
        }

        return found;
    }

    public static boolean theUserSubmitsTheBudgetAndDate(int id) {
        readSPData("sp_price_dates.txt");
        List<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();
        SuperSPData object = new SuperSPData();
        boolean found = false;

        try {
            for (int i = 0; i < serviceProviderList.size(); i++) {
                ServiceProviderClass serviceProvider = serviceProviderList.get(i);
                if (serviceProvider.getId().equals(Integer.toString(id))) {
                    found = true;
                    System.out.println("\u001B[34mName: " + serviceProvider.getName() + "\u001B[0m"); // Blue color for name
                    System.out.println("ID: " + serviceProvider.getId());
                    System.out.println("Email: " + serviceProvider.getEmail());
                    System.out.print("Services: ");
                    for (String service : serviceProvider.getServicesList()) {
                        System.out.print(service + " ");
                    }
                    System.out.println("\nFree Dates: ");
                    List<List<String>> freeDates = object.getAllFreeDates();
                    if (i < freeDates.size()) {
                        List<String> dates = freeDates.get(i);
                        for (String date : dates) {
                            if (date.equals("---")) {
                                System.out.println("\u001B[31mNo free dates available for this provider, please choose another one.\u001B[0m"); // Red color for message

                            } else {
                                System.out.println("\u001B[32m" + date + "\u001B[0m");
                            }
                        }
                    }
                    break;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }

        if (!found) {
            System.out.println("No service provider found with the ID: " + id);
        }
        return found;
    }


     public static void main(String[] args) throws IOException {

     }




}

