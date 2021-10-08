package ovchip.reiziger;

import ovchip.ovchipkaart.OVChipkaart;
import ovchip.product.ProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO{

    public Connection conn;

    public ReizigerDAOPsql(Connection conn) throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Appelcake10";
        this.conn = DriverManager.getConnection(url);
    }

    public int findId(Reiziger reiziger) throws SQLException {
        String q = "select reiziger_id from reiziger where voorletters=? and achternaam=? and geboortedatum=?";
        PreparedStatement preparedStatement = conn.prepareStatement(q);
        preparedStatement.setString(1, reiziger.voorletters);
        preparedStatement.setString(2, reiziger.achternaam);
        preparedStatement.setDate(3, reiziger.geboortedatum);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            return id;
        }
        return 0;
    }

    public boolean save(Reiziger reiziger) throws SQLException {
        try {
            for (OVChipkaart ovChipkaart : reiziger.ovChipkaartList) {
                String q = "insert into ov_chipkaart values (nextval(kaart_nummers), ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(q);
                preparedStatement.setDate(1, ovChipkaart.geldig_tot);
                preparedStatement.setInt(2, ovChipkaart.klasse);
                preparedStatement.setDouble(3, ovChipkaart.saldo);
                preparedStatement.setInt(4, reiziger.id);
                preparedStatement.execute();
            }
            String query = "insert into reiziger values (nextval('reiziger_ids'), ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, reiziger.voorletters);
            pst.setString(2, reiziger.tussenvoegsel);
            pst.setString(3, reiziger.achternaam);
            pst.setDate(4, reiziger.geboortedatum);
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Reiziger reiziger, int id) {
        try {
            for (OVChipkaart ovChipkaart : reiziger.ovChipkaartList) {
                String q = "insert into ov_chipkaart values (nextval('kaart_nummers'), ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(q);
                preparedStatement.setDate(1, ovChipkaart.geldig_tot);
                preparedStatement.setInt(2, ovChipkaart.klasse);
                preparedStatement.setDouble(3, ovChipkaart.saldo);
                preparedStatement.setInt(4, id);
                preparedStatement.execute();
            }
            String q = "UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, geboortedatum=? WHERE reiziger_id= ?";
            PreparedStatement preparedStatement = conn.prepareStatement(q);
            preparedStatement.setString(1, reiziger.voorletters);
            preparedStatement.setString(2, reiziger.tussenvoegsel);
            preparedStatement.setString(3, reiziger.achternaam);
            preparedStatement.setDate(4, reiziger.geboortedatum);
            preparedStatement.setInt(5, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(Reiziger reiziger) {
        try {
            String query = "delete from ov_chipkaart where reiziger_id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, findId(reiziger));
            preparedStatement.execute();
            String q = "DELETE FROM reiziger WHERE reiziger_id=?";
            PreparedStatement preparedStatement1 = conn.prepareStatement(q);
            preparedStatement1.setInt(1, findId(reiziger));
            preparedStatement1.execute();
            return true;
        } catch (Exception e) {
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
                Reiziger newReiziger = new Reiziger(voorletter, tussenvoegsel, achternaam, geboorteDatum);
                return newReiziger;
            }
        } catch (Exception e) {
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
                Reiziger newReiziger = new Reiziger(voorletter, tussenvoegsel, achternaam, geboorteDatum);
                reizigers.add(newReiziger);
            }
        } catch (Exception e) {
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
                Reiziger newReiziger = new Reiziger(voorletter, tussenvoegsel, achternaam, geboorteDatum);
                reizigers.add(newReiziger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reizigers;
    }
}
