import com.ayush.mymoney.PortfolioApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class PortfolioInteractiveTest {
    private InputStream sysInBackup;
    private PrintStream sysOutBackup;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws Exception {
        sysInBackup = System.in; // backup System.in to restore it later
        sysOutBackup = System.out; // backup System.out to restore it later
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() throws Exception {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test
    public void testInteractiveMode() throws IOException {
        final String commands =
                "ALLOCATE 6000 3000 1000\r\n"
                        + "SIP 2000 1000 500\r\n"
                        + "CHANGE 4.00% 10.00% 2.00% JANUARY\r\n"
                        + "CHANGE -10.00% 40.00% 0.00% FEBRUARY\r\n"
                        + "CHANGE 12.50% 12.50% 12.50% MARCH\r\n"
                        + "CHANGE 8.00% -3.00% 7.00% APRIL\r\n"
                        + "CHANGE 13.00% 21.00% 10.50% MAY\r\n"
                        + "CHANGE 10.00% 8.00% -5.00% JUNE\r\n"
                        + "BALANCE MARCH\r\n"
                        + "REBALANCE\r\n";

        final String expectedOutput =
                "10593 7897 2272\n"
                        + "23619 11809 3936\n";

        final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
        System.setIn(in);

        PortfolioApp.main(new String[]{});
        Assertions.assertEquals(expectedOutput, outContent.toString());
    }

}