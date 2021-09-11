package com.practice.wemakeprice.vo.alphabet;

import com.practice.wemakeprice.exception.alphabet.AlphabetEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlphabetVoTest {

    private char ch = 'c';
    private AlphabetVo alphabetVo;

    @BeforeEach
    public void initAlphabetVo() {
        this.alphabetVo  = new AlphabetVo(ch, 1);
    }

    @Test
    public void 알파벳_객체_생성_테스트() {

        // given
        // when
        // then
        assertThat(alphabetVo.getAlphabet(), equalTo(ch));
    }

    @Test
    public void 알파벳_사용_테스트() {
        // given
        // when
        // then
        char alphabet = alphabetVo.consume();
        assertThat(alphabet, equalTo(ch));
    }

    @Test
    public void 알파벳_모두_소진시_AlphabetEmptyException_발생_테스트() {
        // given
        // when
        // then
        char alphabet = alphabetVo.consume();
        AlphabetEmptyException alphabetEmptyException = assertThrows(AlphabetEmptyException.class, () -> {
            alphabetVo.consume();
        });

        assertThat(alphabetEmptyException.getMessage(), equalTo(ch + "을 모두 소진하였습니다."));
    }
}
