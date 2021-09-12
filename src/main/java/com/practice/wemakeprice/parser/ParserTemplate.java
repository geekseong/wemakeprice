package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.infra.WebRequest;

public abstract class ParserTemplate {
    protected final static String EXCEPT_EN_AND_NUM_REGEX = "[^a-zA-Z0-9]";
    private final WebRequest webRequest;

    public ParserTemplate(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public String parse(){
        String data = webRequest.get();
        String parsedData = afterFetchDo(data);
        return removeExceptNumAndEn(parsedData);
    }

    private String removeExceptNumAndEn(String parsedData) {
        return parsedData.replaceAll(EXCEPT_EN_AND_NUM_REGEX, "");
    }

    protected abstract String afterFetchDo(String data);
}
