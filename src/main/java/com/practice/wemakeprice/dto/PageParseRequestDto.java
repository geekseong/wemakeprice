package com.practice.wemakeprice.dto;

import com.practice.wemakeprice.enumtype.ParserType;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class PageParseRequestDto {

    @URL(message = "URL 형식을 다시 확인 해주세요.")
    @NotNull(message = "URL은 필수입니다.")
    private String url;

    @NotNull(message = "type은 필수 입니다.")
    private ParserType type;

    @PositiveOrZero(message = "출력단위묶음은 0 또는 양수만 올 수 있습니다.")
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

    public void setType(String type) {
        this.type = ParserType.from(type);
    }

    public int getChunkNum() {
        return chunkNum;
    }

    public void setChunkNum(int chunkNum) {
        this.chunkNum = chunkNum;
    }
}
