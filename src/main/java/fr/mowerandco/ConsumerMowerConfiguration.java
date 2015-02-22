package fr.mowerandco;

import com.google.common.base.Preconditions;
import fr.mowerandco.core.Lawn;
import fr.mowerandco.core.Mower;
import fr.mowerandco.core.cmd.Command;
import fr.mowerandco.core.plan.Orientation;
import fr.mowerandco.core.plan.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lecture du fichier de configuration
 * La premiere ligne represente la taille du terrain
 * Les lignes impaires la position d'une tondeuse
 * les lignes pair les commandes
 */
class ConsumerMowerConfiguration implements Consumer<String> {

    /** position cursor to read lawn information */
    public static final int CONF_POSITION_LAWN_INFO = 0;
    /** position cursor to read mower information */
    public static final int CONF_POSITION_MOWER_INFO = 1;
    /** El logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerMowerConfiguration.class);
    /** Pattern for lawn configuration. */
    private static final Pattern PATTERN_LAWN_INFO = Pattern.compile("^(\\d+) (\\d+)$");
    /** Largeur du terrain. */
    private static final int LAWN_X = 1;
    /** Hauteur du terrain. */
    private static final int LAWN_Y = 2;
    /** Pattern for mower configuration. */
    private static final Pattern PATTERN_MOWER_INFO = Pattern.compile("^(\\d+) (\\d+) ([NESW])$");
    /** Position de l'abscisse pour la tondeuse */
    private static final int MOWER_POINT_X = 1;
    /** Position de l'ordonne pour la tondeuse */
    private static final int MOWER_POINT_Y = 2;
    /** Orientation pour la tondeuse */
    private static final int MOWER_ORIENTATION = 3;
    /** Cursor reading */
    int cursor = 0;
    /** The current lawn. */
    private Lawn lawn;
    /** The current mower. */
    private Mower mower;

    /**
     * Lecture d'une ligne du fichier de configuration.
     *
     * @param line la ligne a traiter.
     */
    @Override
    public void accept(final String line) {
        Preconditions.checkNotNull(line);
        switch (cursor) {
            //premiere ligne
            case CONF_POSITION_LAWN_INFO:
                readLineLawn(line);
                cursor = CONF_POSITION_MOWER_INFO;
                break;
            //ligne pair
            case CONF_POSITION_MOWER_INFO:
                readLineMower(line);
                cursor++;
                break;
            //ligne impair (sans la premiere ligne)
            default:
                readLineCommands(line);
                cursor = CONF_POSITION_MOWER_INFO;
                break;
        }
    }

    /**
     * La ligne doit respecter le Pattern {@link #PATTERN_LAWN_INFO}.<br/>
     * Initialise le terrain {@link #lawn}.
     *
     * @param line la ligne a lire
     */
    private void readLineLawn(String line) {
        final Matcher matcher = getAndCheckMatcher(PATTERN_LAWN_INFO, line);
        lawn = new Lawn(Integer.valueOf(matcher.group(LAWN_X)),
                Integer.valueOf(matcher.group(LAWN_Y)));
    }

    /**
     * La ligne doit respecter le Pattern {@link #PATTERN_MOWER_INFO}.<br/>
     * Initialise une tondeuse {@link #mower} depuis le terrain {@link #lawn}
     *
     * @param line la ligne a lire
     */
    private void readLineMower(String line) {
        final Matcher matcher = getAndCheckMatcher(PATTERN_MOWER_INFO, line);
        mower = new Mower(new Point(
                Integer.valueOf(matcher.group(MOWER_POINT_X)),
                Integer.valueOf(matcher.group(MOWER_POINT_Y))),
                Orientation.valueOfByShortName(matcher.group(MOWER_ORIENTATION)).get());
        Preconditions.checkArgument(lawn.addMower(mower), "The mower is out of grid");

    }

    /**
     * Pour chaque caractere, on execute la commande associee si trouvee.
     *
     * @param line la ligne a lire
     */
    private void readLineCommands(String line) {
        line.chars().forEach(new ConsumerCommand());

    }

    /**
     * Valide la ligne avec le pattern et retourne le matcher.
     *
     * @param pattern la pattern a utiliser
     * @param line    la ligne a tester
     * @return le matcher si tout va bien.
     */
    private Matcher getAndCheckMatcher(Pattern pattern, String line) {
        final Matcher matcher = pattern.matcher(line);
        Preconditions.checkArgument(matcher.matches(), String.format("This line '%s' doesn't respect '%s'", line, pattern));
        return matcher;
    }

    /**
     * Le terrain.
     *
     * @return le terrain.
     */
    public Lawn getLawn() {
        return lawn;
    }

    /**
     * Pour la ligne des commandes
     */
    class ConsumerCommand implements IntConsumer {

        @Override
        public void accept(int letterCmd) {
            final Optional<Command> optionalCmd = Command.valueOfByShortName((char) letterCmd);
            optionalCmd.ifPresent(cmd -> cmd.execute(lawn, mower));
        }
    }
}
