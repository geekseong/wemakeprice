package com.practice.wemakeprice.utils;

public class StringUtil {
    private final static String EXCEPT_EN_AND_NUM_REGEX = "[^a-zA-Z0-9]";

    public static String removeAllExceptEnAndNum(String str) {
        return str.replaceAll(EXCEPT_EN_AND_NUM_REGEX, "");
    }
}
