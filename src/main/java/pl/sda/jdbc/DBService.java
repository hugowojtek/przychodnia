package pl.sda.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void removeDBdoctor(Integer id) {
        String sql = "DELETE FROM lekarze WHERE ID_lekarz=?";

        try {
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            System.out.println("usunięto "+i+" pozycję");
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
                "w.Data_umowienia as DataUmowienia FROM wizyta w JOIN pacjenci p ON w.Pacjent=p.ID_pacjent JOIN lekarze l ON w.Lekarz=l.ID_lekarz JOIN specjalizacje s ON l.Specjalizacja=s.ID_specjalizacja";

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
                String dateVisit = resultSet.getString("DataWizyty").substring(0, 10);
                String dateBooking = resultSet.getString("DataUmowienia").substring(0, 10);

//                String sum = idVisit + "| ImiePacjenta: " + namePatient + "| NazwiskoPacjenta: " + surnamePatient +""
//                         + "| PeselPacjenta: " + peselPatient + "| ImieDoctora: " + nameDoctor + "| NazwiskoDoktora: " + surnameDoctor +""
//                        + "| SpecjDoktora: " + specjalizationDoctor;

                String sum = idVisit + "| DanePacjenta: " + namePatient + "| " + surnamePatient + ""
                        + "| Pesel: " + peselPatient + "| DaneDoctora: " + nameDoctor + "| " + surnameDoctor + ""
                        + "| Specjalizacja: " + specjalizationDoctor + "| DataWizyty: " + dateVisit + "| DataUmowienia: " + dateBooking;

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
            connection=DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id4);
            int i = preparedStatement.executeUpdate();
            System.out.println("usunięto "+i+" pozycję");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertDBnewVisit(int idDoctor, int idPatient, String dateVisit) {
        String sql = "SELECT ID_lekarz,Nr_uprawnien FROM lekarze WHERE ID_lekarz=?";
        List<String> listOfAllDaysVisitsForDoctor = new ArrayList();

        try {
            connection=DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idDoctor);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String visit = resultSet.getString("Data_wizyty");
                listOfAllDaysVisitsForDoctor.add(visit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
