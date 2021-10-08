package ovchip.product;

import java.sql.SQLException;

public interface ProductDAO {

    public int findId(Product product) throws SQLException;

    public boolean save(Product product) throws SQLException;

    public boolean update(Product product, int id) throws SQLException;

    public boolean delete(Product product) throws SQLException;

    public Product findById(int id) throws SQLException;
}
