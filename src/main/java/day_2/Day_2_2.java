package day_2;

import java.util.List;

public class Day_2_2 {

    public String getCommon() {
        List<String> listOfStrings = new Day_2_1().getListOfStringsFromFile();

        for (int i = 0; i < listOfStrings.size(); i++) {
            for (int j = i + 1; j < listOfStrings.size(); j++) {
                if (differentCharsBetweenStrings(listOfStrings.get(i), listOfStrings.get(j)) == 1) {
                    return getCommonPartFromBothStrings(listOfStrings.get(i), listOfStrings.get(j));
                }
            }
        }

        throw new RuntimeException("Common part not found :(");
    }

    private int differentCharsBetweenStrings(String string1, String string2) {
        int difference = 0;

        char[] chars1 = string1.toCharArray();
        char[] chars2 = string2.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                difference++;
            }
        }

        return difference;
    }

    private String getCommonPartFromBothStrings(String string1, String string2) {
        String common = "";

        char[] chars1 = string1.toCharArray();
        char[] chars2 = string2.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] == chars2[i]) {
                common += chars1[i];
            }
        }

        return common;
    }
}
