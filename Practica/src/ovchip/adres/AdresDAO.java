package ovchip.adres;

import ovchip.reiziger.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface AdresDAO {

    public int findId(Adres adres) throws SQLException;

    public boolean save(Adres adres);

    public boolean update(Adres adres, int id);

    public boolean delete(Adres adres);

    public Adres findByReiziger(int id);

    public List<Adres> findAll();
}
