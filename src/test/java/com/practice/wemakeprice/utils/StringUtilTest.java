package com.practice.wemakeprice.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class StringUtilTest {

    @Test
    public void 불필요한_문자_제거_테스트(){
        String tc = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890<>!~@#$%^&*()_+{}:\",./ㄱㄴㄷ가나다";
        String answer = StringUtil.removeAllExceptEnAndNum(tc);
        assertThat(answer, notNullValue());
        assertThat(answer, equalTo("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890"));

    }

    @Test
    public void 숫자_소문자_대문자_판별_테스트() {
        String number = "1234567890";
        String lowercase = "qwertyuiopasdfghjklzxcvbnm";
        String uppercase = "QWERTYUIOPASDFGHJKLZXCVBNM";

        // 숫자 검증
        for (int i = 0; i < number.length(); ++i) {
            char ch = number.charAt(i);
            assertThat(StringUtil.isNumber(ch), equalTo(true));
            assertThat(StringUtil.isLowerCase(ch), equalTo(false));
            assertThat(StringUtil.isUpperCase(ch), equalTo(false));
        }

        // 소문자 검증
        for (int i = 0; i < lowercase.length(); ++i) {
            char ch = number.charAt(i);
            assertThat(StringUtil.isNumber(ch), equalTo(false));
            assertThat(StringUtil.isLowerCase(ch), equalTo(true));
            assertThat(StringUtil.isUpperCase(ch), equalTo(false));
        }

        // 대문자 검증
        for (int i = 0; i < uppercase.length(); ++i) {
            char ch = number.charAt(i);
            assertThat(StringUtil.isNumber(ch), equalTo(false));
            assertThat(StringUtil.isLowerCase(ch), equalTo(false));
            assertThat(StringUtil.isUpperCase(ch), equalTo(true));
        }
    }
}
