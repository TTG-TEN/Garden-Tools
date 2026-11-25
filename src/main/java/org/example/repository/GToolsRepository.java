package org.example.repository;
import org.example.database.DatabaseConnection;
import org.example.model.Product;

import java.sql.*;



public class GToolsRepository {
    public Product find(String product) throws SQLException {
        String sql = """
                SELECT * FROM products WHERE product = ?;
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Product(
                            result.getInt("productID"),
                            result.getString("product"),
                            result.getString("brand"),
                            result.getString("model"),
                            result.getString("category"),
                            result.getDouble("price"),
                            result.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    public void create(Product product) throws SQLException{
        String sql = """
                INSERT INTO products (product, brand, model, category, price, stock) VALUES (?,?,?,?,?,?);
                """;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.product());
            statement.setString(2, product.brand());
            statement.setString(3, product.model());
            statement.setString(4, product.category());
            statement.setDouble(5, product.price());
            statement.setInt(6, product.stock());
            statement.executeUpdate();
        }
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE productID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updatePrice(double price, int id) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE productID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, price);
            statement.setInt(2, id);
            return statement.executeUpdate() > 0;
        }
    }






}