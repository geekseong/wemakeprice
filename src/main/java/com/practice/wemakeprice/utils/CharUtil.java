package com.practice.wemakeprice.utils;

public class CharUtil {

    public static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isUpperCase(char ch) {
        return ch >='A' && ch <= 'Z';
    }

    public static boolean isLowerCase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }
}
