package pl.sda.jpa;


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

                //jpaDBService.getDate();
                jpaDBService.getJpaDBallDoctors();
                break;

            case 2:

                jpaDBService.getDate();
                
                break;

            case 0:
                System.out.println("kończe.....");
                System.exit(0);

            default:
        }
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
