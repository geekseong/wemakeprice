package com.practice.wemakeprice.vo.alphabet;

import com.practice.wemakeprice.exception.UnSupportedCharacterException;
import com.practice.wemakeprice.exception.alphabet.AlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.LowercaseAlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.UppercaseAlphabetEmptyException;
import com.practice.wemakeprice.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AlphabetPairListVo {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int EN_COUNT = 26;
    private final List<AlphabetPairVo> alphabetPairVoList;

    public AlphabetPairListVo(String rawData) {
        this.alphabetPairVoList = initAlphabetPairList(rawData);
    }

    private List<AlphabetPairVo> initAlphabetPairList(String rawData){;

        int[] lowercaseCount = new int[EN_COUNT];
        Arrays.fill(lowercaseCount, 0);
        int[] uppercaseCount = new int[EN_COUNT];
        Arrays.fill(uppercaseCount, 0);

        for (int i = 0; i < rawData.length(); ++i) {
            char ch = rawData.charAt(i);
            if (StringUtil.isLowerCase(ch)) {
                lowercaseCount[ch - 'a']++;
            } else if (StringUtil.isUpperCase(ch)) {
                uppercaseCount[ch - 'A']++;
            } else{
                throw new UnSupportedCharacterException("소문자, 대문자만 입력 가능합니다. your input : '" + ch+"'");
            }
        }

        List<AlphabetPairVo> alphabetPairVoList = new LinkedList<>();
        for (int i = 0; i < EN_COUNT; ++i) {

            if (lowercaseCount[i] == 0 && uppercaseCount[i] == 0) {
                continue;
            }

            char lowercase = (char)(i + 'a');
            char uppercase = (char)(i + 'A');

            AlphabetVo lowercaseAlphabetVo = new AlphabetVo(lowercase, lowercaseCount[i]);
            AlphabetVo uppercaseAlphabetVo = new AlphabetVo(uppercase, uppercaseCount[i]);
            alphabetPairVoList.add(new AlphabetPairVo(lowercaseAlphabetVo, uppercaseAlphabetVo));
        }

        return alphabetPairVoList;
    }

    private boolean isAllAlphabetEmpty() {
        return this.alphabetPairVoList.isEmpty();
    }

    public char consume() {
        if (isAllAlphabetEmpty()) {
            throw new AlphabetEmptyException("모든 알파벳을 소진하였습니다.");
        }

        char ch = 0;
        final AlphabetPairVo alphabetPairVo = this.alphabetPairVoList.get(0);

        // 대문자가 있는지 검사.
        try {
            ch = alphabetPairVo.uppercaseConsume();
        } catch (UppercaseAlphabetEmptyException e) {

            // 대문자가 없을경우, 소문자 검사
            try {
                ch = alphabetPairVo.lowercaseConsume();
            } catch (LowercaseAlphabetEmptyException emptyException) {
                throw new AlphabetEmptyException("모든 알파벳을 소진하였습니다.");
            }
        }

        if (alphabetPairVo.isAllPairEmpty()) {
            this.alphabetPairVoList.remove(0);
        }

        return ch;
    }
}

