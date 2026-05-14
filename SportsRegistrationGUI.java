import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SportsRegistrationGUI extends JFrame {
    private SportsRegistrationSystem system;
    private JTextArea outputArea;

    public SportsRegistrationGUI() {
        system = new SportsRegistrationSystem();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sports Activity Registration System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5));

        JButton registerButton = new JButton("Register Student");
        JButton showEventsButton = new JButton("Show Events");
        JButton showRegistrationsButton = new JButton("Show Registrations");
        JButton searchButton = new JButton("Search Registrations");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(registerButton);
        buttonPanel.add(showEventsButton);
        buttonPanel.add(showRegistrationsButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });

        showEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEvents();
            }
        });

        showRegistrationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRegistrations();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRegistrations();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void registerStudent() {
        // Create a dialog for student details
        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField studentIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField departmentField = new JTextField();
        JTextField yearField = new JTextField();

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Gender (Male/Female):"));
        panel.add(genderField);
        panel.add(new JLabel("Department:"));
        panel.add(departmentField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Student Details", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) return;

        try {
            String studentId = studentIdField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String department = departmentField.getText();
            int year = Integer.parseInt(yearField.getText());

            // Check if student exists
            Student student = system.findStudentById(studentId);
            if (student == null) {
                student = new Student(studentId, name, age, gender, department, year);
                system.addStudent(student);
            } else {
                JOptionPane.showMessageDialog(this, "Student already exists. Using existing details.");
            }

            // Show events in a dialog
            ArrayList<SportEvent> events = system.getEvents();
            String[] eventOptions = new String[events.size()];
            for (int i = 0; i < events.size(); i++) {
                eventOptions[i] = events.get(i).getEventId() + " - " + events.get(i).getEventName();
            }

            String selectedEvent = (String) JOptionPane.showInputDialog(this, "Select Event:", "Choose Event",
                    JOptionPane.QUESTION_MESSAGE, null, eventOptions, eventOptions[0]);
            if (selectedEvent == null) return;

            String eventId = selectedEvent.split(" - ")[0];
            SportEvent event = system.findEventById(eventId);

            // Register
            String message = system.registerStudent(student, event);
            JOptionPane.showMessageDialog(this, message);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for age or year.");
        }
    }

    private void showEvents() {
        outputArea.setText("");
        ArrayList<SportEvent> events = system.getEvents();
        for (SportEvent event : events) {
            outputArea.append("Event ID: " + event.getEventId() + "\n");
            outputArea.append("Event Name: " + event.getEventName() + "\n");
            outputArea.append("Age Range: " + event.getMinAge() + " - " + event.getMaxAge() + "\n");
            outputArea.append("Allowed Gender: " + event.getAllowedGender() + "\n");
            outputArea.append("Max Participants: " + event.getMaxParticipants() + "\n");
            outputArea.append("Current Participants: " + event.getCurrentParticipants() + "\n");
            outputArea.append("-----------------------------\n");
        }
    }

    private void showRegistrations() {
        outputArea.setText("");
        ArrayList<Registration> registrations = system.getRegistrations();
        if (registrations.isEmpty()) {
            outputArea.append("No registrations found.\n");
            return;
        }
        for (Registration reg : registrations) {
            outputArea.append("Registration ID: " + reg.getRegistrationId() + "\n");
            outputArea.append("Student Details:\n");
            reg.getStudent().displayStudentDetailsToArea(outputArea);
            outputArea.append("Event Details:\n");
            reg.getEvent().displayEventDetailsToArea(outputArea);
            outputArea.append("-----------------------------\n");
        }
    }

    private void searchRegistrations() {
        String studentId = JOptionPane.showInputDialog(this, "Enter Student ID:");
        if (studentId == null || studentId.trim().isEmpty()) return;

        outputArea.setText("");
        ArrayList<Registration> registrations = system.getRegistrations();
        boolean found = false;
        for (Registration reg : registrations) {
            if (reg.getStudent().getStudentId().equals(studentId)) {
                outputArea.append("Registration ID: " + reg.getRegistrationId() + "\n");
                outputArea.append("Student Details:\n");
                reg.getStudent().displayStudentDetailsToArea(outputArea);
                outputArea.append("Event Details:\n");
                reg.getEvent().displayEventDetailsToArea(outputArea);
                outputArea.append("-----------------------------\n");
                found = true;
            }
        }
        if (!found) {
            outputArea.append("No registrations found for this student.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SportsRegistrationGUI().setVisible(true);
            }
        });
    }
}