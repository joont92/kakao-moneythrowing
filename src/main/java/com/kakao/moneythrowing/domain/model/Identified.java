package com.kakao.moneythrowing.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Identified {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;
}
