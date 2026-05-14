public class Registration {
    private String registrationId;
    private Student student;
    private SportEvent event;

    // Constructor to initialize values
    public Registration(String registrationId, Student student, SportEvent event) {
        this.registrationId = registrationId;
        this.student = student;
        this.event = event;
    }

    // Getter methods
    public String getRegistrationId() {
        return registrationId;
    }

    public Student getStudent() {
        return student;
    }

    public SportEvent getEvent() {
        return event;
    }

    // Method to display registration details
    public void displayRegistrationDetails() {
        System.out.println("Registration ID: " + registrationId);
        System.out.println("Student Details:");
        student.displayStudentDetails();
        System.out.println("Event Details:");
        event.displayEventDetails();
        System.out.println("-----------------------------");
    }
}