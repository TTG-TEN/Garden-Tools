package org.example.model;

public class Product {
    private final Integer productID;
    private final String product;
    private final String brand;
    private final String model;
    private final String category;
    private final double price;
    private final Integer stock;

    public Product(Integer productID, String product, String brand, String model, String category, double price, Integer stock) {
        this.productID = productID;
        this.product = product;
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
    public Product(String product, String brand, String model,
                   String category, double price, Integer stock) {
        this(null, product, brand, model, category, price, stock);


    }

    public Integer ProductID() {
        return productID;
    }

    public double price() {
        return price;
    }

    public String product() {
        return product;
    }

    public Integer stock() {
        return stock;
    }


    public String brand() {
        return brand;
    }

    public String category() {
        return category;
    }


    public String model() {
        return model;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", product='" + product + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}

