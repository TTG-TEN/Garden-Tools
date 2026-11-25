
    package org.example.cli;

    import org.example.model.Product;
    import org.example.repository.GToolsRepository;
    import org.example.service.GToolsService;

    import java.sql.SQLException;
    import java.util.Scanner;

    public class GardenToolsCli {
        private Scanner input;
        private GToolsService service;
        private GToolsRepository repository;

        public GardenToolsCli(Scanner input, GToolsService service, GToolsRepository repository) {
            this.input = input;
            this.service = service;

        }

        private String ask(String prompt) {
            System.out.print(prompt + ": ");
            return input.nextLine().strip();
        }

        public void add() throws SQLException {
            String product = ask("Produkt");
            String brand = ask("Brand");
            String model = ask("Model");
            String category = ask("Kategori");
            double price = Double.parseDouble(ask("Pris"));
            Integer stock = Integer.parseInt(ask("Stock"));

            service.add(product, brand, model, category, price, stock);
        }
 public Product find(String product) throws SQLException {
            return repository.find(product);
 }


    }
