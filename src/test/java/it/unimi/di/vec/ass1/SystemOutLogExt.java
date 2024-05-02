package it.unimi.di.vec.ass1;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutLogExt implements BeforeEachCallback, AfterEachCallback {

    private PrintStream originalOut;
    private ByteArrayOutputStream outputStream;

    @Override
    public void beforeEach(ExtensionContext context) {
        originalOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        System.setOut(originalOut);
        String capturedOutput = outputStream.toString();
        if (!capturedOutput.isEmpty()) {
            System.out.println("Captured output: " + capturedOutput);
        } else {
            System.out.println("No output captured");
        }
    }
}
