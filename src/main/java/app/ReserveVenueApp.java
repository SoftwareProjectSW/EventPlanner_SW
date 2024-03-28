package app;

import DataB.VenueData;
import org.example.ColoredOutput;
import org.example.VenueClass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.ColoredOutput.ANSI_BLUE;


public class ReserveVenueApp {
    public double getSelectedVenuePrice(Double budget , Integer size,Integer a){

        Integer i = a-1;

        ArrayList <VenueClass> array = getVenueWithBudget( budget , size);
        if( i < array.size())
            return array.get(i).getPrice();
        else return -1;

    }

    public boolean reserveVenue(Double budget, Integer size){

        VenueData venueData = new VenueData();

        if(budget < venueData.getMinPrice()){


            System.out.println(ColoredOutput.ANSI_GREEN +"You can not reserve any venue," +
                    " insufficient budget "+budget+"$" + ColoredOutput.ANSI_RESET);

            return false;
        }
        else if(venueData.getMaxSize() < size){

            System.out.println( ANSI_BLUE + "You can not reserve any venue," +
                    " there is no Enough chairs with " +size +" number" + ColoredOutput.ANSI_RESET);

            return false;
        }


        ArrayList<VenueClass> venues = venueData.getVenueArrayList();
        int i = 1 ;

        for (VenueClass temp : venues ) {

            if (temp.getPrice() <= budget && temp.getSize() >= size ) {

                System.out.println(i + ") " +temp.toString() );
                i++;

            }


        }



        return true;
    }

    public  ArrayList<VenueClass> getVenueWithBudget(Double budget , Integer size){

        VenueData venueData = new VenueData();
        ArrayList<VenueClass> venues = venueData.getVenueArrayList();
        ArrayList<VenueClass> array = new ArrayList<>();

        for (VenueClass temp : venues ) {
            if (temp.getPrice() <= budget && temp.getSize() >= size ) {
                //System.out.println(  temp.toString());
                array .add(temp);
            }

        }

        return array;
    }

    public String getSelectedVenue(Double budget , Integer size,Integer a){

        Integer i = a-1;

        ArrayList <VenueClass> array = getVenueWithBudget( budget , size);
        if( i < array.size())
            return array.get(i).toString();
        else return " index out of range ";

    }


    public boolean isAddedVenue(String name, int capacity, double price) {

        Integer flag = 0;
        VenueData venueData = new VenueData();
        String valid = isValidVenueDetails(name, capacity, price);

        if (! valid.equals("valid")) return false;

        String smallName = name.toLowerCase();
        for (VenueClass temp : venueData.getVenueArrayList()) {

            if (temp.getName().toLowerCase().equals( smallName )) {
                System.out.println(
                        ColoredOutput.ANSI_GREEN+
                                "This name already exists"
                                +ColoredOutput.ANSI_RESET
                );
                return false;
            }
        }

        String venueDetails = name + ","+ capacity + ",$" + price ;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DataVenueFile.txt", true))) {
            writer.write(venueDetails);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }

    public String isValidVenueDetails(String name, int capacity, double price) {
        if (name == null || name.isEmpty()) {
            return "Please provide a name for the venue";
        }
        if (capacity <= 0) {
            return "Venue capacity must be valid";
        }
        if (price <= 0) {
            return "Venue price must be valid";
        }
        return "valid"; // No error
    }



}