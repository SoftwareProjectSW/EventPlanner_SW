package DataB;

import app.LoggerUtility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SuperSPData {
    private static final Logger logger = LoggerUtility.getLogger();

    public   String SPFile="sp_price_dates.txt";

    private static List<String> allBudgets = new ArrayList<>();
    private static List<List<String>> allFreeDates = new ArrayList<>();
    private static List<List<String>> allBookedDates = new ArrayList<>();
    public  int findMinValue(List<String> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("List is empty or null");
        }
        int min = parseBudget(values.get(0)); for (String value : values) { int intValue = parseBudget(value);   if (intValue < min) {    min = intValue;
            }
        }   return min;
    }

    private static int parseBudget(String budget) {
        return Integer.parseInt(budget.replace("$", ""));
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
            processServiceData(allBudgets, allFreeDates, allBookedDates, budgetLine, freeDateLine, bookedDateLine);

        } catch (IOException e) {  logger.log(Level.SEVERE, "there is error ", e);

        
    }}

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
