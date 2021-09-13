package com.practice.wemakeprice.vo.number;

import com.practice.wemakeprice.exception.number.NumberEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberVoTest {

    private final int targetNum = 9;
    private  NumberVo numberVo;

    @BeforeEach
    public void initNumberVo() {
        this.numberVo = new NumberVo(targetNum);
    }


    @Test
    public void 남은숫자_없을경우_EmptyException_발생_테스트() {

        //given
        //when
        //then
        NumberEmptyException numberEmptyException = assertThrows(NumberEmptyException.class, () -> {
            numberVo.consume();
        });
        assertThat(numberEmptyException.getMessage(), equalTo(targetNum+"을 모두 소진하였습니다."));
    }
}
