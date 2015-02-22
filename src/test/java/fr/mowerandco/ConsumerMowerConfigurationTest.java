package fr.mowerandco;

import fr.mowerandco.core.Mower;
import fr.mowerandco.core.plan.Orientation;
import fr.mowerandco.core.plan.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * ConsumerMowerConfiguration.
 */
public class ConsumerMowerConfigurationTest {

    ConsumerMowerConfiguration consumerMowerConfiguration;

    @BeforeMethod
    public void setUp() {
        consumerMowerConfiguration = new ConsumerMowerConfiguration();
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "This line 'bad line' doesn't respect '.*'")
    public void should_throw_when_add_bad_line() {
        consumerMowerConfiguration.accept("bad line");
    }


    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "This line 'bad line two' doesn't respect '.*'")
    public void should_throw_when_add_second_bad_line() {
        consumerMowerConfiguration.accept("7 5");
        Assert.assertEquals(7, consumerMowerConfiguration.getLawn().getWidth());
        Assert.assertEquals(5, consumerMowerConfiguration.getLawn().getHeight());

        consumerMowerConfiguration.accept("bad line two");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "The mower is out of grid")
    public void should_throw_mower_out_of_grid() {
        consumerMowerConfiguration.accept("7 5");
        Assert.assertEquals(7, consumerMowerConfiguration.getLawn().getWidth());
        Assert.assertEquals(5, consumerMowerConfiguration.getLawn().getHeight());

        consumerMowerConfiguration.accept("8 4 N");


    }

    @Test
    public void should_accept_ok() {
        consumerMowerConfiguration.accept("7 5");
        Assert.assertEquals(7, consumerMowerConfiguration.getLawn().getWidth());
        Assert.assertEquals(5, consumerMowerConfiguration.getLawn().getHeight());

        consumerMowerConfiguration.accept("4 4 N");
        Assert.assertEquals(consumerMowerConfiguration.getLawn().getMowers().size(), 1);
        final Mower mower = consumerMowerConfiguration.getLawn().getMowers().get(new Point(4, 4));
        Assert.assertEquals(mower.getOrientation(), Orientation.NORTH);
        consumerMowerConfiguration.accept("GDA");

        final Mower mowerMoved = consumerMowerConfiguration.getLawn().getMowers().get(new Point(4, 5));
        Assert.assertSame(mowerMoved, mower);

        consumerMowerConfiguration.accept("4 4 N");
        Assert.assertEquals(consumerMowerConfiguration.getLawn().getMowers().size(), 2);

    }
}
