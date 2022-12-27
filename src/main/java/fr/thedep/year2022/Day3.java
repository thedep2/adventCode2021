package fr.thedep.year2022;

import fr.thedep.utils.ReadFilesUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Day3 {

    private static List<String> inputs = List.of(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
    );

    private Day3() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day3exo1.txt")));
        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day3exo1.txt")));
    }

    @NotNull
    private static String exo1(List<String> inputs) {
        final int sum = inputs.stream()
                              .map(Rucksack::new)
                              .mapToInt(Rucksack::getPriority)
                              .sum();

        return "" + sum;
    }

    @NotNull
    private static String exo2(List<String> inputs) {
        final int sum1 = IntStream.range(0, inputs.size())
                                  .filter(value -> value % 3 == 0)
                                  .mapToObj(value -> new Group(
                                          new Rucksack(inputs.get(value)),
                                          new Rucksack(inputs.get(value + 1)),
                                          new Rucksack(inputs.get(value + 2))
                                  ))
                                  .mapToInt(Group::getScore)
                                  .sum();
        return "" + sum1;
    }

    //    97
    //            122
    //            65
    //            90
    record Item(int item) {

        public int getValue() {
            if (97 <= item && item <= 122) {
                return item - 96;
            } else if (65 <= item && item <= 90) {
                return item - 38;
            } else
                throw new IllegalArgumentException();

        }
    }

    record Compartment(Item[] items) {

        public Compartment(@NotNull String s) {
            this(
                    s.chars()
                     .mapToObj(Item::new)
                     .toArray(Item[]::new)
            );
        }

        @Contract(value = "null -> false", pure = true)
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Compartment that = (Compartment) o;
            return Arrays.equals(items, that.items);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(items);
        }

        @NotNull
        @Contract(pure = true)
        @Override
        public String toString() {
            return "Compartment{" +
                   "items=" + Arrays.toString(items) +
                   '}';
        }
    }

    record Rucksack(Compartment c1, Compartment c2) {

        public Rucksack(@NotNull String s) {
            this(
                    new Compartment(s.substring(0, s.length() / 2)),
                    new Compartment(s.substring(s.length() / 2))
            );
        }

        @NotNull
        public Item getCommonItem() {
            final Optional<Item> commonItem
                    = Arrays.stream(c1.items())
                            .filter(Arrays.asList(c2.items())::contains)
                            .findFirst();

            return commonItem.get();
        }

        public int getPriority() {
            return getCommonItem().getValue();
        }

        public Set<Item> getDistinctItems() {
            final Set<Item> set1 = Arrays.stream(c1.items()).collect(Collectors.toSet());
            final Set<Item> set2 = Arrays.stream(c2.items()).collect(Collectors.toSet());
            set1.addAll(set2);
            return set1;
        }

    }

    record Group(Rucksack r1, Rucksack r2, Rucksack r3) {

        public Item getCommonItem() {
            final Set<Item> items1 = r1.getDistinctItems();
            final Set<Item> items2 = r2.getDistinctItems();
            final Set<Item> items3 = r3.getDistinctItems();

            final Optional<Item> first = items1.stream()
                                               .filter(items2::contains)
                                               .filter(items3::contains)
                                               .findFirst();
            return first.get();
        }

        public int getScore() {
            return getCommonItem().getValue();
        }
    }

}
