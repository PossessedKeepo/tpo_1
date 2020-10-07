import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class runTest {
    public static void main(String[] args) {
        JUnitCore junitCore;
        junitCore = new JUnitCore();
        junitCore.addListener(new ExecutionListener());

        Result result = junitCore.run(Positive.class);
    }
}