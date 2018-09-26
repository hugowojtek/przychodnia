package pl.sda.jpa;

import pl.sda.jpa.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import java.util.*;

public class JpaDBService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public JpaDBService() {
        emf = Persistence.createEntityManagerFactory("JPA");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Transactional
    public void getJpaDBallDoctors() {
        String jpql = "SELECT l FROM Lekarze l ORDER BY l.nazwisko";
        List<Lekarze> lekarze = em.createQuery(jpql).getResultList();
        for (Lekarze l : lekarze) {
            System.out.println(l.getId() + "," + l.getImie() + "," + l.getNazwisko());
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

    @Transactional
    public Wojewodztwa getJpaDBDistrictByIdNumber(Long district) {
        Wojewodztwa wojewodztwa;

        wojewodztwa = em.find(Wojewodztwa.class, district);
        return wojewodztwa;
    }

    @Transactional
    public Specjalizacje getJpaDBSpecializationByIdNumber(Long spec) {
        Specjalizacje specjalizacje;

        specjalizacje = em.find(Specjalizacje.class, spec);
        return specjalizacje;
    }

    @Transactional
    public void removeJpaDBdoctor(Long id) {

        Lekarze lekarze = em.find(Lekarze.class, id);
        em.remove(lekarze);
    }

    @Transactional
    public void getJpaDBdoctorDetails(long id2) {

        Lekarze lekarze = em.find(Lekarze.class, id2);
        System.out.println(lekarze);
    }

    @Transactional
    public void getJpaDBallPatients() {

        String jpql = "SELECT p FROM Pacjenci p ORDER BY p.id";
        List<Pacjenci> pacjenci = em.createQuery(jpql).getResultList();
        for (Pacjenci p : pacjenci) {
            System.out.println("id_pacjenta:" + p.getId() + ",imie:" + p.getImie() + ",nazwisko:" + p.getNazwisko() + ",pesel:" + p.getPesel());
        }

    }

    @Transactional
    public void getJpaDBVisitsWithDate() {
        String jpql = "SELECT w FROM Wizyty w ORDER BY w.id";
        List<Wizyty> wizyty = em.createQuery(jpql).getResultList();
        for (Wizyty w : wizyty) {
            System.out.println("-id_wizyty:" + w.getLd() + "-dane_lekarza:" + w.getLekarz() + "-dane_pacjenta:" + w.getPacjent() + "-cena:" + w.getAktualnaCenaWizyty());
        }
    }

    @Transactional
    public void getJpaDBpatientDetails(Long id3) {
        Pacjenci pacjent = em.find(Pacjenci.class, id3);
        System.out.println(pacjent);
    }

    @Transactional
    public void insertJpaDBnewPatient(Pacjenci pacjent) {

        em.persist(pacjent);

    }

    @Transactional
    public void removeJpaDBpatient(long id4) {

        Pacjenci pacjent = em.find(Pacjenci.class, id4);
        em.remove(pacjent);
    }

    public boolean insertJpaDBnewVisit(long idDoctor, long idPatient, String dateVisit) {

        String jpql = "SELECT w FROM Wizyty w WHERE w.lekarz=?1";

        Lekarze lekarz = em.find(Lekarze.class, idDoctor);
        List<Wizyty> list = em.createQuery(jpql).setParameter(1, lekarz).getResultList();

        Set<String> set = null;
        Map<String, Set<String>> map = new TreeMap<String, Set<String>>();

        for (Wizyty w : list) {
            String visit = String.valueOf(w.getDataWizyty());
            String date = visit.substring(0, 10);
            String time = visit.substring(11);

            set = new TreeSet<String>();
            boolean mark = false;

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

//        String dateForCheck = dateVisit.substring(0, 10);
//        String timeForCheck = dateVisit.substring(11);
//        Set<String> set3 = null;
//        if (map.containsKey(dateForCheck)) {
//            set3 = map.get(dateForCheck);
//            if (set3.contains(timeForCheck)) {
//                //System.out.println("termin zajęty");
//                return false;
//            } else {
//                //System.out.println("wolny termin wiec rezerwujemy");
//                set3.add(timeForCheck);
//                map.put(dateForCheck, set3);
//            }
//        } else {
//            //System.out.println("wolny termin wiec rezerwujemy");
//            set3 = new TreeSet<String>();
//            set3.add(timeForCheck);
//            map.put(dateForCheck, set3);
//        }
//
//
//        String sql2 = "INSERT INTO wizyta(Pacjent,Lekarz,Data_wizyty,Data_umowienia) VALUES (?,?,?,?)";
//
//        try {
//            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
//            preparedStatement = connection.prepareStatement(sql2);
//            preparedStatement.setInt(1,idPatient);
//            preparedStatement.setInt(2,idDoctor);
//            preparedStatement.setString(3,dateVisit);
//            preparedStatement.setString(4,"2010-01-01-01:00:00");
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


            return true;
        }
    }