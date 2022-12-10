package fr.thedep.year2022.day1;

import fr.thedep.utils.ReadFilesUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day2 {

    private static List<String> INPUTS = List.of(
            "A Y",
            "B X",
            "C Z"
    );

    public static void main(String[] args) throws FileNotFoundException {
        //        System.out.println(exo1(INPUTS));
        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day2exo1.txt")));

    }

    private static String exo1(List<String> inputs) {
        final int sum = inputs.stream()
                              .map(Game::new)
                              .mapToInt(Game::getScore)
                              .sum();

        return "" + sum;
    }

    enum Shifoumi {
        A(1),
        B(2),
        C(3);

        private final int value;

        Shifoumi(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum PlayerResult {
        X(0),
        Y(3),
        Z(6);

        private final int value;

        PlayerResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum Gain {
        LOST(0),
        DRAW(3),
        WON(6);

        private final int gain;

        Gain(int gain) {
            this.gain = gain;
        }

        public int getGain() {
            return gain;
        }
    }

    record Game(Shifoumi adv, PlayerResult player) {

        public Game(String game) {
            this(Shifoumi.valueOf(String.valueOf(game.charAt(0))), PlayerResult.valueOf(String.valueOf(game.charAt(2))));
        }

        public int getScore() {
            return player().value + choice().value;
        }

        public Shifoumi choice() {
            return switch (adv) {
                case A -> switch (player) {
                    case X -> Shifoumi.C;
                    case Y -> Shifoumi.A;
                    case Z -> Shifoumi.B;
                };
                case B -> switch (player) {
                    case X -> Shifoumi.A;
                    case Y -> Shifoumi.B;
                    case Z -> Shifoumi.C;
                };
                case C -> switch (player) {
                    case X -> Shifoumi.B;
                    case Y -> Shifoumi.C;
                    case Z -> Shifoumi.A;
                };
            };
        }
    }
}
