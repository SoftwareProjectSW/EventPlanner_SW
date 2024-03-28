package app;

import DataB.AdminData;
import DataB.ServiceProviderData;
import io.cucumber.java.sl.In;
import org.example.AdminClass;
import org.example.ServiceProviderClass;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DeleteSP_App {
    private static final String TEMP_FILE_NAME = "temp.txt";


    private boolean isSPExists = false ;
    private Integer Count = 0 ;
    private boolean isUnique = false;
    private boolean removeByIdFlag = false;

//    public Integer getCount() {
//        return Count;
//    }

////////////

    public boolean getIsRemoveByIdFlag() {
        return removeByIdFlag;
    }
    public void setremoveByIdFlagTrue() {

        removeByIdFlag = true;
    }
    public void setremoveByIdFlagFalse() {

        removeByIdFlag = false;
    }

    ////////////
    public boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUniqueTrue() {

        isUnique = true;
    }
    public void setIsUniqueFalse() {

        isUnique = false;
    }
    ///////////
    public boolean getSP_Exists() {
        return isSPExists;
    }

    public void setSP_Exists(boolean SPExists) {
        isSPExists = SPExists;
    }

    public void setSPExistsTrue() {

        isSPExists = true;
    }
    public void setSPExistsFalse() {

        isSPExists = false;
    }

    ////////////

    public void enterServiceProviderName(String name) {

        Integer Count =0 ;
        ServiceProviderData sp = new ServiceProviderData();

        for (ServiceProviderClass temp : sp.getServiceProviderList()) {
            if (name.equals( temp.getName() )) {
                Count++;
                setSPExistsTrue();
            }

        }
        if (Count== 1){
            setIsUniqueTrue();
        } else if (Count==0 ) { // we could remove this else if part
            setSPExistsFalse();
            setIsUniqueFalse();
        }else if (Count >0 ){
            setIsUniqueFalse();
        }else {
            setSPExistsFalse();
            setIsUniqueFalse();
        }

    }

    public Integer getCount(String name) {

        Count =0 ;
        ServiceProviderData sp =new ServiceProviderData();

        for (ServiceProviderClass temp : sp.getServiceProviderList()) {
            if (name.equals( temp.getName() )) {
                Count++;
                setSPExistsTrue();
            }

        }
        return Count;
    }


    public void removeServiceProviderByName(String name) {
        int count = countServiceProvidersByName(name);
        if (count == 1) {
            ServiceProviderData SPData = new ServiceProviderData();
            List<ServiceProviderClass> serviceProviderList = SPData.getServiceProviderList();
            removeServiceProviderAndWriteToFile(serviceProviderList, name);
        } else if (count > 1) {
            System.out.println("Multiple service providers with the same name.");
        } else {
            System.out.println("No service provider found with the given name.");
        }
    }

    private int countServiceProvidersByName(String name) {
        int count = 0;
        ServiceProviderData sp = new ServiceProviderData();
        for (ServiceProviderClass temp : sp.getServiceProviderList()) {
            if (name.equals(temp.getName())) {
                count++;
                setSPExistsTrue();
            }
        }
        return count;
    }

    private void removeServiceProviderAndWriteToFile(List<ServiceProviderClass> serviceProviderList, String name) {
        ServiceProviderData SPData = new ServiceProviderData();
        File serviceProviderFile = new File("DataForSP.txt");

        serviceProviderList.removeIf(provider -> provider.getName().equals(name));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_NAME))) {
            for (ServiceProviderClass provider : serviceProviderList) {
                writeServiceProviderToFile(provider, writer);
            }
            writer.close();
            if (serviceProviderFile.delete() && new File(TEMP_FILE_NAME).renameTo(serviceProviderFile)) {
                System.out.println("Service provider with name " + name + " deleted successfully.");
            } else {
                System.out.println("Error occurred while deleting the service provider.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeServiceProviderToFile(ServiceProviderClass provider, BufferedWriter writer) throws IOException {
        writer.write(provider.getName() + "," + provider.getId() + "," + provider.getEmail());
        for (String services : provider.getServicesList()) {
            writer.write("," + services);
        }
        writer.newLine();
    }



   public void removeServiceProviderById(String id) {
    ServiceProviderData sp = new ServiceProviderData();
    List<ServiceProviderClass> serviceProviderList = sp.getServiceProviderList();
    boolean serviceProviderFound = false;

    // Check if the service provider with the given ID exists
    for (ServiceProviderClass serviceProvider : serviceProviderList) {
        if (id.equals(serviceProvider.getId())) {
            serviceProviderFound = true;
            serviceProviderList.remove(serviceProvider); // Remove from ArrayList
            break;
        }
    }

    if (serviceProviderFound) {
        // Write the updated service providers list to the file
        writeServiceProviderListToFile(serviceProviderList);
        System.out.println("Service provider with ID " + id + " deleted successfully.");
    } else {
        System.out.println("No service provider found with the given ID.");
    }
}

private void writeServiceProviderListToFile(List<ServiceProviderClass> serviceProviderList) {
    File serviceProviderFile = new File("DataForSP.txt");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE_NAME))) {
        for (ServiceProviderClass provider : serviceProviderList) {
            writer.write(provider.getName() + "," + provider.getId() + "," + provider.getEmail());
            for (String service : provider.getServicesList()) {
                writer.write("," + service);
            }
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Rename the temporary file to the original file name
    File tempFile = new File(TEMP_FILE_NAME);
    if (tempFile.renameTo(serviceProviderFile)) {
        System.out.println("File updated successfully.");
    } else {
        System.out.println("Error occurred while updating the file.");
    }
}



    public boolean checkExistence(String name){

        ServiceProviderData sp =new ServiceProviderData();

        for (ServiceProviderClass temp : sp.getServiceProviderList()) {
            if (name.equals( temp.getName() )) {

                return true;
            }

        }
        return false;

    }


    public void printMessage(){

        JOptionPane.showMessageDialog(null,
                "no service providers are available for deletion","Warning",JOptionPane.WARNING_MESSAGE);

    }



}
