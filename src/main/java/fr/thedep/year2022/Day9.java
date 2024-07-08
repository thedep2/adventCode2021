package fr.thedep.year2022;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public final class Day9 {

    public static final int START = 10;
    private static final String INPUTS = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
            """;

    private Day9() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(INPUTS.lines().toList()));
        //        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day9exo1.txt")));
        //        System.out.println(exo2(INPUTS.lines().toList()));
        //        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day9exo1.txt")));
    }

    @NotNull
    private static String exo1(List<String> inputs) {
        boolean[][] matrix = new boolean[START * 2][START * 2];
        Head head = new Head(START, START);
        Tail tail = new Tail(START, START, head);

        for (final boolean[] row : matrix) {
            Arrays.fill(row, false);
        }

        //        display(matrix, head, tail);

        for (var input : inputs) {

            final char direction = input.charAt(0);
            final int quantity = Integer.parseInt(String.valueOf(input.charAt(2)));
            System.out.println("input = " + input);

            Head.Direction directionEnum = switch (direction) {
                case 'R' -> Head.Direction.RIGHT;
                case 'U' -> Head.Direction.UP;
                case 'L' -> Head.Direction.LEFT;
                case 'D' -> Head.Direction.DOWN;
                default -> throw new RuntimeException();
            };

            for (int i = 0; i < quantity; i++) {
                head.move(directionEnum);
                tail.follow(head);
                matrix[tail.x][tail.y] = true;
                //                display(matrix, head, tail);
            }
            //            System.out.println(tail.x + "-" + tail.y);
            //            display(matrix, head, tail);
        }
        //        display(matrix, head, tail);
        int count = 0;
        for (final boolean[] row : matrix) {
            for (final boolean place : row) {
                if (place) count++;
            }
        }

        return "" + count;
    }

    private static void display(boolean[][] matrix, Point head, Point tail) {
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            boolean[] row = matrix[i];
            for (int j = 0; j < row.length; j++) {
                if (head.x == i && head.y == j)
                    System.out.print("H");
                else if (tail.x == i && tail.y == j)
                    System.out.print("T");
                else {
                    boolean place = row[j];
                    System.out.print(place ? "#" : ".");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @NotNull
    private static String exo2(List<String> inputs) {
        return "";
    }

    static class Head extends Point {

        public Head(int x, int y) {
            super(x, y);
        }

        public void move(Direction direction) {
            switch (direction) {
                case UP -> moveUp();
                case DOWN -> moveDown();
                case LEFT -> moveLeft();
                case RIGHT -> moveRight();
            }
        }

        private void moveRight() {
            y += 1;
        }

        private void moveUp() {
            x += 1;
        }

        private void moveLeft() {
            y -= 1;
        }

        private void moveDown() {
            x -= 1;
        }

        enum Direction {
            UP,
            RIGHT,
            DOWN,
            LEFT
        }
    }

    static class Tail extends Point {

        private RecordPoint lastHead;

        public Tail(int x, int y, Head head) {
            super(x, y);
            lastHead = new RecordPoint(head.x, head.y);
        }

        public void follow(Head head) {
            final int hX = head.x;
            final int hY = head.y;

            final int diffX = x - hX;
            final int diffY = y - hY;

            if (Math.abs(diffX) > 1 || Math.abs(diffY) > 1) {
                x = lastHead.x();
                y = lastHead.y();
            } else {

            }

            /*
               (x',y') >< (x,y) = if abs dx > 1 || abs dy > 1
      then (signum dx + x',signum dy + y')
      else (x',y')
         where
         (dx,dy) = (x - x',y - y')
             */

            lastHead = new RecordPoint(head);
        }

        record RecordPoint(int x, int y) {

            public RecordPoint(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public RecordPoint(Point p) {
                this(p.x, p.y);
            }
        }
    }

    abstract static class Point {

        public int x;
        public int y;

        protected Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
