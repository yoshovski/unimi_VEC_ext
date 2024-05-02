package it.unimi.di.vec.ass1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TriangleImpl implements Triangle {
    private final int sideA;
    private final int sideB;
    private final int sideC;

    public TriangleImpl() {
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                sideA = scanner.nextInt();
                sideB = scanner.nextInt();
                sideC = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new InvalidTriangleException(Error.MUST_BE_A_NUMBER);
            }

            if (hasInvalidSides()) {
                throw new InvalidTriangleException(Error.INVALID_TRIANGLE);
            }
        }
    }

    @Override
    public void describe() throws InvalidTriangleException {
            if (hasInvalidSides()) {
                throw new InvalidTriangleException(Error.INVALID_TRIANGLE);
            }

            int equalSides = 0;

            if (sideA == sideB)
                equalSides++;
            if (sideA == sideC)
                equalSides++;
            if (sideB == sideC)
                equalSides++;

            switch (equalSides) {
                case 0:
                    System.out.println(TriangleType.SCALENE);
                    break;
                case 1:
                    System.out.println(TriangleType.ISOSCELES);
                    break;
                case 3:
                    System.out.println(TriangleType.EQUILATERAL);
                    break;
                default:
                    throw new InvalidTriangleException(Error.INVALID_TRIANGLE);
            }
    }

    private boolean hasInvalidSides() {
        return !(sideA > 0 && sideB > 0 && sideC > 0 &&
                sideA + sideB > sideC &&
                sideA + sideC > sideB &&
                sideB + sideC > sideA);
    }

}
