package it.unimi.di.vec.ass1;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ErrPrintNamingExt implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod();
        String methodName = testMethod.getName();
        String annotations = Arrays.toString(testMethod.getAnnotations());

        System.err.println("Method name: " + methodName);
        System.err.println("Annotations: " + annotations);
    }
}
