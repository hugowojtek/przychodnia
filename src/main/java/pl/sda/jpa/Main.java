package pl.sda.jpa;

import pl.sda.jpa.model.Specjalizacje;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


public class Main {

    private static EntityManagerFactory emf;
    private static EntityManager entityManager;

    @Transactional
    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("JPA");
        entityManager = emf.createEntityManager();
        Specjalizacje specjalizacje = new Specjalizacje();

        //entityManager.getTransaction().begin();

        specjalizacje = entityManager.find(Specjalizacje.class, 1L);

//        String jpql = "SELECT s FROM Specjalizacje s";
//        TypedQuery query = entityManager.createQuery(jpql, Specjalizacje.class);
//        List<Specjalizacje> spec = query.getResultList();
        TypedQuery query1 = entityManager.createNamedQuery("Spec.findAll",Specjalizacje.class);
        List<Specjalizacje> spec2 = query1.getResultList();

        entityManager.close();
        emf.close();

        System.out.println();
    }
}
