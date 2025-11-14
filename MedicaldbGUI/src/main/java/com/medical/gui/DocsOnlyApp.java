package com.medical.gui;


import java.awt.*;
import java.util.*;
import javax.swing.*;

public class DocsOnlyApp {
    public static void main(String[] args) {
        new LoginWindow(); // Start with the login window
    }
}

// ================= LOGIN WINDOW =================
class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField keyField;

    public LoginWindow() {
        super("DocsOnly");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Key
        JLabel keyLabel = new JLabel("Key:");
        keyLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(keyLabel, gbc);

        keyField = new JTextField(15);
        keyField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        panel.add(keyField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        // Action for login
        loginButton.addActionListener(e -> checkLogin());

        add(panel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void checkLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String key = keyField.getText();

        if (username.equals("User") && password.equals("Pass") && key.equals("12345")) {
            dispose();
            new MenuWindow();
        } else {
            JOptionPane.showMessageDialog(this, "Wrong username, password, or key", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// ================= MENU WINDOW =================
class MenuWindow extends JFrame {
    public MenuWindow() {
        super("Docs Only");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 70));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        JButton btn1 = new JButton("Patient");
        JButton btn2 = new JButton("Appointment");


        Dimension buttonSize = new Dimension(250, 100);
        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        JButton[] buttons = {btn1, btn2};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize);
            b.setFont(buttonFont);
            buttonPanel.add(b);
        }

        btn1.addActionListener(e -> { dispose(); new PatientWindow(); });
        btn2.addActionListener(e -> { dispose(); new AppointmentWindow(); });
        

        add(buttonPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 25));
        logoutButton.addActionListener(e -> { dispose(); new LoginWindow(); });
        add(logoutButton, BorderLayout.SOUTH);
    }
}

// ================= PATIENT WINDOW =================
class PatientWindow extends JFrame {
    public PatientWindow() {
        super("Patient");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Patient Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        JButton btn1 = new JButton("Add Patient");
        JButton btn2 = new JButton("View Patients");
        JButton btn3 = new JButton("Edit Patient");

        Dimension buttonSize = new Dimension(250, 100);
        Font buttonFont = new Font("Arial", Font.BOLD, 30);
        JButton[] buttons = {btn1, btn2, btn3};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize);
            b.setFont(buttonFont);
            buttonPanel.add(b);
        }

        btn2.addActionListener(e -> { dispose(); new ViewPatientWindow(); });

        add(buttonPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 25));
        backButton.addActionListener(e -> { dispose(); new MenuWindow(); });

        btn1.addActionListener(e -> { dispose(); new AddPatientWindow(); });

        add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

// ================= APPOINTMENT WINDOW =================
class AppointmentWindow extends JFrame {
    public AppointmentWindow() {
        super("Appointment");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Appointment Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
        JButton btn1 = new JButton("Create Appointment");
        JButton btn2 = new JButton("View Appointment");
        JButton btn3 = new JButton("Edit Appointment");

        Dimension buttonSize = new Dimension(250, 100);
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        JButton[] buttons = {btn1, btn2, btn3};
        for (JButton b : buttons) {
            b.setPreferredSize(buttonSize);
            b.setFont(buttonFont);
            buttonPanel.add(b);
        }
        add(buttonPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 25));
        backButton.addActionListener(e -> { dispose(); new MenuWindow(); });
        add(backButton, BorderLayout.SOUTH);

        btn1.addActionListener(e -> { dispose(); new CreateAppointmentWindow();});


        setLocationRelativeTo(null);
        setVisible(true);
    }
}


// ================= VIEW PATIENTS WINDOW =================
// class ViewPatientsWindow extends JFrame {
//     private JTextField firstNameField;
//     private JTextField lastNameField;
//     private JTextArea resultArea;
//     private Map<String, String> patientDatabase;

//     public ViewPatientsWindow() {
//         super("View Patients");
//         setSize(800, 500);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         JLabel titleLabel = new JLabel("View Patients", SwingConstants.CENTER);
//         titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
//         add(titleLabel, BorderLayout.NORTH);

//         patientDatabase = new HashMap<>();
//         patientDatabase.put("John Doe", "Patient: John Doe\nAge: 35\nCondition: Hypertension\nRoom: 204B");
//         patientDatabase.put("Jane Smith", "Patient: Jane Smith\nAge: 29\nCondition: Diabetes\nRoom: 102A");
//         patientDatabase.put("Mark Johnson", "Patient: Mark Johnson\nAge: 41\nCondition: Asthma\nRoom: 310");

//         JPanel inputPanel = new JPanel(new GridBagLayout());
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(10, 10, 10, 10);
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         JLabel firstNameLabel = new JLabel("First Name:");
//         firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
//         gbc.gridx = 0;
//         gbc.gridy = 0;
//         inputPanel.add(firstNameLabel, gbc);

//         firstNameField = new JTextField(12);
//         firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
//         gbc.gridx = 1;
//         inputPanel.add(firstNameField, gbc);

//         JLabel lastNameLabel = new JLabel("Last Name:");
//         lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
//         gbc.gridx = 0;
//         gbc.gridy = 1;
//         inputPanel.add(lastNameLabel, gbc);

//         lastNameField = new JTextField(12);
//         lastNameField.setFont(new Font("Arial", Font.PLAIN, 20));
//         gbc.gridx = 1;
//         inputPanel.add(lastNameField, gbc);

//         JButton searchButton = new JButton("Search");
//         searchButton.setFont(new Font("Arial", Font.BOLD, 22));
//         gbc.gridx = 0;
//         gbc.gridy = 2;
//         gbc.gridwidth = 2;
//         gbc.anchor = GridBagConstraints.CENTER;
//         inputPanel.add(searchButton, gbc);

//         add(inputPanel, BorderLayout.CENTER);

//         resultArea = new JTextArea();
//         resultArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
//         resultArea.setEditable(false);
//         resultArea.setLineWrap(true);
//         resultArea.setWrapStyleWord(true);
//         JScrollPane scrollPane = new JScrollPane(resultArea);
//         scrollPane.setPreferredSize(new Dimension(600, 150));
//         add(scrollPane, BorderLayout.SOUTH);

//         searchButton.addActionListener(e -> searchPatient());

//         JButton backButton = new JButton("Back");
//         backButton.setFont(new Font("Arial", Font.PLAIN, 25));
//         backButton.addActionListener(e -> { dispose(); new PatientWindow();});
//         add(backButton, BorderLayout.SOUTH);

//         setLocationRelativeTo(null);
//         setVisible(true);
//     }

//     private void searchPatient() {
//         String first = firstNameField.getText().trim();
//         String last = lastNameField.getText().trim();

//         if (first.isEmpty() || last.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Please enter both first and last names.", "Input Error", JOptionPane.WARNING_MESSAGE);
//             return;
//         }

//         String key = first + " " + last;
//         if (patientDatabase.containsKey(key)) {
//             resultArea.setText(patientDatabase.get(key));
//         } else {
//             resultArea.setText("No record found for " + key + ".");
//         }
//     }
// }


// ================= ADD PATIENT WINDOW =================
class AddPatientWindow extends JFrame {
    private JTextField firstNameField, lastNameField, dobField, phoneField, emailField, insuranceField, bloodTypeField;
    private JComboBox<String> genderBox;

    public AddPatientWindow() {
        super("Add Patient");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Add Patient", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        add(titleLabel, BorderLayout.NORTH);

        // Input panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);

        int y = 0;

        // First Name
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(15);
        firstNameField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);
        y++;

        // Last Name
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(15);
        lastNameField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);
        y++;

        // Date of Birth
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Date of Birth (MM/DD/YYYY):"), gbc);
        dobField = new JTextField(15);
        dobField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(dobField, gbc);
        y++;

        // Phone Number
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Phone Number:"), gbc);
        phoneField = new JTextField(15);
        phoneField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);
        y++;

        // Email
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(15);
        emailField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        y++;

        // Insurance
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Insurance:"), gbc);
        insuranceField = new JTextField(15);
        insuranceField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(insuranceField, gbc);
        y++;

        // BloodType
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("BloodType:"), gbc);
        bloodTypeField = new JTextField(15);
        bloodTypeField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(bloodTypeField, gbc);
        y++;

        // Gender
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Gender:"), gbc);
        genderBox = new JComboBox<>(new String[]{"Select", "Male", "Female"});
        genderBox.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(genderBox, gbc);
        y++;

        // Add button
        JButton addButton = new JButton("Add Patient");
        addButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, gbc);

        add(panel, BorderLayout.CENTER);

        // Add button logic
        addButton.addActionListener(e -> {
            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least First and Last name.", "Missing Info", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new PatientWindow();
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 22));
        backButton.addActionListener(e -> {
            dispose();
            new PatientWindow();
        });
        add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

// ================= CREATE APPOINTMENT WINDOW =================
class CreateAppointmentWindow extends JFrame {
    private JTextField firstNameField, lastNameField, dateField, timeField, reasonField;

    public CreateAppointmentWindow() {
        super("Create Appointment");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Create Appointment", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 45));
        add(titleLabel, BorderLayout.NORTH);

        // Input panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font fieldFont = new Font("Arial", Font.PLAIN, 20);

        int y = 0;

        // Patient First Name
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Patient First Name:"), gbc);
        firstNameField = new JTextField(15);
        firstNameField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(firstNameField, gbc);
        y++;

        // Patient Last Name
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Patient Last Name:"), gbc);
        lastNameField = new JTextField(15);
        lastNameField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(lastNameField, gbc);
        y++;

        // Date
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Date (MM/DD/YYYY):"), gbc);
        dateField = new JTextField(15);
        dateField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(dateField, gbc);
        y++;

        // Time
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Time (HH:MM):"), gbc);
        timeField = new JTextField(15);
        timeField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(timeField, gbc);
        y++;

        // Reason
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel("Reason for Appointment:"), gbc);
        reasonField = new JTextField(15);
        reasonField.setFont(fieldFont);
        gbc.gridx = 1;
        panel.add(reasonField, gbc);
        y++;

        // Add Appointment button
        JButton addButton = new JButton("Add Appointment");
        addButton.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, gbc);

        add(panel, BorderLayout.CENTER);

        // Add button logic
        addButton.addActionListener(e -> {
            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                dateField.getText().isEmpty() || timeField.getText().isEmpty() || reasonField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Appointment successfully created for:\n" +
                        firstNameField.getText() + " " + lastNameField.getText() +
                        "\nDate: " + dateField.getText() +
                        "\nTime: " + timeField.getText() +
                        "\nReason: " + reasonField.getText(),
                        "Appointment Created",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AppointmentWindow();
            }
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 22));
        backButton.addActionListener(e -> {
            dispose();
            new AppointmentWindow();
        });
        add(backButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
