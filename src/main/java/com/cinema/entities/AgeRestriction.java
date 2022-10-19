package com.cinema.entities;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AgeRestriction {
    KIDS(7), TEENAGER(12), YOUNG_ADULT(16), ADULT(18);
    private final int age;
}
