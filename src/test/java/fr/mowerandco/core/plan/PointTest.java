package fr.mowerandco.core.plan;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * fr.mowerandco.core.plan.Point
 */
public class PointTest {

    @Test
    public void should_be_translate() {
        Point myPoint = new Point(3, 5);
        Point translatedPoint = myPoint.translate(4, 6);

        //on verifie pas la meme instance (immutable).
        Assert.assertNotSame(translatedPoint, myPoint);
        Assert.assertEquals(7, translatedPoint.getX());
        Assert.assertEquals(11, translatedPoint.getY());

    }
}
