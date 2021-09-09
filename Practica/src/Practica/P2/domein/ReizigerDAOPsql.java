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
        String query = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum VALUES ('" + reiziger.id + "' , '" + reiziger.voorletters + "', '" + reiziger.tussenvoegsel + "', '" + reiziger.achternaam + "', TO_DATE('17-09-2002', 'dd-mm-yyyy'))";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return true;
    }

    public boolean update(Reiziger reiziger) {
        return true;
    }

    public boolean delete(Reiziger reiziger) {
        return true;
    }

    public Reiziger findById(int id) throws SQLException {
//        String q = "SELECT * FROM user WHERE uname='" + u + "' AND password='" + p + "';";
        String query = "SELECT * FROM reiziger WHERE id='" + id + "';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        int nummer = rs.getInt(1);
        String voorletter = rs.getString(2);
        String tussenvoegsel = rs.getString(3);
        String achternaam = rs.getString(4);
        Date geboorteDatum = rs.getDate(5);
        Reiziger newReiziger = new Reiziger(nummer, voorletter, tussenvoegsel, achternaam, geboorteDatum);

        return newReiziger;
    }

    public List<Reiziger> findByGbdatum(String datum) throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();
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

        return reizigers;
    }

    public List<Reiziger> findAll() throws SQLException {
        List<Reiziger> reizigers = new ArrayList<>();
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
        return reizigers;
    }


//    Create (of insert): Toevoegen van nieuwe gegevens.
//    Read (of select): Opvragen van gegevens.
//    Update: Wijzigen van gegevens.
//    Delete: Verwijderen van gegevens.
}
