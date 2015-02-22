package fr.mowerandco.core.cmd;

import fr.mowerandco.core.Lawn;
import fr.mowerandco.core.Mower;

import java.util.Optional;

/**
 * Ensemble des commandes possibles d'une tondeuse.
 */
public enum Command {

    /**
     * Rotation a gauche
     */
    LEFT('G') {
        @Override
        public void execute(final Lawn lawn, final Mower mower) {
            mower.turnLeft();
        }
    },
    /**
     * Rotation a droite
     */
    RIGHT('D') {
        @Override
        public void execute(final Lawn lawn, final Mower mower) {
            mower.turnRight();
        }
    },
    /**
     * On avance la tondeuse.
     */
    MOVE('A') {
        @Override
        public void execute(final Lawn lawn, final Mower mower) {
            lawn.move(mower);
        }
    };

    /**
     * La lettre representant la commande.
     */
    private final Character shortName;

    /**
     * Initialise la lettre de la commande.
     *
     * @param shortName le nom court.
     */
    private Command(final char shortName) {
        this.shortName = shortName;
    }

    /**
     * Recherche la commande associe a la lettre
     *
     * @param shortName la lettre a rechercher
     * @return la commande ou empty.
     */
    public static Optional<Command> valueOfByShortName(final Character shortName) {
        final Command[] commands = Command.values();
        Command command = null;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].shortName.equals(shortName)) {
                command = commands[i];
                break;
            }
        }
        return Optional.ofNullable(command);
    }

    /**
     * Execute une commande sur la tondeuse.
     *
     * @param mower la tondeuse sur laquelle on execute la commande.
     * @param lawn  le terrain.
     */
    public abstract void execute(final Lawn lawn, final Mower mower);


}
