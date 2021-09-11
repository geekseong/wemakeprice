package com.practice.wemakeprice.vo.number;

import com.practice.wemakeprice.exception.EmptyException;
import com.practice.wemakeprice.exception.UnSupportedCharacterException;
import com.practice.wemakeprice.utils.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NumberListVo {
    private static final int NUM_COUNT = 10;
    private final List<NumberVo> numberVoList;
    private int index = 0;

    public NumberListVo(String rawData) {
        this.numberVoList = initCount(rawData);
    }

    private List<NumberVo> initCount(String rawData){;

        int[] numberCountTable = new int[10];
        Arrays.fill(numberCountTable, 0);

        for (int i = 0; i < rawData.length(); ++i) {
            char ch = rawData.charAt(i);
            if (StringUtil.isNumber(ch)) {
                numberCountTable[ch - '0']++;
            } else{
                throw new UnSupportedCharacterException("숫자만 입력 가능합니다. your input : '" + ch+"'");
            }
        }

        List<NumberVo> numberVoList = new LinkedList<>();
        for (int i = 0; i < NUM_COUNT; ++i) {
            if (numberCountTable[i] != 0) {
                numberVoList.add(new NumberVo(i, numberCountTable[i]));
            }
        }
        return numberVoList;
    }

    public boolean isAllNumberEmpty() {
        return numberVoList.isEmpty();
    }

    public int getNextNumber() {
        if( this.isAllNumberEmpty() )
            throw new EmptyException("모든 숫자를 소진하였습니다.");

        int num = this.numberVoList.get(0).consume();
        if (this.numberVoList.get(0).isEmpty()) {
            this.numberVoList.remove(0);
        }

        return num;
    }

}
