// src/main/java/uvg/edu/PokemonManager.java
package uvg.edu;

import java.io.*;
import java.util.*;

// Clase para manejar Pokémon y colecciones del usuario
public class PokemonManager {
    private Map<String, Pokemon> pokemonMap;
    private Set<String> userCollection = new HashSet<>();

    public PokemonManager(Integer mapType) {
        pokemonMap = MapFactory.getMap(mapType);
    }

    // Cargar datos desde CSV
    public void loadPokemonData(String filename) throws IOException {
        if (!filename.endsWith(".csv")) {
            throw new IllegalArgumentException("El archivo no es un CSV.");
        }

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        // Saltar la primera línea (encabezados)
        br.readLine();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 8) {
                String name = data[0];
                String type1 = data[2];
                String abilities = data[7];
                pokemonMap.put(name, new Pokemon(name, type1, abilities));
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
        Pokemon pokemon = pokemonMap.get(name);
        if (pokemon != null) {
            System.out.println(pokemon);
        } else {
            System.out.println("Pokémon no encontrado.");
        }
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

    // Buscar Pokémon por habilidad
    public void showByAbility(String ability) {
        for (Pokemon pokemon : pokemonMap.values()) {
            if (pokemon.getAbility().contains(ability)) {
                System.out.println(pokemon);
            }
        }
    }

    // Guardar colección en archivo
    public void saveUserCollection() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("collection.csv"));
        for (String name : userCollection) {
            Pokemon pokemon = pokemonMap.get(name);
            if (pokemon != null) {
                pw.println(pokemon.getName() + "," + pokemon.getType1() + "," + pokemon.getAbility());
            }
        }
        pw.close();
    }

    // Cargar colección desde archivo
    public void loadUserCollection() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("collection.csv"));
        String line;
        userCollection.clear();
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 3) {
                userCollection.add(data[0]);
                pokemonMap.put(data[0], new Pokemon(data[0], data[1], data[2]));
            }
        }
        br.close();
    }
}