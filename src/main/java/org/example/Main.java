package org.example;

import APP.*;
import DataB.AdminData;
import org.example.AdminClass;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void displaying(){
        logger.info("|________________________________________________________|\n");
    }
    public static void display(){
        logger.info("__________________________________________________________\n");
    }
    public static void displayed(){
        logger.info("|____________________________________________________|\n");
    }
    public static void displayline(){
        logger.info(" ____________________________________________________ \n");
    }
    public static void displayupline(){logger.info("____________________________________________________\n");}
    public static void displaydownline(){logger.info("|__________________________________________________|\n");}
    public static void displayuptallline(){logger.info("____________________________________________________________________\n");}
    public static void displaydowntallline(){logger.info("|____________________________________________________________________|\n");}
    public static void displayEnterValidNumber(){ logger.warning("|             Please Enter valid number :)         |\n");}

    private static final Logger logger = LoggerUtility.getLogger();
    private static Scanner in = new Scanner(System.in);

    public static void menu(){
        displayupline();
        logger.info("|       Welcome to Event Planner Services System :)     |\n");
        logger.info("| 1- If you want to login as an admin                |\n");
        logger.info("| 2- If you want to login as an user                |\n");
        logger.info("| 3- If you want to login as an service provider               |\n");
        logger.info("|___________________________________________________|\n");
    }

    public static void main(String[] args) {
        int option = 0;
        String email;
        String password;
        while(true) {
            try {
                menu();
                option = in.nextInt();
            } catch (InputMismatchException e) {
                displayupline();
                displayEnterValidNumber();
                displaydownline();
                in.nextLine();
                continue;
            }

            displayupline();
            logger.warning("|             WELCOME TO LOGIN PAGE                |\n");
            displaydownline();

            logger.info("Enter your email:");
            email = in.next();
            logger.info("Enter your password:");
            password = in.next();

            if (option == 1) {
                LogInAsAdmin adminApp = new LogInAsAdmin();
                AdminClass admin = new AdminClass(email ,password) ;
                adminApp.loggInCheck(email,password);
                if (adminApp.isLoggedIn()){
                    for(AdminClass a : AdminData.getAdminList()){
                        if(a.getEmail().equals(email)){
                            admin = a;
                        }
                    }
                    logger.info("WELCOME Admin "+ admin.getEmail()+ "\n");
                    while (true) {
                        displayupline();
                        logger.info("|       Welcome to Admin page :)                   |\n");
                        logger.info("| 1- Add new Super Provider                                    |\n");
                        logger.info("| 2- view information about service provider                                       |\n");
                        logger.info("| 3-Delete                                  |\n");
                        logger.info("| 4- Log Out                                       |\n");
                        displaydownline();
                        int optionadmin = in.nextInt();
                        if(optionadmin==1){
                            addSP();
                        }
                       else if (optionadmin == 2) {
                            viewServiceProviderOptions();
                        }
                       else if(optionadmin==3){
                           deleteSp();
                        }
                        else if(optionadmin==4)
                            break;
                           // System.exit(0);//may set it as break to return to main role menu , and clear the commandline
                    }
                }
            }
        }
    }

    public static void viewServiceProviderOptions() {
        while (true) {
            displayupline();
            logger.info("|       Welcome to service provider Options :)             |\n");
            logger.info("| 1- show all service providers                               |\n");
            logger.info("| 2- Back                              |\n");
            displaydownline();
            int option = in.nextInt();
            if (option == 1) {
                viewInfoSP op1=new viewInfoSP();
                op1.theAdminCanViewDetailsSuchAsNameContactInformationAndServicesOffered();
                logger.info("|  Please enter the name of the service provider you want to show the details  :)  |\n");
                String serviceProviderName = in.next();
               op1.theSystemDisplaysAListOfServiceProvidersWithTheirDetails(serviceProviderName);
                displayed();
            } else if (option == 2) {
                break;
            } else {
                displayline();
                displayEnterValidNumber();
                displayed();
            }
        }
    }

    public static void addSP(){
        boolean flag=true;
        do {
            System.out.println("please enter service provider full information with the following format:" +
                    "name,id,username@gmail.com,services ");
            Scanner line = new Scanner(System.in);
            String Line = line.nextLine();
            AddSP_App A = new AddSP_App(Line);
            if (A.getIsSPLineValide()) {
                flag=false;
                A.addLineToFile(Line);
            } else System.out.println("Invalid input!!!");
        }while(flag);

    }
    public static void deleteSp(){
        while (true) {
            DeleteSP_App delete=new DeleteSP_App();
            Scanner in=new Scanner(System.in);
            Scanner n=new Scanner(System.in);

            displayupline();
            logger.info("| 1- delete by name                           |\n");
            logger.info("| 2- delete by id              |\n");
            logger.info("| 3- exit           |\n");
            displaydownline();
            int option = in.nextInt();
            if (option == 1) {
               String name= n.nextLine();
delete.removeServiceProviderByName(name);
            }
            else if(option==2){
                String id= n.nextLine();
                delete.removeServiceProviderById(id);
            }
            else if (option == 3) {
                break;
            } else {
                displayline();
                displayEnterValidNumber();
                displayed();
            }
        }
    }
}
