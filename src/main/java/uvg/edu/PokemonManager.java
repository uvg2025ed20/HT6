package uvg.edu;

import java.io.*;
import java.util.*;

// Clase para manejar Pokémon y colecciones del usuario
public class PokemonManager {
    private Map<String, Pokemon> pokemonMap;
    private Set<String> userCollection = new HashSet<>();

    public PokemonManager(String mapType) {
        pokemonMap = MapFactory.getMap(mapType);
    }

    // Cargar datos desde CSV
    public void loadPokemonData(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Leyendo línea: " + line); // Mensaje de depuración
            String[] data = line.split(",");
            if (data.length >= 3) {
                pokemonMap.put(data[0], new Pokemon(data[0], data[1], data[2]));
                System.out.println("Pokémon agregado: " + data[0]); // Mensaje de depuración
            } else {
                System.out.println("Línea inválida: " + line); // Mensaje de depuración
            }
        }
        br.close();
    }

    // Agregar Pokémon a la colección del usuario
    public void addPokemonToCollection(String name) {
        if (!pokemonMap.containsKey(name)) {
            System.out.println("Error: Pokémon no encontrado.");
        } else if (userCollection.contains(name)) {
            System.out.println("Ese Pokémon ya está en tu colección.");
        } else {
            userCollection.add(name);
            System.out.println(name + " agregado a la colección.");
        }
    }

    // Mostrar datos de un Pokémon
    public void showPokemon(String name) {
        System.out.println(pokemonMap.getOrDefault(name, new Pokemon("No encontrado", "-", "-")));
    }

    // Mostrar Pokémon de la colección ordenados por Type1
    public void showUserCollectionSorted() {
        List<Pokemon> list = new ArrayList<>();
        for (String name : userCollection) {
            list.add(pokemonMap.get(name));
        }
        list.sort(Comparator.comparing(Pokemon::getType1));
        list.forEach(System.out::println);
    }

    // Mostrar todos los Pokémon ordenados por Type1
    public void showAllSorted() {
        List<Pokemon> list = new ArrayList<>(pokemonMap.values());
        list.sort(Comparator.comparing(Pokemon::getType1));
        list.forEach(System.out::println);
    }

    // Mostrar Pokémon por habilidad
    public void showByAbility(String ability) {
        pokemonMap.values().stream()
                .filter(p -> p.getAbility().equalsIgnoreCase(ability))
                .forEach(System.out::println);
    }

    // Guardar colección en archivo
    public void saveUserCollection() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("collection.ser"));
        oos.writeObject(userCollection);
        oos.close();
    }

    // Cargar colección desde archivo
    public void loadUserCollection() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("collection.ser"));
        userCollection = (Set<String>) ois.readObject();
        ois.close();
    }
}
