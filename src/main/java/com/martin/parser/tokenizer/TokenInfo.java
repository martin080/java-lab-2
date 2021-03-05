package com.martin.parser.tokenizer;

import com.martin.parser.tokenizer.token.Token;

public class TokenInfo {
    private int start, end;
    private Token<?> token;

    public TokenInfo(Token<?> token, int start, int end){
        this.token = token;
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public Token<?> getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "start=" + start +
                ", end=" + end +
                ", token=" + token +
                '}';
    }

}
