import java.awt.*;
import java.awt.event.*;

public class LabActivity4EmpInfoSystemGUI extends Frame implements ActionListener, WindowListener {
    Label lblFirstName, lblLastName, lblAge, lblHoursWorked, lblHourlyRate, lblOutput;
    TextField txtFirstName, txtLastName, txtAge, txtHoursWorked, txtHourlyRate;
    Button btnSubmit;
    TextArea outputArea;

    public LabActivity4EmpInfoSystemGUI() {
        setTitle("Laboratory Activity 4");
        setSize(500, 600);
        setLayout(null);

        // Labels
        lblFirstName = new Label("First Name");
        lblLastName = new Label("Last Name");
        lblAge = new Label("Age");
        lblHoursWorked = new Label("Hours Worked");
        lblHourlyRate = new Label("Hourly Rate");
        lblOutput = new Label("Output:");

        // TextFields
        txtFirstName = new TextField();
        txtLastName = new TextField();
        txtAge = new TextField();
        txtHoursWorked = new TextField();
        txtHourlyRate = new TextField();

        // Button
        btnSubmit = new Button("Submit");
        btnSubmit.addActionListener(this);

        // TextArea
        outputArea = new TextArea("", 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        outputArea.setEditable(false);

        // Positioning
        lblFirstName.setBounds(30, 50, 100, 20);
        txtFirstName.setBounds(150, 50, 300, 20);

        lblLastName.setBounds(30, 80, 100, 20);
        txtLastName.setBounds(150, 80, 300, 20);

        lblAge.setBounds(30, 110, 100, 20);
        txtAge.setBounds(150, 110, 300, 20);

        lblHoursWorked.setBounds(30, 140, 100, 20);
        txtHoursWorked.setBounds(150, 140, 300, 20);

        lblHourlyRate.setBounds(30, 170, 100, 20);
        txtHourlyRate.setBounds(150, 170, 300, 20);

        btnSubmit.setBounds(200, 210, 100, 30);

        lblOutput.setBounds(30, 260, 100, 20);
        outputArea.setBounds(30, 290, 420, 200);

        // Add components
        add(lblFirstName); add(txtFirstName);
        add(lblLastName); add(txtLastName);
        add(lblAge); add(txtAge);
        add(lblHoursWorked); add(txtHoursWorked);
        add(lblHourlyRate); add(txtHourlyRate);
        add(btnSubmit); add(lblOutput); add(outputArea);

        addWindowListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String ageText = txtAge.getText().trim();
        String hoursText = txtHoursWorked.getText().trim();
        String rateText = txtHourlyRate.getText().trim();

        outputArea.setText(""); // Clear output first

        // Check for empty fields
        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || hoursText.isEmpty() || rateText.isEmpty()) {
            outputArea.setText("Error: All fields must be filled out.");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Age must be a valid integer.");
            return;
        }

        double hoursWorked, hourlyRate;
        try {
            hoursWorked = Double.parseDouble(hoursText);
            hourlyRate = Double.parseDouble(rateText);
        } catch (NumberFormatException ex) {
            outputArea.setText("Error: Hours worked and hourly rate must be valid numbers.");
            return;
        }

        // Compute daily salary
        double dailySalary = hoursWorked * hourlyRate;

        // Display output
        outputArea.setText(
            "Full Name: " + firstName + " " + lastName + "\n" +
            "Age: " + age + " years old\n" +
            String.format("Daily Salary: PHP %.2f", dailySalary)
        );
    }

    // Window event methods
    @Override public void windowClosing(WindowEvent e) { dispose(); System.exit(0); }
    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}

    public static void main(String[] args) {
        new LabActivity4EmpInfoSystemGUI();
    }
}