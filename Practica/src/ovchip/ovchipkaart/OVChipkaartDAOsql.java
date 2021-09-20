package ovchip.ovchipkaart;

import ovchip.reiziger.Reiziger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOsql implements OVChipkaartDAO {
    public Connection connection;

    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        List<OVChipkaart> ovChipkaarten = new ArrayList<>();
        return ovChipkaarten;
    }
}
