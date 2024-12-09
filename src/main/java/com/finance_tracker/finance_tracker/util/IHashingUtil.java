package com.finance_tracker.finance_tracker.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface IHashingUtil {

    default String hashSha256(String stringToHash) {
        try {
            // Crea un'istanza di MessageDigest per SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Calcola il digest della stringa come array di byte
            byte[] hashBytes = digest.digest(stringToHash.getBytes());
            // Converti l'array di byte in una stringa esadecimale
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

}
