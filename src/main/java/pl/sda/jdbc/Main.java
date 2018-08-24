package pl.sda.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/przychodnia?serverTimezone=CET";
    private static final String USER = "root";
    private static final String PASSWORD = "hugo";
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {





            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            getDB();
            connection = DriverManager.getConnection(URL_CONNECTION_STRING, USER, PASSWORD);
            insertDB();



        }


    private static void insertDB() throws SQLException{
        String sql2 = "INSERT INTO lekarze(Imie,Nazwisko,Specjalizacja,Nr_gabinetu) VALUES (?,?,?,?)";


        try {
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, "Wojtek");
            preparedStatement.setString(2, "Ozóg");
            preparedStatement.setInt(3,1);
            preparedStatement.setInt(4,2);

            connection.setAutoCommit(false);
            int i = preparedStatement.executeUpdate();
            connection.rollback();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            preparedStatement.close();
            connection.close();
        }




    }
    private static void getDB() throws SQLException {
        String sql = "SELECT ID_Lekarz, Imie, Nazwisko FROM lekarze order by ID_Lekarz";

        try {
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
            preparedStatement.close();
            connection.close();

        }

    }
}
