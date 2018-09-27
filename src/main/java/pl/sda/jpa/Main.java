package pl.sda.jpa;


import pl.sda.jpa.model.Lekarze;
import pl.sda.jpa.model.Pacjenci;
import pl.sda.jpa.model.Specjalizacje;
import pl.sda.jpa.model.Wojewodztwa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class Main {

    private static JpaDBService jpaDBService = null;
    private static Scanner scanner = null;
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public Main() {
        emf = Persistence.createEntityManagerFactory("JPA");
        em = emf.createEntityManager();
        jpaDBService = new JpaDBService(emf,em);
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
        Integer number = scanner.nextInt();

        switch (number) {
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
                break;

            case 5:

                jpaDBService.getJpaDBallPatients();
                break;

            case 6:
                jpaDBService.getJpaDBVisitsWithDate();
                break;

            case 7:

                jpaDBService.getJpaDBallPatients();
                Long id3 = getPatient();
                jpaDBService.getJpaDBpatientDetails(id3);
                break;


            case 8:

                Pacjenci pacjent = new Pacjenci();
                pacjent = enterNewPatient(pacjent);
                jpaDBService.insertJpaDBnewPatient(pacjent);
                break;

            case 9:

                jpaDBService.getJpaDBallPatients();
                long id4 = removePatient();
                jpaDBService.removeJpaDBpatient(id4);
                break;


            case 11:

                jpaDBService.getJpaDBallDoctors();
                long idDoctor = setNewVisitForDoctor();
                jpaDBService.getJpaDBallPatients();
                long idPatient = setNewVisitForPatient();
                LocalDateTime dateVisit = setNewVisitDate();
                if (jpaDBService.insertJpaDBnewVisit(idDoctor, idPatient, dateVisit)) {
                    System.out.println("wolny termin wiec rezerwujemy");
                }
                else {
                    System.out.println("termin zajęty - wybierz inny");
                }

                break;

            case 12:

                jpaDBService.getJpaDBVisitsWithDate();
                long idVisit = removeVisit();
                jpaDBService.removeJpaDBvisit(idVisit);
                break;

            case 13:

                jpaDBService.getJpaDBallDoctors();
                long idDoctor2 = schowVisitForDoctor();
                jpaDBService.getJpaDBvisitsAllForDoctorWithDate(idDoctor2);
                break;

            case 14:

                jpaDBService.getJpaDBvisitsAllForEveryDoctorsWithDate();
                break;

            case 15:
                jpaDBService.getJpaDBallPatients();
                long idPatient2 = showVisitForPatient();
                jpaDBService.getDBvisitsAllForPatient(idPatient2);
                break;

            case 0:
                System.out.println("kończe.....");

                em.close();
                emf.close();
                System.exit(0);

            default:
        }
    }

    private static long showVisitForPatient() {
        System.out.println("podaj numer id pacjenta, którego wizyty chcesz zobaczyć");
        return scanner.nextLong();
    }

    private static long schowVisitForDoctor() {
        System.out.println("podaj numer id lekarza, którego wizyty chcesz zobaczyć");
        return scanner.nextLong();
    }

    private static long removeVisit() {
        System.out.println("podaj numer id wizyty do usunięcia");
        return scanner.nextLong();
    }

    private static LocalDateTime setNewVisitDate() {
        System.out.println("podaj date wizyty format YYYY-MM-ddThh-mm");
        String text = scanner.next();
        DateTimeFormatter dateTimeFormatter = ISO_LOCAL_DATE_TIME;
        LocalDateTime date = LocalDateTime.parse(text,dateTimeFormatter);
        return date;
    }

    private static long setNewVisitForPatient() {
        System.out.println("podaj numer id pacjenta, dla którego chcesz zamówić wizytę");
        return scanner.nextLong();
    }

    private static long setNewVisitForDoctor() {
        System.out.println("podaj numer id lekarza, do którego chcesz zamówić wizytę");
        return scanner.nextLong();
    }

    private static long getPatient() {
        System.out.println("podaj numer pacjenta, którego chcesz szczegółowo zobaczyć");
        Long id = scanner.nextLong();
        return id;

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

    private static long removePatient() {
        System.out.println("podaj numer pacjenta, którego chcesz usunąć");
        long id = scanner.nextLong();
        return id;

    }

    private static Pacjenci enterNewPatient(Pacjenci pacjent) {

        System.out.println("podaj imię pacjenta");
        String name = scanner.next();
        pacjent.setImie(name);

        System.out.println("podaj nazwisko pacjenta");
        String surname = scanner.next();
        pacjent.setNazwisko(surname);

        System.out.println("podaj pesel pacjenta");
        String pesel = scanner.next();
        pacjent.setPesel(pesel);

        System.out.println("podaj datę urodzenia pacjenta");
        String dateBirth = scanner.next();
        Date date = Date.valueOf(dateBirth);
        pacjent.setDataUrodzenia(date);

        System.out.println("podaj płeć pacjenta");
        String sex = scanner.next();
        pacjent.setPlec(sex);

        System.out.println("podaj miasto zamieszkania pacjenta");
        String city = scanner.next();
        pacjent.setMiasto(city);

        System.out.println("podaj ulicę zamieszkania pacjenta");
        String street = scanner.next();
        pacjent.setUlica(street);

        System.out.println("podaj numer domu/lokalu pacjenta ");
        String numberHouse = scanner.next();
        pacjent.setNrLocalu(numberHouse);

        System.out.println("podaj województwo");

        System.out.println("1-dolnośląskie");
        System.out.println("2-kujawsko-pomorskie");
        System.out.println("3-lubelskie");
        System.out.println("4-lubuskie");

        System.out.println("5-łódzkie");
        System.out.println("6-małopolskie");
        System.out.println("7-mazowieckie");
        System.out.println("8-opolskie");

        System.out.println("9-podkarpackie");
        System.out.println("10-podlaskie");
        System.out.println("11-pomorskie");
        System.out.println("12-śląskie");

        System.out.println("13-świętokrzyskie");
        System.out.println("14-warmińsko-mazurskie");
        System.out.println("15-wielkopolskie");
        System.out.println("16-zachodniopomorskie");

        Long spec = scanner.nextLong();

        Wojewodztwa wojewodztwa;
        wojewodztwa = jpaDBService.getJpaDBDistrictByIdNumber(spec);
        pacjent.setWojewodztwo(wojewodztwa);

        return pacjent;
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
