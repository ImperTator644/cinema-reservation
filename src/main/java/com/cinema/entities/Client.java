package com.cinema.entities;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Client {
    private final String fname;
    private final String lname;
    private final String email;
    private final String phoneNumber;
    @Setter
    private Seance seance;
    private final List<Seat> seats;
}
