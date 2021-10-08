package ovchip.product;

import java.sql.*;

public class ProductDAOPsql implements ProductDAO {
    public Connection connection;

    public ProductDAOPsql(Connection connection) throws SQLException {
        String url = "jdbc:postgresql://localhost/ovchip?user=postgres&password=Appelcake10";
        this.connection = DriverManager.getConnection(url);
    }

    public boolean save(Product product) throws SQLException {
        try {
            String saveQuery = "insert into product values (nextval('product_nummers'), ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(saveQuery);
            preparedStatement.setString(1, product.naam);
            preparedStatement.setString(2, product.beschrijving);
            preparedStatement.setDouble(3, product.prijs);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Product product) throws SQLException {
        try {
            String deleteQuery = "delete from product where product_nummer=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, findId(product));
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Product product, int id) throws SQLException {
        try {
            String updateQuery = "update product set naam=?, beschrijving=?, prijs=? where product_nummer=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, product.naam);
            preparedStatement.setString(2, product.beschrijving);
            preparedStatement.setDouble(3, product.prijs);
            preparedStatement.setInt(4, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public int findId(Product product) {
        int product_nummer = 0;
        try {
            String findIdQuery = "select product_nummer from product where naam=? and beschrijving=? and prijs=?";
            PreparedStatement preparedStatement = connection.prepareStatement(findIdQuery);
            preparedStatement.setString(1, product.naam);
            preparedStatement.setString(2, product.beschrijving);
            preparedStatement.setDouble(3, product.prijs);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product_nummer =+ resultSet.getInt(1);
            }
            return product_nummer;
        } catch (Exception e) {
            e.printStackTrace();
            return product_nummer;
        }
    }

    public Product findById(int id) throws SQLException {
        try {
            String findByIdQuery = "select * from product where product_nummer=?";
            PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String naam = resultSet.getString(2);
                String beschrijving = resultSet.getString(3);
                double prijs = resultSet.getDouble(4);
                Product product = new Product(naam, beschrijving, prijs);
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
