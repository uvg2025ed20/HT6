package uvg.edu;

    import java.io.Serializable;

    /**
     * Class to represent a Pokémon.
     */
    public class Pokemon implements Serializable {
        private String name;
        private String type1;
        private String ability;

        /**
         * Constructor for the Pokemon class.
         *
         * @param name The name of the Pokémon.
         * @param type1 The primary type of the Pokémon.
         * @param ability The ability of the Pokémon.
         */
        public Pokemon(String name, String type1, String ability) {
            this.name = name;
            this.type1 = type1;
            this.ability = ability;
        }

        /**
         * Gets the name of the Pokémon.
         *
         * @return The name of the Pokémon.
         */
        public String getName() { return name; }

        /**
         * Gets the primary type of the Pokémon.
         *
         * @return The primary type of the Pokémon.
         */
        public String getType1() { return type1; }

        /**
         * Gets the ability of the Pokémon.
         *
         * @return The ability of the Pokémon.
         */
        public String getAbility() { return ability; }

        /**
         * Returns a string representation of the Pokémon.
         *
         * @return A string representation of the Pokémon.
         */
        @Override
        public String toString() {
            return name + " | Tipo: " + type1 + " | Habilidad: " + ability;
        }
    }