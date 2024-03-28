package APP;

import DataB.EventData;
import DataB.SuperSPData;
import org.example.Event;
import org.example.ServiceProviderClass;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static APP.EnteredBudget.serviceProviderData;
import static DataB.SuperSPData.readSPData;
import static java.lang.System.out;

public class ApproveApp {
 /* public static boolean aListOfPendingEventsAwaitingApproval(){
      EventData events = new EventData();
      for (Event event : events.getEventsList()) {
          out.println(event);
      }

      out.println(events.getEventsList().size());
      return true;
  }*/

 public static boolean aListOfPendingEventsAwaitingApproval() {
     EventData events = new EventData();
     int pendingEventsCount = 0;
     for (Event event : events.getEventsList()) {
         if (event.getStatus() == Event.Status.NOT_SEEN) {
             out.println(event);
             pendingEventsCount++;
         }
     }
     out.println("Total number of pending events: " + pendingEventsCount);
     return true;
 }
    public static boolean aListOfApprovedEvents() {
        EventData events = new EventData();
        int pendingEventsCount = 0;
        for (Event event : events.getEventsList()) {
            if (event.getStatus() == Event.Status.APPROVED) {
                out.println(event);
                pendingEventsCount++;
            }
        }
        out.println("Total number of Upcoming events: " + pendingEventsCount);
        return true;
    }


    public static boolean selectsAnEventToReview(String i) {
        EventData events = new EventData();
        for (Event event : events.getEventsList()) {
            if (event.getSP().getId().equals(i) ) { // Check if the ID matches
                out.println(event.serialize());
                return true; // Exit the loop after printing the event
            }
        }
        out.println("Event with ID " + i + " not found.");
        return false; // Return false since the event was not found
    }




    private static void writeEventsToFile1( Event event) {
        try (FileWriter fw = new FileWriter("DataForEvents.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(event.serialize());
            System.out.println("Event information written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing event information to file: " + e.getMessage());
        }
    }

    private static List<Event> loadEventsFromFile() {
        List<Event> eventList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("DataForEvents.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

            }
        } catch (IOException e) {
            out.println("An error occurred while reading from the file: " + e.getMessage());
        }
        return eventList;
    }
    public static void main(String[] args) {//print events in file
        String id = "5";
        String date = "8/4/2024";
        matchIdWithDates(id, date);
    }

    public static void matchIdWithDates(String id, String date) {
        readSPData("sp_price_dates.txt");
        SuperSPData object = new SuperSPData();
        List<List<String>> freeDates = object.getAllFreeDates();
        List<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();

        boolean found = false;

        try {
            for (int i = 0; i < serviceProviderList.size(); i++) {
                ServiceProviderClass serviceProvider = serviceProviderList.get(i);
                if (serviceProvider.getId().equals(id)) {
                    found = true;
                    System.out.println("\u001B[34mName: " + serviceProvider.getName() + "\u001B[0m"); // Blue color for name
                    System.out.println("ID: " + serviceProvider.getId());
                    System.out.println("Email: " + serviceProvider.getEmail());
                    System.out.print("Services: ");
                    for (String service : serviceProvider.getServicesList()) {
                        System.out.print(service + " ");
                    }
                    System.out.println("\nFree Dates: ");

                    if (i < freeDates.size()) {
                        List<String> dates = freeDates.get(i);
                        boolean dateFound = false;
                        for (int j = 0; j < dates.size(); j++) {
                            String d = dates.get(j);
                            // Match functionality here
                            if (d.equals(date)) {
                                // Match found
                                dateFound = true;
                                System.out.println("Match found for date " + date + " at index " + j);
                                // Add the deleted date plus 7 days to booked dates
                                object.getAllBookedDates().get(i).add(date);

                                // Add the deleted date plus 7 days to free dates
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                                LocalDate deletedDate = LocalDate.parse(date, formatter);
                                LocalDate addedDate = deletedDate.plusDays(7);
                                String addedDateStr = addedDate.format(formatter);
                                freeDates.get(i).add(addedDateStr);

                                // Remove the matched date from the list
                                dates.remove(j);
                                break;
                            }
                        }
                        if (!dateFound) {
                            System.out.println("Date " + date + " not found for ID " + id);
                        }
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println("No match found for ID " + id);
            }

            // After all updates, clear the file and rewrite the data
            updateFreeDates(freeDates, object.getAllBookedDates(), object.getAllBudgets());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }


    public static void updateFreeDates(List<List<String>> freeDates, List<List<String>> bookedDates, List<String> allBudgets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sp_price_dates.txt"))) {
            for (int i = 0; i < freeDates.size(); i++) {
                writer.write("");

                writer.write(allBudgets.get(i));
                writer.newLine();

                List<String> dates = freeDates.get(i);
                for (String date : dates) {
                    writer.write(date + " ");
                }
                writer.newLine();

                List<String> booked = bookedDates.get(i);
                if (!booked.isEmpty()) {
                    for (String bookedDate : booked) {
                        writer.write(bookedDate + " ");
                    }
                    writer.newLine();
                }

                writer.write("***");
                writer.newLine();
            }

            System.out.println("Free dates updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating free dates: " + e.getMessage());
        }
    }


    public static boolean changeEventStatus(String eventId, String statusChange, String date) {
        // Load events from file into a list
        EventData events = new EventData();

        // Find and remove the event with the specified ID and date
        Event eventToRemove = null;
        for (Event event : events.getEventsList()) {
            if (event.getSP().getId().equals(eventId) && event.getDate().equals(date)) {
                eventToRemove = event;
                break;
            }
        }
        if (eventToRemove != null) {
            events.getEventsList().remove(eventToRemove);
        } else {
            System.out.println("Event with ID " + eventId + " and date " + date + " not found.");
            return false;
        }

        // Modify the status of the removed event
        switch (statusChange.toUpperCase()) {
            case "APPROVED":{
                eventToRemove.setStatus(Event.Status.APPROVED);
                matchIdWithDates(eventId,date);
                String recipientEmail = "s12113094@stu.najah.edu";
                String subject = "You have a new event!";
                String messageContent = "You have a new event for the provider with ID: " + eventId;
                sendEmail(recipientEmail, subject, messageContent);
                break;
            }
            case "DECLINED":{
                eventToRemove.setStatus(Event.Status.DECLINED);
                String recipientEmail = "s12113094@stu.najah.edu";
                String subject = "Event Declined";
                String messageContent = "Your event with the provider with ID: " + eventId + " has been declined.";
                sendEmail(recipientEmail, subject, messageContent);
                break;
            }
            default:
                System.out.println("Invalid status change. Please enter 'Approved' or 'Declined'.");
                return false;
        }

        // Add the modified event back to the list
        events.getEventsList().add(eventToRemove);

        // Write the updated events list back to the file
        try (FileWriter fw = new FileWriter("DataForEvents.txt");
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Event event : events.getEventsList()) {
                out.println(event.serialize());
            }
            System.out.println("\u001B[32mEvent information updated successfully.\u001B[0m");
            return true;
        } catch (IOException e) {
            System.err.println("Error updating event information: " + e.getMessage());
        }
        return false;
    }


    public static void sendEmail(String recipientEmail, String subject, String messageContent) {
        String senderEmail = "raghadmoh.tha@gmail.com";
        String password = "ydfa jwou trps uxqw";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(messageContent);
            Transport.send(message);

            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            System.out.println("Error occurred while sending email: " + e.getMessage());
        }
    }


}



