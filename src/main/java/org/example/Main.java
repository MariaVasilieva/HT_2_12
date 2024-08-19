package org.example;

import org.example.model.Client;
import org.example.model.Planet;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;

public class Main {
    public static void main(String[] args) {
        // Create services
        ClientCrudService clientService = new ClientCrudService();
        PlanetCrudService planetService = new PlanetCrudService();

        // Test Client CRUD operations
        System.out.println("Testing Client CRUD operations...");
        Client client1 = new Client();
        client1.setName("Alice");
        clientService.createClient(client1);
        Client retrievedClient = clientService.getClient(client1.getId());
        System.out.println("Retrieved Client: " + retrievedClient.getName());

        client1.setName("Alice Updated");
        clientService.updateClient(client1);
        retrievedClient = clientService.getClient(client1.getId());
        System.out.println("Updated Client: " + retrievedClient.getName());

        clientService.deleteClient(client1.getId());
        retrievedClient = clientService.getClient(client1.getId());
        System.out.println("Deleted Client: " + (retrievedClient == null ? "null" : retrievedClient.getName()));

        // Test Planet CRUD operations
        System.out.println("Testing Planet CRUD operations...");
        Planet planet1 = new Planet("MARS", "Mars");
        planetService.createClient(planet1);
        Planet retrievedPlanet = planetService.getPlanet(planet1.getId());
        System.out.println("Retrieved Planet: " + retrievedPlanet.getName());

        planet1.setName("Mars Updated");
        planetService.updatePlanet(planet1);
        retrievedPlanet = planetService.getPlanet(planet1.getId());
        System.out.println("Updated Planet: " + retrievedPlanet.getName());

        planetService.deletePlanet(planet1.getId());
        retrievedPlanet = planetService.getPlanet(planet1.getId());
        System.out.println("Deleted Planet: " + (retrievedPlanet == null ? "was not found" : retrievedPlanet.getName()));
    }
}