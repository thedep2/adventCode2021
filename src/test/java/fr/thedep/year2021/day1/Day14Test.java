package fr.thedep.year2021.day1;

import fr.thedep.utils.ReadFilesUtils;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day14Test {

    private static List<String> input1;

    static {
        try {
            input1 = ReadFilesUtils.readFileInString("test", "day14exo1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testExo1() {
        assertThat(Day14.exo(input1, 10)).isEqualTo(1588);
    }

    @Test
    void testExo2() {
        assertThat(Day14.exo(input1, 40)).isEqualTo(2188189693529l);
    }

    //    @Test
    //    void testExo2() {
    //        assertThat(Day2.exo2(input1)).isEqualTo(900);
    //    }
}
