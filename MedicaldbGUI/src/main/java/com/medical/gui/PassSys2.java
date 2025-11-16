package com.medical.gui;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class PassSys2 {

    private static final String AES_MODE = "AES/ECB/PKCS5Padding";

    // AES-256 password encryption
    public static String encryptAES256(String keyString, String plainText) throws Exception {
        byte[] keyBytes = keyString.getBytes("UTF-8");
        if (keyBytes.length != 32)
            throw new IllegalArgumentException("AES-256 key must be 32 chars.");
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // AES-256 password decryption (matching encryptAES256)
    public static String decryptAES256(String keyString, String base64CipherText) throws Exception {
        byte[] keyBytes = keyString.getBytes("UTF-8");
        if (keyBytes.length != 32)
            throw new IllegalArgumentException("AES-256 key must be 32 chars.");
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decoded = Base64.getDecoder().decode(base64CipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8").trim();
    }

// Authenticate user by decrypting stored AES-encrypted password and comparing plaintexts
    public static boolean authenticate(String username, String inputPassword, String aesKey, Map<String, String> userMap) {
        try {
            if (!userMap.containsKey(username)) return false; // username not present

            String storedEncrypted = userMap.get(username); // this is Base64(AES(ciphertext))
            String storedPlainPassword = decryptAES256(aesKey, storedEncrypted);

            // compare exact plaintexts (trim inputs to be safe)
            return storedPlainPassword.equals(inputPassword == null ? null : inputPassword.trim());
        } catch (Exception e) {
            // optionally log e.getMessage() during debugging
            return false;
        }
    }

    // Save user data to an RSA-encrypted file
    public static void saveUserFile(Map<String, String> userMap, PublicKey pubKey, String filename) throws Exception {
    StringBuilder sb = new StringBuilder();

    for (Map.Entry<String, String> entry : userMap.entrySet()) {
        String line = entry.getKey() + ":" + entry.getValue();
        // Encrypt each username:password pair individually
        String encryptedLine = RSAUtil.encryptRSA(line, pubKey); // each line < 245 bytes
        sb.append(encryptedLine).append("\n");
    }

    try (BufferedWriter bw = java.nio.file.Files.newBufferedWriter(java.nio.file.Path.of(filename), java.nio.charset.StandardCharsets.UTF_8)) {
    for (Map.Entry<String, String> entry : userMap.entrySet()) {
        String line = entry.getKey() + ":" + entry.getValue();
        String encryptedLine = RSAUtil.encryptRSA(line, pubKey);
        bw.write(encryptedLine);
        bw.newLine(); // safe line separator
        }
    }
}

    // Load and decrypt user data from RSA-encrypted file
    public static Map<String, String> loadUserFile(String filename, PrivateKey privKey) throws Exception {
    List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Path.of(filename),
            java.nio.charset.StandardCharsets.UTF_8);

    Map<String, String> userMap = new HashMap<>();

    for (String line : lines) {
        if (line.trim().isEmpty()) continue; // skip empty lines

        // Decrypt each line individually
        String decryptedLine = RSAUtil.decryptRSA(line.trim(), privKey);

        // Split into username and AES-encrypted password
        String[] parts = decryptedLine.split(":", 2);
        if (parts.length == 2) {
            userMap.put(parts[0], parts[1]);
        }
    }

    return userMap;
}

}
