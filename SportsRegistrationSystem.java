import java.util.ArrayList;
import java.util.Scanner;

public class SportsRegistrationSystem {
    private ArrayList<Student> students;
    private ArrayList<SportEvent> events;
    private ArrayList<Registration> registrations;
    private Scanner scanner;
    private int registrationCounter;

    public SportsRegistrationSystem() {
        students = new ArrayList<>();
        events = new ArrayList<>();
        registrations = new ArrayList<>();
        scanner = new Scanner(System.in);
        registrationCounter = 1;
        addSampleEvents();
    }

    // Method to add sample events
    public void addSampleEvents() {
        events.add(new SportEvent("E001", "Basketball", 18, 25, "Both", 20));
        events.add(new SportEvent("E002", "Football", 18, 22, "Male", 22));
        events.add(new SportEvent("E003", "Volleyball", 17, 24, "Both", 12));
        events.add(new SportEvent("E004", "Tennis", 16, 30, "Both", 8));
        events.add(new SportEvent("E005", "Swimming", 15, 28, "Both", 15));
    }

    // Method to register student for event
    public void registerStudentForEvent() {
        System.out.println("Enter Student Details:");
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Gender (Male/Female): ");
        String gender = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());

        // Check if student already exists
        Student student = findStudentById(studentId);
        if (student == null) {
            student = new Student(studentId, name, age, gender, department, year);
            students.add(student);
        } else {
            System.out.println("Student already exists. Using existing details.");
        }

        // Show available events
        showAllEvents();

        System.out.print("Enter Event ID to register: ");
        String eventId = scanner.nextLine();
        SportEvent event = findEventById(eventId);

        if (event == null) {
            System.out.println("Invalid Event ID.");
            return;
        }

        // Check eligibility
        if (!event.isEligible(student)) {
            System.out.println("Student is not eligible for this event.");
            return;
        }

        // Check available slot
        if (!event.hasAvailableSlot()) {
            System.out.println("Event is full. No available slots.");
            return;
        }

        // Check for duplicate registration
        if (isAlreadyRegistered(studentId, eventId)) {
            System.out.println("Student is already registered for this event.");
            return;
        }

        // Register
        String regId = "REG" + registrationCounter++;
        Registration registration = new Registration(regId, student, event);
        registrations.add(registration);
        event.incrementParticipants();
        System.out.println("Registration successful! Registration ID: " + regId);
    }

    // Method to show all events
    public void showAllEvents() {
        System.out.println("Available Events:");
        for (SportEvent event : events) {
            event.displayEventDetails();
            System.out.println("-----------------------------");
        }
    }

    // Method to show all registrations
    public void showAllRegistrations() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations found.");
            return;
        }
        System.out.println("All Registrations:");
        for (Registration reg : registrations) {
            reg.displayRegistrationDetails();
        }
    }

    // Method to search student registrations
    public void searchStudentRegistrations() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        boolean found = false;
        for (Registration reg : registrations) {
            if (reg.getStudent().getStudentId().equals(studentId)) {
                reg.displayRegistrationDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No registrations found for this student.");
        }
    }

    // Method to find student by ID
    public Student findStudentById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    // Method to find event by ID
    public SportEvent findEventById(String eventId) {
        for (SportEvent e : events) {
            if (e.getEventId().equals(eventId)) {
                return e;
            }
        }
        return null;
    }

    // Getter for students
    public ArrayList<Student> getStudents() {
        return students;
    }

    // Getter for events
    public ArrayList<SportEvent> getEvents() {
        return events;
    }

    // Getter for registrations
    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    // Method to add student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to register student for event (non-interactive)
    public String registerStudent(Student student, SportEvent event) {
        if (!event.isEligible(student)) {
            return "Student is not eligible for this event.";
        }

        if (!event.hasAvailableSlot()) {
            return "Event is full. No available slots.";
        }

        if (isAlreadyRegistered(student.getStudentId(), event.getEventId())) {
            return "Student is already registered for this event.";
        }

        String regId = "REG" + registrationCounter++;
        Registration registration = new Registration(regId, student, event);
        registrations.add(registration);
        event.incrementParticipants();
        return "Registration successful! Registration ID: " + regId;
    }

    // Helper method to check if already registered
    private boolean isAlreadyRegistered(String studentId, String eventId) {
        for (Registration r : registrations) {
            if (r.getStudent().getStudentId().equals(studentId) && r.getEvent().getEventId().equals(eventId)) {
                return true;
            }
        }
        return false;
    }

    // Main method with menu
    public static void main(String[] args) {
        SportsRegistrationSystem system = new SportsRegistrationSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nSports Activity Registration System");
            System.out.println("1. Register for an Event");
            System.out.println("2. Show All Events");
            System.out.println("3. Show All Registrations");
            System.out.println("4. Search Student Registrations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    system.registerStudentForEvent();
                    break;
                case 2:
                    system.showAllEvents();
                    break;
                case 3:
                    system.showAllRegistrations();
                    break;
                case 4:
                    system.searchStudentRegistrations();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}