package app;

import DataB.ServiceProviderData;

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

  

    public void setAlredyExists(boolean alredyExists) {
        isAlredyExists = alredyExists;
    }

    public AddSP_App(){

    }
    public AddSP_App(String inputLine) {
        this.inputLine = inputLine;
        this.isSPLineValide = isValidLine(inputLine);
    }


    public boolean getIsSPLineValide() {
        return isSPLineValide;
    }


    public void setSP_Added(boolean SP_Added) {
        isSP_Added = SP_Added;
    }

    public  boolean isValidLine(String line) {
        String[] fields = line.split(",");

        if (fields.length < 4) {
            System.out.println("few fields, please enter complete information !");
            return false;
        }

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

        if (!fields[2].contains("@") || !fields[2].endsWith(".com")) {
            System.out.println("make sure to enter an valid email format such as username@gmale.com !");
            return false;
        }

        return true;
    }

    public  void addLineToFile(String line) {
        ServiceProviderData sp = new ServiceProviderData();

        String filename = "DataForSP.txt";
        if (isValidLine(line)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String fileLines;
                Set<String> ids = new HashSet<>();

                while ((fileLines = reader.readLine()) != null) {

                    String[] fields = fileLines.split(",");
                    if (fields.length >= 2) {
                        ids.add(fields[1]); 
                    }
                }

                String[] newFields = line.split(",");
                if (!ids.contains(newFields[1])) {
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


  

    public void showMessageToEnterFullDetails(){
        JOptionPane.showMessageDialog(null,"please provide all required information "
                , "ERROR",JOptionPane.ERROR_MESSAGE);

    }

    

}
