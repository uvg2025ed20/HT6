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
         * Tests that a Pokemon is added to the collection.
         * @throws IOException if an I/O error occurs.
         */
        @Test
        void addPokemonToCollectionAddsPokemon() throws IOException {
            manager.cargarPokemones("src/pokemon_data_pokeapi.csv");
            manager.agregarAColection("Pikachu");
        }

        /**
         * Tests that adding a non-existent Pokemon to the collection is handled correctly.
         */
        @Test
        void addPokemonToCollectionHandlesNonExistentPokemon() {
            manager.agregarAColection("prueba");
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

        /**
         * Tests that the user's collection is saved correctly.
         * @throws IOException if an I/O error occurs.
         */
        @Test
        void saveUserCollectionSavesCorrectly() throws IOException {
            manager.cargarPokemones("src/pokemon_data_pokeapi.csv");
            manager.agregarAColection("Pikachu");
            manager.guardarEnCollection();
            List<String> lines = Files.readAllLines(Paths.get("collection.csv"));
            lines.forEach(System.out::println); // Debug print
            assertTrue(lines.contains("Pikachu,Electric,Static"));
        }
    }