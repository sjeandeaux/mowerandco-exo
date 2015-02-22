package fr.mowerandco.core;

import fr.mowerandco.core.plan.Orientation;
import fr.mowerandco.core.plan.Point;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Mower
 */
public class MowerTest {

    //constructor
    @DataProvider(name = "dataProviderNullPointer")
    public Object[][] dataProviderNullPointer() throws Exception {
        return new Object[][]{
                {null, null},//
                {new Point(1, 1), null},//
                {null, Orientation.EAST},//
        };
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "dataProviderNullPointer")
    public void should_throw_NullPointer(final Point point, final Orientation orientation) {
        new Mower(point, orientation);
    }

    //turnLeft
    @Test
    public void should_turn_left() {
        Mower mower = new Mower(new Point(1, 2), Orientation.NORTH);

        mower.turnLeft();
        Assert.assertEquals(Orientation.WEST, mower.getOrientation());

        mower.turnLeft();
        Assert.assertEquals(Orientation.SOUTH, mower.getOrientation());


        mower.turnLeft();
        Assert.assertEquals(Orientation.EAST, mower.getOrientation());

        mower.turnLeft();
        Assert.assertEquals(Orientation.NORTH, mower.getOrientation());

    }

    //turnRight
    @Test
    public void should_turn_right() {
        Mower mower = new Mower(new Point(1, 2), Orientation.NORTH);

        mower.turnRight();
        Assert.assertEquals(Orientation.EAST, mower.getOrientation());

        mower.turnRight();
        Assert.assertEquals(Orientation.SOUTH, mower.getOrientation());


        mower.turnRight();
        Assert.assertEquals(Orientation.WEST, mower.getOrientation());

        mower.turnRight();
        Assert.assertEquals(Orientation.NORTH, mower.getOrientation());

    }

    //translate
    //constructor
    @DataProvider(name = "dataTranslate")
    public Object[][] dataTranslate() throws Exception {
        return new Object[][]{
                {new Mower(new Point(0, 0), Orientation.NORTH), new Point(0, 1)},//
                {new Mower(new Point(0, 0), Orientation.EAST), new Point(1, 0)},//
                {new Mower(new Point(0, 0), Orientation.SOUTH), new Point(0, -1)},//
                {new Mower(new Point(0, 0), Orientation.WEST), new Point(-1, 0)},//
        };
    }

    @Test(dataProvider = "dataTranslate")
    public void should_translate(final Mower mower, Point expected) {
        Assert.assertEquals(expected, mower.translate());
        Assert.assertNotEquals(expected, mower.getPoint());

    }
}
