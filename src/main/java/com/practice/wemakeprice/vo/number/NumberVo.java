package com.practice.wemakeprice.vo.number;

import com.practice.wemakeprice.exception.number.NumberEmptyException;

class NumberVo {
    private final int num;
    private int count;

    public NumberVo(int num) {
        this.num = num;
        this.count = 0;
    }

    public NumberVo(int num, int count) {
        this.num = num;
        this.count = count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public int consume() {
        if (!isEmpty()) {
            this.count--;
            return this.num;
        }
        throw new NumberEmptyException(num + "을 모두 소진하였습니다.");
    }
}
