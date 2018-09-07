package pl.sda.jpa;

import pl.sda.jpa.model.Specjalizacje;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


public class Main {

    private static EntityManagerFactory emf;
    private static EntityManager entityManager;

    @Transactional
    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("JPA");
        entityManager = emf.createEntityManager();
        Specjalizacje specjalizacje = new Specjalizacje();

        entityManager.getTransaction().begin();

        specjalizacje = entityManager.find(Specjalizacje.class, 1L);

        entityManager.close();
        emf.close();

        System.out.println();
    }
}
