package com.practice.wemakeprice.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StringUtilTest {

    @Test
    @DisplayName("불필요한_문자_제거_테스트")
    public void 불필요한_문자_제거_테스트(){
        String tc = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890<>!~@#$%^&*()_+{}:\",./ㄱㄴㄷ가나다";
        String answer = StringUtil.removeAllExceptEnAndNum(tc);
        assertThat(answer, notNullValue());
        assertThat(answer, equalTo("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890"));

    }
}
