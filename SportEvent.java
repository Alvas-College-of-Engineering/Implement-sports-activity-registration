import javax.swing.JTextArea;

public class SportEvent {
    private String eventId;
    private String eventName;
    private int minAge;
    private int maxAge;
    private String allowedGender; // "Male", "Female", or "Both"
    private int maxParticipants;
    private int currentParticipants;

    // Constructor to initialize values
    public SportEvent(String eventId, String eventName, int minAge, int maxAge, String allowedGender, int maxParticipants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.allowedGender = allowedGender;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = 0;
    }

    // Getter methods
    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public String getAllowedGender() {
        return allowedGender;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    // Method to check if a student is eligible
    public boolean isEligible(Student student) {
        if (student.getAge() < minAge || student.getAge() > maxAge) {
            return false;
        }
        if (!allowedGender.equals("Both") && !student.getGender().equalsIgnoreCase(allowedGender)) {
            return false;
        }
        return true;
    }

    // Method to check if there is an available slot
    public boolean hasAvailableSlot() {
        return currentParticipants < maxParticipants;
    }

    // Method to increment participants
    public void incrementParticipants() {
        currentParticipants++;
    }

    // Method to display event details
    public void displayEventDetails() {
        System.out.println("Event ID: " + eventId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Age Range: " + minAge + " - " + maxAge);
        System.out.println("Allowed Gender: " + allowedGender);
        System.out.println("Max Participants: " + maxParticipants);
        System.out.println("Current Participants: " + currentParticipants);
    }

    // Method to display event details to JTextArea
    public void displayEventDetailsToArea(JTextArea area) {
        area.append("Event ID: " + eventId + "\n");
        area.append("Event Name: " + eventName + "\n");
        area.append("Age Range: " + minAge + " - " + maxAge + "\n");
        area.append("Allowed Gender: " + allowedGender + "\n");
        area.append("Max Participants: " + maxParticipants + "\n");
        area.append("Current Participants: " + currentParticipants + "\n");
    }
}