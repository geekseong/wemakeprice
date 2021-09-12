package com.practice.wemakeprice.infra;

public class WebRequest {
    private final String url;

    public WebRequest(String url) {
        this.url = url;
    }

    public String get() {
        return "html";
    }
}
