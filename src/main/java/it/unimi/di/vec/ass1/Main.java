package it.unimi.di.vec.ass1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            TriangleImpl triangle = new TriangleImpl();
            triangle.describe();
        }
        catch (InvalidTriangleException e) {
            System.err.println(e.getMessage());
        }
    }
}
