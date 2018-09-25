package pl.sda.jpa;


import pl.sda.jpa.model.Lekarze;
import pl.sda.jpa.model.Specjalizacje;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static JpaDBService jpaDBService = null;
    private static Scanner scanner = null;

    public Main(){
       jpaDBService = new JpaDBService();
       scanner = new Scanner(System.in);

    }
    public static void main(String[] args) {
        new Main();

        while(true){
            showMenu();
            choice();
        }
    }

    private static void choice(){
        Integer number = scanner.nextInt();

        switch(number){
            case 1:

                jpaDBService.getJpaDBallDoctorsAndSpecial();
                break;

            case 2:

                Lekarze lekarz = new Lekarze();
                lekarz = enterNewDoctor(lekarz);
                jpaDBService.insertJpaDBnewDoctor(lekarz);
                break;

            case 3:

                jpaDBService.getJpaDBallDoctors();
               long id = removeDoctor();
                jpaDBService.removeJpaDBdoctor(id);
                break;

            case 4:

                jpaDBService.getJpaDBallDoctors();
                long id2 = getDoctor();
                jpaDBService.getJpaDBdoctorDetails(id2);


            case 0:
                System.out.println("kończe.....");
                System.exit(0);

            default:
        }
    }


    private static long getDoctor() {
        System.out.println("podaj numer lekarza, którego chcesz szczegółowo zobaczyć");
        long id = scanner.nextLong();
        return id;

    }

    private static long removeDoctor() {
        System.out.println("podaj numer lekarza, którego chcesz usunąć");
        long id = scanner.nextLong();
        return id;

    }

    private static Lekarze enterNewDoctor(Lekarze lekarz) {

        System.out.println("podaj imię lekarza");
        String name = scanner.next();
        lekarz.setImie(name);

        System.out.println("podaj nazwisko lekarza");
        String surname = scanner.next();
        lekarz.setNazwisko(surname);

        System.out.println("podaj specjalizację");
        System.out.println("1-kardiolog");
        System.out.println("2-laryngolog");
        System.out.println("3-neurolog");
        System.out.println("4-internista");
        System.out.println("5-okulista");
        System.out.println("6-chirurg");
        System.out.println("7-psychiatra");
        Long spec = scanner.nextLong();

        Specjalizacje specjalizacje;
        specjalizacje = jpaDBService.getJpaDBSpecializationByIdNumber(spec);

        lekarz.setSpecjalizacja(specjalizacje);

        System.out.println("podaj numer uprawnień");
        String nr_perm = scanner.next();
        lekarz.setNrUprawnien(nr_perm);

        System.out.println("podaj numer gabinetu");
        Integer nr_office = scanner.nextInt();
        lekarz.setNrGabinetu(nr_office);

        System.out.println("podaj numer telefonu");
        String nr_phone = scanner.next();
        lekarz.setNrTelefonu(nr_phone);

        System.out.println("podaj email");
        String email = scanner.next();
        lekarz.setEmail(email);

        System.out.println("podaj cenę wizyty");
        String price_visit = scanner.next();
        BigDecimal bigDecimal = new BigDecimal(price_visit);
        lekarz.setCenaWizyty(bigDecimal);

        return lekarz;
    }

    private static void showMenu() {
        System.out.println("**************************");
        System.out.println("***Przychodnia lekarska***");
        System.out.println("1-lista dostępnych lekarzy");
        System.out.println("2-dodaj nowego lekarza");
        System.out.println("3-usuń istniejacego lekarza");
        System.out.println("4-wyświetl lekarza szczegółowo");
        System.out.println("5-lista dostępnych pacjentów");
        System.out.println("6-lista zarezerwowanych wizyt");
        System.out.println("7-wyświetl pacjenta szczegółowo");
        System.out.println("8-dodaj nowego pacjenta");
        System.out.println("9-usuń istniejacego pacjenta");

        System.out.println("11-dodaj nowy termin wizyty");
        System.out.println("12-usuń stary termin wizyty");
        System.out.println("13-wizyty danego lekarza");
        System.out.println("14-wizyty wszystkich lekarzy");
        System.out.println("15-wizyty danego pacjenta");

        System.out.println("0-zakończ program");
        System.out.println("?");
    }
}
