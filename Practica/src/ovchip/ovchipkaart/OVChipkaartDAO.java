package ovchip.ovchipkaart;

import ovchip.reiziger.Reiziger;

import java.util.List;

public interface OVChipkaartDAO {

    public List<OVChipkaart> findByReiziger(Reiziger reiziger);

}
