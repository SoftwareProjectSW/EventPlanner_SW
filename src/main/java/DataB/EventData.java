package DataB;

import org.example.Event;
import org.example.ServiceProviderClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class EventData {

    public void setEventsList(ArrayList<Event> eventsList) {
        this.eventsList = eventsList;
    }

    private  ArrayList<Event> eventsList = new ArrayList<>();


    public EventData() {

        eventsList = readEventsFromFile();

    }

    public ArrayList<Event> getEventsList() {
        return eventsList;
    }

    public static void main(String[] args) {//print events in file

        EventData events = new EventData();
        for (Event event : events.getEventsList()) {
            System.out.println(event);
        }

     //   System.out.println(events.eventsList.size());
    }

    public static ArrayList<Event> readEventsFromFile() {
        ArrayList<Event> events = new ArrayList<>();
        String filename = "DataForEvents.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder eventString = new StringBuilder();

            // Read the file line by line
            while ((line = br.readLine()) != null) {
                if (line.equals("***")) {
                    if (eventString.length() > 0) {
                        Event event = createEventFromString(eventString.toString());
                   
                            events.add(event);
                        
                    }
                    eventString = new StringBuilder();
                } else {
                    eventString.append(line).append("\n");
                }
            }

        
        } catch (IOException e) {
            logger.log(Level.SEVERE, "there is error ", e);
        }

        return events;
    }

    public static Event createEventFromString(String eventString) {
        String[] lines = eventString.split("\n");
        String status = null;
        String customer = null;
        ServiceProviderClass serviceProvider = null;
        String venue = null;
        String date = null;
        String price = null;

        for (String line : lines) {
            if (line.startsWith("status:")) {
                status = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("customer email:")) {
                customer = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("Service Provider's INFO {")) {
                serviceProvider = createServiceProviderFromString(lines);
            } else if (line.startsWith("venue:")) {
                venue = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("date:")) {
                date = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("price:")) {
                price = line.substring(line.indexOf(":") + 1).trim();
            }
        }

        if (status != null && customer != null && serviceProvider != null && venue != null && date != null && price != null) {
            return new Event(status, customer, serviceProvider, venue, date, price);
        } else {
            return null; // Return null if event cannot be created
        }
    }

    public static ServiceProviderClass createServiceProviderFromString(String[] lines) {
        String name = null;
        String id = null;
        String email = null;
        ArrayList<String> servicesList = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("NAME:")) {
                name = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("ID:")) {
                id = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("Email:")) {
                email = line.substring(line.indexOf(":") + 1).trim();
            } else if (line.startsWith("servicesList:")) {
                String servicesString = line.substring(line.indexOf(":") + 1).trim();
                servicesList = parseServicesList(servicesString);
            }
        }

        return new ServiceProviderClass(name, id, email, servicesList);
    }

    public static ArrayList<String> parseServicesList(String servicesString) {
        ArrayList<String> servicesList = new ArrayList<>();
        servicesString = servicesString.substring(1, servicesString.length() - 1); // Remove square brackets
        String[] servicesArray = servicesString.split(", ");
        for (String service : servicesArray) {
            servicesList.add(service.trim());
        }
        return servicesList;
    }





}
