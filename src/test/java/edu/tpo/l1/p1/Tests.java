package edu.tpo.l1.p1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Tests {
    private double functionArgument;

    public Tests(double functionArgument) {
        this.functionArgument = functionArgument;
    }

    @Parameterized.Parameters(name="x = {0}")
    public static Collection parameters() {
        return Arrays.asList(new Object[][] {
                //граничные значения
                {1.0},
                {0.8},
                {0.52},
                {0},
                {-0.8},
                {-0.52},
                {-1.0},

                //значения больше 1 или меньше -1
                {2.0},
                {-5.0},
                {7.77},
                {-8.88}


        });
    }

    @Test
    public void test_arcsin_equals_MathArcsin() {
        Assert.assertEquals(
                arcsin.arcsin(functionArgument),
                Math.asin(functionArgument),
                arcsin.eps
        );
    }
}
