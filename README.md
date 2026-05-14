# Sports Registration System - Swing GUI Version

This is a Java Swing-based GUI application for managing sports event registrations for students.

## Features

- Register students for sports events
- View available events
- View all registrations
- Search registrations by student ID
- Interactive GUI with dialogs for input

## Files

- `Student.java`: Represents a student with details like ID, name, age, etc.
- `SportEvent.java`: Represents a sports event with eligibility criteria.
- `Registration.java`: Represents a registration linking a student to an event.
- `SportsRegistrationSystem.java`: Core logic for managing students, events, and registrations.
- `SportsRegistrationGUI.java`: Swing GUI for user interaction.

## How to Run

1. Compile all Java files:
   ```
   javac *.java
   ```

2. Run the GUI application:
   ```
   java SportsRegistrationGUI
   ```

The GUI window will open with buttons for different actions. Use the dialogs to input data.

## Sample Events

The system comes pre-loaded with 5 sample events:
- Basketball (E001): Ages 18-25, Both genders, 20 slots
- Football (E002): Ages 18-22, Male only, 22 slots
- Volleyball (E003): Ages 17-24, Both genders, 12 slots
- Tennis (E004): Ages 16-30, Both genders, 8 slots
- Swimming (E005): Ages 15-28, Both genders, 15 slots

## Usage

- Click "Register Student" to add a new student and register them for an event.
- Click "Show Events" to display all available events in the text area.
- Click "Show Registrations" to display all registrations.
- Click "Search Registrations" to find registrations for a specific student ID.
- Click "Exit" to close the application.