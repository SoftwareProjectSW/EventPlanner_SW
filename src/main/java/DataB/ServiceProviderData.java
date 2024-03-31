package DataB;

import app.LoggerUtility;
import org.example.AdminClass;
import org.example.ServiceProviderClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;


public class ServiceProviderData {
    private static final Logger logger = LoggerUtility.getLogger();

    private  ArrayList<ServiceProviderClass>  serviceProviderList = new ArrayList<>();
    private  String spFile="DataForSP.txt";

    public ServiceProviderData() {
        this.readServiceProviderDataFromFile();

    }

    public  String getSPFile() {
        return spFile;
    }

    public ArrayList <ServiceProviderClass> getServiceProviderList() {
        return  serviceProviderList ;
    }

    public void readServiceProviderDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(spFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the data is formatted as "email,password"
                String[] parts = line.split(",");
                if (parts.length > 3) {
                    String name = parts[0].trim();
                    String id = parts[1].trim();
                    String email = parts[2].trim();
                    ServiceProviderClass sProvider = new ServiceProviderClass(name, id,email);

                    String services = null;
                    Integer trim =3;

                    while (parts.length > trim){
                        services = parts[trim].trim();
                        sProvider.getServicesList().add(services);
                        trim++;

                    }
                    serviceProviderList.add(sProvider);


                } else {
                   logger.info("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "there is error ", e);
        }
    }

    public void viewInfo() {
        ServiceProviderData ob=new ServiceProviderData();
        for(ServiceProviderClass temp : ob.getServiceProviderList()){
          logger.info(temp.getName());
        }
    }
}




