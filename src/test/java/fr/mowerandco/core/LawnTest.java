package fr.mowerandco.core;

import fr.mowerandco.core.plan.Orientation;
import fr.mowerandco.core.plan.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Lawn.
 */
public class LawnTest {


    @Test(expectedExceptions = NullPointerException.class)
    public void should_throw_NullPointer_because_Mower_Null() {
        final Lawn lawn = new Lawn(3, 3);
        lawn.addMower(null);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void should_throw_NullPointer_because_Point_Null() {
        final Lawn lawn = new Lawn(3, 3);
        Mower mower = new Mower(new Point(3, 3), Orientation.EAST);
        mower.setPoint(null);
        lawn.addMower(mower);
    }


    @Test
    public void should_add_mower() {
        //prepare
        final Lawn lawn = new Lawn(5, 5);
        final Point pointWhereIPut = new Point(3, 3);
        Mower mower = new Mower(pointWhereIPut, Orientation.EAST);

        //action
        Assert.assertTrue(lawn.addMower(mower));

        //action et verification
        Assert.assertEquals(1, lawn.getMowers().size());
        Assert.assertSame(mower, lawn.getMowers().get(pointWhereIPut));
    }


    @Test
    public void should_not_add_mower_because_out_of_grid() {
        //prepare
        final Lawn lawn = new Lawn(5, 5);
        final Point pointWhereIPut = new Point(30, 30);
        Mower mower = new Mower(pointWhereIPut, Orientation.EAST);

        //action et verification
        Assert.assertFalse(lawn.addMower(mower));

        Assert.assertTrue(lawn.getMowers().isEmpty());
    }


    @Test
    public void should_not_add_mower_is_not_free_place() {
        //prepare
        final Lawn lawn = new Lawn(5, 5);
        final Point pointWhereIPut = new Point(2, 1);
        Mower mower = new Mower(pointWhereIPut, Orientation.EAST);
        Assert.assertTrue(lawn.addMower(mower));

        //action et verification
        Mower mowerNotAdd = new Mower(pointWhereIPut, Orientation.EAST);
        Assert.assertFalse(lawn.addMower(mowerNotAdd));

        Assert.assertEquals(1, lawn.getMowers().size());
        Assert.assertSame(mower, lawn.getMowers().get(pointWhereIPut));
    }

    @Test
    public void should_not_move_mower_because_not_in() {
        //prepare
        final Lawn lawn = new Lawn(5, 5);
        final Point mowerPosition = new Point(2, 1);
        Mower mower = new Mower(mowerPosition, Orientation.EAST);

        //action
        lawn.move(mower);

        //verification
        //la position n'a pas change
        Assert.assertSame(mower.getPoint(), mowerPosition);
        Assert.assertFalse(lawn.getMowers().containsValue(mower));

    }

    @Test
    public void should_not_move_mower_because_translate_is_out() {
        //prepare
        final Lawn lawn = new Lawn(5, 5);
        final Point mowerPosition = new Point(5, 5);
        Mower mower = new Mower(mowerPosition, Orientation.EAST);
        lawn.addMower(mower);

        //action
        lawn.move(mower);

        //verification
        //la position n'a pas change
        Assert.assertSame(mower.getPoint(), mowerPosition);
        Assert.assertTrue(lawn.getMowers().containsValue(mower));

    }

    @Test
    public void should_not_move_mower_because_other_mower() {
        //prepare
        final Lawn lawn = new Lawn(5, 5);
        final Point mowerPosition = new Point(5, 5);
        Mower mower = new Mower(mowerPosition, Orientation.EAST);
        lawn.addMower(mower);

        final Point moweWeWantMovePosition = new Point(5, 4);
        Mower mowerWeWantMove = new Mower(moweWeWantMovePosition, Orientation.NORTH);
        lawn.addMower(mowerWeWantMove);

        //action
        lawn.move(mowerWeWantMove);

        //verification
        //la position n'a pas change
        Assert.assertSame(mowerWeWantMove.getPoint(), moweWeWantMovePosition);

    }

    @Test
    public void should_move_mower() {
        //prepare
        final Lawn lawn = new Lawn(5, 5);
        final Point mowerPosition = new Point(5, 5);
        Mower mower = new Mower(mowerPosition, Orientation.EAST);
        lawn.addMower(mower);

        final Point moweWeWantMovePosition = new Point(5, 3);
        Mower mowerWeWantMove = new Mower(moweWeWantMovePosition, Orientation.NORTH);
        lawn.addMower(mowerWeWantMove);

        //action
        lawn.move(mowerWeWantMove);

        //verification
        //la position n'a pas change
        Assert.assertEquals(mowerWeWantMove.getPoint(), new Point(5, 4));

    }

}
