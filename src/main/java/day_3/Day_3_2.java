package day_3;

import java.util.List;
import java.util.stream.Collectors;

public class Day_3_2 {

    public int getIdOfNotOverleapedClaim() {
        Day_3_1 day_3_1 = new Day_3_1();

        List<Integer> claimsThatAreStacked = day_3_1.getClaimsBoard()
                .values()
                .stream()
                .filter(x -> x.size() != 1)
                .flatMap(List::stream)
                .map(Claim::getId)
                .collect(Collectors.toList());

        return day_3_1.getClaimsBoard()
                .values()
                .stream()
                .flatMap(List::stream)
                .map(Claim::getId)
                .distinct()
                .filter(x -> !claimsThatAreStacked.contains(x))
                .findFirst()
                .get();
    }
}
