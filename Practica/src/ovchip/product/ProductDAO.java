package ovchip.product;

import ovchip.ovchipkaart.OVChipkaart;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    public int findId(Product product) throws SQLException;

    public boolean save(Product product) throws SQLException;

    public boolean update(Product product, int id) throws SQLException;

    public boolean delete(Product product) throws SQLException;

    public Product findById(int id) throws SQLException;

    public List<Product> findAll() throws SQLException;

    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException;
}
