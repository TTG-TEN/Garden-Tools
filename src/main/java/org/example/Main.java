package org.example;

import org.example.model.Product;
import org.example.repository.GToolsRepository;

import java.sql.SQLException;

public class Main {
    static void main(String[] args) {

        GToolsRepository repository = new GToolsRepository();


        try {
            Product tool = repository.find("Hækkeklipper");
            System.out.println(tool);
        } catch (SQLException e) {
            System.out.println("FEJL " + e.getMessage());
        }





    }
}
