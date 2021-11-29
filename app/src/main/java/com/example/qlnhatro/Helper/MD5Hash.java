package com.example.qlnhatro.Helper;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Hash {
    public static String encrypt(String srcText) {
        try {
            MessageDigest msd = MessageDigest.getInstance("MD5");
            byte[] messageDigest = msd.digest(srcText.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
            return "";
        }

    }
}
