package com.example.buahku.helper;

public class Helper {
    public static String numberWithCommas(Integer x) {
        return x != null ? String.format("%,d", x) : null;
    }
}
