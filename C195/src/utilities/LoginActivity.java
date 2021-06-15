package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class LoginActivity {
    private static final String FILENAME = "login_activity.txt";

   public LoginActivity() {
   // LoginActivity Method Is Called Here
   }
    public static void login_activity (String uName, boolean success) {
        try (FileWriter fw = new FileWriter(FILENAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
             pw.println(ZonedDateTime.now() + " Log in for " + uName + " is a " + (success ? "success." : "failure."));
        } catch (IOException e) {
            System.out.println("Logger Error: " + e.getMessage());
        }
    }
}
