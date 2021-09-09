package Practica.P2.domein;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {

    public boolean save(Reiziger reiziger) throws SQLException;

    public boolean update(Reiziger reiziger);

    public boolean delete(Reiziger reiziger);

    public Object findById(int id) throws SQLException;

    public List<Reiziger> findByGbdatum(String datum) throws SQLException;

    public List<Reiziger> findAll() throws SQLException;
}
