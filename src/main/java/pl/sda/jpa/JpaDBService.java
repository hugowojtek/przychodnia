package pl.sda.jpa;

import pl.sda.jpa.model.Lekarze;
import pl.sda.jpa.model.Pacjenci;
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
    public  void getJpaDBallDoctors(){
        String jpql = "SELECT l FROM Lekarze l ORDER BY l.nazwisko";
        List<Lekarze> lekarze = em.createQuery(jpql).getResultList();
        for (Lekarze l : lekarze) {
            System.out.println(l.getId()+","+l.getImie()+","+l.getNazwisko());
        }
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
        Specjalizacje specjalizacje;

        specjalizacje = em.find(Specjalizacje.class,spec);
        return specjalizacje;
    }

    public void removeJpaDBdoctor(Long id) {

        Lekarze lekarze = em.find(Lekarze.class,id);
        em.remove(lekarze);
    }

    public void getJpaDBdoctorDetails(long id2) {

        Lekarze lekarze = em.find(Lekarze.class,id2);
        System.out.println(lekarze);
    }

    public void getJpaDBallPatients() {

        String jpql = "SELECT p FROM Pacjenci p order by p.id";
        List<Pacjenci> pacjenci = em.createQuery(jpql).getResultList();
        for (Pacjenci p:pacjenci){
            System.out.println("id:"+p.getId()+",imie:"+p.getImie()+",nazwisko:"+p.getNazwisko()+",pesel:"+p.getPesel());
        }

    }
}