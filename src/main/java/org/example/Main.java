package org.example;

import APP.LogInAsAdmin;

//import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String email;
        String password;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            LogInAsAdmin app=new LogInAsAdmin();
            //  do {
            System.out.print("Enter your email: ");
            email = scanner.nextLine().trim();

            //  } while (email.isEmpty());

            //  do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine().trim();
            //  } while (password.isEmpty());

            app.loggInCheck(email,password);
            if(app.isLoggedIn())
                System.out.println("successfully login ");


            else app.errorInLogin(email,password);

            // Ask if the user wants to exit
            System.out.print("Do you want to exit the program? (yes/no): ");
            String exitChoice = scanner.nextLine().trim().toLowerCase();
            if (exitChoice.equals("yes")) {
                break; // Exit the loop and end the program
            }
            scanner.nextLine();

        }

        // Close the scanner
        scanner.close();
    }}
