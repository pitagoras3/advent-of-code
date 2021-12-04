package day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day_1_1 {
    public int calculateFrequency() {
        return getListOfIntegersFromFile().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    List<Integer> getListOfIntegersFromFile() {
        List<Integer> listOfIntegers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(("src/main/java/day_1/day1.txt")));

            while (scanner.hasNext()) {
                listOfIntegers.add(scanner.nextInt());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listOfIntegers;
    }
}
