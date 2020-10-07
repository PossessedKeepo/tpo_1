import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;



@RunWith(Parameterized.class)
public class Positive {
    private double functionArgument;

    public Positive(double functionArgument, int precision) {
        this.functionArgument = functionArgument;
    }

    @Parameterized.Parameters(name="x = {0}; precision = {1}")
    public static Collection parameters() {
        return Arrays.asList(new Object[][] {
                { 0.5, 4 },
                { 0.9, 4 }

        });
    }
    @Test
    public void test_arcsin_equals_MathArcsin() {
        /*Assert.assertEquals(
                (double) Math.round(arcsin.arcsin(functionArgument) * Math.pow(10, precision)) / (Math.pow(10, precision)),
                (double) Math.round(Math.asin(functionArgument) * Math.pow(10, precision)) / (Math.pow(10, precision))
        );*/
        Assert.assertEquals(
                arcsin.arcsin(functionArgument),
                Math.asin(functionArgument),
                arcsin.eps
        );
    }
}