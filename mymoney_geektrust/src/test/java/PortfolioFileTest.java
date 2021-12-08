import com.ayush.mymoney.PortfolioApp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class PortfolioFileTest {

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
    public void testFileMode() throws IOException {
        final String expectedOutput =
                "15937 14552 6187\n"
                        + "23292 16055 7690\n"
                        + "CANNOT_REBALANCE\n";
        PortfolioApp.main(new String[]{"file_input.txt" });

        Assertions.assertEquals(expectedOutput, outContent.toString());
    }
}
