package org.example;

import app.*;
import DataB.*;
import io.cucumber.java.bs.A;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static DataB.SuperSPData.readSPData;
import static org.example.Event.writeEventToFile;
import static org.example.SuperSPClass.serviceProviderData;


public class Main {
  static   boolean displayMainMenu = true;
  static   boolean displayUserMenu = true;

    static String email =null;

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
    SuperSPData booking_obj=new SuperSPData();

    public static void menu(){
        displayupline();
        logger.info("|       Welcome to Event Planner Services System :)     \n");
        logger.info("| 1- If you want to login as an admin                \n");
        logger.info("| 2- If you want to login as an user                \n");
        logger.info("| 3- If you want to login as an organizer               \n");
        logger.info("|___________________________________________________|\n");
    }

    public static boolean aListOfApprovedEvents() {
        EventData events = new EventData();
        int pendingEventsCount = 0;
        for (Event event : events.getEventsList()) {
            if (event.getStatus() == Event.Status.APPROVED) {
                logger.info(event.toString() + "\n");
                pendingEventsCount++;
            }
        }
        logger.info("Total number of Upcoming events: " + pendingEventsCount + "\n");
        return true;
    }
    public static boolean changeEventStatus(String eventId, String statusChange, String date) {
        ApproveApp app=new ApproveApp();
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
        if (statusChange.toUpperCase().equals("APPROVED")) {

            eventToRemove.setStatus(Event.Status.APPROVED);
            ApproveApp.matchIdWithDates(eventId,date);
            String recipientEmail = "s12113094@stu.najah.edu";
            String subject = "You have a new event!";
            String messageContent = "You have a new event for the provider with ID: " + eventId;
            ApproveApp.sendEmail(recipientEmail, subject, messageContent);
        }
        else if(statusChange.toUpperCase().equals("DECLINED")){
            eventToRemove.setStatus(Event.Status.DECLINED);
            ApproveApp.matchIdWithDates(eventId,date);
            String recipientEmail = "s12113094@stu.najah.edu";
            String subject = "Event Declined";
            String messageContent = "Your event with the provider with ID: " + eventId + " has been declined.";
            ApproveApp.sendEmail(recipientEmail, subject, messageContent);

        }
        else{
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


    public static void main(String[] args) throws IOException {

      //  readSPData("sp_price_dates.txt");
        int option = 0;
        String password = null;
       //  boolean displayMainMenu = true;
         boolean promptForCredentials = true;
      //   boolean displayUserMenu = true;
         boolean displayPackageMenu=true;
        boolean displayOrgMenu = true;


        while (true) {
            try {
                if (displayMainMenu) {
                    menu();
                    option = in.nextInt();
                }

            } catch (InputMismatchException e) {
                displayupline();
                displayEnterValidNumber();
                displaydownline();
                in.nextLine();
                continue;
            }

            if (promptForCredentials) {
                displayupline();
                logger.warning("|             WELCOME TO LOGIN PAGE                \n");
                displaydownline();

                logger.info("Enter your email:");
                email = in.next();
                logger.info("Enter your password:");
                password = in.next();
            }

            if (option == 1) {
                LogInAsAdmin adminApp = new LogInAsAdmin();
                AdminClass admin = new AdminClass(email, password);
                adminApp.loggInCheck(email, password);
                if (adminApp.isLoggedIn()) {
                    for (AdminClass a : AdminData.getAdminList()) {
                        if (a.getEmail().equals(email)) {
                            admin = a;
                        }
                    }
                    logger.info("WELCOME Admin " + admin.getEmail() + "\n");
                    while (true) {
                        displayupline();
                        logger.info("|       Welcome to Admin page :)                   \n");
                        logger.info("| 1- Add new Super Provider                                    \n");
                        logger.info("| 2- view information about service provider                                       |\n");
                        logger.info("| 3-Delete                                  \n");
                        logger.info("| 4- Log Out                                       \n");
                        displaydownline();
                        int optionadmin = in.nextInt();
                        if (optionadmin == 1) {
                            addSP();
                        } else if (optionadmin == 2) {
                            viewServiceProviderOptions();
                        } else if (optionadmin == 3) {
                            deleteSp();
                        } else if (optionadmin == 4) {
                            break;
                        }
                    }
                }
            }
            else if (option == 2) {
                LogInAsUser userApp = new LogInAsUser();
                UserClass user = new UserClass(email, password);
                userApp.loggInCheck(email, password);
                if (userApp.isLoggedIn()) {
                    for (UserClass a : UserData.getUserList()) {
                        if (a.getEmail().equals(email)) {
                            user = a;
                        }
                    }
                    while (true) {
                        if (displayUserMenu) {
                            logger.info("WELCOME Mr./Ms. " + user.getEmail() + "\n");
                            displayupline();
                            logger.info("|       Welcome to User page :)                   |\n");
                            logger.info("| 1- Book new Event                                    |\n");
                            logger.info("| 2- Log out                                      |\n");
                            displaydownline();
                         //   displayUserMenu = false;
                        }
                        int optionuser = in.nextInt();
                        if (optionuser == 1) {
                            Scanner n=new Scanner(System.in);
                            System.out.println("Please enter your available budget for booking the event with this format 0000$ : ");
                            String budget = n.nextLine();
                            SuperSPData o=new SuperSPData();
                            packageDetails op=new packageDetails();
                            readSPData(o.SPFile);
                            if( Integer.parseInt(budget.replace("$", ""))<=o.findMinValue(o.getAllBudgets())){
                                op.theUserIsNotifiedOfTheBudgetInvalid1(Integer.parseInt(budget.replace("$", "")));
                                displayPackageMenu=true;
                                System.out.println("\u001B[31myou can just choose one of these actions to continue:\u001B[0m");
                                if (displayPackageMenu) {
                                    displayupline();
                                    logger.info("| 1-choose one of the packages                                    \n");
                                    logger.info("| 2- retry booking with higher budget                                     \n");
                                    displaydownline();
                                 //   displayPackageMenu = false;
                                }
                                Scanner c=new Scanner(System.in);
                                int choose=c.nextInt();
                                if(choose==1){
                                   System.out.println("please enter the number of package you need");

                                    int numPackage=c.nextInt();
                                  boolean pa=  op.theSystemShouldDisplayTheFullInformationAboutThePackage(numPackage,Integer.parseInt(budget.replace("$", "")),email);
                                  if(pa==false){
                                      System.out.println("please enter the number of package you need");
                                      Scanner v=new Scanner(System.in);
                                      int reread=v.nextInt();
                                     op.theSystemShouldDisplayTheFullInformationAboutThePackage(reread,Integer.parseInt(budget.replace("$", "")),email);
                                  }
                                    displayUserMenu = true;

                                }
                          else if(choose==2){
                               displayUserMenu = true;
                               break;
                                       }

                            }
                            else {
                                bookingFunction(budget);
                                displayUserMenu = true;
                              //  displayMainMenu = false;
                                promptForCredentials = false;


                                break;
                            }

                          //  displayUserMenu=true;
                         //   break;
                        }
                        else if (optionuser == 2) {
                            displayMainMenu= true;
                          break;
                       //   break;
                        }

                    }

                }

            }


            else  if (option == 3) {
                LoginOrgApp orgApp = new LoginOrgApp();
                Organizer org = new Organizer(email, password);
                orgApp.loggInCheck(email, password);
                if (orgApp.isLoggedIn()) {
                    for (Organizer a : OrganizerData.getorganizersList()) {
                        if (a.getEmail().equals(email)) {
                            org = a;
                        }
                    }

                        logger.info("WELCOME Organizer " + org.getEmail() + "\n");
                    while (true) {
                 if(displayOrgMenu)       {
                        displayupline();
                        logger.info("|       Welcome to Organizer page :)                   \n");
                        logger.info("| 1- View Pinned Events                                  \n");
                        logger.info("| 2-add venue                                      |\n");
                        logger.info("| 3-View Upcoming Events                                \n");
                        logger.info("| 4- Log Out                                       \n");
                        displaydownline();
                    }
                        int optionadmin = in.nextInt();
                        if (optionadmin == 1) {
ApproveApp app=new ApproveApp();
ApproveApp.aListOfPendingEventsAwaitingApproval();
Scanner i=new Scanner(System.in);
System.out.println("\u001B[33mplease enter the id of SP that have events to review :\u001B[0m ");
String id=i.nextLine();
System.out.println("\u001B[33mplease enter the date of the evente also:\u001B[0m");
String dat=i.nextLine();
System.out.println("\u001B[33mplease write the new statue approved/decline:\u001B[0m");
String status=i.nextLine();
changeEventStatus(id,status,dat);


                        }
                        else if (optionadmin == 2) {
                            System.out.println("please enter the new venue name:");
                            Scanner in=new Scanner(System.in);
                            String name=in.nextLine();
                            System.out.println("please enter the new venue capacity:");
                            Scanner inn=new Scanner(System.in);
                            int capacity =inn.nextInt();
                            System.out.println("please enter the new venue price:");
                            Scanner i=new Scanner(System.in);
                            double price =i.nextDouble();
                            ReserveVenueApp op=new ReserveVenueApp();
                          String validOrNot=  op.isValidVenueDetails(name,capacity,price);
                          System.out.println(validOrNot);
                            op.isAddedVenue(name,capacity,price);
                            displayOrgMenu=true;
                        } else if (optionadmin == 3) {
                           //     ApproveApp app=new ApproveApp();
                                aListOfApprovedEvents();
                                displayOrgMenu=true;

                        } else if (optionadmin == 4) {
                            displayMainMenu=true;
                            break;
                        }
                    }
                }
            }//end organizer section
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

    public static void bookingFunction(String budget) {
        EnteredBudget ob = new EnteredBudget();
        ob.aSufficientBudgetProvidedByTheUser(budget);
        System.out.println("\u001B[33mYou can choose one of these providers:\u001B[0m");
        ob.theSelectedServiceProviderHasAvailabilityOnTheRequiredDate(budget);
        System.out.println("\u001B[33mNow please choose the provider you want, according to the services he provides,\u001B[0m");
        System.out.println("\u001B[33mand then enter the ID of the provider you choose to show the available dates:\u001B[0m");
        System.out.println("Enter the ID:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        System.out.println("\u001B[33mHere are the full details about the provider you chose:\u001B[0m");
        ob.theUserSubmitsTheBudgetAndDate(id);

        SuperSPData object = new SuperSPData(); // Assuming you have an instance of SuperSPData
        List<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();
        boolean hasUnavailableDates = false;
        for (int y = 0; y < serviceProviderList.size(); y++) {
            ServiceProviderClass serviceProvider = serviceProviderList.get(y);
            if (serviceProvider.getId().equals(Integer.toString(id))) {
                List<List<String>> freeDates = object.getAllFreeDates();
                if (y < freeDates.size()) {
                    List<String> dates = freeDates.get(y);
                    for (String date : dates) {
                        if (date.equals("---")) {
                            hasUnavailableDates = true;
                            break; // Exit the loop if any date is marked as '---'
                        }
                    }
                }
                break;
            }
        }

        if (hasUnavailableDates) {
            System.out.println("\u001B[31mThis provider has no available dates. Please choose another provider.\u001B[0m");
            bookingFunction(budget); // Recursively call the booking function to re-enter the ID
            return; // Exit the function after re-entering the ID
        }

        // If the selected provider has available dates, prompt the user to enter the date
        System.out.println("the provider's ID is : " + id);
        System.out.println("\u001B[33mPlease enter the Date you wish to reserve with this format D/M/Y :\u001B[0m");
        String day = scanner.nextLine();
        // Ensure that the entered date is not an empty string (i.e., the user didn't just press Enter)
        while (day.isEmpty()) {
            day = scanner.nextLine();
        }
        // Confirm the event with the entered date
        checkAndConfirmEvent(day, id, budget);
         displayMainMenu=true;
       // displayUserMenu=true;/////////////////////////
      //  displayMainMenu=true;//////////////////////
      //  break;
    }


    static Scanner scanner = new Scanner(System.in);
    static List<ServiceProviderClass> chosenProviders = new ArrayList<>();
    public static void checkAndConfirmEvent(String date, int id,String budget) {
        double p =0;
        SuperSPData object = new SuperSPData();

        List<List<String>> freeDates = object.getAllFreeDates();

        try {
            String venueSelected="";

            List<String> dates = freeDates.get(id - 1);
            if (dates.contains(date)) {
                List<ServiceProviderClass> serviceProviderList = serviceProviderData.getServiceProviderList();

                ServiceProviderClass chosenProvider = serviceProviderList.get(id - 1);

                List<String> allBudgets = object.getAllBudgets();
                String chosenBudget = allBudgets.get(id - 1);
                double venueBudget=Double.parseDouble(budget.replace("$", ""))-Double.parseDouble(chosenBudget.replace("$", ""));
                System.out.println("please now select venue knowing that the remaining budget is :"+venueBudget);
                System.out.println("please enter the number of chairs");
                Scanner v=new Scanner(System.in);
                int size= v.nextInt();
                ReserveVenueApp resApp=new ReserveVenueApp();
                boolean ve=resApp.reserveVenue(venueBudget,size);
                if(ve){
                 do{
                     System.out.println("please choose one of the venues");
                     Scanner inn=new Scanner(System.in);
                     int y= inn.nextInt();
                     venueSelected= resApp.getSelectedVenue(venueBudget,size,y)  ;
                   p=resApp.getSelectedVenuePrice(venueBudget,size,y);
                 }while(venueSelected.equals(" index out of range "));

                }
                //else choose package for the main budget
if(venueSelected.equals(""))
System.out.println("there is no venue with your budget/needed size");
                System.out.println("Do you want to confirm the event? (yes/no)");
                String confirm = scanner.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    List<ServiceProviderClass> serviceProviderList1 = serviceProviderData.getServiceProviderList();

                    ServiceProviderClass chosenProvider1 = serviceProviderList1.get(id - 1);
                    List<String> allBudgets1 = object.getAllBudgets();
                    double originalBudget = Double.parseDouble(allBudgets1.get(id - 1).replace("$", ""));
                    double newBudget = originalBudget + p;
                    String chosenBudget1 =  String.format("%.2f", newBudget)+"$" ;
                    System.out.println("\u001B[33mConfirmation Details:\u001B[0m");
                    System.out.println("\u001B[33mthe event is for user: " + email + "\u001B[0m");
                    System.out.println("\u001B[33mService Provider's Name: " + chosenProvider1.getName() + "\u001B[0m");
                    System.out.println("\u001B[33mProvider's ID: " + chosenProvider1.getId() + "\u001B[0m");
                    System.out.println("\u001B[33mEmail: " + chosenProvider1.getEmail() + "\u001B[0m");
                    System.out.println("\u001B[33mServices: " + chosenProvider1.getServicesList() + "\u001B[0m");
                    System.out.println("\u001B[33mPrice: " + chosenBudget1 + "\u001B[0m");
                    System.out.println("\u001B[33mDate: " + date + "\u001B[0m");
                    System.out.println("\u001B[33mVenue choosed: " + venueSelected + "\u001B[0m");

                    String venName = VenueClass.extractName(venueSelected);
Event event=new Event(email,chosenProvider1.getName(),chosenProvider1.getId(),chosenProvider1.getEmail(),chosenProvider1.getServicesList(),venName,date,chosenBudget1);
                    writeEventToFile(event);

                }

                else {
                    System.out.println("Event confirmation cancelled.");
                }
            } else {
                System.out.println("Date not available for confirmation. Please try another date.");
                System.out.println("Enter a new date (yyyy-MM-dd):");
                String newDate = scanner.nextLine();
                checkAndConfirmEvent(newDate, id,budget); // Recursively call the function with the new date
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid ID provided.");
        }

    }



}




