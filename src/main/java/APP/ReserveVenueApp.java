package APP;

import DataB.VenueData;
import org.example.ColoredOutput;
import org.example.VenueClass;

import java.util.ArrayList;

import static org.example.ColoredOutput.ANSI_BLUE;


public class ReserveVenueApp {


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
            for (VenueClass temp : venues ) {
                int i = 1 ;
                if (temp.getPrice() <= budget && temp.getSize() >= size ) {
                    System.out.println(
                            i+") " +temp.toString()

                            );
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
        if(i < array.size())
        return array.get(i).toString();
        else return " index out of range ";

    }

}
