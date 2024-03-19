package org.example;

public class VenueClass {


    String name;
    Integer size;
    Double price;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public VenueClass( String name,Integer size, Double price) {
        this.name = name;
        this.size = size;
        this.price = price;
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
