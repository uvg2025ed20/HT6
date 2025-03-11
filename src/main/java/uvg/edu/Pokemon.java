package uvg.edu;

import java.io.Serializable;

// Clase para representar un Pokémon
public class Pokemon implements Serializable {
    private String name;
    private String type1;
    private String ability;

    public Pokemon(String name, String type1, String ability) {
        this.name = name;
        this.type1 = type1;
        this.ability = ability;
    }

    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getAbility() { return ability; }

    @Override
    public String toString() {
        return name + " | Tipo: " + type1 + " | Habilidad: " + ability;
    }
}