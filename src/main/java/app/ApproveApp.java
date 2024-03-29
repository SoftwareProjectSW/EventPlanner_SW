package app;

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
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import static app.EnteredBudget.serviceProviderData;
import static DataB.SuperSPData.readSPData;

public class ApproveApp {
    private static final Logger logger = LoggerUtility.getLogger();


    public static boolean aListOfPendingEventsAwaitingApproval() {
        EventData events = new EventData();
        int pendingEventsCount = 0;
        for (Event event : events.getEventsList()) {
            if (event.getStatus() == Event.Status.NOT_SEEN) {
                logger.info(event.toString() + "\n");
                pendingEventsCount++;
            }
        }
        logger.info("Total number of pending events: " + pendingEventsCount + "\n");
        return true;
    }



    public static boolean selectsAnEventToReview(String i) {
        EventData events = new EventData();
        for (Event event : events.getEventsList()) {
            if (event.getSP().getId().equals(i) ) { // Check if the ID matches
                logger.info(event.serialize() + "\n");
                return true; // Exit the loop after printing the event
            }
        }
        logger.info("Event with ID " + i + " not found." + "\n");
        return false;
    }

  

    public static void matchIdWithDates(String id, String date) {
        readSPData("sp_price_dates.txt");
        SuperSPData object = new SuperSPData();
        List<List<String>> freeDates = object.getAllFreeDates();
        List<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();

        boolean found = findServiceProviderAndPrintDetails(id, date, freeDates, serviceProviderList, object);

        if (!found) {
            logger.info("No match found for ID " + id + "\n");
        }

        updateFreeDates(freeDates, object.getAllBookedDates(), object.getAllBudgets());
    }

    private static boolean findServiceProviderAndPrintDetails(String id, String date, List<List<String>> freeDates, List<ServiceProviderClass> serviceProviderList, SuperSPData object) {
        try {
            for (int i = 0; i < serviceProviderList.size(); i++) {
                ServiceProviderClass serviceProvider = serviceProviderList.get(i);
                if (serviceProvider.getId().equals(id)) {
                    printServiceProviderDetails(serviceProvider);
                    return processFreeDates(id, date, freeDates, i, object);
                }
            }
        } catch (Exception e) {
            logger.severe("Error occurred: " + e.getMessage() + "\n");
        }
        return false;
    }

    private static void printServiceProviderDetails(ServiceProviderClass serviceProvider) {
        logger.info("\u001B[34mName: " + serviceProvider.getName() + "\u001B[0m" + "\n"); // Blue color for name
        logger.info("ID: " + serviceProvider.getId() + "\n");
        logger.info("Email: " + serviceProvider.getEmail() + "\n");
        logger.info("Services: " + "\n");
        for (String service : serviceProvider.getServicesList()) {
            logger.info(service + " " + "\n");
        }
        logger.info("\nFree Dates: " + "\n");
    }

    private static boolean processFreeDates(String id, String date, List<List<String>> freeDates, int serviceProviderIndex, SuperSPData object) {
        if (serviceProviderIndex < freeDates.size()) {
            List<String> dates = freeDates.get(serviceProviderIndex);
            return findAndProcessDate(id, date, dates, serviceProviderIndex, object);
        }
        return false;
    }

    private static boolean findAndProcessDate(String id, String date, List<String> dates, int serviceProviderIndex, SuperSPData object) {
        boolean dateFound = false;
        for (int j = 0; j < dates.size(); j++) {
            String d = dates.get(j);
            if (d.equals(date)) {
                dateFound = true;
                processMatchedDate(id, date, dates, j, serviceProviderIndex, object);
                break;
            }
        }
        if (!dateFound) {
            logger.info("Date " + date + " not found for ID " + id + "\n");
        }
        return dateFound;
    }

    private static void processMatchedDate(String id, String date, List<String> dates, int dateIndex, int serviceProviderIndex, SuperSPData object) {
        logger.info("Match found for date " + dates.get(dateIndex) + " at index " + dateIndex + "\n");
        // Add the deleted date plus 7 days to booked dates
        object.getAllBookedDates().get(serviceProviderIndex).add(date);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate deletedDate = LocalDate.parse(date, formatter);
        LocalDate addedDate = deletedDate.plusDays(7);
        String addedDateStr = addedDate.format(formatter);
        dates.add(addedDateStr);

        dates.remove(dateIndex);
    }


    public static void updateFreeDates(List<List<String>> freeDates, List<List<String>> bookedDates, List<String> allBudgets) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sp_price_dates.txt"))) {
            for (int i = 0; i < freeDates.size(); i++) {
                writer.write(allBudgets.get(i) + "\n");

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

                writer.write("***" + "\n");
            }

            logger.info("Free dates updated successfully." + "\n");
        } catch (IOException e) {
            logger.severe("Error updating free dates: " + e.getMessage() + "\n");
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
            logger.warning("Event with ID " + eventId + " and date " + date + " not found." + "\n");
            return false;
        }

        // Modify the status of the removed event
        if(statusChange.toUpperCase().equals("APPROVED"))
        switch (statusChange.toUpperCase()) {
            case "APPROVED":{
                eventToRemove.setStatus(Event.Status.APPROVED);
                matchIdWithDates(eventId,date);
                String recipientEmail = "s12113094@stu.najah.edu";
                String subject = "You have a new event!";
                String messageContent = "You have a new event for the provider with ID: " + eventId;
                sendEmail(recipientEmail, subject, messageContent);
break;            }
            case "DECLINED":{
                eventToRemove.setStatus(Event.Status.DECLINED);
                String recipientEmail = "s12113094@stu.najah.edu";
                String subject = "Event Declined";
                String messageContent = "Your event with the provider with ID: " + eventId + " has been declined.";
                sendEmail(recipientEmail, subject, messageContent);

break;
            }
            default:
                logger.warning("Invalid status change. Please enter 'Approved' or 'Declined'." + "\n");
                return false;
        }

        events.getEventsList().add(eventToRemove);

        try (FileWriter fw = new FileWriter("DataForEvents.txt");
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Event event : events.getEventsList()) {
                out.println(event.serialize());
            }
            logger.info("\u001B[32mEvent information updated successfully.\u001B[0m" + "\n");
            return true;
        } catch (IOException e) {
            logger.severe("Error updating event information: " + e.getMessage() + "\n");
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

            logger.info("Email sent successfully to " + recipientEmail + "\n");
        } catch (MessagingException e) {
            logger.severe("Error occurred while sending email: " + e.getMessage() + "\n");
        }
    }


}
