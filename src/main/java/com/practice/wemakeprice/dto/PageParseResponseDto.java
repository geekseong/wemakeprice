package com.practice.wemakeprice.dto;

public class PageParseResponseDto {
    private String quotient;
    private String remainder;

    public PageParseResponseDto(String quotient, String remainder) {
        this.quotient = quotient;
        this.remainder = remainder;
    }

    public String getQuotient() {
        return quotient;
    }

    public String getRemainder() {
        return remainder;
    }
}
