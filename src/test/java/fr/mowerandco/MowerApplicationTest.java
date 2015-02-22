package fr.mowerandco;

import fr.mowerandco.core.Lawn;
import fr.mowerandco.core.plan.Orientation;
import fr.mowerandco.core.plan.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

/**
 * Test Run application.
 */
public class MowerApplicationTest {


    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Usage .*")
    public void should_have_one_argument() {
        MowerApplication.main(new String[]{});
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Usage .*")
    public void should_have_just_one_argument() {
        MowerApplication.main(new String[]{"just", "one"});
    }


    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Parameter format URI")
    public void should_have_one_argument_uri() {
        MowerApplication.main(new String[]{" TEST: / / AAA:BBB "});
    }

    @Test
    public void should_be_ok_main() throws URISyntaxException {
        MowerApplication.main(new String[]{String.valueOf(getClass().getResource("/sample.txt").toURI())});
    }

    @Test
    public void should_be_null_lawn() throws URISyntaxException {
        Assert.assertNull(new MowerApplication(getClass().getResource("/empty.txt").toURI()).run());
    }

    @Test
    public void should_be_lawn_without_lawn() throws URISyntaxException {
        final Lawn lawn = new MowerApplication(getClass().getResource("/lawn_without_mower.txt").toURI()).run();
        Assert.assertNotNull(lawn);
        Assert.assertTrue(lawn.getMowers().isEmpty());
    }

    @Test
    public void should_be_like_sample() throws URISyntaxException {
        final Lawn lawn = new MowerApplication(getClass().getResource("/sample.txt").toURI()).run();
        Assert.assertNotNull(lawn);
        Assert.assertEquals(lawn.getMowers().size(), 2);
        Assert.assertEquals(lawn.getMowers().get(new Point(1, 3)).getOrientation(), Orientation.NORTH);
        Assert.assertEquals(lawn.getMowers().get(new Point(5, 1)).getOrientation(), Orientation.EAST);
    }
}
