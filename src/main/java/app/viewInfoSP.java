package app;

import DataB.ServiceProviderData;
import org.example.ServiceProviderClass;

import java.io.File;

public class viewInfoSP {
    public boolean theAdminIsLoggedIntoTheSystem(String f) {
        String file = "DataForAdmin.txt";
        if (f.equals(file)) ;
        return true;
    }

    public boolean theAdminCanViewDetailsSuchAsNameContactInformationAndServicesOffered() {
        ServiceProviderData a = new ServiceProviderData();
        String file = "DataForSP.txt";
        a.ViewInfo();
        if (!(a.getServiceProviderList().isEmpty()))
            System.out.println(" service providers are founded");
        return true;
    }

    public boolean thereAreNoServiceProvidersRegisteredInTheSystem() {
        File file = new File("DataForSP.txt");
        if (file.length() == 0)
            System.out.println("no service providers founded");
        return true;
    }

    public static boolean theSystemDisplaysAListOfServiceProvidersWithTheirDetails(String na) {
        boolean found = false;
        ServiceProviderData ob=new ServiceProviderData();
        for(ServiceProviderClass temp : ob.getServiceProviderList()){
            if(temp.getName().equals(na)){
                found=true;
                System.out.println(na);
                System.out.println(temp.getId());
                System.out.println(temp.getEmail());
                for(String s :temp.getServicesList() ){
                    System.out.println(s);
                }
            }
        }

            return found;

    }


}