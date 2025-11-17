package com.medical.gui;


import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class EditPatientWindow extends JFrame {

    private JTextField idField, nameField, genderField, dobField,
            phoneField, emailField, bloodField, insuranceField;
    
    public EditPatientWindow(){
        super("Edit Patient");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Edit Patient", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        //Form fields
        JPanel form = new JPanel(new GridLayout(9, 2, 5, 5 ));

        form.add(new JLabel("Patient ID:"));
        idField = new JTextField();
        form.add(idField);

        form.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Gender:"));
        genderField = new JTextField();
        form.add(genderField);

        form.add(new JLabel("DOB (YYYY-MM-DD):"));
        dobField = new JTextField();
        form.add(dobField);

        form.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        form.add(phoneField);

        form.add(new JLabel("Email:"));
        emailField = new JTextField();
        form.add(emailField);

        form.add(new JLabel("Blood Type:"));
        bloodField = new JTextField();
        form.add(bloodField);

        form.add(new JLabel("Insurance"));
        insuranceField = new JTextField();
        form.add(insuranceField);

        add(form, BorderLayout.CENTER);

        //buttons
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load");
        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("Back");

        loadButton.addActionListener(e -> loadPatient());
        updateButton.addActionListener(e -> updatePatient());
        backButton.addActionListener(e -> {
            dispose();
            new PatientWindow();
        });

        buttonPanel.add(loadButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);


    }

    public EditPatientWindow(int patientId) {
        this();
        idField.setText(String.valueOf(patientId));
        loadPatient();
    }

    //Load patient from the Backend
    @SuppressWarnings("deprecation")
    private void loadPatient(){
        try{
            int id = Integer.parseInt(idField.getText().trim());

            URL url= new URL("http://localhost:8080/api/patients/search/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if(conn.getResponseCode() != 200) {
                JOptionPane.showMessageDialog(this, "Patien not found.");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = br.readLine();
            JSONObject patient = new JSONObject(json);

            //Fill fields using JSON keys
            nameField.setText(patient.optString("fullName", ""));
            genderField.setText(patient.optString("gender", ""));
            dobField.setText(patient.optString("dob", ""));
            phoneField.setText(patient.optString("phoneNumber", ""));
            emailField.setText(patient.optString("email", ""));
            bloodField.setText(patient.optString("bloodType", ""));
            insuranceField.setText(patient.optString("fullName", ""));

            JOptionPane.showMessageDialog(this, "Patient Loaded!");

        } catch (Exception exc){
            exc.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading patient: " + exc.getMessage());
        }
    }


    //Update patent PUT request
    @SuppressWarnings("deprecation")
    private void updatePatient() {
        try{
            int id = Integer.parseInt(idField.getText().trim());

            JSONObject body = new JSONObject();
            body.put("fullName", nameField.getText());
            body.put("gender", genderField.getText());
            body.put("dob", dobField.getText());
            body.put("phoneNumber", phoneField.getText());
            body.put("email", emailField.getText());
            body.put("bloodType", bloodField.getText());
            body.put("insurance", insuranceField.getText());

            URL url = new URL("http://localhost:8080/api/patients/update/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(body.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() == 200){
                JOptionPane.showMessageDialog(this, "Patient updated Successfully!");

             
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                JOptionPane.showMessageDialog(this, "Faild: " + br.readLine());
            }

            conn.disconnect();

        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating patient: " + e.getMessage());
        }
    }
    
}
