package com.medical.gui;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class AddAppointmentWindow extends JFrame {

    private JComboBox<String> patientDropdown;
    private JComboBox<String> doctorDropdown;
    private JTextField dateField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField reasonField;
    private JTextField statusField;

    private ArrayList<Integer> patientIds = new ArrayList<>();
    private ArrayList<Integer> doctorIds = new ArrayList<>();

    //Load patients from the backend
    @SuppressWarnings("deprecation")
    private void loadPatients(){
        try{
            URL url = new URL("http://localhost:8080/api/patients/view-all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            JSONArray array = new JSONArray(new String(conn.getInputStream().readAllBytes()));
            for (int i = 0; i < array.length(); i++){
                JSONObject patient = array.getJSONObject(i);
                int id = patient.getInt("id");
                String fullName = patient.getString("fullName");

                patientIds.add(id);
                patientDropdown.addItem(fullName);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Failed to load patients.");
        }
    }

    //load doctors from backend
    @SuppressWarnings("deprecation")
    private void loadDoctors(){
        try{
            URL url = new URL("http://localhost:8080/api/doctors/view-all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            JSONArray array = new JSONArray(new String(conn.getInputStream().readAllBytes()));
            for (int i = 0; i < array.length(); i++){
                JSONObject doctor = array.getJSONObject(i);
                int id = doctor.getInt("id");
                String name = doctor.getString("name");

                doctorIds.add(id);
                doctorDropdown.addItem(name);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Failed to load doctors.");
        }
    }

    @SuppressWarnings("deprecation")
    private void submitAppointment(){
        try{
            int selectedPatientIndex = patientDropdown.getSelectedIndex();
            int selectedDoctorIndex = doctorDropdown.getSelectedIndex();

            int patientId = patientIds.get(selectedPatientIndex);
            int doctorId = doctorIds.get(selectedDoctorIndex);

            JSONObject body = new JSONObject();
            body.put("patientId", patientId);
            body.put("doctorId", doctorId);
            body.put("appointmentDate", dateField.getText());
            body.put("startTime", startTimeField.getText());
            body.put("endTime", endTimeField.getText());
            body.put("reason", reasonField.getText());
            body.put("status", statusField.getText());

            URL url = new URL ("http://localhost:8080/api/appointments/add");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(body.toString().getBytes());
            os.flush();
            os.close();

            if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201){
                JOptionPane.showMessageDialog(this, "Appointment Created Successfully!");
                dispose();
                new AppointmentWindow();
            } else{
                JOptionPane.showMessageDialog(this, "Failed to create appointment.");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public AddAppointmentWindow() {
        super("Add Appointment");
        setSize(600, 500);
        setLayout(new GridLayout(8, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Fields and Labels
        JLabel patientLabel = new JLabel("Select Patient:");
        JLabel doctorLabel = new JLabel("Select Doctor:");
        JLabel dateLabel = new JLabel("Appointment Date (YYYY-MM-DD):");
        JLabel startLabel = new JLabel("Start Time (HH:MM):");
        JLabel endLabel = new JLabel("End Time (HH:MM):");
        JLabel reasonLabel = new JLabel("Reason:");
        JLabel statusLabel = new JLabel("Status:");

        patientDropdown = new JComboBox<>();
        doctorDropdown = new JComboBox<>();
        dateField = new JTextField();
        startTimeField = new JTextField();
        endTimeField = new JTextField();
        reasonField = new JTextField();
        statusField = new JTextField();
        

        //Add componenets
        add(patientLabel); add(patientDropdown);
        add(doctorLabel); add(doctorDropdown);
        add(dateLabel); add(dateField);
        add(startLabel); add(startTimeField);
        add(endLabel); add(endTimeField);
        add(reasonLabel); add(reasonField);
        add(statusLabel); add(statusField);

        JButton submitButton = new JButton("Create Appointment");
        JButton backButton = new JButton("Back");

        add (submitButton);
        add(backButton);

        submitButton.addActionListener(e -> submitAppointment());
        backButton.addActionListener(e ->{
            dispose();
            new AppointmentWindow();
        });

        //Load dropdown data
        loadPatients();
        loadDoctors();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    
}
