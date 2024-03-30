package DataB;

import org.example.ServiceProviderClass;
import org.example.VenueClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class VenueData {

    Double minPrice;
    Double maxPrice;
    Integer maxSize;

    private static ArrayList <VenueClass> venueArrayList = new ArrayList<>();
    private static String VenueFile="DataVenueFile.txt";


    public VenueData() {
        ArrayList array = new ArrayList();

        boolean a=  this.readVenueDataFromFile(array);
// Check if the list is not empty
        if (!venueArrayList.isEmpty()) {

            minPrice = venueArrayList.get(0).getPrice();
            maxPrice = venueArrayList.get(0).getPrice();
            maxSize = venueArrayList.get(0).getSize();

            for (VenueClass temp : venueArrayList) {
                double price = temp.getPrice();
                Integer size = temp.getSize();

                if (price < minPrice) {
                    minPrice = price;
                }
                else if (price > maxPrice) {
                    maxPrice = price;
                }

                if(size > maxSize){
                    maxSize = size;
                }

            }
        }

    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public ArrayList <VenueClass> getVenueArrayList() {
        return venueArrayList ;
    }

    public Double getMinPrice() {
        return minPrice;
    }



    public static boolean readVenueDataFromFile(ArrayList array) {


        try(BufferedReader reader = new BufferedReader(new FileReader(VenueFile))) {
            int size1=array.size();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int size = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim().replaceAll("\\$", ""));
                    VenueClass venue = new VenueClass(name, size, price);
                    venueArrayList.add(venue);

                }
            }
                return true;
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return false;
        }


    }

}