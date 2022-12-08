package fr.thedep.year2021.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day3Test {

    private static List<String> input1 = List.of("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010");

    @Test
    void testExo1() {
        assertThat(Day3.exo1(input1)).isEqualTo(198);
    }

    //    @Test
    //    void testExo2() {
    //        assertThat(Day2.exo2(input1)).isEqualTo(900);
    //    }
}
