package com.practice.wemakeprice.vo.number;

import com.practice.wemakeprice.exception.number.NumberEmptyException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberListVoTest {

    @Test
    public void 숫자_리스트_객체_테스트() {

        // given
        String number = "1234567890";
        int[] numCountArr = new int[10];
        Arrays.fill(numCountArr, 1);

        // when
        NumberListVo numberListVo = new NumberListVo(number);
        while (!numberListVo.isAllNumberEmpty()) {
            int nextNum = numberListVo.consume();
            numCountArr[nextNum]--;
        }

        //then
        for (int count : numCountArr) {
            assertThat(0, equalTo(count));
        }
    }

    @Test
    public void 숫자_리스트_객체_전체소진시_EmptyException_발생_테스트() {

        //given
        String number = "1";
        NumberListVo numberListVo = new NumberListVo(number);

        //when
        numberListVo.consume();
        NumberEmptyException numberEmptyException = assertThrows(NumberEmptyException.class, () -> {
            numberListVo.consume();
        });

        assertThat(numberEmptyException.getMessage(), equalTo("모든 숫자를 소진하였습니다."));
    }
}
