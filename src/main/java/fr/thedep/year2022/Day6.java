package fr.thedep.year2022;

import fr.thedep.utils.ReadFilesUtils;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public final class Day6 {

    private Day6() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day6exo1.txt").get(0)));
        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day6exo1.txt").get(0)));
    }

    static int exo1(String input) {
        final StringBuffer buffer = new StringBuffer(4);
        final AtomicInteger count = new AtomicInteger(0);

        input.chars()
             .mapToObj(value -> (char) value)
             .map(String::valueOf)
             .flatMap(s -> {
                 count.incrementAndGet();
                 buffer.append(s);
                 if (buffer.length() < 4) return Stream.empty();
                 if (buffer.length() > 4) buffer.deleteCharAt(0);
                 return Stream.of(buffer.toString());
             })
             .filter(s -> s.length() == 4)
             .filter(s -> s.chars().distinct().count() == s.length())
             .findFirst();

        return count.get();
    }

    static int exo2(String input) {
        final int size = 14;
        final StringBuffer buffer = new StringBuffer(size);
        final AtomicInteger count = new AtomicInteger(0);

        input.chars()
             .mapToObj(value -> (char) value)
             .map(String::valueOf)
             .flatMap(s -> {
                 count.incrementAndGet();
                 buffer.append(s);
                 if (buffer.length() < size) return Stream.empty();
                 if (buffer.length() > size) buffer.deleteCharAt(0);
                 return Stream.of(buffer.toString());
             })
             .filter(s -> s.length() == size)
             .filter(s -> s.chars().distinct().count() == s.length())
             .findFirst();

        return count.get();
    }

}
