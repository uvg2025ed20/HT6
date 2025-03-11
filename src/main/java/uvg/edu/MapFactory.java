package uvg.edu;

import java.util.*;

// Factory para seleccionar la implementación de Map
public class MapFactory {
    public static Map<String, Pokemon> getMap(String type) {
        switch (type) {
            case "HashMap": return new HashMap<>();
            case "TreeMap": return new TreeMap<>();
            case "LinkedHashMap": return new LinkedHashMap<>();
            default: throw new IllegalArgumentException("Tipo de mapa no válido");
        }
    }
}
