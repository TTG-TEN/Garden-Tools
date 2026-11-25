package org.example.service;

import org.example.model.Product;
import org.example.repository.GToolsRepository;

import java.sql.SQLException;

public class GToolsService {
    private final GToolsRepository repository;

    public GToolsService(GToolsRepository repository) {
        this.repository = repository;
    }


    public void add(String product, String brand, String model, String category, double price, Integer stock) throws SQLException {
        if (product.isBlank() || product.length() > 120) {
            throw new IllegalArgumentException("Product navn skal have 1-120 tegn.");
        }
        if (brand.isBlank() || brand.length() > 120) {
            throw new IllegalArgumentException("Brand navn skal have 1-120 tegn.");
        }
        if (model.isBlank() || model.length() > 120) {
            throw new IllegalArgumentException("Model navn skal have 1-120 tegn.");
        }
        if (category.isBlank() || category.length() > 60) {
            throw new IllegalArgumentException("Kategori skal have 1-60 tegn.");
        }
        if (price < 0) { //double kan ikke være null?
            throw new IllegalArgumentException("Pris må ikke være negativ.");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Stock må ikke være negativ.");
        }
        Product product1 = new Product(product, brand, model, category, price, stock);
        repository.create(product1);
    }


    public boolean delete(int id) throws SQLException {
        validateId(id);
        return repository.deleteById(id);
    }

    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id skal være positivt.");
        }
    }

    private void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Pris skal være positiv.");
        }
    }

    public boolean changePrice(double price, int id) throws SQLException {
        validatePrice(price);
        validateId(id);
        return repository.updatePrice(price, id);
    }

}