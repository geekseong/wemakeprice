package com.practice.wemakeprice.vo.alphabet;

import com.practice.wemakeprice.exception.alphabet.AlphabetEmptyException;

class AlphabetVo {
    private final char alphabet;
    private int count;

    public AlphabetVo(char alphabet, int count) {
        this.alphabet = alphabet;
        this.count = count;
    }

    public char getAlphabet() {
        return alphabet;
    }

    public char consume(){
        if (!isEmpty()) {
            this.count--;
            return this.alphabet;
        }

        throw new AlphabetEmptyException(alphabet + "을 모두 소진하였습니다.");
    }
    public boolean isEmpty(){
        return this.count <= 0;
    }
}
