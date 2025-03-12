package uvg.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ManejoPokemones class.
 */
class PokemonManagerTest {
    private ManejoPokemones manager;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        manager = new ManejoPokemones(1);
    }


    /**
     * Tests that adding a non-existent Pokemon to the collection is handled correctly.
     */
    @Test
    void addPokemonToCollectionHandlesNonExistentPokemon() throws IOException {
        manager.agregarAColection("prueba");
        List<String> lines = Files.readAllLines(Paths.get("src/collection.csv"));
        assertFalse(lines.contains("prueba"));
    }

    /**
     * Tests that a specific Pokemon is displayed correctly.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void showPokemonDisplaysCorrectPokemon() throws IOException {
        manager.cargarPokemones("src/pokemon_data_pokeapi.csv");
        manager.printPokemon("Pikachu");
    }

    /**
     * Tests that the user's collection is displayed sorted correctly.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void showUserCollectionSortedDisplaysSortedCollection() throws IOException {
        manager.cargarPokemones("src/pokemon_data_pokeapi.csv");
        manager.agregarAColection("Pikachu");
        manager.agregarAColection("Bulbasaur");
        manager.printCollectionSorteada();
    }

    /**
     * Tests that all Pokemon are displayed sorted correctly.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void showAllSortedDisplaysAllPokemonSorted() throws IOException {
        manager.cargarPokemones("src/pokemon_data_pokeapi.csv");
        manager.printTodoSorteado();
    }

    /**
     * Tests that Pokemon with a specific ability are displayed correctly.
     * @throws IOException if an I/O error occurs.
     */
    @Test
    void showByAbilityDisplaysCorrectPokemon() throws IOException {
        manager.cargarPokemones("src/pokemon_data_pokeapi.csv");
        manager.printPorHabilidad("Static");
    }

}