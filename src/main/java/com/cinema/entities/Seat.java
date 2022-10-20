package com.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
@Getter
public class Seat implements Serializable {
    private char row;
    private int number;
}
