package processor;

import java.util.Scanner;

class MatrixProc {

    /* INPUT & OUTPUT *////

    static Scanner scan = new Scanner(System.in);

    public static double[][] inputMatrix () {
        System.out.println("Input number of Rows and Columns:");
        int r = scan.nextInt();
        int c = scan.nextInt();
        double[][] m = new double[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) {
                m[i][j] = scan.nextDouble();
            }
        return m;
    }

    public static void outputMatrix(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }


    static boolean isSquare (double[][] m) {
        return m.length == m[0].length;
    }

    static boolean isEqualSize (double[][] m1, double[][] m2) {
        return m1.length == m2.length
                && m1[0].length == m2[0].length;
    }

    static boolean canMultiply (double[][] m1, double[][] m2) {
        return m1[0].length == m2.length;
    }


    public static double[][] matrixAdd (double[][] m1, double[][] m2) {
        double[][] res = new double[m1.length][m1[0].length];
        for (int i = 0; i < res.length; i++)
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = m1[i][j] + m2[i][j];
            }
        return res;
    }

    public static double[][] matrixMultiplyByNumber(double[][] m, int mult) {
        double[][] res  = new double[m.length][m[0].length];
        for (int i = 0; i < res.length; i++)
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = m[i][j] * mult;
            }
        return res;
    }

    public static double[][] matrixMultiplyByNumber(double[][] m, double mult) {
        double[][] res  = new double[m.length][m[0].length];
        for (int i = 0; i < res.length; i++)
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = m[i][j] * mult;
            }
        return res;
    }

    public static double[][] matrixMultiply (double[][] m1, double[][]m2) {
        double[][] res = new double[m1.length][m2[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = 0;
                for (int k = 0; k < m1[0].length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static double[][] transposeMain(double[][] m) {
        double[][] result = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                result[i][j] = m[j][i];
            }
        }
        return result;
    }

    public static double[][] transposeSide(double[][] m) {
        double[][] result = new double[m.length][m[0].length];
        for (int i = m.length - 1; i >= 0; i--) {
            for (int j = m[i].length - 1; j >= 0; j--) {
                result[m.length - 1 - i][m[0].length - 1 - j] = m[j][i];
            }
        }
        return result;
    }

    public static double[][] transposeVerticalLine(double[][] m) {
        double[][] result = new double[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = m[i].length - 1; j >= 0; j--) {
                result[i][m[0].length - 1 - j] = m[i][j];
            }
        }
        return result;
    }

    public static double[][] transposeHorizontalLine(double[][] m) {
        double[][] result = new double[m.length][m[0].length];
        for (int i = m.length - 1; i >= 0; i--) {
            for (int j = 0; j < m[0].length; j++) {
                result[m.length - 1 - i][j] = m[i][j];
            }
        }
        return result;
    }

    public static double[][] getEachCofactor(double[][] m1) {
        double[][] res = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                res[i][j] = Math.pow(-1, i + j + 2) * MatrixProc.getSingleDeterminant(m1, i, j);
            }
        }
        return res;
    }

    public static double getSingleDeterminant(double[][] m, int row, int col) {
        double[][] minor = getNewMatrixExcludeRowAndCol(row, col, m);
        double result = 0;
        try {
            if (minor.length == 2 && minor[0].length == 2) {
                result += calculateDeterminate(minor);
            } else {
                result += getDeterminant(minor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static double getDeterminant(double[][] m) {
        double result = 0;
        int rowExclude = getRowWithManyZero(m);
        for (int i = 0; i < m[rowExclude].length; i++) {
            if (m[rowExclude][i] == 0) {
                continue;
            }

            double[][] minor = getNewMatrixExcludeRowAndCol(rowExclude, i, m);
            try {
                if (minor.length == 2 && minor[0].length == 2) {
                    result += m[rowExclude][i] * Math.pow(-1, rowExclude + 1 + i + 1) *  calculateDeterminate(minor);
                } else {
                    result += m[rowExclude][i] * Math.pow(-1, rowExclude + 1 + i + 1) * getDeterminant(minor);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return result;
    }

    public static double calculateDeterminate(double[][] m) throws Exception {
        if (m.length != 2 && m[0].length != 2) {
            throw new Exception("Wrong matrix length!");
        }
        return m[0][0] * m[1][1] - m[0][1] * m[1][0];
    }

    public static double[][] getNewMatrixExcludeRowAndCol(int row, int col, double[][] m) {
        double[][] newM = new double[m.length-1][m[0].length-1];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (i != row && j != col) {
                    if (i > row && j < col) {
                        newM[i-1][j] = m[i][j];
                    } else if (i < row && j > col) {
                        newM[i][j-1] = m[i][j];
                    } else if (i > row && j > col) {
                        newM[i-1][j-1] = m[i][j];
                    } else {
                        newM[i][j] = m[i][j];
                    }
                }
            }
        }
        return newM;
    }

    public static int getRowWithManyZero(double[][] m) {
        int[] zeros = new int[m.length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0) {
                    ++zeros[i];
                }
            }
        }

        int maxZerosInRow = 0;
        int row = 0;
        for (int i = 0; i < zeros.length; i++) {
            if (zeros[i] > maxZerosInRow) {
                maxZerosInRow = zeros[i];
                row = i;
            }
        }

        return row;
    }

}
