package com.practice.wemakeprice.enumtype;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ParserType {
    INCLUDE_TAG, REMOVE_TAG;

    @JsonCreator
    public static ParserType from(String s) {
        return ParserType.valueOf(s.toUpperCase());
    }
}
