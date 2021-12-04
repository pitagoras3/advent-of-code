package day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day_2_1 {

    public int calculateChecksum() {
        return getListOfStringsFromFile()
                .stream()
                .map(this::getListOfPairsForOneLine)
                .map(Map::values)
                .flatMap(list -> list.stream().distinct())
                .filter(x -> x > 1)
                .collect(Collectors.groupingBy(x -> x))
                .values()
                .stream()
                .map(List::size)
                .reduce((x, y) -> x * y)
                .get();
    }

    private Map<Character, Integer> getListOfPairsForOneLine(String line) {
        Map<Character, Integer> mapOfChars = new HashMap<>();

        for (char aChar : line.toCharArray()) {
            mapOfChars.put(aChar, mapOfChars.containsKey(aChar) ?
                    mapOfChars.get(aChar) + 1 : 1);
        }

        return mapOfChars;
    }

    List<String> getListOfStringsFromFile() {
        List<String> listOfStrings = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(("src/main/java/day_2/day2.txt")));

            while (scanner.hasNext()) {
                listOfStrings.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listOfStrings;
    }
}
