package DataB;

import org.example.AdminClass;
import org.example.ServiceProviderClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ServiceProviderData {
    private  ArrayList<ServiceProviderClass> ServiceProviderList = new ArrayList<>();
    private  String SPFile="DataForSP.txt";

    public ServiceProviderData() {
        this.readServiceProviderDataFromFile();

    }

    public  String getSPFile() {
        return SPFile;
    }

    public ArrayList <ServiceProviderClass> getServiceProviderList() {
        return ServiceProviderList ;
    }

    public void readServiceProviderDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(SPFile))) {
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
                    ServiceProviderList.add(sProvider);


                } else {
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}