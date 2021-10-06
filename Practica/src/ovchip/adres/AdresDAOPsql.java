package ovchip.adres;

import ovchip.reiziger.Reiziger;
import ovchip.reiziger.ReizigerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPsql implements AdresDAO {
    public Connection connection;
    public ReizigerDAO rdao;

    public AdresDAOPsql(Connection connection) {
        this.connection = connection;
    }

    public int findId(Adres adres) throws SQLException {
        int id = 0;
        try {
            String q = "select adres_id from adres where postcode=? and huisnummer=? and straat=? and woonplaats=?";
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1, adres.postcode);
            preparedStatement.setString(2, adres.huisnummer);
            preparedStatement.setString(3, adres.straat);
            preparedStatement.setString(4, adres.woonplaats);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id += resultSet.getInt(1);
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean save(Adres adres) {
        try {
            String q = "insert into adres values (nextval('adres_ids'), ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(q);
            preparedStatement.setString(1, adres.postcode);
            preparedStatement.setString(2, adres.huisnummer);
            preparedStatement.setString(3, adres.straat);
            preparedStatement.setString(4, adres.woonplaats);
            preparedStatement.setInt(5, adres.reiziger_id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Adres adres, int id) {
        try {
            String query = "UPDATE adres SET postcode=?, huisnummer=?, straat=?, woonplaats=?, reiziger_id=? WHERE adres_id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, adres.postcode);
            preparedStatement.setString(2, adres.huisnummer);
            preparedStatement.setString(3, adres.straat);
            preparedStatement.setString(4, adres.woonplaats);
            preparedStatement.setInt(5, adres.reiziger_id);
            preparedStatement.setInt(6, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Adres adres) {
        try {
            String query = "DELETE FROM adres WHERE adres_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, findId(adres));
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Adres findByReiziger(int id) {
        try {
            String query = "select * from adres where reiziger_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int adres_id = resultSet.getInt(1);
                String postcode = resultSet.getString(2);
                String huisnummer = resultSet.getString(3);
                String straat = resultSet.getString(4);
                String woonplaats = resultSet.getString(5);
                int reiziger_id = resultSet.getInt(6);
                Adres nieuwAdres = new Adres(postcode, huisnummer, straat, woonplaats, reiziger_id);
                return nieuwAdres;
            }
            return null;
        } catch (Exception e) {
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
                Adres nieuwAdres = new Adres(postcode, huisnummer, straat, woonplaats, reiziger_id);
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
