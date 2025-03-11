package uvg.edu;

            import java.util.*;

            /**
             * Factory class to select the implementation of Map.
             */
            public class MapFactory {
                /**
                 * Returns a Map implementation based on the provided type.
                 *
                 * @param type The type of Map to create (1 for HashMap, 2 for TreeMap, 3 for LinkedHashMap).
                 * @return A Map implementation.
                 * @throws IllegalArgumentException If the provided type is not valid.
                 */
                public static Map<String, Pokemon> getMap(Integer type) {
                    switch (type) {
                        case 1: return new HashMap<>();
                        case 2: return new TreeMap<>();
                        case 3: return new LinkedHashMap<>();
                        default: throw new IllegalArgumentException("Tipo de mapa no válido");
                    }
                }
            }