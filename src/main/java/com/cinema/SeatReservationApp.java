package com.cinema;

import com.cinema.controllers.ReserveSeat;
import com.cinema.controllers.reservation.ReserveSeatImpl;
import com.cinema.entities.AgeRestriction;
import com.cinema.entities.Client;
import com.cinema.entities.Seance;
import com.cinema.entities.Seat;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class SeatReservationApp {
    static ArrayList<Seance> seances;
    static ArrayList<Client> clients;
    static String seancesFileName = "seances.txt";
    static String clientsFileName = "clients.txt";
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        createSeances();
        createClients();

        ReserveSeat reserveSeat = ReserveSeatImpl.getInstance();
        reserveSeat.reserveSeat(clients.get(0), seances.get(0), new Seat('A', 13));
        reserveSeat.reserveSeat(clients.get(1), seances.get(1), new Seat('B', 14));
        reserveSeat.reserveSeat(clients.get(2), seances.get(2), new Seat('C', 15));
        reserveSeat.reserveSeat(clients.get(3), seances.get(3), new Seat('D', 16));

        serialize(seances, seancesFileName);
        seances.clear();
        seances = (ArrayList<Seance>) deserialize(seancesFileName);

        serialize(clients, clientsFileName);
        clients.clear();
        clients = (ArrayList<Client>) deserialize(clientsFileName);
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
        for(Seance s: seances) {
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

    private static void serialize(Object obj, String filename) throws IOException {
        System.out.println("Serializing data: ");
        System.out.println(obj);
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
        fos.close();
    }

    private static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        System.out.println("Deserialized data: ");
        System.out.println(obj);
        ois.close();
        return obj;
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

    private static void initializeSeance(Seance seance) {
        int rowCount = 6;
        int columnCount = 16;
        char maxRow = 'F';
        int maxNumber = 16;
        Map<Integer, Boolean> columnMap = new HashMap<>(columnCount);
        Map<Character, Map<Integer, Boolean>> seatMap = new HashMap<>(rowCount);
        seance.setSeats(seatMap);
        for(char row = 'A'; row <= maxRow; row++){
            seance.getSeats().put(row, columnMap);
            for(int number = 1; number <= maxNumber; number++){
                seance.getSeats().get(row).put(number, false);
            }
        }
    }
}
