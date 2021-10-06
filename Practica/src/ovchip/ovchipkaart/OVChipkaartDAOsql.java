package ovchip.ovchipkaart;

import ovchip.reiziger.Reiziger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOsql implements OVChipkaartDAO {
    public Connection connection;

    public OVChipkaartDAOsql(Connection conn) {
        this.connection = conn;
    }

    public List<OVChipkaart> findByReiziger(Reiziger reiziger, int id) throws SQLException {
        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        try {
            String query = "select * from ov_chipkaart where reiziger_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int nummer = resultSet.getInt(1);
                Date date = resultSet.getDate(2);
                int klasse = resultSet.getInt(3);
                double saldo = resultSet.getDouble(4);
                int reiziger_id = resultSet.getInt(5);
                OVChipkaart ovChipkaart = new OVChipkaart(nummer, date, klasse, saldo, reiziger_id);
                ovChipkaarten.add(ovChipkaart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ovChipkaarten;
    }
}
