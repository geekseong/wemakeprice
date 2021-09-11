package com.practice.wemakeprice.vo.alphabet;

import com.practice.wemakeprice.exception.alphabet.AlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.LowercaseAlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.UppercaseAlphabetEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlphabetPairListVoTest {

    private String str = "aC";
    private AlphabetPairListVo alphabetPairListVo;

    @BeforeEach
    public void initAlphabetVo() {
        this.alphabetPairListVo = new AlphabetPairListVo(str);
    }

    @Test
    public void 알파벳_페어_컬랙션객체_consume_테스트() {

        // given
        // when
        // then
        char lowercase = alphabetPairListVo.consume();
        char uppercase = alphabetPairListVo.consume();
        assertThat(uppercase, equalTo('C'));
        assertThat(lowercase, equalTo('a'));
    }

    @Test
    public void 알바벳_페어_컬렉션객체_모든_알파벳_소진시_AlphabetEmptyException_발생_테스트() {
        alphabetPairListVo.consume();
        alphabetPairListVo.consume();

        AlphabetEmptyException alphabetEmptyException = assertThrows(AlphabetEmptyException.class, () -> {
            alphabetPairListVo.consume();
        });

        assertThat(alphabetEmptyException.getMessage(), equalTo("모든 알파벳을 소진하였습니다."));
    }

}
