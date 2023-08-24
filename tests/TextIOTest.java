import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TextIOTest {
    TextIO io;
    @BeforeEach
    void beforeAllTests(){
        io = new TextIO();
    }

    @org.junit.jupiter.api.Test
    void initiate() {
        assertDoesNotThrow(() -> io.initiate());
    }

    @org.junit.jupiter.api.Test
    void run() {
        ByteArrayInputStream bais = new ByteArrayInputStream("back\n".getBytes());
        System.setIn(bais);
        io.initiate();
        assertDoesNotThrow(() -> io.run());
    }

    @org.junit.jupiter.api.Test
    void message() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String msg = "Unit Test message()";
        io.message(msg);
        assertEquals(msg + "\r\n", baos.toString());
    }
}