package DataB;
import app.LoggerUtility;
import org.example.Organizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
public class OrganizerData {
    private static final Logger logger = LoggerUtility.getLogger();
    private static ArrayList<Organizer> organizersList =new ArrayList<>();
    private  String orgFile="DataForOrganizer.txt";
    public OrganizerData() {
        ArrayList arrayList = new ArrayList<>();
        boolean flag = this.readOrgDataFromFile(arrayList);
    }
    public static ArrayList<Organizer> getorganizersList() {
        return organizersList;
    }
    public  boolean readOrgDataFromFile(ArrayList array)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(orgFile))) {
            Integer size = array.size();
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
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "there is error ", e);
            return false;
        }
    }
}
