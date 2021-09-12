package com.practice.wemakeprice.infra;

import com.practice.wemakeprice.exception.RequestFailException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebRequest {
    private final String url;

    public WebRequest(String url) {
        this.url = url;
    }

    public String get() {

        try {
            Document document = Jsoup.connect(this.url).get();
            return document.toString();
        } catch (IOException e) {
            throw new RequestFailException("요청에 실패하였습니다. request url : " + this.url);
        }
    }
}
