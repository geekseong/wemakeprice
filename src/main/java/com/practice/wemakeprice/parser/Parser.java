package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.exception.UnSupportedCharacterException;
import com.practice.wemakeprice.infra.WebRequest;
import com.practice.wemakeprice.utils.StringUtil;
import com.practice.wemakeprice.vo.alphabet.AlphabetPairListVo;
import com.practice.wemakeprice.vo.number.NumberListVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Parser {
    protected final static String EXCEPT_EN_AND_NUM_REGEX = "[^a-zA-Z0-9]";
    private final WebRequest webRequest;
    private final int chunkNum;
    public Parser(WebRequest webRequest, int chunkNum) {
        this.webRequest = webRequest;
        this.chunkNum = chunkNum;
    }

    public Result parse(){

        // url 요청
        String data = webRequest.get();

        // type별 파싱
        String parsedData = afterFetchDo(data);

        // 숫자, 영어외의 문자 제거.
        String removeExceptNumAndEn = removeExceptNumAndEn(parsedData);

        // 교차출력물 만들기.
        String crossStringResult = makeCrossStringResult(removeExceptNumAndEn);

        // 결과 리턴
        return getResult(crossStringResult);
    }

    protected abstract String afterFetchDo(String data);

    private String removeExceptNumAndEn(String parsedData) {
        return parsedData.replaceAll(EXCEPT_EN_AND_NUM_REGEX, "");
    }
    private String makeCrossStringResult(String parsedData) {

        NumberAlphabetSplit numberAlphabetSplit = new NumberAlphabetSplit(parsedData);
        NumberListVo numberListVo = new NumberListVo(numberAlphabetSplit.number);
        AlphabetPairListVo alphabetPairListVo = new AlphabetPairListVo(numberAlphabetSplit.alphabet);

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

    public Result getResult(String data) {
        return new Result(data, chunkNum);
    }


    public class Result{
        private final String quotient;
        private final String remainder;

        public Result(String crossStringResult, int chunkNum) {

            int offset = 0;
            int q = crossStringResult.length() / chunkNum;

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
