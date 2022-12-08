package fr.thedep.year2021.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.thedep.year2021.day1.Day1.calc;
import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    private static List<Integer> INPUT = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

    @Test
    void testGetResult() {
        assertThat(calc(INPUT, 1)).isEqualTo(7);
    }

    @Test
    void testPart2() {
        assertThat(calc(INPUT, 3)).isEqualTo(5);
    }
}
