package fr.mowerandco.core.cmd;

import fr.mowerandco.core.Lawn;
import fr.mowerandco.core.Mower;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * fr.mowerandco.core.cmd.Command
 */
public class CommandTest {

    @DataProvider(name = "dataProviderMyValueOf")
    public Object[][] dataProviderMyValueOf() throws Exception {
        return new Object[][]{
                {'G', Optional.of(Command.LEFT)},//
                {'D', Optional.of(Command.RIGHT)},//
                {'A', Optional.of(Command.MOVE)},//

                {null, Optional.empty()},//
                {'V', Optional.empty()},//
                //
        };
    }

    @Test(dataProvider = "dataProviderMyValueOf")
    public void should_be_equals_valueOfByShortName(Character search, Optional<Command> expected) {
        Assert.assertEquals(Command.valueOfByShortName(search), expected);
    }


    @Test
    public void should_rotate_left_my_mower() {
        IMocksControl controller = EasyMock.createControl();
        Mower mower = controller.createMock(Mower.class);
        mower.turnLeft();
        controller.replay();
        Command.LEFT.execute(null, mower);
        controller.verify();
    }


    @Test
    public void should_rotate_right_my_mower() {
        IMocksControl controller = EasyMock.createControl();
        Mower mower = controller.createMock(Mower.class);
        mower.turnRight();
        controller.replay();
        Command.RIGHT.execute(null, mower);
        controller.verify();
    }

    @Test
    public void should_move_my_mower() {
        IMocksControl controller = EasyMock.createControl();
        Mower mower = controller.createMock(Mower.class);
        Lawn lawn = controller.createMock(Lawn.class);
        lawn.move(mower);
        controller.replay();
        Command.MOVE.execute(lawn, mower);
        controller.verify();
    }
}
