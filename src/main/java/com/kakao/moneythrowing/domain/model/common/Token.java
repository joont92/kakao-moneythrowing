package com.kakao.moneythrowing.domain.model.common;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Random;
import java.util.stream.Collectors;

@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Token {
    private static int TOKEN_LENGTH = 3;

    @Column(name = "token")
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
                .limit(TOKEN_LENGTH)
                .collect(Collectors.joining()));
    }

    public static Token createToken(String token) {
        if(token.length() != TOKEN_LENGTH) {
            throw new IllegalArgumentException();
        }

        return new Token(token);
    }

    public String getValue() {
        return token;
    }
}