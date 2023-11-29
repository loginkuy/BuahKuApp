package com.example.buahku.helper;
import org.apache.commons.codec.digest.Crypt;
public class HashPassword {
    public static String crypt(String input, String salt) {
        return Crypt.crypt(input.getBytes(), salt);
    }
}
