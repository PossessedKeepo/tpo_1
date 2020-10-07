package edu.tpo.l1.p1;

import org.junit.runner.JUnitCore;

public class runTest {
    public static void main(String[] args) {
        JUnitCore junitCore;
        junitCore = new JUnitCore();
        junitCore.addListener(new ExecutionListener());
        junitCore.run(Tests.class);
    }
}
