package com.practice.wemakeprice.utils;

public class StringUtil {
    private final static String EXCEPT_EN_AND_NUM_REGEX = "[^a-zA-Z0-9]";

    public static String removeAllExceptEnAndNum(String str) {
        return str.replaceAll(EXCEPT_EN_AND_NUM_REGEX, "");
    }

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
