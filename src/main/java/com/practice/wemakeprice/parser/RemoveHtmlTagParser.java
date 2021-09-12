package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.infra.WebRequest;

public class RemoveHtmlTagParser extends ParserTemplate {

    private final static String TAG_PATTERN = "<([^>]+)>";

    public RemoveHtmlTagParser(WebRequest webRequest) {
        super(webRequest);
    }

    @Override
    protected String afterFetchDo(String html) {
        return html.replaceAll(TAG_PATTERN, "");
    }
}
