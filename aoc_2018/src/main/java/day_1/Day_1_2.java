package day_1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day_1_2 {

    private static final int INITIAL_FREQUENCY = 0;

    public int firstRepeatedFrequency() {
        List<Integer> integersFromFile = new Day_1_1().getListOfIntegersFromFile();
        Set<Integer> frequencies = new HashSet<>();

        boolean notFoundRepetition = true;
        int frequency = INITIAL_FREQUENCY;

        while (notFoundRepetition) {
            for (int i = 0; i < integersFromFile.size() && notFoundRepetition; i++) {
                frequency += integersFromFile.get(i);
                notFoundRepetition = frequencies.add(frequency);
            }
        }

        return frequency;
    }
}
