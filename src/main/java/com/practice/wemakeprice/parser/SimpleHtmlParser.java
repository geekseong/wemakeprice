package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.infra.WebRequest;

public class SimpleHtmlParser extends ParserTemplate {

    public SimpleHtmlParser(WebRequest webRequest) {
        super(webRequest);
    }

    @Override
    protected String afterFetchDo(String html) {
        return html;
    }
}
