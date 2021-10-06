package ovchip.ovchipkaart;

import ovchip.reiziger.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {

    public List<OVChipkaart> findByReiziger(Reiziger reiziger, int id) throws SQLException;

}
