package processor;

import processor.MatrixProc;

import java.util.Scanner;

public class Main {

    static boolean exit = false;

    public static void main(String[] args) {

        while (!exit) chooseAction();

    }// main method

    public static void chooseAction() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Calculate a determinant\n" +
                "6. Inverse matrix\n" +
                "0. Exit");
        Scanner s = new Scanner(System.in);
        int action = s.nextInt();
        System.out.println("Your choise: " + action);
        double[][] m1, m2;
        switch (action) {
            case 0:
                exit = true;
                break;
            case 1:
                m1 = MatrixProc.inputMatrix();
                m2 = MatrixProc.inputMatrix();
                System.out.println("The result is:");
                if (MatrixProc.isEqualSize(m1, m2)) {
                    MatrixProc.outputMatrix(MatrixProc.matrixAdd(m1, m2));
                } else {
                    System.out.println("ERROR");
                }
                break;
            case 2:
                m1 = MatrixProc.inputMatrix();
                final int mult = s.nextInt();
                System.out.println("The result is:");
                MatrixProc.outputMatrix(MatrixProc.matrixMultiplyByNumber(m1, mult));
                break;
            case 3:
                m1 = MatrixProc.inputMatrix();
                m2 = MatrixProc.inputMatrix();
                if (MatrixProc.canMultiply(m1, m2)) {
                    System.out.println("The result is:");
                    MatrixProc.outputMatrix(MatrixProc.matrixMultiply(m1, m2));
                } else {
                    System.out.println("ERROR");
                }
                break;
            case 4:
                System.out.println(
                        "1. Main diagonal\n" +
                        "2. Side diagonal\n" +
                        "3. Vertical line\n" +
                        "4. Horizontal line\n"
                        );
                int transpose = s.nextInt();
                double[][] matrix = MatrixProc.inputMatrix();
                switch (transpose) {
                    case 1:
                        MatrixProc.outputMatrix(MatrixProc.transposeMain(matrix));
                        break;
                    case 2:
                        MatrixProc.outputMatrix(MatrixProc.transposeSide(matrix));
                        break;
                    case 3:
                        MatrixProc.outputMatrix(MatrixProc.transposeVerticalLine(matrix));
                        break;
                    case 4:
                        MatrixProc.outputMatrix(MatrixProc.transposeHorizontalLine(matrix));
                        break;
                }
                break;
            case 5:
                m1 = MatrixProc.inputMatrix();
                System.out.println("The result is:\n" + MatrixProc.getDeterminant(m1));
                break;
            case 6:
                m1 = MatrixProc.inputMatrix();

                double determinate = 0;
                if (m1.length == 2 && m1[0].length == 2) {
                    try {
                        determinate = MatrixProc.calculateDeterminate(m1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    determinate = MatrixProc.getDeterminant(m1);
                }
                if (determinate == 0) {
                    System.out.println("This matrix doesn't have an inverse.");
                    break;
                }

                double[][] transposeMain = MatrixProc.transposeMain(MatrixProc.getEachCofactor(m1));
                System.out.println("The result is:");
                MatrixProc.outputMatrix(MatrixProc.matrixMultiplyByNumber(transposeMain, 1/determinate));
                break;
        }

    }

}