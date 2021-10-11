package ovchip.product;

import ovchip.ovchipkaart.OVChipkaart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            for (OVChipkaart ovChipkaart : product.ovChipkaartList) {
                String saveRelatieQuery = "INSERT INTO ov_chipkaart_product(kaart_nummer, product_nummer, status, last_update) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(saveRelatieQuery);
                preparedStatement1.setInt(1, ovChipkaart.kaart_nummer);
                preparedStatement1.setInt(2, findId(product));
                preparedStatement1.setString(3, null);
                preparedStatement1.setDate(4, null);
                preparedStatement1.execute();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Product product) throws SQLException {
        try {
            String deleteRelatieQuery = "delete from ov_chipkaart_product where product_nummer = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(deleteRelatieQuery);
            preparedStatement1.setInt(1, findId(product));
            preparedStatement1.execute();

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

    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String query = "select * from product where product_nummer = any (select product_nummer from ov_chipkaart_product where kaart_nummer=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ovChipkaart.kaart_nummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int product_nummer = resultSet.getInt(1);
                String naam = resultSet.getString(2);
                String beschrijving = resultSet.getString(3);
                double prijs = resultSet.getDouble(4);
                Product product = new Product(naam, beschrijving, prijs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            String findAllQuery = "select * from product";
            PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int product_nummer = resultSet.getInt(1);
                String naam = resultSet.getString(2);
                String beschrijving = resultSet.getString(3);
                double prijs = resultSet.getDouble(4);
                Product product = new Product(naam, beschrijving, prijs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
