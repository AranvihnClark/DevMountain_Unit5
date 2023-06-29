import static org.junit.Assert.*;

import com.aranvihnclark.project2.BalancedParenthesis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class) // Remove to test Step2. - more for myself if I need to go back and check something.
public class BalancedParenthesisTest {
    //***************************************************
    //******** Step2 - AssertTrue * AssertFalse *********
    //***************************************************

/*
    // Just some random testing strings.
    final private String missingNone = "[]<[]>";
    final private String missingOpen = "]<[]>";
    final private String missingClose = "[<[]>";
    final private String missingNoneNested = "<[{}()]>";
    final private String missingOpenNested = "<{}()]>";
    final private String missingCloseNested = "<[{}()>";
    final private String openAtEnd = "<>[]<";
    final private String emptyString = "";
    final private String balancedMess = "<[Like(){who said <this> when?}]>";
    final private String mixedBrackets = "(>()[]";

    private BalancedParenthesis bp = new BalancedParenthesis();

    @Test
    public void testBalancedParenthesis_MissingNone() {
        assertTrue(bp.balancedParenthesis(missingNone));
    }

    @Test
    public void testBalancedParenthesis_MissingOpen() {
        assertFalse(bp.balancedParenthesis(missingOpen));
    }

    @Test
    public void testBalancedParenthesis_MissingClose() {
        assertFalse(bp.balancedParenthesis(missingClose));
    }

    @Test
    public void testBalancedParenthesis_MissingNoneNested() {
        assertTrue(bp.balancedParenthesis(missingNoneNested));

    }

    @Test
    public void testBalancedParenthesis_MissingOpenNested() {
        assertFalse(bp.balancedParenthesis(missingOpenNested));
    }

    @Test
    public void testBalancedParenthesis_MissingCloseNested() {
        assertFalse(bp.balancedParenthesis(missingCloseNested));
    }

    @Test
    public void testBalancedParenthesis_openAtEnd() {
        assertFalse(bp.balancedParenthesis(openAtEnd));
    }

    @Test
    public void testBalancedParenthesis_EmptyString() {
        assertTrue(bp.balancedParenthesis(emptyString));
    }

    @Test
    public void testBalancedParenthesis_BalancedMess() {
        assertTrue(bp.balancedParenthesis(balancedMess));
    }

    @Test
    public void testBalancedParenthesis_MixedBrackets() {
        assertFalse(bp.balancedParenthesis(mixedBrackets));
    }
*/

    //***************************************************
    //*********** Step4 - Parameterized Tests ***********
    //***************************************************

    final private String input;
    final private boolean expectedOutput;

    BalancedParenthesis bp = new BalancedParenthesis();

    public BalancedParenthesisTest(String input, boolean expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {

        String missingNone = "[]<[]>";
        String missingOpen = "]<[]>";
        String missingClose = "[<[]>";
        String missingNoneNested = "<[{}()]>";
        String missingOpenNested = "<{}()]>";
        String missingCloseNested = "<[{}()>";
        String openAtEnd = "<>[]<";
        String emptyString = "";
        String balancedMess = "<[Like(){who said <this> when?}]>";
        String mixedBrackets = "(>()[]";

        Object[][] expectedOutputs = {
                {missingNone, true},
                {missingOpen, false},
                {missingClose, false},
                {missingNoneNested, true},
                {missingOpenNested, false},
                {missingCloseNested, false},
                {openAtEnd, false},
                {emptyString, true},
                {balancedMess, true},
                {mixedBrackets, false}
        };

        return Arrays.asList(expectedOutputs);
    }

    @Test
    public void testBalancedParenthesis() {
        assertEquals(expectedOutput, bp.balancedParenthesis(input));
    }
}
