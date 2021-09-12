package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.infra.WebRequest;

public class SimpleHtmlParser extends Parser {

    public SimpleHtmlParser(WebRequest webRequest, int chunkNum) {
        super(webRequest, chunkNum);
    }

    @Override
    protected String afterFetchDo(String html) {
        return html;
    }
}
