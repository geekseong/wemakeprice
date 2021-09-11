package com.practice.wemakeprice.vo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PageParseResultVoTest {

    private final String tc = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    @Test
    public void 소문자_대문자_숫자_교차출력_결과_테스트(){

        // given
        //when
        PageParseResultVo result = new PageParseResultVo(tc);

        //then
        String expect = "A0a1B2b3C4c5D6d7E8e9FfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        assertThat(result.getCrossStringResult(), equalTo(expect));
    }

}
