package Practica.P3.domein;

import Practica.P2.domein.Reiziger;
import Practica.P2.domein.ReizigerDAO;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    public Connection connection;
    public ReizigerDAO rdao;

    public AdresDAOPsql(Connection connection) {
        this.connection = connection;
    }

    public boolean save(Adres adres) {
        try {
            String query = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, adres.adres_id);
            preparedStatement.setString(2, adres.postcode);
            preparedStatement.setString(3, adres.huisnummer);
            preparedStatement.setString(4, adres.straat);
            preparedStatement.setString(5, adres.woonplaats);
            preparedStatement.setInt(6, adres.reiziger_id);
            preparedStatement.execute();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Adres adres) {
        try {
            String query = "UPDATE adres \n" +
                    "        SET postcode=?, huisnummer=?, straat=?, woonplaats=?, reiziger_id=? \n" +
                    "        WHERE adres_id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, adres.postcode);
            preparedStatement.setString(2, adres.huisnummer);
            preparedStatement.setString(3, adres.straat);
            preparedStatement.setString(4, adres.woonplaats);
            preparedStatement.setInt(5, adres.reiziger_id);
            preparedStatement.setInt(6, adres.adres_id);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Adres adres) {
        try {
            String query = "DELETE FROM adres WHERE adres_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, adres.adres_id);
            preparedStatement.execute();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Adres findByReiziger(Reiziger reiziger) {
        try {
            String query = "SELECT FROM adres WHERE reiziger_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, reiziger.id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int adres_id = resultSet.getInt(1);
                String postcode = resultSet.getString(2);
                String huisnummer = resultSet.getString(3);
                String straat = resultSet.getString(4);
                String woonplaats = resultSet.getString(5);
                int reiziger_id = resultSet.getInt(6);
                Adres nieuwAdres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id);
                return nieuwAdres;
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Adres> findAll() {
        List<Adres> adresList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM adres");
            while (resultSet.next()) {
                int adres_id = resultSet.getInt(1);
                String postcode = resultSet.getString(2);
                String huisnummer = resultSet.getString(3);
                String straat = resultSet.getString(4);
                String woonplaats = resultSet.getString(5);
                int reiziger_id = resultSet.getInt(6);
                Adres nieuwAdres = new Adres(adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id);
                adresList.add(nieuwAdres);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return adresList;
        }
        return adresList;
    }
}
