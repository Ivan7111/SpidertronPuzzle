package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String SPACE = "\\s";

    public static void main(String[] args) {
	     Scanner in = new Scanner(System.in);
	     String input;
        while (true) {
            System.out.println("0. Exit\n1. Calculate vector");
	         input = in.nextLine();
	         switch (input) {
                case "0": {
                    return;
                }
                case "1": {
                    solvePuzzle();
                }
            }
        }
    }

    private static void solvePuzzle() {
        Scanner in = new Scanner(System.in);
        List<CoordinatesSet> anglePoints = new ArrayList<>();
        int gridSize;
        int[] target;
        System.out.println("Upper left: ");
        String input = in.nextLine();
        CoordinatesSet ul = new CoordinatesSet(Double.parseDouble(input.split(SPACE)[0]),
                Double.parseDouble(input.split(SPACE)[1]), Double.parseDouble(input.split(SPACE)[2]));
        anglePoints.add(ul);
        System.out.println("Upper right: ");
        input = in.nextLine();
        CoordinatesSet ur = new CoordinatesSet(Double.parseDouble(input.split(SPACE)[0]),
                Double.parseDouble(input.split(SPACE)[1]), Double.parseDouble(input.split(SPACE)[2]));
        anglePoints.add(ur);
        System.out.println("Down left: ");
        input = in.nextLine();
        CoordinatesSet dl = new CoordinatesSet(Double.parseDouble(input.split(SPACE)[0]),
                Double.parseDouble(input.split(SPACE)[1]), Double.parseDouble(input.split(SPACE)[2]));
        anglePoints.add(dl);
        anglePoints.add(calculateFourthPoint(anglePoints));
        System.out.println("Down right: " + anglePoints.get(3).getX() + " " + anglePoints.get(3).getY()
                + " " + anglePoints.get(3).getZ());
        System.out.println("Grid size: ");
        gridSize = Integer.parseInt(in.nextLine());
        System.out.println("Target sector: ");
        input = in.nextLine();
        target = new int[]{Integer.parseInt(input.split(SPACE)[0]), Integer.parseInt(input.split(SPACE)[1])};
        System.out.println("Result: " + calculateResult(anglePoints, gridSize, target));
    }

    private static CoordinatesSet calculateFourthPoint(List<CoordinatesSet> knownPoints) {
        CoordinatesSet ul = knownPoints.get(0);
        CoordinatesSet ur = knownPoints.get(1);
        CoordinatesSet dl = knownPoints.get(2);
        return sum(sub(ur, ul), dl);
    }

    private static CoordinatesSet calculateResult(List<CoordinatesSet> angles, int gridSize, int[] target) {
        CoordinatesSet xSeeker = sub(angles.get(1), angles.get(0));
        CoordinatesSet ySeeker = sub(angles.get(2), angles.get(0));
        CoordinatesSet normalizedX = div(xSeeker, gridSize);
        CoordinatesSet normalizedY = div(ySeeker, gridSize);
        CoordinatesSet targetedX = mul(normalizedX, target[0] * 2);
        CoordinatesSet correctedTargetedX = sub(targetedX, normalizedX);
        CoordinatesSet targetedY = mul(normalizedY, target[1] * 2);
        CoordinatesSet correctedTargetedY = sub(targetedY, normalizedY);
        CoordinatesSet arrivedX = sum(correctedTargetedX, angles.get(0));
        CoordinatesSet arrivedY = sum(correctedTargetedY, angles.get(0));
        return sum(arrivedX, arrivedY);
    }

    private static CoordinatesSet sum(CoordinatesSet v1, CoordinatesSet v2) {
        return new CoordinatesSet(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
    }

    private static CoordinatesSet sub(CoordinatesSet v1, CoordinatesSet v2) {
        return new CoordinatesSet(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ());
    }

    private static CoordinatesSet div(CoordinatesSet v, double d) {
        return new CoordinatesSet(v.getX() / d, v.getY() / d, v.getZ() / d);
    }

    private static CoordinatesSet mul(CoordinatesSet v, double d) {
        return new CoordinatesSet(v.getX() * d, v.getY() * d, v.getZ() * d);
    }
}
