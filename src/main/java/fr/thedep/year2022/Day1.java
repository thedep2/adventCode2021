package fr.thedep.year2022;

import fr.thedep.utils.ReadFilesUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Day1 {

    private Day1() {}

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day1exo1.txt")));
        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day1exo2.txt")));

    }

    private static String exo1(List<String> input) {
        List<Integer> elf = getCalByElf(input);
        return "" + elf.stream().max(Integer::compareTo);
    }

    private static List<Integer> getCalByElf(List<String> input) {
        List<Integer> elf = new ArrayList<>();

        int somme = 0;
        for (var cal : input) {
            if (!"".equals(cal)) {
                somme += Integer.parseInt(cal);
            } else {
                elf.add(somme);
                somme = 0;
            }

        }
        return elf;
    }

    private static String exo2(List<String> input) {
        List<Integer> elf = getCalByElf(input);

        final int sum1 = elf.stream()
                            .sorted(Comparator.comparingInt(value -> (int) value).reversed())
                            .limit(3)
                            .mapToInt(value -> value)
                            .sum();

        return "" + sum1;
    }
}
