package fr.mowerandco;

import com.google.common.base.Preconditions;
import fr.mowerandco.core.Lawn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Run a mower.
 */
public final class MowerApplication {

    /** El logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MowerApplication.class);

    /**
     * URI configuration.
     */
    private final URI uriConfiguration;

    /**
     * Initialize configuration mower.
     *
     * @param uriConfiguration uri with configuration of mower.
     */
    public MowerApplication(URI uriConfiguration) {
        Preconditions.checkNotNull(uriConfiguration);
        this.uriConfiguration = uriConfiguration;
    }

    /**
     * Need on argument URI with configuration to run application.
     *
     * @param args one argument URI to InputStream to Read.
     */
    public static void main(String[] args) {
        //one argument URI
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage file://file.txt");
        }
        try {
            final Lawn lawn = new MowerApplication(new URI(args[0])).run();
            LOGGER.info("{}", lawn);
        } catch (URISyntaxException uriSyntaxException) {
            throw new IllegalArgumentException("Parameter format URI", uriSyntaxException);
        }
    }

    /**
     * Read configuration and execute commands on lawn.
     *
     * @return the lawn with mowers
     */
    public Lawn run() {
        final Path path = Paths.get(uriConfiguration);
        try {
            ConsumerMowerConfiguration consumer = new ConsumerMowerConfiguration();
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(consumer);
            }
            return consumer.getLawn();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
