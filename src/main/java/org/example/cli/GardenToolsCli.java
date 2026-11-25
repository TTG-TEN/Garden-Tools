package org.example.cli;
import org.example.model.Product;
import org.example.service.GToolsService;
import org.example.repository.GToolsRepository;



import java.sql.SQLException;
import java.util.Scanner;

public class GardenToolsCli {
    private final Scanner input;
    private final GToolsService service;
    private final GToolsRepository repository;

    public GardenToolsCli(Scanner input, GToolsService service, GToolsRepository repository) {
        this.input = input;
        this.service = service;
        this.repository = repository;
    }

    private String ask(String prompt) {
        System.out.print(prompt + ": ");
        return input.nextLine().strip();
    }


    public void run() {
        while (true) {
            System.out.println("\n1. Vis produkt information \n2. Tilføj et produkt \n3. Ret pris\n4. Slet produkt \n0. Afslut");
            try {
                switch (ask("Vælg")) {
                    case "1" -> showProductDetails();
                    case "2" -> add();
                    case "3" -> changePrice(); //note gør validation bedre for price
                    case "4" -> delete();
                    case "0" -> { return; }
                    default -> System.out.println("Vælg fra 0 til 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Skriv id som et helt tal.");
            } catch (IllegalArgumentException | UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                System.out.println("Databasefejl: " + e.getMessage());
            } catch (java.util.NoSuchElementException e) {
                return;
            }
        }
    }


    public void add() throws SQLException { //create
        String product = ask("Produkt navn");
        String brand = ask("Brand navn");
        String model = ask("Model");
        String category  = ask("Kategori");
        double price = Double.parseDouble(ask("Pris"));
        Integer stock = Integer.parseInt(ask("Stock"));

        service.add(product, brand, model, category, price, stock);
        System.out.println("Produktet er gemt.");
    }

    private void delete() throws SQLException { //delete
        int id = Integer.parseInt(ask("Id"));
        boolean found = service.delete(id);
        System.out.println(found ? "Produktet er slettet." : "Id blev ikke fundet.");
    }

    private void changePrice() throws SQLException { //update
        int id = Integer.parseInt(ask("Id"));
        double price = Double.parseDouble(ask("Ny pris"));
        boolean found = service.changePrice(price, id);
        System.out.println(found ? "prisen er gemt." : "Id blev ikke fundet eller ugyldig pris.");
    }

    private void showProductDetails() throws SQLException { //read
        String productName = ask("Produkt navn");
        Product product = repository.find(productName);
        if (product == null) {
            System.out.println("Intet produkt med det navn.");
        }
        System.out.println(product.toString());
    }

}