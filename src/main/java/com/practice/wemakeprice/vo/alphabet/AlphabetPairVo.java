package com.practice.wemakeprice.vo.alphabet;

import com.practice.wemakeprice.exception.alphabet.AlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.LowercaseAlphabetEmptyException;
import com.practice.wemakeprice.exception.alphabet.UppercaseAlphabetEmptyException;

class AlphabetPairVo {

    private final AlphabetVo lowercase;
    public final AlphabetVo uppercase;

    public AlphabetPairVo(AlphabetVo lowercase, AlphabetVo uppercase) {
        this.lowercase = lowercase;
        this.uppercase = uppercase;
    }

    public boolean isAllPairEmpty() {
        return isLowercaseEmpty() && isUppercaseEmpty();
    }

    public boolean isLowercaseEmpty() {
        return this.lowercase.isEmpty();
    }

    public boolean isUppercaseEmpty() {
        return this.uppercase.isEmpty();
    }

    public char uppercaseConsume() {
        try{
            return uppercase.consume();
        } catch (AlphabetEmptyException e){
            throw new UppercaseAlphabetEmptyException(e.getMessage());
        }
    }

    public char lowercaseConsume() {
        try {
            return lowercase.consume();
        } catch (AlphabetEmptyException e) {
            throw new LowercaseAlphabetEmptyException(e.getMessage());
        }
    }
}
