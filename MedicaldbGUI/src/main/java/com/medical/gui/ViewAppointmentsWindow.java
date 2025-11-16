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

public class ViewAppointmentsWindow extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    private void openEditWindow(){
        int row = table.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Select an appointment first.");
            return;
        }

        int appointmentId = (int) tableModel.getValueAt(row, 0);
        //int patientId = (int) tableModel.getValueAt(row, 10);
        //int doctorId = (int) tableModel.getValueAt(row, 11);

        dispose();
        new EditAppointmentWindow(appointmentId);
    }

    public ViewAppointmentsWindow(){
        super("View Appointmens");
        setSize(1000,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("All Appointments", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        add(label, BorderLayout.NORTH);

        //Table Setup
        String[] columnNames = {"ID", "PatientID", "DoctorID", "Appointment Date", "Start Time", "End Time", "Reason", "Status", "Created", "Updated", "PatienID_Hide", "DoctorID_hide" };
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // table.getColumnModel().getColumn(10).setMinWidth(0);
        // table.getColumnModel().getColumn(10).setMaxWidth(0);
        // table.getColumnModel().getColumn(10).setWidth(0);

        // table.getColumnModel().getColumn(11).setMinWidth(0);
        // table.getColumnModel().getColumn(11).setMaxWidth(0);
        // table.getColumnModel().getColumn(11).setWidth(0);



        //Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("back");
        JButton editButton = new JButton("Edit Selected");

        refreshButton.addActionListener(e -> loadAppointments());
        editButton.addActionListener(e -> openEditWindow());
        backButton.addActionListener(e -> {
            dispose();
            new AppointmentWindow(); //return to previous screen
        });



        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);
        

        setLocationRelativeTo(null);
        setVisible(true);

        //Load data on startup
        loadAppointments();
    }

    @SuppressWarnings("deprecation")
    private void loadAppointments(){
        try{
            URL url = new URL("http://localhost:8080/api/appointments/view-all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200){
                throw new RuntimeException("Failed : HTTP error code: " + conn.getResponseCode());

            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while((output = br.readLine()) != null) sb.append(output);

            //conn.disconnect();

            //Parse JSON
            JSONArray appointmentsArray = new JSONArray(sb.toString());
            tableModel.setRowCount(0); //Clear table before loading

            for (int i = 0; i < appointmentsArray.length(); i++){
                JSONObject appointment = appointmentsArray.getJSONObject(i);

                JSONObject patientObj = appointment.optJSONObject("patient");
                JSONObject doctorObj = appointment.optJSONObject("doctor");

                String patientName = (patientObj != null)
                ? patientObj.optString("fullName", "")
                : "";

                String doctorName = (doctorObj != null)
                ? doctorObj.optString("name", "")
                : "";

                int patientId = patientObj != null ? patientObj.optInt("id", 0) : 0;
                int doctorId = doctorObj !=null ? doctorObj.optInt("id", 0) : 0;

                Object[] row = {
                    appointment.optInt("id"),
                    patientName,
                    doctorName,
                    appointment.optString("appointmentDate", ""),
                    appointment.optString("startTime", ""),
                    appointment.optString("endTime", ""),
                    appointment.optString("reason", ""),
                    appointment.optString("status", ""),
                    appointment.optString("createdAt", ""),
                    appointment.optString("updatedAt", ""),
                    patientId,
                    doctorId
                };
                tableModel.addRow(row);
            }

            conn.disconnect();

            //Hide the last 2 columns
            table.removeColumn(table.getColumnModel().getColumn(11));
            table.removeColumn(table.getColumnModel().getColumn(10));

        } catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load appointments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
