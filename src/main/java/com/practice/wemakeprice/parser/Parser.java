package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.exception.UnSupportedCharacterException;
import com.practice.wemakeprice.infra.WebRequest;
import com.practice.wemakeprice.utils.CharUtil;
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
        String parsedData = parseByParserType(data);

        // 숫자, 영어외의 문자 제거.
        String removeExceptNumAndEn = removeExceptNumAndEn(parsedData);

        // 교차출력물 만들기.
        String crossStringResult = makeCrossStringResult(removeExceptNumAndEn);

        // 몫, 나머지를 구한다.
        return getQuotientAndRemainder(crossStringResult);
    }

    // 타입별로 상속받아서 재정의 할 수 있도록 추상매서드로 생성.
    protected abstract String parseByParserType(String data);

    private String removeExceptNumAndEn(String parsedData) {
        return parsedData.replaceAll(EXCEPT_EN_AND_NUM_REGEX, "");
    }
    private String makeCrossStringResult(String parsedData) {

        NumberAlphabetSplit numberAlphabetSplit = getNumberAlphabetSplit(parsedData);
        NumberListVo numberListVo = new NumberListVo(numberAlphabetSplit.getNumber());
        AlphabetPairListVo alphabetPairListVo = new AlphabetPairListVo(numberAlphabetSplit.getAlphabet());

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

    private NumberAlphabetSplit getNumberAlphabetSplit(String data){
        StringBuilder numberBuilder = new StringBuilder();
        StringBuilder alphabetBuilder = new StringBuilder();

        for (int i = 0; i < data.length(); ++i){
            char ch = data.charAt(i);
            if (CharUtil.isNumber(ch)) {
                numberBuilder.append(ch);
            } else if (CharUtil.isLowerCase(ch) || CharUtil.isUpperCase(ch)) {
                alphabetBuilder.append(ch);
            } else {
                throw new UnSupportedCharacterException("숫자, 소문자, 대문자만 입력 가능합니다. your input : '" + ch+"'");
            }
        }

        return new NumberAlphabetSplit(numberBuilder.toString(), alphabetBuilder.toString());
    }

    private Result getQuotientAndRemainder(String data) {

        if (chunkNum == 0) {
            return new Result(data);
        }

        else{
            int offset = 0;
            int q = data.length() / chunkNum;

            List<String> chunkList = new ArrayList<>();
            for (; offset < q * chunkNum; offset += chunkNum) {
                chunkList.add(data.substring(offset, offset + chunkNum));
            }

            String quotient = chunkList.stream().collect(Collectors.joining(","));
            String remainder = data.substring(offset);
            return new Result(quotient, remainder);
        }
    }

    public class Result{
        private final String quotient;
        private final String remainder;

        public Result(String quotient) {
            this.quotient = quotient;
            this.remainder = "";
        }

        public Result(String quotient, String remainder) {
            this.quotient = quotient;
            this.remainder = remainder;
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

        public NumberAlphabetSplit(String number, String alphabet) {
            this.number = number;
            this.alphabet = alphabet;
        }

        public String getNumber() {
            return number;
        }

        public String getAlphabet() {
            return alphabet;
        }
    }
}
