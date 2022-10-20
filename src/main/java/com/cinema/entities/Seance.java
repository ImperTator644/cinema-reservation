package com.cinema.entities;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class Seance implements Serializable {
    private final String title;
    private final LocalDateTime dateTime;
    private final AgeRestriction ageRestriction;
    @Setter
    private Map<Character, Map<Integer, Boolean>> seats;
}
