package com.cinema.controllers;

import com.cinema.entities.Client;
import com.cinema.entities.Seance;
import com.cinema.entities.Seat;

public interface ReserveSeat {
    boolean reserveSeat(Client client, Seance seance, Seat seat);
}
