package com.practice.wemakeprice.vo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PageParseVoTest {

    private final String tc = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    @Test
    public void 소문자_대문자_숫자_교차출력_결과_테스트(){

        // given
        //when
        PageParseVo result = new PageParseVo(tc);

        //then
        String expect = "A0a1B2b3C4c5D6d7E8e9FfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        assertThat(result.getCrossStringResult(), equalTo(expect));
    }

    @Test
    public void 교차출력문자열_몫_나머지_테스트() {

        // given
        int chunkNumber = 10;
        String expectedQuotient = "A0a1B2b3C4,c5D6d7E8e9,FfGgHhIiJj,KkLlMmNnOo,PpQqRrSsTt,UuVvWwXxYy";
        String expectedRemainder = "Zz";
        PageParseVo pageParseResultVo = new PageParseVo(tc);

        //when
        PageParseVo.Result result = pageParseResultVo.getResult(chunkNumber);

        //then
        assertThat(result.getQuotient(), equalTo(expectedQuotient));
        assertThat(result.getRemainder(), equalTo(expectedRemainder));

    }

}
