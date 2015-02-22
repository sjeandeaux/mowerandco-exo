package fr.mowerandco.core.plan;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * fr.mowerandco.core.plan.Orientation
 */
public class OrientationTest {

    @DataProvider(name = "dataProviderMyValueOf")
    public Object[][] dataProviderMyValueOf() throws Exception {
        return new Object[][]{
                {"N", Optional.of(Orientation.NORTH)},//
                {"E", Optional.of(Orientation.EAST)},//
                {"S", Optional.of(Orientation.SOUTH)},//
                {"W", Optional.of(Orientation.WEST)},//

                {null, Optional.empty()},//
                {"V", Optional.empty()},//
                //
        };
    }

    @Test(dataProvider = "dataProviderMyValueOf")
    public void should_be_equals_valueOfByShortName(String search, Optional<Orientation> expected) {
        Assert.assertEquals(Orientation.valueOfByShortName(search), expected);
    }


}
