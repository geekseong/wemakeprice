package com.practice.wemakeprice.vo;

import com.practice.wemakeprice.exception.UnSupportedCharacterException;
import com.practice.wemakeprice.utils.StringUtil;
import com.practice.wemakeprice.vo.alphabet.AlphabetPairListVo;
import com.practice.wemakeprice.vo.number.NumberListVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageParseVo {
    private final NumberListVo numberListVo;
    private final AlphabetPairListVo alphabetPairListVo;
    private final String crossStringResult;

    public PageParseVo(String dirty){
        String clean = StringUtil.removeAllExceptEnAndNum(dirty);
        NumberAlphabetSplit numAlphabetSplit = new NumberAlphabetSplit(clean);
        this.numberListVo = new NumberListVo(numAlphabetSplit.number);
        this.alphabetPairListVo = new AlphabetPairListVo(numAlphabetSplit.alphabet);
        this.crossStringResult = makeCrossStringResult();
    }

    private String makeCrossStringResult() {
        StringBuilder builder = new StringBuilder();
        while (!alphabetPairListVo.isAllAlphabetEmpty() || !numberListVo.isAllNumberEmpty()) {
            if (!alphabetPairListVo.isAllAlphabetEmpty()) {
                builder.append(alphabetPairListVo.consume());
            }

            if (!numberListVo.isAllNumberEmpty()) {
                builder.append(numberListVo.consume());
            }
        }

        return builder.toString();
    }

    public String getCrossStringResult() {
        return this.crossStringResult;
    }
    public Result getResult(int chunkNum) {
        return new Result(chunkNum, crossStringResult);
    }

    public class Result{
        private final String quotient;
        private final String remainder;

        public Result(int chunkNum, String crossStringResult) {

            int q = crossStringResult.length() / chunkNum;
            StringBuilder builder = new StringBuilder();
            int offset = 0;
            List<String> chunkList = new ArrayList<>();
            for (; offset < q * chunkNum; offset += chunkNum) {
                chunkList.add(crossStringResult.substring(offset, offset + chunkNum));
            }

            this.quotient = chunkList.stream().collect(Collectors.joining(","));
            this.remainder = crossStringResult.substring(offset);
        }

        public String getQuotient() {
            return quotient;
        }

        public String getRemainder() {
            return remainder;
        }
    }

    private class NumberAlphabetSplit{
        private final String number;
        private final String alphabet;

        public NumberAlphabetSplit(String rawData) {
            StringBuilder numberBuilder = new StringBuilder();
            StringBuilder alphabetBuilder = new StringBuilder();

            for (int i = 0; i < rawData.length(); ++i){
                char ch = rawData.charAt(i);
                if (StringUtil.isNumber(ch)) {
                    numberBuilder.append(ch);
                } else if (StringUtil.isLowerCase(ch) || StringUtil.isUpperCase(ch)) {
                    alphabetBuilder.append(ch);
                } else {
                    throw new UnSupportedCharacterException("숫자, 소문자, 대문자만 입력 가능합니다. your input : '" + ch+"'");
                }
            }

            this.number = numberBuilder.toString();
            this.alphabet = alphabetBuilder.toString();
        }
    }
}
