package wgu.c169;

public final class Student {
   
    // Create all instance variables as public string, int, or int array
    public String studentID;
    public String first_name;
    public String last_name;
    public String email;
    public int age;
    public int[] grades;
    
    // create student array and pass in variable type and variable name
    public Student(String studentID, String first_name, String last_name, String email, int age, int[] grades) 
    {
        setStudentID(studentID);
        setFirstName(first_name);
        setLastName(last_name);
        setEmail(email);
        setAge(age);
        setGrades(grades);
    }
    
    // Accessor - Getter methods for instance variables 
    public String getStudentID() {
       return studentID;
    }

    public String getFirstName() {
       return first_name; 
    }

    public String getLastName() {
       return last_name;
    }
    
    public String getEmail() {
        return email; 
    }

    public int getAge(){
        return age;
    }
    
    public int[] getGrades() {
        return grades;
    }
    
    // Mutator - Setter for all instance variables 
    public void setStudentID(String studentID){
        this.studentID = studentID;
    }
    public void setFirstName(String first_name){
        this.first_name = first_name;
    }
    public void setLastName(String last_name){
        this.last_name = last_name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setGrades(int[] grades){
        this.grades = grades;
    }
}