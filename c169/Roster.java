// Package Name
package wgu.c169;

/**
 * This is the Roster class file for project C169 for project For Performance
 * Assessment: For Scripting and Programming Application
 * @author gwestmoreland
 */

// Import List 
import java.util.ArrayList; 
import java.util.Arrays;

public class Roster {

     // Create Array List For Student Roster
    private static ArrayList<Student> roster; 
    static {
        // Create Array List For Roster
        roster = new ArrayList<>(); 
        // Add Student values to Array List using ArrayList.add() method
        roster.add(new Student("1","John","Smith","John1989@gmail.com",20,new int[]{88,79,89}));
        roster.add(new Student("2","Suzan","Erickson","Erickson_1999@gmailcom",19,new int[]{91,72,85}));
        roster.add(new Student("3","Jack","Napoli","The_lawyer99yahoo.com",19,new int[]{85,84,87}));
        roster.add(new Student("4","Erin","Black","Erin.black@comcast.net",22,new int[]{91,98,82}));
        roster.add(new Student("5","Greg","West","gregorywestmo@wgu.edu",46,new int[]{89,92,78}));
    }
    
    // Print Remove () Method 
    public static void remove(String studentID)
    {
        for (int i = 0; i < roster.size(); i++)
        {
            String checkID = roster.get(i).getStudentID();
            if (checkID.equals(studentID))
            {
                System.out.println("The following Student ID: " + studentID + " was removed from the roster.");
            }
            if (checkID.equals(studentID))
            {
                roster.remove(i);
                System.out.println("The following Student ID: " + studentID + " was not found in the roster.");
                return;
	    } 
	}
    }

    // Print All () Method
    public static void print_all()
    {
       for (int i = 0; i < roster.size(); i++) 
        {
            Student student = roster.get(i);
            System.out.println("Student ID: " + student.getStudentID() + "\t" + "First Name:  " + student.getFirstName() + "\t" + " Last Name: " + 
                    student.getLastName() + "\t" + " Email Address: " + student.getEmail() + "\t" + " Student Age: " + student.getAge() 
                    + "\t" +  " Students Test Grade: " + Arrays.toString(student.getGrades()));
        }
        System.out.println(); // Insert blank line in output
    }
    
    // Print Average Grade () Method
    public static void print_average_grade(String studentID)
    {
        int index = -1;
        for (int i = 0; i < roster.size(); i++) 
        {
            if (roster.get(i).getStudentID().equals(studentID)) 
            {
                index = i;
                break;
            }
        }
        if (index == -1) 
            {
                System.out.println("Student ID:" + studentID + " not found.");
            } else 
            {
                int grades[] = roster.get(index).getGrades();
                int avg = (grades[0] + grades[1] + grades[2]) / 3;
                System.out.println("Student ID: "+ studentID + " average grade is: " + avg);
            }
    }
    
    // Print Emails With Print Invlaid Emails () Method 
    public static void print_invalid_emails() 
    {
        roster.forEach((student) -> {
            // Check top level domains of email address to see if it is .net .com .org .mil or .gov everything else is invalid
            if (student.getEmail().contains("@") && student.getEmail().contains(".") && !student.getEmail().contains(" ")) {
                System.out.println(); // Print blank line
            } else {
                System.out.println(student.first_name + " " + student.last_name + " with the following email address " + 
                        student.email + " is not a valid email.");
            }
        });
    }
    
    // Main Method
    public static void main(String[] args)
    {
        System.out.println("Print All Method:");
        print_all();
        System.out.println("Print Invalid Emails Method:");
        print_invalid_emails(); 
        System.out.println("Print Average Grade Method:");
        // Loop through the ArrayList and for each element:
        for (Student currentStudent : Roster.roster)
        {
            print_average_grade(currentStudent.getStudentID().toString());
        }
        System.out.println(); // Insert blank line
        System.out.println("Print Remove Method:");
        remove("3");
        // Expected: This should print a message saying such a student with this ID was not found.
        remove("3"); 
    }
}