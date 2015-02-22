package fr.mowerandco.core.plan;

import java.util.Optional;

/**
 * The four cardinal points.
 */
public enum Orientation {

    /**
     * Cardinal Point NORTH
     */
    NORTH("N"),
    /**
     * Cardinal Point EAST
     */
    EAST("E"),
    /**
     * Cardinal Point SOUTH
     */
    SOUTH("S"),
    /**
     * Cardinal Point WEST
     */
    WEST("W");

    /**
     * Short name.
     */
    private final String shortName;

    /**
     * Initiale short name;
     *
     * @param shortName
     */
    private Orientation(final String shortName) {
        this.shortName = shortName;
    }

    /**
     * Recherche l'orientation associe a la lettre
     *
     * @param shortName la lettre a rechercher
     * @return l'orientation ou empty.
     */
    public static Optional<Orientation> valueOfByShortName(final String shortName) {
        final Orientation[] orientations = Orientation.values();
        Orientation orientation = null;
        for (int i = 0; i < orientations.length; i++) {
            if (orientations[i].shortName.equals(shortName)) {
                orientation = orientations[i];
                break;
            }
        }
        return Optional.ofNullable(orientation);
    }


}
