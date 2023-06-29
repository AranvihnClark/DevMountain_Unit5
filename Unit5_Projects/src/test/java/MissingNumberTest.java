import static org.junit.Assert.*;

import com.aranvihnclark.project1.MissingNumber;
import org.junit.Test;

public class MissingNumberTest {

    // Just some random arrays for testing.
    final private int[] missingNone = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // 0
    final private int[] missingTen = { 1, 2, 3, 4, 5, 6, 7, 8, 9 }; // 10
    final private int[] missingSomething = { 5, 1, 4, 2, 7, 10, 8, 9, 6 }; // 3

    // I wasn't going to create this variable to hold our class, but I guess it does make sense in that it reduces the amount of typing needed.
    private MissingNumber mn = new MissingNumber();

    @Test
    public void testMissingNumber_MissingTen() {
        assertEquals(10, mn.missingNumber(missingTen, 10));
    }

    @Test
    public void testMissingNumber_MissingNone() {
        assertEquals(0, mn.missingNumber(missingNone, 10));
    }

    @Test
    public void testMissingNumber_MissingSomething() {
        assertEquals(3, mn.missingNumber(missingSomething, 10));
    }


}