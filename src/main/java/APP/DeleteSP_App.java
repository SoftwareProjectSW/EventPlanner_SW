package APP;

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

public class DeleteSP_App {


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
        ServiceProviderData sp =new ServiceProviderData();

        for (ServiceProviderClass temp : sp.getServiceProviderList()) {
            if (name.equals( temp.getName() )) {
                Count++;
                setSPExistsTrue();
            }

        }
        if (Count== 1){
            setIsUniqueTrue();
        } else if (Count==0 ) {
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

    public void removeServiceProviderByName(String name){

        Integer Count =0 ;
        ServiceProviderData sp =new ServiceProviderData();

        for (ServiceProviderClass temp : sp.getServiceProviderList()) {
            if (name.equals( temp.getName() )) {
                Count++;
                setSPExistsTrue();
            }

        }


        ServiceProviderData SPData = new ServiceProviderData();
        File serviceProviderFile = new File("DataForSP.txt");

        enterServiceProviderName(name);
        //long count = serviceProviderList.stream().filter(provider -> provider.getName().equals(name)).count();

        if (Count == 1) {
            // Remove from ArrayList
            SPData.getServiceProviderList().removeIf(provider -> provider.getName().equals(name));

            // Remove from file
            try {
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                for (ServiceProviderClass provider : SPData.getServiceProviderList()) {
                    writer.write(provider.getName() + "," + provider.getId());
                    writer.newLine();
                }
                writer.close();
                serviceProviderFile.delete();
                tempFile.renameTo(serviceProviderFile);
                System.out.println("Service provider with name " + name + " deleted successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Count > 1) {
            System.out.println("Multiple service providers with the same name.");
        } else {
            System.out.println("No service provider found with the given name.");
        }

        enterServiceProviderName(name);

    }


    public void removeServiceProviderById(String Id){

        boolean flag = false;
        setremoveByIdFlagFalse();
        ServiceProviderData sp =new ServiceProviderData();

        for (ServiceProviderClass temp : sp.getServiceProviderList()) {
            if (Id.equals( temp.getId() )) {
                flag = true;
                setremoveByIdFlagTrue();
            }

        }


        ServiceProviderData SPData = new ServiceProviderData();
        File serviceProviderFile = new File("DataForSP.txt");

        //enterServiceProviderName(name);

        if (flag ) {
            // Remove from ArrayList
            SPData.getServiceProviderList().removeIf(provider -> provider.getId().equals(Id));

            // Remove from file
            try {
                File tempFile = new File("temp.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                for (ServiceProviderClass provider : SPData.getServiceProviderList()) {
                    writer.write(provider.getName() + "," + provider.getId());
                    writer.newLine();
                }
                writer.close();
                serviceProviderFile.delete();
                tempFile.renameTo(serviceProviderFile);
                System.out.println("Service provider with ID " + Id + " deleted successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No service provider found with the given ID.");
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
