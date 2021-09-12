package com.practice.wemakeprice.dto;

import com.practice.wemakeprice.enumtype.ParserType;

public class PageParseRequestDto {
    private String url;
    private ParserType type;
    private int chunkNum;

    public PageParseRequestDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ParserType getType() {
        return type;
    }

    public void setType(ParserType type) {
        this.type = type;
    }

    public int getChunkNum() {
        return chunkNum;
    }

    public void setChunkNum(int chunkNum) {
        this.chunkNum = chunkNum;
    }
}
