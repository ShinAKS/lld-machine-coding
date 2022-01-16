import com.ayush.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LedgerTest {

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
                "IDIDI Dale 1326 9\r\n"
                        + "IDIDI Dale 3652 4\r\n"
                        + "UON Shelly 15856 3\r\n"
                        + "MBI Harry 9044 10\r\n";
        Driver.main(new String[]{"input.txt" });

        assertEquals(expectedOutput,outContent.toString());
    }
}
