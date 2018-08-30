package pl.sda.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static DBService dbService = null;
    private static Scanner scanner = null;

    public Main() {
        dbService = new DBService();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {

        new Main();

        while (true) {
            showMenu();
            choice();
        }


    }

    private static void choice() {
        String[] tabEnterNewDoctor = new String[8];
        Integer number = scanner.nextInt();

        switch (number) {
            case 1:
                //dbService.getDBallDoctors();
                dbService.getDBallDoctorsAndSpecial();
                break;

            case 2:
                String[] tabStr = new String[8];
                tabStr = enterNewDoctor();
                dbService.insertDBnewDoctor(tabStr);
                break;

            case 3:
                dbService.getDBallDoctors();
                int id = removeDoctor();
                dbService.removeDBdoctor(id);
                break;

            case 4:
                dbService.getDBallDoctors();
                int id2 = getDoctor();
                dbService.getDBdoctorDetails(id2);
                break;

            case 0:
                System.out.println("kończe...");
                System.exit(0);

            default:

        }
    }

    private static int getDoctor() {
        System.out.println("podaj numer lekarza, którego chcesz szczegółowo zobaczyć");
        Integer id = scanner.nextInt();
        return id;

    }

    private static int removeDoctor() {
        System.out.println("podaj numer lekarza, którego chcesz usunąć");
        Integer id = scanner.nextInt();
        return id;

   }

    private static String[] enterNewDoctor() {

        String[] tabStr = new String[8];

        System.out.println("podaj imię lekarza");
        String name = scanner.next();
        tabStr[0] = name;

        System.out.println("podaj nazwisko lekarza");
        String surname = scanner.next();
        tabStr[1] = surname;

        System.out.println("podaj specjalizację");
        System.out.println("1-kardiolog");
        System.out.println("2-laryngolog");
        System.out.println("3-neurolog");
        System.out.println("4-internista");
        System.out.println("5-okulista");
        System.out.println("6-chirurg");
        System.out.println("7-psychiatra");
        Integer spec = scanner.nextInt();
        tabStr[2] = String.valueOf(spec);

        System.out.println("podaj numer uprawnień");
        String nr_perm = scanner.next();
        tabStr[3] = nr_perm;

        System.out.println("podaj numer gabinetu");
        Integer nr_office = scanner.nextInt();
        tabStr[4] = String.valueOf(nr_office);

        System.out.println("podaj numer telefonu");
        String nr_phone = scanner.next();
        tabStr[5] = nr_phone;

        System.out.println("podaj email");
        String email = scanner.next();
        tabStr[6] = email;

        System.out.println("podaj cenę wizyty");
        Float price_visit = scanner.nextFloat();
        tabStr[7] = String.valueOf(price_visit);

        return tabStr;
    }

    private static void showMenu() {
        System.out.println("**************************");
        System.out.println("***Przychodnia lekarska***");
        System.out.println("1-lista dostępnych lekarzy");
        System.out.println("2-dodaj nowego lekarza");
        System.out.println("3-usuń istniejacego lekarza");
        System.out.println("4-wyświetl lekarza szczegółowo");
        System.out.println("0-zakończ program");
    }


}
