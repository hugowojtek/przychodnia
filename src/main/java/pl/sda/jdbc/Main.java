package pl.sda.jdbc;

import java.util.*;

public class Main {

    private static DBService dbService = null;
    private static Scanner scanner = null;

    public Main() {
        dbService = new DBService();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {



        scanner = new Scanner(System.in);
        Set<String> set = null;
        Map<String,Set<String>> map = new TreeMap<String, Set<String>>();
        while(true) {
            set = new TreeSet<String>();
            boolean mark = false;
            System.out.println("podaj date i godzine");
            String visit = scanner.nextLine();
            if (visit.equals("1")) break;
            String date = visit.substring(0, 10);
            String time = visit.substring(11);
            for (Map.Entry m:map.entrySet()){
                if (date.equals(m.getKey())){

                    Set<String> set2 = (Set)m.getValue();
                    set2.add(time);
                    m.setValue(set2);
                    mark = true;
                    break;
                }
            }
            if (!(mark)) {
                set.add(time);
                map.put(date, set);
            }

        }

        while(true){
            System.out.println("podaj date i godzine do sprawdzenia");
            String visit = scanner.nextLine();
            if (visit.equals("2")) break;
            String date = visit.substring(0, 10);
            String time = visit.substring(11);
            Set<String> set3 = null;
            if (map.containsKey(date)) {
                set3 = map.get(date);
                if (set3.contains(time)) {
                    System.out.println("termin zajęty");
                } else {
                    System.out.println("wolny termin");
                    set3.add(time);
                    map.put(date,set3);
                }
            }
            else {
                System.out.println("wolny termin");
                set3 = new TreeSet<String>();
                set3.add(time);
                map.put(date,set3);
            }
        }


//        new Main();
//
//        while (true) {
//            showMenu();
//            choice();
//        }


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

            case 5:
                dbService.getDBallPatients();
                break;

            case 6:
                dbService.getDBVisitsWithDate();
                break;

            case 7:
                dbService.getDBallPatients();
                Integer id3 = getPatient();
                dbService.getDBpatientDetails(id3);
                break;

            case 8:
                String[] tabStr2 = new String[9];
                tabStr2 = enterNewPatient();
                dbService.getDBnewPatient(tabStr2);
                break;

            case 9:
                dbService.getDBallPatients();
                int id4 = removePatient();
                dbService.removeDBpatient(id4);
                break;

            case 11:
                dbService.getDBallDoctors();
                int idDoctor = setNewVisitForDoctor();
                dbService.getDBallPatients();
                int idPatient = setNewVisitForPatient();
                String dateVisit = setNewVisitDate();
                dbService.insertDBnewVisit(idDoctor, idPatient, dateVisit);

                break;


            case 0:
                System.out.println("kończe...");
                System.exit(0);

            default:

        }
    }

    private static String setNewVisitDate() {
        System.out.println("podaj date wizyty");
        return scanner.nextLine();
    }

    private static int setNewVisitForPatient() {
        System.out.println("podaj numer id pacjenta, dla którego chcesz zamówić wizytę");
        return scanner.nextInt();
    }

    private static int setNewVisitForDoctor() {
        System.out.println("podaj numer id lekarza, do którego chcesz zamówić wizytę");
        return scanner.nextInt();
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


    private static int getPatient() {
        System.out.println("podaj numer pacjenta, którego chcesz szczegółowo zobaczyć");
        Integer id = scanner.nextInt();
        return id;

    }

    private static int setDoctor() {
        System.out.println("podaj numer lekarza, do którego chcesz wizytę");
        Integer id = scanner.nextInt();
        return id;

    }

    private static int removePatient() {
        System.out.println("podaj numer pacjenta, którego chcesz usunąć");
        Integer id = scanner.nextInt();
        return id;
    }

    private static int setPatient() {
        System.out.println("podaj numer pacjenta, któremu chcesz ustalić wizytę");
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

    private static String[] enterNewPatient() {

        String[] tabStr = new String[9];

        System.out.println("podaj imię pacjenta");
        String name = scanner.next();
        tabStr[0] = name;

        System.out.println("podaj nazwisko pacjenta");
        String surname = scanner.next();
        tabStr[1] = surname;

        System.out.println("podaj pesel pacjenta");
        String pesel = scanner.next();
        tabStr[2] = pesel;

        System.out.println("podaj datę urodzenia pacjenta");
        String dateBirth = scanner.next();
        tabStr[3] = dateBirth;

        System.out.println("podaj płeć pacjenta");
        String sex = scanner.next();
        tabStr[4] = sex;

        System.out.println("podaj miasto zamieszkania pacjenta");
        String city = scanner.next();
        tabStr[5] = city;

        System.out.println("podaj ulicę zamieszkania pacjenta");
        String street = scanner.next();
        tabStr[6] = street;

        System.out.println("podaj numer domu/lokalu pacjenta ");
        String numberHouse = scanner.next();
        tabStr[7] = numberHouse;

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

        Integer spec = scanner.nextInt();
        tabStr[8] = String.valueOf(spec);

        return tabStr;
    }

    private static String[] enterNewVisit() {

        String[] tabStr = new String[4];
        dbService.getDBallDoctors();
        int IdDoctor = setDoctor();
        int IdPatient = setPatient();


        return tabStr;

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

        System.out.println("0-zakończ program");
        System.out.println("?");
    }


}
