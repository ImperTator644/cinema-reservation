package com.cinema.controllers.reservation;

import com.cinema.controllers.ReserveSeat;
import com.cinema.entities.Client;
import com.cinema.entities.Seance;
import com.cinema.entities.Seat;

public class ReserveSeatImpl implements ReserveSeat {

    @Override
    public boolean reserveSeat(Client client, Seance seance, Seat seat) {
        if(isSeatTaken(seance, seat)){
            return false;
        }
        client.setSeance(seance);
        client.getSeats().add(seat);

        seance.getSeats()
                .get(seat.getRow())
                .put(seat.getNumber(), true);

        return true;
    }

    private boolean isSeatTaken(Seance seance, Seat seat){
        return seance.getSeats().get(seat.getRow()).get(seat.getNumber());
    }
}
