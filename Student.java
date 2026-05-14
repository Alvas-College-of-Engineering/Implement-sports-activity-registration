import javax.swing.JTextArea;

public class Student {
    private String studentId;
    private String name;
    private int age;
    private String gender;
    private String department;
    private int year;

    // Constructor to initialize values
    public Student(String studentId, String name, int age, String gender, String department, int year) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.year = year;
    }

    // Getter methods
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getYear() {
        return year;
    }

    // Method to display student details
    public void displayStudentDetails() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Department: " + department);
        System.out.println("Year: " + year);
    }

    // Method to display student details to JTextArea
    public void displayStudentDetailsToArea(JTextArea area) {
        area.append("Student ID: " + studentId + "\n");
        area.append("Name: " + name + "\n");
        area.append("Age: " + age + "\n");
        area.append("Gender: " + gender + "\n");
        area.append("Department: " + department + "\n");
        area.append("Year: " + year + "\n");
    }
}