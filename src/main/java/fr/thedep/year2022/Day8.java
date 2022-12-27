package fr.thedep.year2022;

import fr.thedep.utils.ReadFilesUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.List;

public final class Day8 {

    private static final String INPUTS = """
            30373
            25512
            65332
            33549
            35390
            """;

    private Day8() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(INPUTS.lines().toList()));
        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day8exo1.txt")));
        System.out.println(exo2(INPUTS.lines().toList()));
        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day8exo1.txt")));
    }

    @NotNull
    private static String exo1(List<String> inputs) {
        int result = 0;

        final int[][] ints = inputs.stream()
                                   .map(s -> s.chars().toArray())
                                   .toArray(int[][]::new);

        final int lineSize = ints[0].length;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < lineSize; j++) {
                final int result1 = (isVisibleForExo1(ints, i, j)) ? 1 : 0;
                result += result1;
                System.out.print(result1 + " ");
            }
            System.out.println();
        }

        return "" + result;
    }

    @NotNull
    private static String exo2(List<String> inputs) {
        int max = 0;

        final int[][] ints = inputs.stream()
                                   .map(s -> s.chars().toArray())
                                   .toArray(int[][]::new);

        final int lineSize = ints[0].length;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < lineSize; j++) {
                final int result1 = calculScore(ints, i, j);
                if (result1 > max) max = result1;
                System.out.print(result1 + " ");
            }
            System.out.println();
        }

        return "" + max;
    }

    private static int calculScore(int[][] matrix, int i, int j) {
        final int tree = matrix[i][j];
        final int largeur = matrix.length;
        final int hauteur = matrix[0].length;

        int a = 0;
        for (int k = i + 1; k < hauteur; k++) {
            final int matrix1 = matrix[k][j];
            a++;
            if (tree <= matrix1)
                break;
        }

        int b = 0;
        for (int k = j + 1; k < largeur; k++) {
            final int matrix1 = matrix[i][k];
            b++;
            if (tree <= matrix1)
                break;
        }

        int c = 0;
        for (int k = i - 1; k >= 0; k--) {
            final int matrix1 = matrix[k][j];
            c++;
            if (tree <= matrix1)
                break;
        }

        int d = 0;
        for (int k = j - 1; k >= 0; k--) {
            final int matrix1 = matrix[i][k];
            d++;
            if (tree <= matrix1)
                break;
        }

        return a * b * c * d;

    }

    private static boolean isVisibleForExo1(int[][] matrix, int i, int j) {
        final int tree = matrix[i][j];
        final int largeur = matrix.length;
        final int hauteur = matrix[0].length;

        boolean a = true;
        for (int k = 0; k < i; k++) {
            final int matrix1 = matrix[k][j];
            if (tree <= matrix1) {
                a = false;
                break;
            }
        }

        boolean b = true;
        for (int k = 0; k < j; k++) {
            final int matrix1 = matrix[i][k];
            if (tree <= matrix1) {
                b = false;
                break;
            }
        }

        boolean c = true;
        for (int k = largeur - 1; k > i; k--) {
            final int matrix1 = matrix[k][j];
            if (tree <= matrix1) {
                c = false;
                break;
            }
        }

        boolean d = true;
        for (int k = hauteur - 1; k > j; k--) {
            final int matrix1 = matrix[i][k];
            if (tree <= matrix1) {
                d = false;
                break;
            }
        }

        return a || b || c || d;
    }

}
