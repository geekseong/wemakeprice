package com.practice.wemakeprice.enumtype;

public enum ParserType {
    INCLUDE_TAG, REMOVE_TAG;

    public static ParserType from(String s) {
        return ParserType.valueOf(s.toUpperCase());
    }
}
