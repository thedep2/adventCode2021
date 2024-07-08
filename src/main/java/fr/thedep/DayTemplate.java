package fr.thedep;

import fr.thedep.utils.ReadFilesUtils;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.List;

public final class DayTemplate {

    private static final String INPUTS = """
            """;

    private DayTemplate() {}

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exo1(INPUTS.lines().toList()));
        System.out.println(exo1(ReadFilesUtils.readFileInString("main", "year2022day3exo1.txt")));
        System.out.println(exo2(INPUTS.lines().toList()));
        System.out.println(exo2(ReadFilesUtils.readFileInString("main", "year2022day3exo1.txt")));
    }

    @NotNull
    private static String exo1(List<String> inputs) {
        return "";
    }

    @NotNull
    private static String exo2(List<String> inputs) {
        return "";
    }

}
