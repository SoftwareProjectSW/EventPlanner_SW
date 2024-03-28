package app;

import org.example.Event;
import org.example.ServiceProviderClass;
import org.example.VenueClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.Event.writeEventToFile;

public class packageDetails {
    public boolean theUserIsNotifiedOfTheBudgetInvalid1(int price) throws IOException {
        System.out.println("\u001B[31mThe budget is not valid. Please make sure that you have enough budget,and you can choose one of this packages\u001B[0m");
        printSuitablePackage1(price);
        return true;
    }

    public List<Integer> printSuitablePackage1(int budgetNumber) throws IOException {
        List<Integer> serialNumbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("packageFile.txt"))) {
            String line;
            int serialNumber = 1;
            while ((line = br.readLine()) != null) {
                int budget;
                try {
                    budget = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    continue;
                }
                String providerDetails = br.readLine();
                if (providerDetails == null) {
                    break;
                }
                // Check if the budget is suitable
                if (budget <= budgetNumber) {
                    // Print package details
                    System.out.print(serialNumber++ + ")");
                    System.out.println("Suitable Package:");
                    System.out.println("Budget: " + budget);
                    String[] providerData = providerDetails.split(",");
                    String name = providerData[0];
                    String id = providerData[1];
                    String email = providerData[2];
                    ServiceProviderClass serviceProvider = new ServiceProviderClass(name, id, email);
                    List<String> services = new ArrayList<>();
                    for (int i = 3; i < providerData.length; i++) {
                        services.add(providerData[i]);
                    }
                    serviceProvider.setServicesListPackage((ArrayList<String>) services);
                    System.out.println("Provider Name: " + name);
                    System.out.println("ID: " + id);
                    System.out.println("Email: " + email);
                    System.out.println("Services: " + serviceProvider.getServicesListPackage());
                    String date = br.readLine();
                    System.out.println("Date: " + date);
                    String venue = br.readLine();
                    System.out.println("Venue: " + venue.split(",")[0]); // Assuming venue is comma-separated
                    System.out.println();
                    serialNumbers.add(serialNumber - 1);
                }
                // Skip the package separator
                String separator = br.readLine();
            }
        }
        return serialNumbers;
    }

    public boolean theSystemShouldDisplayTheFullInformationAboutThePackage(int targetSerialNumber, int budgetNumber, String em) throws IOException {
        List<Integer> serialNumbers = printSuitablePackage1(budgetNumber);
        if (serialNumbers.contains(targetSerialNumber)) {
            try (BufferedReader br = new BufferedReader(new FileReader("packageFile.txt"))) {
                String line;
                int serialNumber = 1;
                while ((line = br.readLine()) != null) {
                    // Parse the budget
                    String budget;
                    try {
                        budget = String.valueOf(Integer.parseInt(line.trim()));
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    String providerDetails = br.readLine();
                    if (providerDetails == null) {
                        break;
                    }
                    String[] providerData = providerDetails.split(",");
                    if (providerData.length < 3) {
                        continue;
                    }
                    String name = providerData[0];
                    String id = providerData[1];
                    String email = providerData[2];
                    ServiceProviderClass serviceProvider = new ServiceProviderClass(name, id, email);
                    List<String> services = new ArrayList<>();
                    for (int i = 3; i < providerData.length; i++) {
                        services.add(providerData[i]);
                    }
                    serviceProvider.setServicesListPackage((ArrayList<String>) services);
                    String date = br.readLine();
                    String venue = br.readLine();
                    String separator = br.readLine(); // Store the value returned by readLine()
                    if (venue == null) {
                        break;
                    }
                    String[] venueData = venue.split(",");
                    if (venueData.length < 1) {
                        continue;
                    }
                    venue = venueData[0];
                    if (serialNumber == targetSerialNumber) {
                        // Print package details
                        System.out.println("\u001B[33mConfirmation Details:\u001B[0m");
                        System.out.println("\u001B[33mthe event is for user: " + em + "\u001B[0m");
                        System.out.println("\u001B[33mService Provider's Name: " + name + "\u001B[0m");
                        System.out.println("\u001B[33mProvider's ID: " + id + "\u001B[0m");
                        System.out.println("\u001B[33mEmail: " + email + "\u001B[0m");
                        System.out.println("\u001B[33mServices: " + serviceProvider.getServicesListPackage() + "\u001B[0m");
                        System.out.println("\u001B[33mPrice: " + budget + "\u001B[0m");
                        System.out.println("\u001B[33mDate: " + date + "\u001B[0m");
                        System.out.println("\u001B[33mVenue choosed: " + venue + "\u001B[0m");
                        System.out.println();
                        String venName = VenueClass.extractName(venue);
                        Event event = new Event(em, name, id, email, serviceProvider.getServicesListPackage(), venName, date, budget);
                        writeEventToFile(event);
                        return true;
                    }
                    serialNumber++;
                }
            }
        } else {
            System.out.println("\u001B[31mPackage with serial number " + targetSerialNumber + " not found or does not match the budget.\u001B[0m");
            return false;
        }
        return false;
    }
}
