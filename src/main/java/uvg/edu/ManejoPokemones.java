// src/main/java/uvg/edu/ManejoPokemones.java
        package uvg.edu;

        import java.io.*;
        import java.util.*;

        /**
         * Class to manage Pokémon and user collections.
         */
        public class ManejoPokemones {
            private Map<String, Pokemon> pokemonMap;
            private Set<String> userCollection = new HashSet<>();

            /**
             * Constructor for ManejoPokemones.
             *
             * @param mapType The type of map to use (1 for HashMap, 2 for TreeMap, 3 for LinkedHashMap).
             */
            public ManejoPokemones(Integer mapType) {
                pokemonMap = MapFactory.getMap(mapType);
            }

            /**
             * Load Pokémon data from a CSV file.
             *
             * @param filename The path to the CSV file.
             * @throws IOException If an I/O error occurs.
             */
            public void cargarPokemones(String filename) throws IOException {
                if (!filename.endsWith(".csv")) {
                    throw new IllegalArgumentException("El archivo no es un CSV.");
                }

                BufferedReader br = new BufferedReader(new FileReader(filename));
                String line;
                // Skip the first line (headers)
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

            /**
             * Add a Pokémon to the user's collection.
             *
             * @param name The name of the Pokémon to add.
             */
            public void agregarAColection(String name) {
                if (!pokemonMap.containsKey(name)) {
                    System.out.println("Error: Pokémon no encontrado.");
                } else if (userCollection.contains(name)) {
                    System.out.println("Ese Pokémon ya está en tu colección.");
                } else {
                    userCollection.add(name);
                    System.out.println(name + " agregado a la colección.");
                }
            }

            /**
             * Show data of a specific Pokémon.
             *
             * @param name The name of the Pokémon to show.
             */
            public void printPokemon(String name) {
                Pokemon pokemon = pokemonMap.get(name);
                if (pokemon != null) {
                    System.out.println(pokemon);
                } else {
                    System.out.println("Pokémon no encontrado.");
                }
            }

            /**
             * Show the user's collection sorted by Type1.
             */
            public void printCollectionSorteada() {
                List<Pokemon> list = new ArrayList<>();
                for (String name : userCollection) {
                    list.add(pokemonMap.get(name));
                }
                list.sort(Comparator.comparing(Pokemon::getType1));
                list.forEach(System.out::println);
            }

            /**
             * Show all Pokémon sorted by Type1.
             */
            public void printTodoSorteado() {
                List<Pokemon> list = new ArrayList<>(pokemonMap.values());
                list.sort(Comparator.comparing(Pokemon::getType1));
                list.forEach(System.out::println);
            }

            /**
             * Search for Pokémon by ability.
             *
             * @param ability The ability to search for.
             */
            public void printPorHabilidad(String ability) {
                for (Pokemon pokemon : pokemonMap.values()) {
                    if (pokemon.getAbility().contains(ability)) {
                        System.out.println(pokemon);
                    }
                }
            }

            /**
             * Save the user's collection to a file.
             *
             * @throws IOException If an I/O error occurs.
             */
            public void guardarEnCollection() throws IOException {
                PrintWriter pw = new PrintWriter(new FileWriter("collection.csv"));
                for (String name : userCollection) {
                    Pokemon pokemon = pokemonMap.get(name);
                    if (pokemon != null) {
                        pw.println(pokemon.getName() + "," + pokemon.getType1() + "," + pokemon.getAbility());
                    }
                }
                pw.close();
            }

            /**
             * Load the user's collection from a file.
             *
             * @throws IOException If an I/O error occurs.
             */
            public void cargarCollection() throws IOException {
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