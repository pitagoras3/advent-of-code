package day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day_3_1 {

    public int getCommonFabricSquareInches() {
        return (int) getClaimsBoard().values()
                .stream()
                .filter(x -> x.size() > 1)
                .count();
    }

    Map<Position, List<Claim>> getClaimsBoard() {
        final Map<Position, List<Claim>> mapOfFabric = new HashMap<>();

        getListOfStringsFromFile()
                .stream()
                .map(this::convertLineFromFileToClaim)
                .forEach(claim -> {
                    for (int i = claim.getLeftPadding(); i < claim.getLeftPadding() + claim.getWidth(); i++) {
                        for (int j = claim.getTopPadding(); j < claim.getTopPadding() + claim.getHeight(); j++) {
                            Position position = new Position(i, j);
                            mapOfFabric.put(position, mapOfFabric.get(position) == null ?
                                    Collections.singletonList(claim) :
                                    new ArrayList<Claim>() {{
                                        addAll(mapOfFabric.get(position));
                                        add(claim);
                                    }});
                        }
                    }
                });

        return mapOfFabric;
    }

    private Claim convertLineFromFileToClaim(String lineFromFile) {
        // Example input: #1385 @ 612,761: 21x19

        String[] splittedLine = lineFromFile.split(" ");
        int id = Integer.valueOf(splittedLine[0].replace("#", ""));
        int leftPadding = Integer.valueOf(splittedLine[2].split(",")[0]);
        int topPadding = Integer.valueOf(splittedLine[2].split(",")[1].replace(":", ""));
        int width = Integer.valueOf(splittedLine[3].split("x")[0]);
        int height = Integer.valueOf(splittedLine[3].split("x")[1]);

        return new Claim(id, leftPadding, topPadding, width, height);
    }

    List<String> getListOfStringsFromFile() {
        List<String> listOfStrings = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(("src/main/java/day_3/day_3.txt")));

            while (scanner.hasNext()) {
                listOfStrings.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return listOfStrings;
    }
}
