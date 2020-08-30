package com.kakao.moneythrowing.domain.model.throwing;

import com.kakao.moneythrowing.domain.model.common.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public interface ThrowingRepository extends JpaRepository<Throwing, Long> {
    @Query("SELECT t FROM Throwing t JOIN FETCH t.threads " +
            "WHERE t.token = :token AND t.startDate > :startDate")
    Optional<Throwing> findByToken(@Param("token") Token token, @Param("startDate") Instant startDate);

    default Optional<Throwing> findByToken(Token token) {
        return findByToken(token, Instant.now().minus(7, ChronoUnit.DAYS));
    }
}
