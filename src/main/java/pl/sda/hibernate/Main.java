package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sda.jpa.JpaDBService;
import pl.sda.jpa.model.Lekarze;

public class Main {

    private static HibernateDBService hibernateDBService = null;

    public Main(){
        hibernateDBService = new HibernateDBService();
    }

    public static void main(String...strings){

        new Main();

        Lekarze lekarze = hibernateDBService.getLekarz();
        System.out.println("one doctor----------------------------");
        System.out.println(lekarze);
        System.out.println("all doctors----------------------------");
        hibernateDBService.getJpaDBallDoctors();


    }
}
