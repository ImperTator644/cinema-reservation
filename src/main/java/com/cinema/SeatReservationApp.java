package com.cinema;

import com.cinema.controllers.ReserveSeat;
import com.cinema.controllers.reservation.ReserveSeatImpl;
import com.cinema.entities.AgeRestriction;
import com.cinema.entities.Client;
import com.cinema.entities.Seance;
import com.cinema.entities.Seat;
import com.cinema.services.SerializationService;
import com.cinema.services.serialization.SerializationServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class SeatReservationApp {
    private static List<Seance> seances;
    private static List<Client> clients;
    private static final String SEANCES_FILE_NAME = "seances.txt";
    private static final String CLIENTS_FILE_NAME = "clients.txt";

    public static void main(String[] args) {
        SerializationService serializationService = SerializationServiceImpl.getInstance();

        try {
            seances = (ArrayList<Seance>) serializationService.deserialize(SEANCES_FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("Deserialization failed with message %s", e.getMessage());
            System.out.print("\nCreating new List of Seances");
            createSeances();
        }

        try {
            clients = (ArrayList<Client>) serializationService.deserialize(CLIENTS_FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("Deserialization failed with message %s", e.getMessage());
            System.out.print("\nCreating new List of Clients");
            createClients();
        }

        ReserveSeat reserveSeat = ReserveSeatImpl.getInstance();
        reserveSeat.reserveSeat(clients.get(0), seances.get(0), new Seat('A', 13));
        reserveSeat.reserveSeat(clients.get(1), seances.get(1), new Seat('B', 14));
        reserveSeat.reserveSeat(clients.get(2), seances.get(2), new Seat('C', 15));
        reserveSeat.reserveSeat(clients.get(3), seances.get(3), new Seat('D', 16));

        try {
            serializationService.serialize(seances, SEANCES_FILE_NAME);
            serializationService.serialize(clients, CLIENTS_FILE_NAME);
        } catch (IOException e) {
            System.out.printf("Serialization failed with message %s ", e.getMessage());
        }
    }

    private static void createSeances() {
        seances = new ArrayList<>();
        seances.add(Seance.builder()
                .title("Shrek")
                .dateTime(LocalDateTime.now())
                .ageRestriction(AgeRestriction.KIDS)
                .build());
        seances.add(Seance.builder()
                .title("Matrix")
                .dateTime(LocalDateTime.now())
                .ageRestriction(AgeRestriction.YOUNG_ADULT)
                .build());
        seances.add(Seance.builder()
                .title("Dune")
                .dateTime(LocalDateTime.now())
                .ageRestriction(AgeRestriction.YOUNG_ADULT)
                .build());
        seances.add(Seance.builder()
                .title("Home Alone")
                .dateTime(LocalDateTime.now())
                .ageRestriction(AgeRestriction.TEENAGER)
                .build());
        for (Seance s : seances) {
            initializeSeance(s);
        }
    }

    private static void createClients() {
        clients = new ArrayList<>();
        clients.add(Client.builder()
                .fname("Kasia")
                .lname("Filiciak")
                .email("kfiliciak@email.com")
                .phoneNumber("123456789")
                .seats(new LinkedList<>())
                .build());
        clients.add(Client.builder()
                .fname("Lukasz")
                .lname("Kaczynski")
                .email("lkaczynski@email.com")
                .phoneNumber("543874236")
                .seats(new LinkedList<>())
                .build());
        clients.add(Client.builder()
                .fname("Konrad")
                .lname("Blicharz")
                .email("kblicharz@email.com")
                .phoneNumber("987654321")
                .seats(new LinkedList<>())
                .build());
        clients.add(Client.builder()
                .fname("Patryk")
                .lname("Cyran")
                .email("pcyran@email.com")
                .phoneNumber("762849362")
                .seats(new LinkedList<>())
                .build());
    }

    private static void initializeSeance(Seance seance) {
        int rowCount = 6;
        int columnCount = 16;
        char maxRow = 'F';
        int maxNumber = 16;
        Map<Integer, Boolean> columnMap = new HashMap<>(columnCount);
        Map<Character, Map<Integer, Boolean>> seatMap = new HashMap<>(rowCount);
        seance.setSeats(seatMap);
        for (char row = 'A'; row <= maxRow; row++) {
            seance.getSeats().put(row, columnMap);
            for (int number = 1; number <= maxNumber; number++) {
                seance.getSeats().get(row).put(number, false);
            }
        }
    }
}
