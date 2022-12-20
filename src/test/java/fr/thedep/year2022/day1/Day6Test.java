package fr.thedep.year2022.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    @Test
    void exo1() {
        assertEquals(7, Day6.exo1("mjqjpqmgbljsphdztnvjfqwrcgsmlb"));
        assertEquals(5, Day6.exo1("bvwbjplbgvbhsrlpgdmjqwftvncz"));
        assertEquals(6, Day6.exo1("nppdvjthqldpwncqszvftbrmjlhg"));
        assertEquals(10, Day6.exo1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
        assertEquals(11, Day6.exo1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }

    @Test
    void exo2() {
        assertEquals(19, Day6.exo2("mjqjpqmgbljsphdztnvjfqwrcgsmlb"));
        assertEquals(23, Day6.exo2("bvwbjplbgvbhsrlpgdmjqwftvncz"));
        assertEquals(23, Day6.exo2("nppdvjthqldpwncqszvftbrmjlhg"));
        assertEquals(29, Day6.exo2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"));
        assertEquals(26, Day6.exo2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"));
    }
}
