package APP;

import DataB.AdminData;
import DataB.ServiceProviderData;
import io.cucumber.java.sl.In;
import org.example.AdminClass;
import org.example.ServiceProviderClass;
import javax.swing.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class AddSP_App {
    private String inputLine;

    private boolean isSPLineValide ;
    private boolean isSP_Added ;
    private boolean isAlredyExists;

    public boolean isAlredyExists() {
        return isAlredyExists;
    }

    public void setAlredyExists(boolean alredyExists) {
        isAlredyExists = alredyExists;
    }

    public AddSP_App(){

    }
    public AddSP_App(String inputLine) {
        this.inputLine = inputLine;
        this.isSPLineValide = isValidLine(inputLine);
    }

    public String getInputLine() {
        return inputLine;
    }

    public boolean getIsSPLineValide() {
        return isSPLineValide;
    }

    public boolean isSP_Added() {
        return isSP_Added;
    }

    public void setSP_Added(boolean SP_Added) {
        isSP_Added = SP_Added;
    }

    // Method to check if the input line matches the required format
    public  boolean isValidLine(String line) {
        // Split the line by comma to extract fields
        String[] fields = line.split(",");

        // Check if the line has exactly 4 fields
        if (fields.length < 4) {
            System.out.println("few fields, please enter complete information !");
            return false;
        }

        // Check if the second field (unique id) is an integer
        try {
            Integer.parseInt(fields[1]);
        } catch (NumberFormatException e) {
            System.out.println("make sure to enter an Integer ID !");
            return false;
        }

        if (Integer.parseInt(fields[1]) < 0){
            System.out.println("make sure to enter a POSITIVE ID !");
            return false;
        }

        // Check if the email field (third field) has "@" and ends with ".com"
        if (!fields[2].contains("@") || !fields[2].endsWith(".com")) {
            System.out.println("make sure to enter an valid email format such as username@gmale.com !");
            return false;
        }

        // If all conditions pass, return true
        return true;
    }

    // Method to add a line to the file if it meets the required conditions
    public  void addLineToFile(String line) {
        ServiceProviderData sp = new ServiceProviderData();

        String filename = "DataForSP.txt";
        // Check if the line is valid
        if (isValidLine(line)) {
            // Check if the unique id is already present in the file
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String fileLines;
                Set<String> ids = new HashSet<>();

                // Read each line and extract unique ids
                while ((fileLines = reader.readLine()) != null) {

                    String[] fields = fileLines.split(",");
                    if (fields.length >= 2) {
                        ids.add(fields[1]); // Add unique ids to the set
                    }
                    // Add unique ids to the set
                }

                // Check if the unique id of the new line is already present
                String[] newFields = line.split(",");
                if (!ids.contains(newFields[1])) {
                    // Append the line to the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                        writer.write(line);
                        writer.newLine();

                        setAlredyExists(false);
                        setSP_Added(true);
                        System.out.println("Line added successfully!");

                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                } else {
                    setSP_Added(false);
                    setAlredyExists(true);
                    System.err.println("Error: Unique ID already exists!");
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        } else {
            System.err.println("Error: Invalid line format!");
            showMessageToEnterFullDetails();
        }
    }


    public void showMessage(){
        if (isSP_Added) JOptionPane.showMessageDialog(null,"added the service provider with info: " +
                inputLine+ " successfully", "SUCCESS",JOptionPane.INFORMATION_MESSAGE
        );
        else JOptionPane.showMessageDialog(null,"the service provider with info: " +
                inputLine+" Already Exists", "FAIL",JOptionPane.WARNING_MESSAGE);
    }

    public void showMessageToEnterFullDetails(){
        JOptionPane.showMessageDialog(null,"please provide all required information "
                , "ERROR",JOptionPane.ERROR_MESSAGE);

    }


    public void enterLineUntillValid(){
        Scanner scanner = new Scanner(System.in);

        while (!isSPLineValide){

            this.inputLine = scanner.nextLine();
            this.isSPLineValide = isValidLine(inputLine);

        }
        addLineToFile(inputLine);


    }



}
