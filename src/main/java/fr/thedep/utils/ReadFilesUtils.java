package fr.thedep.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ReadFilesUtils {

    private ReadFilesUtils() {}

    public static List<String> readFileInString(String path, String file) throws FileNotFoundException {
        List<String> data = new ArrayList<>();
        File text = new File("src/" + path + "/resources/input/" + file);
        Scanner input = new Scanner(text);
        while (input.hasNextLine()) {
            data.add(input.nextLine());
        }
        return data;
    }

    public static List<Integer> readFileInInteger(String path, String file) throws FileNotFoundException {
        ArrayList<Integer> data = new ArrayList<>();
        File text = new File("src/" + path + "/resources/input/" + file);
        Scanner input = new Scanner(text);
        while (input.hasNextLine()) {
            data.add(Integer.parseInt(input.nextLine()));
        }
        return data;
    }

}
