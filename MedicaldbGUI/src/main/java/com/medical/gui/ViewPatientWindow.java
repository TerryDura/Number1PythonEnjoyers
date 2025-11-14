package com.medical.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ViewPatientWindow extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewPatientWindow(){
        super("View Patients");
        setSize(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("All Patients", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        add(label, BorderLayout.NORTH);

        //Table Setup
        String[] columnNames = {"ID", "Full Name", "Gender", "DOB", "Phone", "Email", "Blood Type", "Insurance"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        //Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("back");

        refreshButton.addActionListener(e -> loadPatients());
        backButton.addActionListener(e -> {
            dispose();
            new PatientWindow(); //return to previous screen
        });

        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

        //Load data on startup
        loadPatients();

    }

    private void loadPatients(){
        try{
            URL url = new URL("http://localhost:8080/api/patients/view-all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code: " + conn.getResponseCode());

            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while((output = br.readLine()) !=null){
                sb.append(output);
            }

            conn.disconnect();

            //Parse JSON
            JSONArray patientsArray = new JSONArray(sb.toString());
            tableModel.setRowCount(0); //Clear table before loading

            for (int i = 0; i < patientsArray.length(); i++){
                JSONObject patient = patientsArray.getJSONObject(i);
                Object[] row = {
                    patient.getInt("id"),
                    patient.optString("fullName", ""),
                    patient.optString("gender", ""),
                    patient.optString("dob", ""),
                    patient.optString("phoneNumber", ""),
                    patient.optString("email", ""),
                    patient.optString("bloodType", ""),
                    patient.optString("insurance", ""),
                };
                tableModel.addRow(row);
            }
        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load patients: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
