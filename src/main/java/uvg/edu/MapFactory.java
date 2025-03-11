package uvg.edu;

import java.util.*;

// Factory para seleccionar la implementación de Map
public class MapFactory {
    public static Map<String, Pokemon> getMap(Integer type) {
        switch (type) {
            case 1: return new HashMap<>();
            case 2: return new TreeMap<>();
            case 3: return new LinkedHashMap<>();
            default: throw new IllegalArgumentException("Tipo de mapa no válido");
        }
    }
}
