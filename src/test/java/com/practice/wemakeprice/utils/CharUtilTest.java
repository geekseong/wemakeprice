package com.practice.wemakeprice.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CharUtilTest {

    @Test
    public void 숫자_소문자_대문자_판별_테스트() {
        String number = "1234567890";
        String lowercase = "qwertyuiopasdfghjklzxcvbnm";
        String uppercase = "QWERTYUIOPASDFGHJKLZXCVBNM";

        // 숫자 검증
        for (int i = 0; i < number.length(); ++i) {
            char ch = number.charAt(i);
            assertThat(CharUtil.isNumber(ch), equalTo(true));
            assertThat(CharUtil.isLowerCase(ch), equalTo(false));
            assertThat(CharUtil.isUpperCase(ch), equalTo(false));
        }

        // 소문자 검증
        for (int i = 0; i < lowercase.length(); ++i) {
            char ch = lowercase.charAt(i);
            assertThat(CharUtil.isNumber(ch), equalTo(false));
            assertThat(CharUtil.isLowerCase(ch), equalTo(true));
            assertThat(CharUtil.isUpperCase(ch), equalTo(false));
        }

        // 대문자 검증
        for (int i = 0; i < uppercase.length(); ++i) {
            char ch = uppercase.charAt(i);
            assertThat(CharUtil.isNumber(ch), equalTo(false));
            assertThat(CharUtil.isLowerCase(ch), equalTo(false));
            assertThat(CharUtil.isUpperCase(ch), equalTo(true));
        }
    }
}
