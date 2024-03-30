package org.example;

import java.util.Arrays;

public class VenueClass {


    String name;
    Integer size;
    Double price;


    public Integer getSize() {
        return size;
    }



    public Double getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }


    public VenueClass( String name,Integer size, Double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }
    public static String extractName(String venueInfo) {
        String regex = "Name:\\s*([^,]+)";
        return venueInfo.replaceAll(regex, "$1").trim();
    }



    @Override
    public String toString() {
        return ColoredOutput.ANSI_PURPLE+
                "Name: " + name +
                ", Size: " + size +
                ", Price: $" + price+
                ColoredOutput.ANSI_RESET+'\n';
    }
}
