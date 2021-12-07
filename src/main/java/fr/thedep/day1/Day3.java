package fr.thedep.day1;

import fr.thedep.utils.ReadFilesUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input1 = ReadFilesUtils.readFileInString("day3exo1.txt");
        System.out.println(exo1(input1));
    }

    protected static int exo1(List<String> input) {
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        final int sizeLine = input.get(0).length();

        int[] rateCount0 = new int[sizeLine];
        int[] rateCount1 = new int[sizeLine];

        for (String line : input) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '0') rateCount0[i]++;
                if (line.charAt(i) == '1') rateCount1[i]++;
            }
        }

        for (int i = 0; i < sizeLine; i++) {
            if (rateCount0[i] < rateCount1[i]) {
                gammaRate.append("1");
                epsilonRate.append("0");
            } else {
                gammaRate.append("0");
                epsilonRate.append("1");
            }
        }

        return Integer.valueOf(gammaRate.toString(), 2) * Integer.valueOf(epsilonRate.toString(), 2);
    }
}
