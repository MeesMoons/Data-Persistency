package Practica.P2.domein;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{

    public Connection conn;

    public ReizigerDAOPsql(Connection conn) throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Appelcake10";
        this.conn = DriverManager.getConnection(url);
    }

    public boolean save(Reiziger reiziger) throws SQLException {
        try {
            String q = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(q);
            pst.setInt(1, reiziger.id);
            pst.setString(2, reiziger.voorletters);
            pst.setString(3, reiziger.tussenvoegsel);
            pst.setString(4, reiziger.achternaam);
            pst.setDate(5, reiziger.geboortedatum);
            pst.execute();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Reiziger reiziger) {


        try {
            String q = "UPDATE reiziger \n" +
                    "        SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? \n" +
                    "        WHERE reiziger_id= ?";

            PreparedStatement preparedStatement = conn.prepareStatement(q);
            preparedStatement.setString(1, reiziger.voorletters);
            preparedStatement.setString(2, reiziger.tussenvoegsel);
            preparedStatement.setString(3, reiziger.achternaam);
            preparedStatement.setDate(4, reiziger.geboortedatum);
            preparedStatement.setInt(5, reiziger.id);
            preparedStatement.execute();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Reiziger reiziger) {
        try {
            String q = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(q);
            preparedStatement.setInt(1, reiziger.id);
            preparedStatement.execute();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Reiziger findById(int id) throws SQLException {

        try {
            String query = "SELECT * FROM reiziger WHERE reiziger_id=" + id;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String nummer = rs.getString(1);
                String voorletter = rs.getString(2);
                String tussenvoegsel = rs.getString(3);
                String achternaam = rs.getString(4);
                Date geboorteDatum = rs.getDate(5);
                Reiziger newReiziger = new Reiziger(Integer.parseInt(nummer), voorletter, tussenvoegsel, achternaam, geboorteDatum);
                return newReiziger;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();

        try {
            String query = "SELECT * FROM reiziger WHERE geboortedatum='" + datum + "';";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int nummer = rs.getInt(1);
                String voorletter = rs.getString(2);
                String tussenvoegsel = rs.getString(3);
                String achternaam = rs.getString(4);
                Date geboorteDatum = rs.getDate(5);
                Reiziger newReiziger = new Reiziger(nummer, voorletter, tussenvoegsel, achternaam, geboorteDatum);
                reizigers.add(newReiziger);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reizigers;
    }

    public List<Reiziger> findAll() throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reiziger");
            while (rs.next()) {
                int nummer = rs.getInt(1);
                String voorletter = rs.getString(2);
                String tussenvoegsel = rs.getString(3);
                String achternaam = rs.getString(4);
                Date geboorteDatum = rs.getDate(5);
                Reiziger newReiziger = new Reiziger(nummer, voorletter, tussenvoegsel, achternaam, geboorteDatum);
                reizigers.add(newReiziger);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reizigers;
    }


//    Create (of insert): Toevoegen van nieuwe gegevens.
//    Read (of select): Opvragen van gegevens.
//    Update: Wijzigen van gegevens.
//    Delete: Verwijderen van gegevens.
}
