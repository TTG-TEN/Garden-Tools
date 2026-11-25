package org.example;

import org.example.cli.GardenToolsCli;
import org.example.model.Product;
import org.example.repository.GToolsRepository;
import org.example.service.GToolsService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GToolsRepository repository = new GToolsRepository();
        GToolsService service = new GToolsService(repository);
        try (Scanner input = new Scanner(System.in)) {
            new GardenToolsCli(input, service, repository).run();

        }

    }
}