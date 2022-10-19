package com.cinema;

import com.cinema.controllers.ReserveSeat;
import com.cinema.controllers.reservation.ReserveSeatImpl;
import com.cinema.entities.AgeRestriction;
import com.cinema.entities.Client;
import com.cinema.entities.Seance;
import com.cinema.entities.Seat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SeatReservationApp {
    public static void main(String[] args) {
        Seance seance1 = initializeSeance("Shrek", LocalDateTime.now(), AgeRestriction.KIDS);
        System.out.println(seance1);
        Client client1 = new Client("Kasia", "Filiciak", "email@email.com", "123456789", new LinkedList<>());
        ReserveSeat reserveSeat = new ReserveSeatImpl();
        reserveSeat.reserveSeat(client1, seance1, new Seat('A', 13));
        System.out.println(client1);
        System.out.println(seance1);
    }

    private static Seance initializeSeance(String title, LocalDateTime dateTime, AgeRestriction ageRestriction){
        int rowCount = 6;
        int columnCount = 16;
        char maxRow = 'F';
        int maxNumber = 16;
        Seance tempSeance = new Seance(title, dateTime, ageRestriction);
        Map<Integer, Boolean> columnMap = new HashMap<>(columnCount);
        Map<Character, Map<Integer, Boolean>> seatMap = new HashMap<>(rowCount);
        tempSeance.setSeats(seatMap);
        for(char row = 'A'; row <= maxRow; row++){
            tempSeance.getSeats().put(row, columnMap);
            for(int number = 1; number <= maxNumber; number++){
                tempSeance.getSeats().get(row).put(number, false);
            }
        }
        return tempSeance;
    }
}
