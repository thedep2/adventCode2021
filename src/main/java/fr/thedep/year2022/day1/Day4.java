package fr.thedep.year2022.day1;

import fr.thedep.utils.ReadFilesUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Day4 {

    private static final List<String> INPUTS = List.of(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8"
    );

    private Day4() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(INPUTS));
        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day4exo1.txt")));
        System.out.println(exo2(INPUTS));
        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day4exo1.txt")));
    }

    @NotNull
    private static String exo1(List<String> inputs) {
        final long count = inputs.stream()
                                 .map(Assignement::new)
                                 .filter(Assignement::isContained)
                                 .count();

        return "" + count;
    }

    @NotNull
    private static String exo2(List<String> inputs) {
        final long count = inputs.stream()
                                 .map(Assignement::new)
                                 .filter(Assignement::isOverlapse)
                                 .count();

        return "" + count;
    }

    record Assignement(int d1, int f1, int d2, int f2) {

        public Assignement(String input) {
            this(getBorneWithRegex(input, 1),
                 getBorneWithRegex(input, 2),
                 getBorneWithRegex(input, 3),
                 getBorneWithRegex(input, 4)
            );
        }

        private static int getBorneWithRegex(String input, int index) {
            final String regex = "\\d+";

            final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            final Matcher matcher = pattern.matcher(input);

            int r = 0;
            for (int i = 0; i < index; i++) {
                matcher.find();
                r = Integer.parseInt(matcher.group(0));
            }

            return r;
        }

        public boolean isOverlapse() {
            return f1 >= d2 && d1 <= f2;
        }

        public boolean isContained() {
            return (d1 >= d2 && f1 <= f2)
                   || (d1 <= d2 && f1 >= f2);
        }
    }

}
