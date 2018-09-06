package pl.sda.jdbc;

import java.net.URL;
import java.sql.*;
import java.util.*;

public class DBService {
    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final String URL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/przychodnia?serverTimezone=CET&autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "hugo";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public DBService() {
        try {
            Class.forName(DRIVER_NAME);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertDB() {
        String sql2 = "INSERT INTO lekarze(Imie,Nazwisko,Specjalizacja,Nr_gabinetu) VALUES (?,?,?,?)";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, "Wojtek");
            preparedStatement.setString(2, "Ozóg");
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 2);

            connection.setAutoCommit(false);
            int i = preparedStatement.executeUpdate();
            connection.rollback();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void insertDBnewDoctor(String[] tabEnterNewDoctor) {

        String name = tabEnterNewDoctor[0];
        String surname = tabEnterNewDoctor[1];
        Integer spec = Integer.valueOf(tabEnterNewDoctor[2]);
        String nr_perm = tabEnterNewDoctor[3];
        Integer nr_office = Integer.valueOf(tabEnterNewDoctor[4]);
        String nr_phone = tabEnterNewDoctor[5];
        String email = tabEnterNewDoctor[6];
        Float price_visit = Float.valueOf(tabEnterNewDoctor[7]);

        String sql2 = "INSERT INTO lekarze(Imie,Nazwisko,Specjalizacja," +
                "Nr_uprawnien,Nr_gabinetu,Nr_telefonu," +
                "Email,Cena_wizyty) VALUES (?,?,?,?,?,?,?,?)";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, spec);
            preparedStatement.setString(4, nr_perm);
            preparedStatement.setInt(5, nr_office);
            preparedStatement.setString(6, nr_phone);
            preparedStatement.setString(7, email);
            preparedStatement.setFloat(8, price_visit);

            connection.setAutoCommit(false);
            int i = preparedStatement.executeUpdate();
//            connection.rollback();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void getDB() {
        String sql = "SELECT ID_Lekarz, Imie, Nazwisko FROM lekarze order by ID_Lekarz";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<String> list = new ArrayList();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_lekarz");
                String name = resultSet.getString("Imie");
                String surname = resultSet.getString("Nazwisko");
                String sum = id + " " + name + " " + surname;
                System.out.println(sum);
                list.add(sum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void getDBallDoctors() {
        String sql = "SELECT ID_Lekarz, Imie, Nazwisko FROM lekarze order by ID_Lekarz";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();


            System.out.println("---Lista wszystkich lekarzy: ");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_lekarz");
                String name = resultSet.getString("Imie");
                String surname = resultSet.getString("Nazwisko");
                String sum = id + " " + name + " " + surname;
                System.out.println(sum);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void getDBallDoctorsAndSpecial() {
        String sql = "SELECT l.ID_Lekarz, l.Imie, l.Nazwisko, s.Specjalizacja FROM lekarze l " +
                "JOIN specjalizacje s ON l.Specjalizacja=s.id_specjalizacja";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_lekarz");
                String name = resultSet.getString("Imie");
                String surname = resultSet.getString("Nazwisko");
                String specialization = resultSet.getString("Specjalizacja");
                String sum = id + " " + name + " " + surname + " - specjalizacja: " + specialization;
                System.out.println(sum);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getDBdoctorDetails(Integer idSet) {
        String sql = "SELECT l.*, s.Specjalizacja FROM lekarze l " +
                "JOIN specjalizacje s ON l.Specjalizacja=s.ID_specjalizacja WHERE Id_lekarz=?";
        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idSet);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_lekarz");
                String name = resultSet.getString("Imie");
                String surname = resultSet.getString("Nazwisko");
                Integer nrSpec = resultSet.getInt("l.Specjalizacja");
                String nrPermit = resultSet.getString("Nr_uprawnien");
                String nrOffice = resultSet.getString("Nr_gabinetu");
                String nrPhone = resultSet.getString("Nr_telefonu");
                String email = resultSet.getString("Email");
                Float visitPrice = resultSet.getFloat("Cena_wizyty");
                Integer numberSpecjalization = resultSet.getInt("l.Specjalizacja");
                String textSpecjalization = resultSet.getString("s.Specjalizacja");

                String sum = id + "| Imie: " + name + "| Nazwisko: " + surname + "| NrSpec: " + nrSpec + "| NrUpraw: " + nrPermit + ""
                        + "| NrGabinet: " + nrOffice + "| NrTel: " + nrPhone + "| NrEmail: " + email + ""
                        + "| CenaWizyty: " + visitPrice + "| NrSpec: " + numberSpecjalization + "| NazwaSpec: " + textSpecjalization;
                System.out.println(sum);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getDBdoctorDetails2(Integer idSet) {
        String sql = "SELECT l.*, s.Specjalizacja FROM lekarze l " +
                "JOIN specjalizacje s ON l.Specjalizacja=s.ID_specjalizacja WHERE Id_lekarz=?";
        String sum = null;
        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idSet);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_lekarz");
                String name = resultSet.getString("Imie");
                String surname = resultSet.getString("Nazwisko");
                Integer nrSpec = resultSet.getInt("l.Specjalizacja");
                String nrPermit = resultSet.getString("Nr_uprawnien");
                String nrOffice = resultSet.getString("Nr_gabinetu");
                String nrPhone = resultSet.getString("Nr_telefonu");
                String email = resultSet.getString("Email");
                Float visitPrice = resultSet.getFloat("Cena_wizyty");
                Integer numberSpecjalization = resultSet.getInt("l.Specjalizacja");
                String textSpecjalization = resultSet.getString("s.Specjalizacja");

                sum = id + "| Imie: " + name + "| Nazwisko: " + surname + "| NrSpec: " + nrSpec + "| NrUpraw: " + nrPermit + ""
                        + "| NrGabinet: " + nrOffice + "| NrTel: " + nrPhone + "| NrEmail: " + email + ""
                        + "| CenaWizyty: " + visitPrice + "| NrSpec: " + numberSpecjalization + "| NazwaSpec: " + textSpecjalization;
                System.out.println(sum);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public void removeDBdoctor(Integer id) {
        String sql = "DELETE FROM lekarze WHERE ID_lekarz=?";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            System.out.println("usunięto " + i + " pozycję");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getDBallPatients() {
        String sql = "SELECT ID_pacjent, Imie, Nazwisko, Pesel FROM pacjenci order by ID_pacjent";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            System.out.println("--Lista wszystkich pacjentów: ");
            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID_pacjent");
                String name = resultSet.getString("Imie");
                String surname = resultSet.getString("Nazwisko");
                String pesel = resultSet.getString("Pesel");

                String sum = id + " " + name + " " + surname + " - pesel: " + pesel;
                System.out.println(sum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void getDBVisits() {
        String sql = "SELECT w.ID_wizyta, p.Imie as ImiePacjenta, p.Nazwisko as NazwiskoPacjenta, p.Pesel," +
                "l.Imie as ImieDoktora, l.Nazwisko as NazwiskoDoktora," +
                "s.Specjalizacja FROM wizyta w JOIN pacjenci p ON w.Pacjent=p.ID_pacjent JOIN lekarze l ON w.Lekarz=l.ID_lekarz JOIN specjalizacje s ON l.Specjalizacja=s.ID_specjalizacja";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer idVisit = resultSet.getInt("ID_wizyta");
                String namePatient = resultSet.getString("ImiePacjenta");
                String surnamePatient = resultSet.getString("NazwiskoPacjenta");
                String peselPatient = resultSet.getString("Pesel");
                String nameDoctor = resultSet.getString("ImieDoktora");
                String surnameDoctor = resultSet.getString("NazwiskoDoktora");
                String specjalizationDoctor = resultSet.getString("Specjalizacja");

//                String sum = idVisit + "| ImiePacjenta: " + namePatient + "| NazwiskoPacjenta: " + surnamePatient +""
//                         + "| PeselPacjenta: " + peselPatient + "| ImieDoctora: " + nameDoctor + "| NazwiskoDoktora: " + surnameDoctor +""
//                        + "| SpecjDoktora: " + specjalizationDoctor;

                String sum = idVisit + "| DanePacjenta: " + namePatient + "| " + surnamePatient + ""
                        + "| Pesel: " + peselPatient + "| DaneDoctora: " + nameDoctor + "| " + surnameDoctor + ""
                        + "| Specjalizacja: " + specjalizationDoctor;

                System.out.println(sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getDBVisitsWithDate() {
        String sql = "SELECT w.ID_wizyta, p.Imie as ImiePacjenta, p.Nazwisko as NazwiskoPacjenta, p.Pesel," +
                "l.Imie as ImieDoktora, l.Nazwisko as NazwiskoDoktora, s.Specjalizacja,w.Data_wizyty as DataWizyty," +
                "w.Data_umowienia as DataUmowienia FROM wizyta w JOIN pacjenci p ON w.Pacjent=p.ID_pacjent JOIN lekarze l ON w.Lekarz=l.ID_lekarz JOIN specjalizacje s ON l.Specjalizacja=s.ID_specjalizacja GROUP BY w.ID_wizyta ";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer idVisit = resultSet.getInt("ID_wizyta");
                String namePatient = resultSet.getString("ImiePacjenta");
                String surnamePatient = resultSet.getString("NazwiskoPacjenta");
                String peselPatient = resultSet.getString("Pesel");
                String nameDoctor = resultSet.getString("ImieDoktora");
                String surnameDoctor = resultSet.getString("NazwiskoDoktora");
                String specjalizationDoctor = resultSet.getString("Specjalizacja");
                String dateVisit = resultSet.getString("DataWizyty");//.substring(0, 10);
                String dateBooking = resultSet.getString("DataUmowienia").substring(0, 10);

//                String sum = idVisit + "| ImiePacjenta: " + namePatient + "| NazwiskoPacjenta: " + surnamePatient +""
//                         + "| PeselPacjenta: " + peselPatient + "| ImieDoctora: " + nameDoctor + "| NazwiskoDoktora: " + surnameDoctor +""
//                        + "| SpecjDoktora: " + specjalizationDoctor;

                String sum = idVisit + "| DanePacjenta: " + namePatient + "| " + surnamePatient + ""
                        + "| Pesel: " + peselPatient + "| DaneDoctora: " + nameDoctor + "| " + surnameDoctor + ""
                        + "| Specjalizacja: " + specjalizationDoctor + "| DataWizyty: " + dateVisit;// + "| DataUmowienia: " + dateBooking;

                System.out.println(sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getDBpatientDetails(Integer id) {
        String sql = "SELECT p.*, w.wojewodztwo FROM pacjenci p " +
                "JOIN wojewodztwa w ON p.wojewodztwo=w.ID_wojewodztwa WHERE Id_pacjent=?";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Integer lp = resultSet.getInt("ID_pacjent");
                String namePatient = resultSet.getString("Imie");
                String surnamePatient = resultSet.getString("Nazwisko");
                String peselPatient = resultSet.getString("Pesel");
                String dataBirth = resultSet.getString("Data_urodzenia");
                String sex = resultSet.getString("Plec");
                String city = resultSet.getString("miasto");
                String street = resultSet.getString("ulica");
                String numberHouse = resultSet.getString("nr_lokalu");
                Integer numberDistrict = resultSet.getInt("p.wojewodztwo");
                String textDistrict = resultSet.getString("w.wojewodztwo");

                String sum = lp + "| " + namePatient + "| " + surnamePatient + ""
                        + "| Pesel: " + peselPatient + "| data_urodzenia: " + dataBirth + "| płeć: " + sex + ""
                        + "| miasto: " + city + "| ulica: " + street + "| nr_domu: " + numberHouse + ""
                        + "| nr_wojewodztwa: " + numberDistrict + "| nazwa_wojewodztwa: " + textDistrict;

                System.out.println(sum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getDBnewPatient(String[] tabEnterNewPatient) {

        String name = tabEnterNewPatient[0];
        String surname = tabEnterNewPatient[1];
        String pesel = tabEnterNewPatient[2];
        String dateBirth = tabEnterNewPatient[3];
        String sex = tabEnterNewPatient[4];
        String city = tabEnterNewPatient[5];
        String street = tabEnterNewPatient[6];
        String numberHouse = tabEnterNewPatient[7];
        String district = tabEnterNewPatient[8];

        String sql2 = "INSERT INTO pacjenci(Imie,Nazwisko,Pesel," +
                "Data_urodzenia,Plec,miasto," +
                "ulica,nr_lokalu,wojewodztwo) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, pesel);
            preparedStatement.setString(4, dateBirth);
            preparedStatement.setString(5, sex);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, street);
            preparedStatement.setString(8, numberHouse);
            preparedStatement.setString(9, district);

            connection.setAutoCommit(false);
            int i = preparedStatement.executeUpdate();
//            connection.rollback();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void removeDBpatient(int id4) {
        String sql = "DELETE FROM pacjenci WHERE ID_pacjent=?";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id4);
            int i = preparedStatement.executeUpdate();
            System.out.println("usunięto " + i + " pozycję");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean insertDBnewVisit(int idDoctor, int idPatient, String dateVisit) {

        String sql = "SELECT w.Data_wizyty FROM wizyta w JOIN lekarze l ON w.Lekarz = l.ID_lekarz WHERE ID_lekarz=?";

        Set<String> set = null;
        Map<String,Set<String>> map = new TreeMap<String, Set<String>>();

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idDoctor);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String visit = resultSet.getString("Data_wizyty");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String dateForCheck = dateVisit.substring(0, 10);
        String timeForCheck = dateVisit.substring(11);
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


        String sql2 = "INSERT INTO wizyta(Pacjent,Lekarz,Data_wizyty,Data_umowienia) VALUES (?,?,?,?)";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1,idPatient);
            preparedStatement.setInt(2,idDoctor);
            preparedStatement.setString(3,dateVisit);
            preparedStatement.setString(4,"2010-01-01-01:00:00");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public int removeDBvisit(int idVisit) {
        String sql = "DELETE FROM wizyta WHERE ID_wizyta=?";
        int result = 0;
        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idVisit);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    public void getDBvisitsAllForDoctorWithDate(int idDoctor2) {

        String sql = "SELECT w.Data_wizyty FROM wizyta w JOIN lekarze l ON w.Lekarz = l.ID_lekarz WHERE ID_lekarz=?";

        Set<String> set = null;
        Map<String,Set<String>> map = new TreeMap<String, Set<String>>();

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idDoctor2);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){

                String visit = resultSet.getString("Data_wizyty");
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

            for (Map.Entry m: map.entrySet()){
                System.out.print(m.getKey().toString());
                System.out.print(" + ");
                for (Object s:(Set)m.getValue()){
                    System.out.print(s);
                    System.out.print(" | ");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getDBvisitsAllForEveryDoctorsWithDate() {

        String sql = "select ID_lekarz from lekarze ORDER BY ID_lekarz";

        List<Integer> list = new ArrayList<Integer>();

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer id = resultSet.getInt("ID_lekarz");
                list.add(id);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (Integer i:list){
            getDBdoctorDetails2(i);
            getDBvisitsAllForDoctorWithDate(i);
        }


    }

    public void getDBvisitsAllForPatient(int idPatient2) {

        String sql="SELECT w.Pacjent ,w.Lekarz, l.Imie, l.Nazwisko, s.Specjalizacja, w.Data_wizyty FROM wizyta w\n" +
                "JOIN lekarze l ON w.Lekarz = l.ID_lekarz\n" +
                "JOIN specjalizacje s ON s.ID_specjalizacja = l.Specjalizacja WHERE Pacjent=?";


        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idPatient2);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer idPatient = resultSet.getInt("Pacjent");
                Integer idDoctor = resultSet.getInt("Lekarz");
                String name = resultSet.getString("Imie");
                String surname = resultSet.getString("Nazwisko");
                String specjalization = resultSet.getString("Specjalizacja");
                String date = resultSet.getString("Data_wizyty");

                String sum = "DataWizyty: "+date+", DaneLekarza: "+name+" | "+surname+" | "+specjalization;
                System.out.println(sum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
