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
    private EntityManager em;

    public JpaDBService() {
        emf = Persistence.createEntityManagerFactory("JPA");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Transactional
    public void getDate() {


        Specjalizacje specjalizacje = new Specjalizacje();

        specjalizacje = em.find(Specjalizacje.class, 1L);

        System.out.println(specjalizacje);

        //        String jpql = "SELECT s FROM Specjalizacje s";
//        TypedQuery query = entityManager.createQuery(jpql, Specjalizacje.class);
//        List<Specjalizacje> spec = query.getResultList();
//        TypedQuery query1 = entityManager.createNamedQuery("Spec.findAll", Specjalizacje.class);
//        List<Specjalizacje> spec2 = query1.getResultList();
//
//        for (Specjalizacje s:spec2){
//            System.out.println(s);
//        }

//        em.close();
//        emf.close();


    }

    @Transactional
    public void getJpaDBallDoctorsAndSpecial() {

        String jpql = "SELECT l FROM Lekarze l ORDER BY l.nazwisko";
        List<Lekarze> lekarze = em.createQuery(jpql).getResultList();
        for (Lekarze l : lekarze) {
            System.out.println(l);
        }
    }

    @Transactional
    public void insertJpaDBnewDoctor(Lekarze lekarz) {

        em.persist(lekarz);

    }

    public Specjalizacje getJpaDBSpecializationByIdNumber(Long spec) {
        Specjalizacje specjalizacje = new Specjalizacje();

        specjalizacje = em.find(Specjalizacje.class,spec);
        return specjalizacje;
    }
}