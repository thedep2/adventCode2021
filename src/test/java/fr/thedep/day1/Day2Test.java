package fr.thedep.day1;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    private static List<String> input1 = List.of("forward 5",
                                                 "down 5",
                                                 "forward 8",
                                                 "up 3",
                                                 "down 8",
                                                 "forward 2");

    @Test
    void testExo1() {
        assertThat(Day2.exo1(input1)).isEqualTo(150);
    }

    @Test
    void testExo2() {
        assertThat(Day2.exo2(input1)).isEqualTo(900);
    }
}
