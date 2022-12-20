package fr.thedep.year2022.day1;

import fr.thedep.utils.ReadFilesUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day5 {

    private static final List<String> INPUTS = List.of(
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2"
    );

    private Day5() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(getShip1(), INPUTS));
        System.out.println(exo1(getShip2(), ReadFilesUtils.readFileInString("main", "year2022day5exo1.txt")));
        System.out.println(exo2(getShip1(), INPUTS));
        System.out.println(exo2(getShip2(), ReadFilesUtils.readFileInString("main", "year2022day5exo1.txt")));
    }

    @NotNull
    private static List<Deque<String>> getShip1() {
        Deque<String> stack1 = new ArrayDeque<>();
        stack1.addLast("Z");
        stack1.addLast("N");

        Deque<String> stack2 = new ArrayDeque<>();
        stack2.addLast("M");
        stack2.addLast("C");
        stack2.addLast("D");

        Deque<String> stack3 = new ArrayDeque<>();
        stack3.addLast("P");

        List<Deque<String>> ship = new ArrayList<>();
        ship.add(stack1);
        ship.add(stack2);
        ship.add(stack3);
        return ship;
    }

    @NotNull
    private static List<Deque<String>> getShip2() {
        Deque<String> stack1 = new ArrayDeque<>();
        stack1.addLast("B");
        stack1.addLast("W");
        stack1.addLast("N");

        Deque<String> stack2 = new ArrayDeque<>();
        stack2.addLast("L");
        stack2.addLast("Z");
        stack2.addLast("S");
        stack2.addLast("P");
        stack2.addLast("T");
        stack2.addLast("D");
        stack2.addLast("M");
        stack2.addLast("B");

        Deque<String> stack3 = new ArrayDeque<>();
        stack3.addLast("Q");
        stack3.addLast("H");
        stack3.addLast("Z");
        stack3.addLast("W");
        stack3.addLast("R");

        Deque<String> stack4 = new ArrayDeque<>();
        stack4.addLast("W");
        stack4.addLast("D");
        stack4.addLast("V");
        stack4.addLast("J");
        stack4.addLast("Z");
        stack4.addLast("R");

        Deque<String> stack5 = new ArrayDeque<>();
        stack5.addLast("S");
        stack5.addLast("H");
        stack5.addLast("M");
        stack5.addLast("B");

        Deque<String> stack6 = new ArrayDeque<>();
        stack6.addLast("L");
        stack6.addLast("G");
        stack6.addLast("N");
        stack6.addLast("J");
        stack6.addLast("H");
        stack6.addLast("V");
        stack6.addLast("P");
        stack6.addLast("B");

        Deque<String> stack7 = new ArrayDeque<>();
        stack7.addLast("J");
        stack7.addLast("Q");
        stack7.addLast("Z");
        stack7.addLast("F");
        stack7.addLast("H");
        stack7.addLast("D");
        stack7.addLast("L");
        stack7.addLast("S");

        Deque<String> stack8 = new ArrayDeque<>();
        stack8.addLast("Z");
        stack8.addLast("S");
        stack8.addLast("F");
        stack8.addLast("J");
        stack8.addLast("G");
        stack8.addLast("Q");
        stack8.addLast("B");

        Deque<String> stack9 = new ArrayDeque<>();
        stack9.addLast("Z");
        stack9.addLast("W");
        stack9.addLast("M");
        stack9.addLast("S");
        stack9.addLast("C");
        stack9.addLast("D");
        stack9.addLast("J");

        List<Deque<String>> ship = new ArrayList<>();
        ship.add(stack1);
        ship.add(stack2);
        ship.add(stack3);
        ship.add(stack4);
        ship.add(stack5);
        ship.add(stack6);
        ship.add(stack7);
        ship.add(stack8);
        ship.add(stack9);
        return ship;
    }

    @NotNull
    private static String exo1(List<Deque<String>> ship, List<String> inputs) {

        inputs.stream()
              .map(Instruction::parseInstruction)
              .forEach(instruction -> {
                  //                  System.out.println("instruction = " + instruction);
                  final int quantity = instruction.quantity;
                  final int start = instruction.start;
                  final int end = instruction.end;

                  for (int i = 0; i < quantity; i++) {
                      final Deque<String> oldStack = ship.get(start - 1);
                      final String crate = oldStack.pollLast();
                      final Deque<String> newStack = ship.get(end - 1);
                      newStack.addLast(crate);
                  }
              });

        StringBuilder result = new StringBuilder();
        for (var stack : ship) {
            result.append(stack.pollLast());
        }

        return result.toString();
    }

    @NotNull
    private static String exo2(List<Deque<String>> ship, List<String> inputs) {

        inputs.stream()
              .map(Instruction::parseInstruction)
              .forEach(instruction -> {
                  final int quantity = instruction.quantity;
                  final int start = instruction.start;
                  final int end = instruction.end;

                  Deque<String> tempStack = new ArrayDeque<>();
                  final Deque<String> oldStack = ship.get(start - 1);

                  for (int i = 0; i < quantity; i++) {
                      tempStack.addFirst(oldStack.pollLast());
                  }
                  final Deque<String> newStack = ship.get(end - 1);
                  newStack.addAll(tempStack);
              });

        StringBuilder result = new StringBuilder();
        for (var stack : ship) {
            result.append(stack.pollLast());
        }

        return result.toString();
    }

    record Instruction(int quantity, int start, int end) {

        public static Instruction parseInstruction(String input) {
            final String regex = "move (\\d+) from (\\d+) to (\\d+)";

            final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            final Matcher matcher = pattern.matcher(input);
            int[] params = new int[3];
            while (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    params[i - 1] = Integer.parseInt(matcher.group(i));
                }
            }

            return new Instruction(params[0], params[1], params[2]);
        }
    }

}
