package uvg.edu;

import uvg.edu.PokemonManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de Mapa (HashMap, TreeMap, LinkedHashMap):");
        String mapType = scanner.nextLine();

        PokemonManager manager = new PokemonManager(mapType);

        try {
            System.out.println("Ingrese la ruta completa del archivo CSV:");
            String filePath = scanner.nextLine();
            manager.loadPokemonData(filePath);
            manager.loadUserCollection();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error cargando datos: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n1. Agregar Pokémon a la colección\n2. Mostrar datos de un Pokémon\n3. Mostrar colección ordenada por Type1\n4. Mostrar todos los Pokémon ordenados por Type1\n5. Buscar Pokémon por habilidad\n6. Guardar colección\n7. Salir");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Ingrese el nombre del Pokémon:");
                    String name = scanner.nextLine();
                    manager.addPokemonToCollection(name);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del Pokémon:");
                    name = scanner.nextLine();
                    manager.showPokemon(name);
                    break;
                case 3:
                    manager.showUserCollectionSorted();
                    break;
                case 4:
                    manager.showAllSorted();
                    break;
                case 5:
                    System.out.println("Ingrese la habilidad del Pokémon:");
                    String ability = scanner.nextLine();
                    manager.showByAbility(ability);
                    break;
                case 6:
                    try {
                        manager.saveUserCollection();
                    } catch (IOException e) {
                        System.out.println("Error guardando colección: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
