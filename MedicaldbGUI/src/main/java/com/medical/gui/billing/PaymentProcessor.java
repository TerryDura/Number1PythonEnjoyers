// src/main/java/com/medical/gui/billing/PaymentProcessor.java
package com.medical.gui.billing;

import java.sql.*;
import java.util.*;

public class PaymentProcessor {
    private static final String URL = "jdbc:mysql://localhost:3306/medicaldb";
    private static final String USER = "root";
    private static final String PASS = "yourpasswordhere";

    private static final Map<String, Double> TREATMENT_COSTS = Map.of(
        "Flu Shot", 50.0, "Psychotherapy", 150.0, "Weight loss", 100.0,
        "Medication", 80.0, "Antacids", 30.0, "Ozempic", 200.0,
        "Aspirin", 10.0, "Tums", 15.0
    );

    private static final Map<String, Double> INSURANCE_DISCOUNT = Map.of(
        "Blue Cross", 0.50, "Cigna", 0.40, "Humana", 0.30,
        "Kaiser Permanente", 0.60, "Imperial Insurance", 0.20,
        "United HealthCare", 0.50
    );

    public static String calculateBill(int patientId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // Get insurance
            String insuranceSql = "SELECT Pat_Insurance FROM patients WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(insuranceSql);
            ps1.setInt(1, patientId);
            ResultSet rs1 = ps1.executeQuery();
            String insurance = rs1.next() ? rs1.getString(1) : null;

            // Get latest treatment/prescription
            String recordSql = """
                SELECT Treatment, prescription FROM medical_records 
                WHERE patient_id = ? ORDER BY visit_date DESC LIMIT 1
                """;
            PreparedStatement ps2 = conn.prepareStatement(recordSql);
            ps2.setInt(1, patientId);
            ResultSet rs2 = ps2.executeQuery();

            String treatment = null, prescription = null;
            if (rs2.next()) {
                treatment = rs2.getString("Treatment");
                prescription = rs2.getString("prescription");
            }

            String key = (treatment != null) ? treatment : prescription;
            double base = TREATMENT_COSTS.getOrDefault(key, 100.0);
            double discount = INSURANCE_DISCOUNT.getOrDefault(insurance, 0.0);
            double total = base * (1 - discount);

            return String.format("""
                === BILLING SUMMARY ===
                Patient ID: %d
                Treatment/Rx: %s
                Base Cost: $%.2f
                Insurance: %s (%.0f%% off)
                TOTAL DUE: $%.2f
                """, patientId, key, base, insurance != null ? insurance : "None", discount*100, total);

        } catch (Exception e) {
            return "Error calculating bill: " + e.getMessage();
        }
    }
}