package utilities;

// Import statements
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class LoginActivity {
    /**
     * Declare String Variable loginActivity to login_activity.txt
     */
    private static final String loginActivity = "login_activity.txt";

    /**
     * This login_activity method is called from the LoginController
     * @param uName
     * @param success
     */
    public static void login_activity(String uName, boolean success) {
        try (FileWriter fw = new FileWriter(loginActivity, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
             pw.println(ZonedDateTime.now() + " Log in for " + uName + " is a " + (success ? "success." : "failure."));
        } catch (IOException e) {
            System.out.println("Logger Error: " + e.getMessage());
        }
    }
}
