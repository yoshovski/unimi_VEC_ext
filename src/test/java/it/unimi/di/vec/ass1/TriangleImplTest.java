package it.unimi.di.vec.ass1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runners.Parameterized;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(OddExecutionExt.class)
@ExtendWith({SystemInProvideExt.class, SystemOutLogExt.class})
public class TriangleImplTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private TriangleImpl triangle;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    private void verifyOutput(TriangleType expected) {
        assertThat(outContent.toString().trim()).isEqualTo(expected.toString());
    }

    private void setTriangle(String sides) throws InvalidTriangleException {
        System.setIn(new ByteArrayInputStream((sides).getBytes()));
        triangle = new TriangleImpl();
    }

    @TestInput("3 3 3")
    @Tag("describe_tests")
    @ParameterizedTest
    @ValueSource(strings = {
            "3 3 3",
            "20 20 20",
            "1000 1000 1000"
    })
    public void testEquilateral(String input) {
        // SETUP
        setTriangle(input);

        // EXERCISE
        triangle.describe();

        // VERIFY
        verifyOutput(TriangleType.EQUILATERAL);
    }

    @TestInput("3 5 5")
    @Tag("describe_tests")
    @ParameterizedTest
    @ValueSource(strings = {
            "3 5 5",
            "5 3 5",
            "5 5 3"
    })
    public void testIsosceles(String input) {
        // SETUP
        setTriangle(input);

        // EXERCISE
        triangle.describe();

        // VERIFY
        verifyOutput(TriangleType.ISOSCELES);
    }

    @TestInput("3 4 5")
    @Tag("describe_tests")
    @ParameterizedTest
    @ValueSource(strings = {
            "3 4 5",
            "4 3 5",
            "5 4 3"
    })
    public void testScalene(String input) {
        // SETUP
        setTriangle(input);

        // EXERCISE
        triangle.describe();

        // VERIFY
        verifyOutput(TriangleType.SCALENE);
    }

    @TestInput("-5 5 5")
    @Tag("constructor_tests")
    @ParameterizedTest
    @ValueSource(strings = {
            "-5 5 5",
            "5 -5 5",
            "5 5 -5",
            "-5 -5 5",
            "-5 5 -5",
            "5 -5 -5",
            "0 0 0",
            "0 0 5",
            "0 5 0",
            "5 0 0"
    })
    public void testConstructorNotATriangle(String input) throws Exception {
        withTextFromSystemIn(input).execute(() -> {
            assertThatThrownBy(TriangleImpl::new)
                    .isInstanceOf(InvalidTriangleException.class)
                    .hasMessage(Error.INVALID_TRIANGLE);
        });
    }

    @TestInput("5 5 100")
    @Tag("constructor_tests")
    @ParameterizedTest
    @ValueSource(strings = {
            "5 5 100",
            "5 100 5",
            "100 5 5"
    })
    public void testConstructorSideTooLong(String input) throws Exception {
        withTextFromSystemIn(input).execute(() -> {
            assertThatThrownBy(TriangleImpl::new)
                    .isInstanceOf(InvalidTriangleException.class)
                    .hasMessage(Error.INVALID_TRIANGLE);
        });
    }

    @TestInput("a 5 5")
    @Tag("constructor_tests")
    @ParameterizedTest
    @ValueSource(strings = {
            "a 5 5",
            "5 a 5",
            "5 5 a",
            "a b c"
    })
    public void testConstructorInvalidInput(String input) throws Exception {
        withTextFromSystemIn(input).execute(() -> {
            assertThatThrownBy(TriangleImpl::new)
                    .isInstanceOf(InvalidTriangleException.class)
                    .hasMessage(Error.MUST_BE_A_NUMBER);
        });
    }
}
