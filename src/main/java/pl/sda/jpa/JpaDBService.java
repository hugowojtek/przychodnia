package pl.sda.jpa;

import pl.sda.jpa.model.Lekarze;
import pl.sda.jpa.model.Specjalizacje;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class JpaDBService {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public JpaDBService(){
        emf = Persistence.createEntityManagerFactory("JPA");
        entityManager = emf.createEntityManager();
    }
    @Transactional
    public void getDate() {


        Specjalizacje specjalizacje = new Specjalizacje();

        //entityManager.getTransaction().begin();

        specjalizacje = entityManager.find(Specjalizacje.class, 1L);

        //        String jpql = "SELECT s FROM Specjalizacje s";
//        TypedQuery query = entityManager.createQuery(jpql, Specjalizacje.class);
//        List<Specjalizacje> spec = query.getResultList();
        TypedQuery query1 = entityManager.createNamedQuery("Spec.findAll", Specjalizacje.class);
        List<Specjalizacje> spec2 = query1.getResultList();

        for (Specjalizacje s:spec2){
            System.out.println(s);
        }

        entityManager.close();
        emf.close();

        System.out.println();
    }

    @Transactional
    public void getJpaDBallDoctors() {

        Lekarze lekarze = new Lekarze();
        lekarze = entityManager.find(Lekarze.class,1L);
        System.out.println(lekarze);

    }
}
