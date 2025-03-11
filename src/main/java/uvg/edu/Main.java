// src/main/java/uvg/edu/Main.java
        package uvg.edu;

        import java.io.IOException;
        import java.util.Scanner;

        public class Main {
            /**
             * Main method to run the Pokemon Manager application.
             *
             * @param args Command line arguments
             */
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Prompt user to select the type of Map to use
                System.out.println("Seleccione el Mapa que Desea Utilizar");
                System.out.println("1. HashMap");
                System.out.println("2. TreeMap");
                System.out.println("3. LinkedHashMap");
                Integer mapType = scanner.nextInt();
                scanner.nextLine();

                PokemonManager manager = new PokemonManager(mapType);

                try {
                    // Prompt user to enter the file path for the CSV file
                    System.out.println("Ingrese la ruta completa del archivo CSV:");
                    String filePath = scanner.nextLine();
                    manager.loadPokemonData(filePath);
                    manager.loadUserCollection();
                } catch (IOException e) {
                    System.out.println("Error cargando datos: " + e.getMessage());
                }

                // Main loop to handle user commands
                while (true) {
                    System.out.println("\n1. Agregar Pokémon a la colección\n2. Mostrar datos de un Pokémon\n3. Mostrar colección ordenada por Type1\n4. Mostrar todos los Pokémon ordenados por Type1\n5. Buscar Pokémon por habilidad\n6. Guardar colección\n7. Salir");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            // Add a Pokemon to the user's collection
                            System.out.println("Ingrese el nombre del Pokémon:");
                            String name = scanner.nextLine();
                            manager.addPokemonToCollection(name);
                            break;
                        case 2:
                            // Show data of a specific Pokemon
                            System.out.println("Ingrese el nombre del Pokémon:");
                            name = scanner.nextLine();
                            manager.showPokemon(name);
                            break;
                        case 3:
                            // Show the user's collection sorted by Type1
                            manager.showUserCollectionSorted();
                            break;
                        case 4:
                            // Show all Pokemon sorted by Type1
                            manager.showAllSorted();
                            break;
                        case 5:
                            // Search for Pokemon by ability
                            System.out.println("Ingrese la habilidad del Pokémon:");
                            String ability = scanner.nextLine();
                            manager.showByAbility(ability);
                            break;
                        case 6:
                            // Save the user's collection to a file
                            try {
                                manager.saveUserCollection();
                            } catch (IOException e) {
                                System.out.println("Error guardando colección: " + e.getMessage());
                            }
                            break;
                        case 7:
                            // Exit the program
                            System.out.println("Se ha salido del programa");
                            scanner.close();
                            return;
                        default:
                            // Handle invalid options
                            System.out.println("Opción no válida.");
                    }
                }
            }
        }