package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.common.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThrowingRepository extends JpaRepository<Throwing, Long> {
    Throwing findByToken(Token token);
}
