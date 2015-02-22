package fr.mowerandco.core.plan;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * fr.mowerandco.core.plan.Grid
 */
public class GridTest {

    //constructor
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument must be not negative -3 4")
    public void should_throw_IllegalArgumentException_x_negative(){
        new Grid(-3, 4);
    }


    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument must be not negative 3 -4")
    public void should_throw_IllegalArgumentException_y_negative(){
        new Grid(3, -4);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument must be not negative -3 -4")
    public void should_throw_IllegalArgumentException_x_and_y_negative(){
        new Grid(-3, -4);
    }

    //constructor
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument must be not negative 0 4")
    public void should_throw_IllegalArgumentException_x_zero(){
        new Grid(0, 4);
    }


    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument must be not negative 3 0")
    public void should_throw_IllegalArgumentException_y_zero(){
        new Grid(3, 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument must be not negative 0 0")
    public void should_throw_IllegalArgumentException_x_and_y_zero(){
        new Grid(0, 0);
    }

    //isIn
    @Test(expectedExceptions = NullPointerException.class)
    public void should_throw_NullPointerException_isIn(){
        new Grid(2,2).isIn(null);
    }

    @Test
    public void should_equals_IsIn(){
        Assert.assertTrue(new Grid(5, 5).isIn(new Point(5, 5)));
        Assert.assertTrue(new Grid(5,5).isIn(new Point(1, 2)));
        Assert.assertTrue(new Grid(5,5).isIn(new Point(5, 4)));

        Assert.assertTrue(new Grid(5, 5).isIn(new Point(0, 0)));
        Assert.assertFalse(new Grid(5, 5).isIn(new Point(6, 5)));
        Assert.assertFalse(new Grid(5, 5).isIn(new Point(5, 6)));
    }

}
