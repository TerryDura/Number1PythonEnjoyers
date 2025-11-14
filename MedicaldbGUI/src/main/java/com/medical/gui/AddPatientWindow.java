package com.medical.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddPatientWindow extends JFrame {
    public AddPatientWindow(){
        super("Add Patient");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField bloodField = new JTextField();
        JTextField insuranceField = new JTextField();

        add(new JLabel("Full Name:")); add(nameField);
        add(new JLabel("Gender:")); add(genderField);
        add(new JLabel("Date of Birth (YYYY-MM-DD)")); add(dobField);
        add(new JLabel("Phone Number:")); add(phoneField);
        add(new JLabel("Email:")); add(emailField);
        add(new JLabel("Blood Type:")); add(bloodField);
        add(new JLabel("Insurance:")); add(insuranceField);

        JButton saveBtn = new JButton("Save");
        add(saveBtn);
        

        saveBtn.addActionListener((ActionEvent e) -> {
            // Build JSON to send to backend
            String json = String.format(
                "{\"fullName\":\"%s\",\"gender\":\"%s\",\"dob\":\"%s\",\"phoneNumber\":\"%s\",\"email\":\"%s\",\"bloodType\":\"%s\",\"insurance\":\"%s\"}",
                nameField.getText(), genderField.getText(), dobField.getText(),
                phoneField.getText(), emailField.getText(), bloodField.getText(), insuranceField.getText()
            );

            String response = ApiClient.sendRequest("/add", "POST", json);
            JOptionPane.showMessageDialog(this, "Response: " + response);
        });

    

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
