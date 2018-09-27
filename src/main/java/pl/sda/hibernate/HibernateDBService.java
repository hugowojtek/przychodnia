package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.sda.jpa.model.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class HibernateDBService {

    private SessionFactory instance = null;
    private Session session = null;

    public HibernateDBService() {
        instance = HbnConfig.getInstance();
        session = instance.openSession();
    }
    @Transactional
    public void getJpaDBallDoctors() {
        String jpql = "SELECT l FROM Lekarze l ORDER BY l.nazwisko";
        List<Lekarze> lekarze = session.createQuery(jpql).getResultList();
        for (Lekarze l : lekarze) {
            System.out.println(l.getId() + "," + l.getImie() + "," + l.getNazwisko());
        }
    }

    @Transactional
    public void getJpaDBallDoctorsAndSpecial() {

        String jpql = "SELECT l FROM Lekarze l ORDER BY l.nazwisko";
        List<Lekarze> lekarze = session.createQuery(jpql).getResultList();
        for (Lekarze l : lekarze) {
            System.out.println(l);
        }
    }

    @Transactional
    public void insertJpaDBnewDoctor(Lekarze lekarz) {

        session.save(lekarz);

    }

    @Transactional
    public Wojewodztwa getJpaDBDistrictByIdNumber(Long district) {
        Wojewodztwa wojewodztwa;

        wojewodztwa = session.find(Wojewodztwa.class, district);
        return wojewodztwa;
    }

    @Transactional
    public Specjalizacje getJpaDBSpecializationByIdNumber(Long spec) {
        Specjalizacje specjalizacje;

        specjalizacje = session.find(Specjalizacje.class, spec);
        return specjalizacje;
    }

    @Transactional
    public void removeJpaDBdoctor(Long id) {

        Lekarze lekarze = session.find(Lekarze.class, id);
        session.remove(lekarze);
    }

    @Transactional
    public void getJpaDBdoctorDetails(long id2) {

        Lekarze lekarze = session.find(Lekarze.class, id2);
        System.out.println(lekarze);
    }

    @Transactional
    public void getJpaDBallPatients() {

        String jpql = "SELECT p FROM Pacjenci p ORDER BY p.id";
        List<Pacjenci> pacjenci = session.createQuery(jpql).getResultList();
        for (Pacjenci p : pacjenci) {
            System.out.println("id_pacjenta:" + p.getId() + ",imie:" + p.getImie() + ",nazwisko:" + p.getNazwisko() + ",pesel:" + p.getPesel());
        }

    }

    @Transactional
    public void getJpaDBVisitsWithDate() {
        String jpql = "SELECT w FROM Wizyty w ORDER BY w.id";
        List<Wizyty> wizyty = session.createQuery(jpql).getResultList();
        for (Wizyty w : wizyty) {
            System.out.println("-id_wizyty:" + w.getLd() + "-data_wizyty:" + w.getDataWizyty() + "-dane_lekarza:" + w.getLekarz() + "-dane_pacjenta:" + w.getPacjent() + "-cena:" + w.getAktualnaCenaWizyty());
        }
    }

    @Transactional
    public void getJpaDBpatientDetails(Long id3) {
        Pacjenci pacjent = session.find(Pacjenci.class, id3);
        System.out.println(pacjent);
    }

    @Transactional
    public void insertJpaDBnewPatient(Pacjenci pacjent) {

        session.save(pacjent);

    }

    @Transactional
    public void removeJpaDBpatient(long id4) {

        Pacjenci pacjent = session.find(Pacjenci.class, id4);
        session.remove(pacjent);
    }

    public boolean insertJpaDBnewVisit(long idDoctor, long idPatient, LocalDateTime dateVisit) {

        String jpql = "SELECT w FROM Wizyty w WHERE w.lekarz=?1";

        Lekarze lekarz = session.find(Lekarze.class, idDoctor);

        if (lekarz==null) {
            System.out.println("Brak tego lekarza wybierz innego");
            return false;
        }

        List<Wizyty> list = session.createQuery(jpql).setParameter(1, lekarz).getResultList();

        Set<String> set = null;
        Map<String, Set<String>> map = new TreeMap<String, Set<String>>();

        for (Wizyty w : list) {
            String visit = String.valueOf(w.getDataWizyty());
            String date = visit.substring(0, 10);
            String time = visit.substring(11);

            set = new TreeSet<String>();
            boolean mark = false;

            for (Map.Entry m : map.entrySet()) {
                if (date.equals(m.getKey())) {

                    Set<String> set2 = (Set) m.getValue();
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
        String dateVisit2 = String.valueOf(dateVisit);
        String dateForCheck = dateVisit2.substring(0, 10);
        String timeForCheck = dateVisit2.substring(11);
        Set<String> set3 = null;
        if (map.containsKey(dateForCheck)) {
            set3 = map.get(dateForCheck);
            if (set3.contains(timeForCheck)) {
                //System.out.println("termin zajęty");
                return false;
            } else {
                //System.out.println("wolny termin wiec rezerwujemy");
                set3.add(timeForCheck);
                map.put(dateForCheck, set3);
            }
        } else {
            //System.out.println("wolny termin wiec rezerwujemy");
            set3 = new TreeSet<String>();
            set3.add(timeForCheck);
            map.put(dateForCheck, set3);
        }

        Wizyty wizyta = new Wizyty();

        wizyta.setLekarz(lekarz);

        Pacjenci pacjent = session.find(Pacjenci.class, idPatient);
        wizyta.setPacjent(pacjent);

        wizyta.setAktualnaCenaWizyty(new BigDecimal(0.0));

        wizyta.setDataUmowienia(LocalDateTime.now());

        wizyta.setDataWizyty(dateVisit);

        session.save(wizyta);

        return true;
    }

    public void removeJpaDBvisit(long idVisit) {

        Wizyty wizyta = session.find(Wizyty.class, idVisit);
        session.remove(wizyta);

    }

    public void getJpaDBvisitsAllForDoctorWithDate(long idDoctor2) {

        String jpql = "SELECT w FROM Wizyty w WHERE w.lekarz=?1";

        Lekarze lekarz = session.find(Lekarze.class, idDoctor2);
        List<Wizyty> list = session.createQuery(jpql).setParameter(1, lekarz).getResultList();

        Set<String> set = null;
        Map<String, Set<String>> map = new TreeMap<String, Set<String>>();

        for (Wizyty w : list) {
            String visit = String.valueOf(w.getDataWizyty());
            String date = visit.substring(0, 10);
            String time = visit.substring(11);

            set = new TreeSet<String>();
            boolean mark = false;

            for (Map.Entry m : map.entrySet()) {
                if (date.equals(m.getKey())) {

                    Set<String> set2 = (Set) m.getValue();
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

        for (Map.Entry m : map.entrySet()) {
            System.out.print(m.getKey().toString());
            System.out.print(" + ");
            for (Object s : (Set) m.getValue()) {
                System.out.print(s);
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public void getJpaDBvisitsAllForEveryDoctorsWithDate() {

        String jpql = "SELECT l.id FROM Lekarze l ORDER BY l.id";

        List<Long> list = session.createQuery(jpql).getResultList();

        for (Long l : list) {
            getJpaDBdoctorDetails(l);
            getJpaDBvisitsAllForDoctorWithDate(l);
        }
    }

    public void getDBvisitsAllForPatient(long idPatient2) {

        Pacjenci pacjent = session.find(Pacjenci.class,idPatient2);

        String jpql = "SELECT w FROM Wizyty w WHERE pacjent=?1";

        List<Wizyty> list = session.createQuery(jpql).setParameter(1,pacjent).getResultList();

        for (Wizyty w:list) {
            System.out.println("id_wizyty:"+w.getLd()+"-data_wizyty:"+w.getDataWizyty()+"-dane_lekarza:"+w.getLekarz());
        }

    }
}
