package com.kakao.moneythrowing.domain.model.common;

import java.util.Random;
import java.util.stream.Collectors;

public class Token {
    private String token;

    private Token(String token) {
        this.token = token;
    }

    public static Token generateToken() {
        Random random = new Random();
        int leftLimit = 48; // '0'
        int rightLimit = 122; // 'z'

        return new Token(random.ints(leftLimit, rightLimit)
                .filter(i -> i <= 57 || (i >= 65 && i <= 90) || i >= 97)
                .mapToObj(i -> String.valueOf((char) i))
                .limit(3)
                .collect(Collectors.joining()));
    }

    public String getValue() {
        return token;
    }
}