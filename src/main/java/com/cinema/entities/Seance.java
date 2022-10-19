package com.cinema.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Getter
@ToString
public class Seance {
    private final String title;
    private final LocalDateTime dateTime;
    private final AgeRestriction ageRestriction;
    @Setter
    private Map<Character, Map<Integer, Boolean>> seats;
}
