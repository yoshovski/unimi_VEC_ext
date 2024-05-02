package it.unimi.di.vec.ass1;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SystemInProvideExt implements BeforeEachCallback, AfterTestExecutionCallback {

    private ByteArrayInputStream inContent;
    private final InputStream originalIn = System.in;

    @Override
    public void beforeEach(ExtensionContext context) {
        String input = context.getRequiredTestMethod().getAnnotation(TestInput.class).value();
        this.inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        System.setIn(originalIn);
    }
}
