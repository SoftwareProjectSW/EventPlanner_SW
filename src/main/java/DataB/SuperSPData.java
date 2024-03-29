package DataB;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class SuperSPData {

    public   String SPFile="sp_price_dates.txt";

    private static List<String> allBudgets = new ArrayList<>();
    private static List<List<String>> allFreeDates = new ArrayList<>();
    private static List<List<String>> allBookedDates = new ArrayList<>();
    public  int findMinValue(List<String> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("List is empty or null");
        }
        int min = parseBudget(values.get(0));
        for (String value : values) {
            int intValue = parseBudget(value);
            if (intValue < min) {
                min = intValue;
            }
        }
        return min;
    }

    private static int parseBudget(String budget) {
        return Integer.parseInt(budget.replace("$", ""));
    }
    public static void main(String[] args) throws IOException {
      //  SuperSPData s=new SuperSPData();
      //  readSPData(s.SPFile);
      //  int minValue = findMinValue(s.getAllBudgets());
      //  System.out.println("Minimum value: " + minValue);


    }

    public static void readSPData(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String budgetLine = "";
            String freeDateLine = "";
            String bookedDateLine = "";

            while ((line = br.readLine()) != null) {
                if (line.equals("***")) {
                    // Process the stored data
                    processServiceData(allBudgets, allFreeDates, allBookedDates, budgetLine, freeDateLine, bookedDateLine);

                    // Reset lines for next service provider
                    budgetLine = "";
                    freeDateLine = "";
                    bookedDateLine = "";
                } else {
                    // Accumulate data for current service provider
                    if (budgetLine.isEmpty()) {
                        budgetLine = line;
                    } else if (freeDateLine.isEmpty()) {
                        freeDateLine = line;
                    } else if (bookedDateLine.isEmpty()) {
                        bookedDateLine = line;
                    }
                }
            }

            // Process the last service provider data
            processServiceData(allBudgets, allFreeDates, allBookedDates, budgetLine, freeDateLine, bookedDateLine);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "there is error ", e);
        }

        // Print out the data for demonstration

    }

    private static void processServiceData(List<String> allBudgets, List<List<String>> allFreeDates, List<List<String>> allBookedDates,
                                           String budgetLine, String freeDateLine, String bookedDateLine) {
        if (!budgetLine.isEmpty() && !freeDateLine.isEmpty() && !bookedDateLine.isEmpty()) {
            allBudgets.add(budgetLine);
            allFreeDates.add(parseDateLine(freeDateLine));
            allBookedDates.add(parseDateLine(bookedDateLine));
        }
    }

    private static List<String> parseDateLine(String line) {
        String[] dateParts = line.trim().split("\\s");
        List<String> dates = new ArrayList<>();
        for (String datePart : dateParts) {
            dates.add(datePart);
        }
        return dates;
    }
  /*  public void updateFreeDates(List<List<String>> freeDates) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("sp_price_dates.txt"));
SuperSPData object=new SuperSPData();
            // Loop through all budget, free dates, and booked dates
            List<String> allBudgets = object.getAllBudgets();
            List<List<String>> allFreeDates = object.getAllFreeDates();
            List<List<String>> allBookedDates = object.getAllBookedDates();

            // Rewrite the file with the updated free dates
            for (int i = 0; i < allBudgets.size(); i++) {
                String budget = allBudgets.get(i);
                writer.write(budget);
                writer.newLine();

                List<String> dates = allFreeDates.get(i);
                for (String date : dates) {
                    writer.write(date + " ");
                }
                writer.newLine();

                writer.write("***");
                writer.newLine();
            }

            // Write booked dates
            for (List<String> bookedDates : allBookedDates) {
                for (String date : bookedDates) {
                    writer.write(date + " ");
                }
                writer.newLine();
            }

            writer.close();
            System.out.println("Free dates updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating free dates: " + e.getMessage());
        }
    }*/
    public List<String> getAllBudgets() {
        return allBudgets;
    }

    public List<List<String>> getAllFreeDates() {
        return allFreeDates;
    }

    public List<List<String>> getAllBookedDates() {
        return allBookedDates;
    }

}
