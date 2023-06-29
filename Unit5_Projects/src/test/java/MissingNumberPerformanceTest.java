import com.aranvihnclark.project1.MissingNumber;
import org.junit.Test;

// I only created 2 tests (because there was no further need to lower the runtime and space complexity after I finished the second test.
// As such, there will only be two tests.
// Though, honestly, they should run very close to the same time since they are very similar.
// IF I had to guess, I would guess my second one is fast (as it is technically the most refined), only because of what is being done in each for loop.
public class MissingNumberPerformanceTest {

    // Just some random arrays for testing - copied from MissingNumberTest.
    final private int[] missingNone = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // 0
    final private int[] missingTen = { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // 10
    final private int[] missingSomething = { 5, 1, 4, 2, 7, 10, 8, 9, 6 }; // 3

    final private int max = 10;

    private MissingNumber mn = new MissingNumber();

    @Test(timeout=100)
    public void testMissingNumberOne_Performance() {
        for (int i = 0; i < 1000000; i++) {
            mn.findMissingNumberOne(missingNone, max);
            mn.findMissingNumberOne(missingTen, max);
            mn.findMissingNumberOne(missingSomething, max);
        }
    }

    @Test(timeout=100)
    public void testMissingNumberTwo_Performance() {
        for (int i = 0; i < 1000000; i++) {
            mn.findMissingNumberTwo(missingNone, max);
            mn.findMissingNumberTwo(missingTen, max);
            mn.findMissingNumberTwo(missingSomething, max);
        }
    }
}
