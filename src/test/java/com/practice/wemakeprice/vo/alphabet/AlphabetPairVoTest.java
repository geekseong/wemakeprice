package com.practice.wemakeprice.vo.alphabet;

import com.practice.wemakeprice.exception.alphabet.AlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.LowercaseAlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.UppercaseAlphabetEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlphabetPairVoTest {

    private char lowercase = 'c';
    private char uppercase = 'C';
    private AlphabetPairVo alphabetPairVo;

    @BeforeEach
    public void initAlphabetVo() {
        this.alphabetPairVo = new AlphabetPairVo(new AlphabetVo(lowercase, 1), new AlphabetVo(uppercase, 1));
    }


    @Test
    public void 알파벳_사용_테스트() {
        // given
        // when
        char uppercaseCh = this.alphabetPairVo.uppercaseConsume();
        char lowercaseCh = this.alphabetPairVo.lowercaseConsume();

        // then
        assertThat(uppercaseCh, equalTo('C'));
        assertThat(lowercaseCh, equalTo('c'));
    }

    @Test
    public void 알파벳_모두_소진시_LowercaseAlphabetEmptyException_발생_테스트() {

        // given
        // when
        this.alphabetPairVo.lowercaseConsume();
        // then
        LowercaseAlphabetEmptyException lowercaseAlphabetEmptyException = assertThrows(LowercaseAlphabetEmptyException.class, () ->{
            this.alphabetPairVo.lowercaseConsume();
        });

        assertThat(lowercaseAlphabetEmptyException.getMessage(), equalTo(lowercase + "을 모두 소진하였습니다."));
    }

    @Test
    public void 알파벳_모두_소진시_UppercaseAlphabetEmptyException_발생_테스트() {

        // given
        // when
        this.alphabetPairVo.uppercaseConsume();
        // then
        UppercaseAlphabetEmptyException uppercaseAlphabetEmptyException = assertThrows(UppercaseAlphabetEmptyException.class, () ->{
            this.alphabetPairVo.uppercaseConsume();
        });

        assertThat(uppercaseAlphabetEmptyException.getMessage(), equalTo(uppercase + "을 모두 소진하였습니다."));
    }
}
