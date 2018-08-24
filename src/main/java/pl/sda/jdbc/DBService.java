package pl.sda.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private final String URL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/przychodnia?serverTimezone=CET";
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

    public void insertDBnewDoctor(String[] tabEnterNewDoctor){

        String name = tabEnterNewDoctor[0];
        String surname = tabEnterNewDoctor[1];
        Integer spec =  Integer.valueOf(tabEnterNewDoctor[2]);
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
            preparedStatement.setInt(3,spec);
            preparedStatement.setString(4,nr_perm);
            preparedStatement.setInt(5,nr_office);
            preparedStatement.setString(6,nr_phone);
            preparedStatement.setString(7,email);
            preparedStatement.setFloat(8,price_visit);

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
            }
            catch (SQLException e){
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
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }

    }


}
