package com.medical.gui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class EditAppointmentWindow extends JFrame {
    private JTextField idField, dateField, startTimeField, endTimeField, reasonField, statusField;
    private JTextField patientField, doctorField;

    public EditAppointmentWindow(int appointmentId){
        setupUI();
        idField.setText(String.valueOf(appointmentId));
        loadAppointmentById(appointmentId);
    }

    private void setupUI(){
        setTitle("Edit Appointment");
        setSize(600, 500);
        setLayout(new BorderLayout());

        //Form window
        JPanel form = new JPanel(new GridLayout(10, 2, 10, 5));

        idField = addField(form, "Appointment ID:");
        patientField = addField(form, "Patient ID:");
        doctorField = addField(form, "Doctor ID:");
        dateField = addField(form, "Date (YYYY-MM-DD):");
        startTimeField = addField(form, "Start Time (HH:MM):");
        endTimeField = addField(form, "End Time (HH:MM):");
        reasonField = addField(form, "Reason:");
        statusField = addField(form, "Status:");

        add(form, BorderLayout.CENTER);

        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton loadButton = new JButton("Load");
        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("back");

        loadButton.addActionListener(e -> loadAppointmentById(Integer.parseInt(idField.getText())));
        updateButton.addActionListener(e -> updateAppointment());
        backButton.addActionListener(e -> {
            dispose();
            new ViewAppointmentsWindow();
        });


        buttonPanel.add(loadButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);


    }

    private JTextField addField(JPanel panel, String label){
        panel.add(new JLabel(label));
        JTextField field = new JTextField();
        panel.add(field);
        return field;
    }

    

    @SuppressWarnings("deprecation")
    private void loadAppointmentById(int id){
        try{
            
            URL url = new URL("http://localhost:8080/api/appointments/search/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200){
                JOptionPane.showMessageDialog(this, "Appointment not found.");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //String json = br.readLine();
            JSONObject appt = new JSONObject(br.readLine());

            //Fill fields
            patientField.setText(String.valueOf(appt.getJSONObject("patient").getInt("id")));
            doctorField.setText(String.valueOf(appt.getJSONObject("doctor").getInt("id")));
            dateField.setText(appt.getString("appointmentDate"));
            startTimeField.setText(appt.getString("startTime"));
            endTimeField.setText(appt.getString("endTime"));
            reasonField.setText(appt.getString("reason"));
            statusField.setText(appt.optString("status", ""));

            JOptionPane.showMessageDialog(this, "Appointment Loaded!");

        } catch (Exception exc){
            exc.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading appointment: " + exc.getMessage());
        }
    }

    //Update Appointment
    @SuppressWarnings("deprecation")
    private void updateAppointment(){
        try{

            int id = Integer.parseInt(idField.getText());

            JSONObject body = new JSONObject();
            body.put("appointmentDate", dateField.getText());
            body.put("startTime", startTimeField.getText());
            body.put("endTime", endTimeField.getText());
            body.put("reason", reasonField.getText());
            body.put("status", statusField.getText());

            JSONObject patientObj = new JSONObject();
            patientObj.put("id", Integer.parseInt(patientField.getText()));
            body.put("patient", patientObj);

            JSONObject doctorObj = new JSONObject();
            doctorObj.put("id", Integer.parseInt(doctorField.getText()));
            body.put("doctor", doctorObj);

            URL url = new URL("http://localhost:8080/api/appointments/update/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(body.toString().getBytes());
            os.flush();

            if(conn.getResponseCode() == 200){
                JOptionPane.showMessageDialog(this, "Appointment Updated Successfully!");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                JOptionPane.showMessageDialog(this, "Failed: " + br.readLine());
            }

            //conn.disconnect();

        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating appointment: " + ex.getMessage());

        }
    }

    
    
}
