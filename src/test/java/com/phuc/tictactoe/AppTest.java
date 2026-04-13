package com.phuc.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class AppTest {

    private final PrintStream originalOutputStream = System.out;

    private PipedOutputStream outputStream;
    private BufferedReader scanner;

    @BeforeEach
    private void setUp() {
        outputStream = new PipedOutputStream();
        try {
            PipedInputStream inputStream = new PipedInputStream(outputStream);
            scanner = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            //Logger.getLogger(AbstractPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    private void tearDown() {
        System.setOut(originalOutputStream);
    }

    @Test
    public void shouldWarnNoCLIArgument() throws IOException {
        // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // System.setOut(new PrintStream(outputStream));

        App.main(new String[]{});

        String expectedOutput = "Please, input a valid option [1-2]";

        assertEquals(expectedOutput, scanner.readLine());
    }

    @Test
    public void shouldWarnInvalidCLIArgument() throws IOException {
        App.main(new String[]{"abc"});

        String expectedOutput = "Please, input a valid option [1-2]";

        assertEquals(expectedOutput, scanner.readLine());
    }

    @Test
    public void shouldWarnMultipleCLIArgument() throws IOException {
        App.main(new String[]{"1", "2"});

        String expectedOutput = "Please, input a valid option [1-2]";

        assertEquals(expectedOutput, scanner.readLine());
    }

}
