package org.example.repository;
//import org.example.model.product;
import org.example.database.DatabaseConnection;
import org.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GToolsRepository {
    public Product find(String product) throws SQLException {
        String sql = "SELECT * FROM products where product = ?; ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product);
try (ResultSet result = stmt.executeQuery()) {
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

    public static void create(Product product) throws  SQLException {
        String sql = "INSERT INTO products (product, brand, model, category, price, stock) VALUES (?,?,?,?,?,?); ";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, product.product());
            stmt.setString(2, product.brand());
            stmt.setString(3, product.model());
            stmt.setString(4, product.category());
            stmt.setDouble(5, product.price());
            stmt.setInt(6, product.stock());
            stmt.executeUpdate();
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
    public boolean changePrice(int id, double price) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE productID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, price);
            statement.setInt(2, id);

            return statement.executeUpdate() > 0;
        }
    }
}