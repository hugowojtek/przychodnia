package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sda.jpa.model.Lekarze;

import javax.transaction.Transactional;
import java.util.List;

public class HibernateDBService {

    private SessionFactory instance = null;
    private Session session = null;

    public HibernateDBService() {
        instance = HbnConfig.getInstance();
        session = instance.openSession();
    }
//    @Transactional
    public Lekarze getLekarz() {
        Lekarze lekarz = session.find(Lekarze.class, 5L);
        return lekarz;
    }

//    @Transactional
    public void getJpaDBallDoctors() {
        String jpql = "SELECT l FROM Lekarze l ORDER BY l.nazwisko";
        List<Lekarze> lekarze = session.createQuery(jpql).getResultList();
        for (Lekarze l : lekarze) {
            System.out.println(l.getId() + "," + l.getImie() + "," + l.getNazwisko());
        }
    }
}
