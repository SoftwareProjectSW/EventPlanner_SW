package app;
import DataB.EventData;
import DataB.SuperSPData;
import org.example.EmailConfig;
import org.example.Event;
import org.example.Main;
import org.example.ServiceProviderClass;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
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
                pendingEventsCount++; }
        }
        logger.info("Total number of pending events: " + pendingEventsCount + "\n");
        return true;
    }
    public static void matchIdWithDates(String id, String date) {
        readSPData("sp_price_dates.txt");
        SuperSPData object = new SuperSPData();
        List<List<String>> freeDates = object.getAllFreeDates();
        List<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();
        boolean found = Main.findServiceProviderAndPrintDetails(id, date, freeDates, serviceProviderList, object);
        if (!found) {
            logger.info("No match found for ID " + id + "\n");
        }
        updateFreeDates(freeDates, object.getAllBookedDates(), object.getAllBudgets());
    }
    public static void printServiceProviderDetails(ServiceProviderClass serviceProvider) {
        logger.info("\u001B[34mName: " + serviceProvider.getName() + "\u001B[0m" + "\n"); // Blue color for name
        logger.info("ID: " + serviceProvider.getId() + "\n");
        logger.info("Email: " + serviceProvider.getEmail() + "\n");
        logger.info("Services: " + "\n");
        for (String service : serviceProvider.getServicesList()) {
            logger.info(service + " " + "\n");
        }
        logger.info("\nFree Dates: " + "\n");
    }

    public static boolean processFreeDates(String id, String date, List<List<String>> freeDates, int serviceProviderIndex, SuperSPData object) {
        if (serviceProviderIndex < freeDates.size()) {
            List<String> dates = freeDates.get(serviceProviderIndex);
            return findAndProcessDate(id, date, dates, serviceProviderIndex, object);
        }
        return false;
    }
    public static boolean findAndProcessDate(String id, String date, List<String> dates, int serviceProviderIndex, SuperSPData object) {
        boolean dateFound = false;
        for (int j = 0; j < dates.size(); j++) {
            String d = dates.get(j);
            if (d.equals(date)) {
                dateFound = true;
                processMatchedDate(id, date, dates, j, serviceProviderIndex, object);
                break;  }
        }
        if (!dateFound) {
            logger.info("Date " + date + " not found for ID " + id + "\n");
        }
        return dateFound;
    }
    private static void processMatchedDate(String id, String date, List<String> dates, int dateIndex, int serviceProviderIndex, SuperSPData object) {
        logger.info("Match found for date " + dates.get(dateIndex) + " at index " + dateIndex + "\n");
        object.getAllBookedDates().get(serviceProviderIndex).add(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate deletedDate = LocalDate.parse(date, formatter);
        LocalDate addedDate = deletedDate.plusDays(7);
        String addedDateStr = addedDate.format(formatter);
        dates.add(addedDateStr);
        dates.remove(dateIndex);
    }
    public static boolean updateFreeDates(List<List<String>> freeDates, List<List<String>> bookedDates, List<String> allBudgets) {
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
                writer.write("*" + "\n");
            }
            logger.info("Free dates updated successfully." + "\n");
        } catch (Exception e) {
            logger.severe("Error updating free dates: " + e.getMessage()+'\n');
            return false;
        }
        return true;
    }
    public static void sendEmail(String recipientEmail, String subject, String messageContent) {
        String senderEmail = EmailConfig.getSenderEmail();
        String password = EmailConfig.getPassword();
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
         } catch (MessagingException e) {
            logger.severe("Error occurred while sending email: " + e.getMessage() + "\n");
            logger.log(Level.SEVERE, "there is error ", e);}}
}