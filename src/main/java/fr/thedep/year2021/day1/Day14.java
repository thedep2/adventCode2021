package fr.thedep.year2021.day1;

import fr.thedep.utils.ReadFilesUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Long.MAX_VALUE;
import static java.lang.Long.MIN_VALUE;

public class Day14 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input1 = ReadFilesUtils.readFileInString("main", "day14exo1.txt");
        System.out.println(exo(input1, 10));
        System.out.println(exo(input1, 40));
    }

    protected static long exo(List<String> input, long step) {

        final Map<String, String> dict = getDict(input);
        String poly = input.get(0);

        for (int i = 0; i < step; i++) {
            long debut = System.currentTimeMillis();
            System.out.println("Step: " + (i + 1));
            System.out.println("poly size: " + poly.length());

            final char lastChar = poly.charAt(poly.length() - 1);

            int bufferSize = 2013265929;

            List<String> listToStream = new ArrayList<>();

            for (int j = 0; j < poly.length() - 1; j = j + bufferSize - 1) {
                int endIndex = j + bufferSize;
                if (endIndex > poly.length() - 1) endIndex = poly.length();
                String substring = poly.substring(j, endIndex);
                listToStream.add(substring);
            }

            String nextPoly = listToStream.stream()
                                          .reduce("", (s, s2) -> s.concat(subPolyRec(dict, s2)));

            poly = nextPoly.concat(String.valueOf(lastChar));

            System.out.println("-> " + (System.currentTimeMillis() - debut) + " ms");
            System.out.println("dict size: " + dict.size());
            System.out.println();
        }

        return getAnswer(poly);
    }

    private static long getAnswer(String poly) {
        long max = MIN_VALUE;
        long min = MAX_VALUE;

        final Set<Character> unikChar = poly.chars().mapToObj(value -> (char) value).collect(Collectors.toSet());
        for (Character charToCount : unikChar) {
            long count = poly.chars().filter(ch -> ch == charToCount).count();
            System.out.println(charToCount + " : " + count);
            if (count < min) min = count;
            if (count > max) max = count;
        }
        return max - min;
    }

    private static String subPolyRec(Map<String, String> dict, String poly) {
        String s = dict.get(poly);
        if (s == null) {
            final String sub1 = poly.substring(0, (poly.length() / 2) + 1);
            final String result1 = subPolyRec(dict, sub1);

            final String sub2 = poly.substring(poly.length() / 2);
            final String result2 = subPolyRec(dict, sub2);

            s = result1 + result2;

            if (poly.length() < 393217)
                dict.put(poly, s);
        }
        return s;
    }

    private static Map<String, String> getDict(List<String> input) {
        final Map<String, String> dict = new HashMap<>();

        String regex = "([A-Z][A-Z]) -> ([A-Z])";
        Pattern pattern = Pattern.compile(regex);

        input.forEach(s -> {
            Matcher matcher = pattern.matcher(s);
            while (matcher.find())
                dict.put(matcher.group(1), insert(matcher));
        });

        return dict;
    }

    private static String insert(Matcher matcher) {
        return matcher.group(1).charAt(0) + matcher.group(2);
    }

}
