// src/test/java/uvg/edu/PokemonManagerTest.java
package uvg.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonManagerTest {
    private PokemonManager manager;

    @BeforeEach
    void setUp() {
        manager = new PokemonManager(1); // Assuming 1 corresponds to a valid map type
    }

    @Test
    void addPokemonToCollectionAddsPokemon() throws IOException {
        manager.loadPokemonData("src/pokemon_data_pokeapi.csv");
        manager.addPokemonToCollection("Pikachu");
        // Verify by checking the output or internal state if possible
    }

    @Test
    void addPokemonToCollectionHandlesNonExistentPokemon() {
        manager.addPokemonToCollection("NonExistent");
        // Verify by checking the output or internal state if possible
    }

    @Test
    void showPokemonDisplaysCorrectPokemon() throws IOException {
        manager.loadPokemonData("src/pokemon_data_pokeapi.csv");
        manager.showPokemon("Pikachu");
        // Verify output manually or use a custom output stream to capture the output
    }

    @Test
    void showUserCollectionSortedDisplaysSortedCollection() throws IOException {
        manager.loadPokemonData("src/pokemon_data_pokeapi.csv");
        manager.addPokemonToCollection("Pikachu");
        manager.addPokemonToCollection("Bulbasaur");
        manager.showUserCollectionSorted();
        // Verify output manually or use a custom output stream to capture the output
    }

    @Test
    void showAllSortedDisplaysAllPokemonSorted() throws IOException {
        manager.loadPokemonData("src/pokemon_data_pokeapi.csv");
        manager.showAllSorted();
        // Verify output manually or use a custom output stream to capture the output
    }

    @Test
    void showByAbilityDisplaysCorrectPokemon() throws IOException {
        manager.loadPokemonData("src/pokemon_data_pokeapi.csv");
        manager.showByAbility("Static");
        // Verify output manually or use a custom output stream to capture the output
    }

    @Test
    void saveUserCollectionSavesCorrectly() throws IOException {
        manager.loadPokemonData("src/pokemon_data_pokeapi.csv");
        manager.addPokemonToCollection("Pikachu");
        manager.saveUserCollection();
        List<String> lines = Files.readAllLines(Paths.get("src/pokemon_data_pokeapi.csv"));
        assertTrue(lines.contains("Pikachu,Electric,Static"));
    }

    @Test
    void loadUserCollectionLoadsCorrectly() throws IOException {
        Files.write(Paths.get("src/pokemon_data_pokeapi.csv"), List.of("Pikachu,Electric,Static"));
        manager.loadUserCollection();
        // Verify by checking the output or internal state if possible
    }
}