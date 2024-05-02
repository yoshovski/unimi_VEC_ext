package it.unimi.di.vec.ass1;


import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.opentest4j.TestAbortedException;

public class OddExecutionExt implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        ExtensionContext.Store store = context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL);
        Integer testIndex = store.getOrComputeIfAbsent("testIndex", key -> 0, Integer.class);
        store.put("testIndex", testIndex + 1);

        if (isOdd(testIndex)) {
            throw new TestAbortedException("Test skipped");
        }
    }

    private boolean isOdd(int number) {
        return number % 2 != 0;
    }
}


