package com.medical.gui;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/patients";

    public static String sendRequest(String endpoint, String method, String jsonBody){
        try{
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            if (jsonBody !=null && !jsonBody.isEmpty()){
                try (OutputStream os = conn.getOutputStream()){
                    byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) !=null) response.append(line);
            br.close();
            conn.disconnect();

            return response.toString();

        } catch (Exception e){
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    
}
