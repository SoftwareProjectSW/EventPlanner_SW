package DataB;
import org.example.Organizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class OrganizerData {
    private static ArrayList<Organizer> organizersList =new ArrayList<>();
    private  String orgFile="DataForOrganizer.txt";

    public OrganizerData() {
        this.readOrgDataFromFile();
    }

    public static ArrayList<Organizer> getorganizersList() {
        return organizersList;
    }

    public  void readOrgDataFromFile()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(orgFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the data is formatted as "email,password"
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String email = parts[0].trim();
                    String password = parts[1].trim();
                    Organizer organizer = new Organizer(email, password);

                    this.organizersList.add(organizer);

                } else {
                    System.err.println("Invalid data format: " + line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
