package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.infra.WebRequest;

public class RemoveHtmlTagParser extends Parser {

    private final static String TAG_PATTERN = "<([^>]+)>";

    public RemoveHtmlTagParser(WebRequest webRequest, int chunkNum) {
        super(webRequest, chunkNum);
    }

    @Override
    protected String parseByParserType(String html) {
        return html.replaceAll(TAG_PATTERN, "");
    }
}
