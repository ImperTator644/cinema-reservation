package com.cinema.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Client implements Serializable {
    private final String fname;
    private final String lname;
    private final String email;
    private final String phoneNumber;
    @Setter
    private Seance seance;
    private final List<Seat> seats;
}
